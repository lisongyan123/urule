package com.bstek.urule.console.cache;

import com.bstek.urule.console.repository.model.ResourcePackage;
import java.util.List;

public interface ResourcePackageCache {
    String BEAN_ID = "urule.resourcePackageCache";

    ResourcePackage loadResourcePackage(String var1);

    void resetResourcePackageTag(String var1);

    void storeResourcePackages(List<ResourcePackage> var1);
}
