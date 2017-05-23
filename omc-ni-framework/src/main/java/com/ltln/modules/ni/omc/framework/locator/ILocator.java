package com.ltln.modules.ni.omc.framework.locator;

import java.util.List;

public interface ILocator {

    public <T> List<T> getInstances(Class<T> infClazz);

    public <T> T getInstance(Class<T> infClazz, String moduleName);
}
