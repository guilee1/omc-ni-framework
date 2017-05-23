package com.ltln.modules.ni.omc.framework.start;

//import com.ltln.modules.ptn.ni.omc.NIUtil;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.remoting.rmi.RmiServiceExporter;

import com.ltln.modules.ni.omc.core.annotation.Facade;
import com.ltln.modules.ni.omc.framework.aware.SelfBeanFactoryAware;
import com.ltln.modules.ni.omc.framework.cq.CqSocketClient;
import com.ltln.modules.ni.omc.framework.locator.ILocator;
import com.ltln.modules.ni.omc.framework.log.Logger;
import com.ltln.modules.ni.omc.framework.msg.IObserverRegister;
import com.ltln.modules.ni.omc.framework.scanner.ClassScaner;
import com.ltln.modules.ni.omc.framework.util.Constants;
import com.ltln.netmgt.common.util.CommonUtil;
import com.ltln.netmgt.server.faultd.AlarmAPI;
import com.ltln.netmgt.server.faultd.AlarmObserver;
import com.ltln.netmgt.server.util.NmsUtil;
import com.ltln.netmgt.server.util.ServiceDaemon;

/**
 * OMC-NI server class. it will publish rmi interfaces and lunch a JMS broker to
 * store messages, after all that done,it register alarm ,event .etc observer
 * into server platform for receiving messages and sending them out.
 *
 * @author Administrator
 */
public class OMCMain implements ServiceDaemon {

    static final String SPRING_XML_FILE = "classpath:omc_applicationContext.xml";
    boolean isInitialized;
    AlarmAPI alarmAPI;

    void registerModuleObserver(boolean start) {
        ILocator locator = SelfBeanFactoryAware.getBean("serviceLocator");
        List<IObserverRegister> modules = locator.getInstances(IObserverRegister.class);
        for (IObserverRegister item : modules) {
            item.registerObserver(start);
        }
    }

    void exportRMI() {
    	new FileSystemXmlApplicationContext(SPRING_XML_FILE);
        Set<Class<?>> clazzes = new ClassScaner().scan(Constants.INF_PKG_NAME, Facade.class);
        Logger.info(String.format("#ModuleCaller# ClassScanner scan %s,result is %d", Constants.INF_PKG_NAME, clazzes.size()));
        publishRemoteInterface(clazzes);
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void shutDown() {
        registerModuleObserver(false);
        destroy();
    }

    public void destroy() {
        Set<Class<?>> clazzes = new ClassScaner().scan(Constants.INF_PKG_NAME, Facade.class);
        for (Class<?> clazz : clazzes) {
            try {
                RmiServiceExporter exporter = (RmiServiceExporter) SelfBeanFactoryAware.getBean(clazz.getSimpleName().toLowerCase());
                if (exporter != null) {
                    exporter.destroy();
                }
                Logger.info("Success to destroy mgr:" + clazz.getName());
            } catch (RemoteException e) {
                Logger.error(String.format("destroy mgr fail: %s", clazz.getName()), e);
            }
        }
    }

    @Override
    public void setServiceName(String name) {
    }

    @Override
    public String getServiceName() {
        return CommonUtil.getString(OMCMain.class, "OMC Northbound Interface Module");
    }

    public static void main(String[] args) throws Exception {
        new OMCMain().exportRMI();
    }

    @Override
    public void onStart() {
    	exportRMI();
        initAlarmOberserver();
        cqNIStart();
        registerModuleObserver(true);
        isInitialized = true;
    }

    private void initAlarmOberserver() {
        while (alarmAPI == null) {
            alarmAPI = ((AlarmAPI) NmsUtil.getAPI("AlarmAPI"));
            if (alarmAPI != null) {
                break;
            }
            try {
                Thread.sleep(300L);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            Iterator<AlarmObserver> it = ServiceLoader.load(AlarmObserver.class).iterator();
            while (it.hasNext()) {
                AlarmObserver alarmObersever = (AlarmObserver) it.next();
                alarmAPI.registerForAlarms(alarmObersever);
            }
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    private void cqNIStart() {
        NIUtil.init();
        if (NIUtil.getIsCqNI().equalsIgnoreCase("true")) {
            (new CqSocketClient(NIUtil.getNICqNi_ip(), Integer.parseInt(NIUtil.getNICqNi_port()))).start();
        }
    }

    void publishRemoteInterface(Set<Class<?>> clazzes) {
        RmiServiceExporter exporter = new RmiServiceExporter();
        exporter.setRegistryPort(Integer.parseInt(Constants.RMI_PORT));
        int count = 0;
        for (Class<?> clazz : clazzes) {
            try {
                Logger.info("Aware mgr:" + clazz);
                exporter.setServiceName(clazz.getAnnotation(Facade.class).serviceName());
                exporter.setService(SelfBeanFactoryAware.getBean(clazz.getAnnotation(Facade.class).serviceBean()));
                exporter.setServiceInterface(clazz);
                exporter.afterPropertiesSet();
                ((DefaultListableBeanFactory) SelfBeanFactoryAware.getBeanFactory()).registerSingleton(clazz.getSimpleName().toLowerCase(), exporter);
                Logger.info("Success to export mgr:" + clazz.getName() + ";serviceName:" + clazz.getAnnotation(Facade.class).serviceName());
                count++;
            } catch (RemoteException e) {
                Logger.error("register rmi service failure", e);
                throw new RuntimeException(e);
            }
        }
        Logger.info("Auto exporter mgr count:" + count);
    }

}
