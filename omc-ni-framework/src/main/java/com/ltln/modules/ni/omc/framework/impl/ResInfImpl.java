package com.ltln.modules.ni.omc.framework.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ltln.modules.ni.omc.core.IResInf;
import com.ltln.modules.ni.omc.core.vo.CmCard;
import com.ltln.modules.ni.omc.core.vo.CmEth;
import com.ltln.modules.ni.omc.core.vo.CmEthSPInfo;
import com.ltln.modules.ni.omc.core.vo.CmEthSerciePWInfo;
import com.ltln.modules.ni.omc.core.vo.CmHolder;
import com.ltln.modules.ni.omc.core.vo.CmLabelSwitch;
import com.ltln.modules.ni.omc.core.vo.CmNE;
import com.ltln.modules.ni.omc.core.vo.CmOMC;
import com.ltln.modules.ni.omc.core.vo.CmPW;
import com.ltln.modules.ni.omc.core.vo.CmPWPW;
import com.ltln.modules.ni.omc.core.vo.CmPWTunnel;
import com.ltln.modules.ni.omc.core.vo.CmPort;
import com.ltln.modules.ni.omc.core.vo.CmPortBinding;
import com.ltln.modules.ni.omc.core.vo.CmSubNet;
import com.ltln.modules.ni.omc.core.vo.CmSubNetNe;
import com.ltln.modules.ni.omc.core.vo.CmTDM;
import com.ltln.modules.ni.omc.core.vo.CmTopoLink;
import com.ltln.modules.ni.omc.core.vo.CmTunnel;
import com.ltln.modules.ni.omc.core.vo.CmTunnelPGBackupInfo;
import com.ltln.modules.ni.omc.core.vo.CmTunnelPGInfo;
import com.ltln.modules.ni.omc.framework.locator.ILocator;
import com.ltln.modules.ni.omc.framework.start.NIUtil;
import com.ltln.modules.ni.omc.framework.util.SsdxOmcNiDataUtil;

@Component("resInfImpl")
public class ResInfImpl implements IResInf {

	@Autowired
	ILocator locator;
	
	@Override
	public CmOMC getCmOMC() {
	    CmOMC cmOmc = new CmOMC();
	    cmOmc.setNativeName(NIUtil.getEmsNativeEMSName());
	    cmOmc.setCommuAddress(NIUtil.getLocalHost());
	    cmOmc.setOmcVersion(NIUtil.getNiEmsVersion());
	    cmOmc.setInterfaceVersion(NIUtil.getEmsNBIVersion());
	    cmOmc.setEquipmemtDomain(NIUtil.getEquipmemtDomain());
	    cmOmc.setVendor(NIUtil.getNiFactoryCode());
	    cmOmc.setRmUID(SsdxOmcNiDataUtil.getOMCUID());
		return cmOmc;
	}

	@Override
	public List<CmNE> getCmNE() {
		List<CmNE> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmNE> oneList = resInf.getCmNE();
			if(oneList!=null&&!oneList.isEmpty())
				result.addAll(oneList);
		}
		return result;
	}

	@Override
	public List<CmHolder> getCmHolder() {
		List<CmHolder> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmHolder> oneList = resInf.getCmHolder();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmCard> getCmCard() {
		List<CmCard> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmCard> oneList = resInf.getCmCard();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmPort> getCmPort() {
		List<CmPort> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmPort> oneList = resInf.getCmPort();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmPortBinding> getCmPortBinding() {
		List<CmPortBinding> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmPortBinding> oneList = resInf.getCmPortBinding();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmTunnel> getCmTunnel() {
		List<CmTunnel> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmTunnel> oneList = resInf.getCmTunnel();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmLabelSwitch> getCmLabelSwitch() {
		List<CmLabelSwitch> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmLabelSwitch> oneList = resInf.getCmLabelSwitch();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmTunnelPGInfo> getCmTunnelPGInfo() {
		List<CmTunnelPGInfo> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmTunnelPGInfo> oneList = resInf.getCmTunnelPGInfo();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmTunnelPGBackupInfo> getCmTunnelPGBackupInfo() {
		List<CmTunnelPGBackupInfo> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmTunnelPGBackupInfo> oneList = resInf.getCmTunnelPGBackupInfo();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmPW> getCmPW() {
		List<CmPW> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmPW> oneList = resInf.getCmPW();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmPWPW> getCmPWPW() {
		List<CmPWPW> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmPWPW> oneList = resInf.getCmPWPW();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmPWTunnel> getCmPWTunnel() {
		List<CmPWTunnel> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmPWTunnel> oneList = resInf.getCmPWTunnel();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmEth> getCmEth() {
		List<CmEth> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmEth> oneList = resInf.getCmEth();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmEthSPInfo> getCmEthSPInfo() {
		List<CmEthSPInfo> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmEthSPInfo> oneList = resInf.getCmEthSPInfo();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmEthSerciePWInfo> getCmEthSerciePWInfo() {
		List<CmEthSerciePWInfo> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmEthSerciePWInfo> oneList = resInf.getCmEthSerciePWInfo();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmTDM> getCmTDM() {
		List<CmTDM> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmTDM> oneList = resInf.getCmTDM();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmTopoLink> getCmTopoLink() {
		List<CmTopoLink> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmTopoLink> oneList = resInf.getCmTopoLink();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmSubNet> getCmSubNet() {
		List<CmSubNet> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmSubNet> oneList = resInf.getCmSubNet();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

	@Override
	public List<CmSubNetNe> getCmSubNetNe() {
		List<CmSubNetNe> result = new ArrayList<>();
		List<IResInf> resInfs = locator.getInstances(IResInf.class);
		for(IResInf resInf : resInfs){
			List<CmSubNetNe> oneList = resInf.getCmSubNetNe();
			if(oneList != null && !oneList.isEmpty()){
				result.addAll(oneList);
			}
		}
		return result;
	}

}
