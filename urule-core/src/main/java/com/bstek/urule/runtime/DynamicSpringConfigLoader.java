//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

public interface DynamicSpringConfigLoader {
    String BEAN_ID = "urule.dynamicSpringConfigLoader";

    void loadDynamicJars(String var1) throws Exception;

    String buildDynamicJarsStoreDirectPath();

    String getDynamicJarsStoreDirectPath();

    String getDynamicJarsStoreDirectDirName();
}
