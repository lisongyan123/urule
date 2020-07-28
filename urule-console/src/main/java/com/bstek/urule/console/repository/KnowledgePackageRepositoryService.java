//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.repository;

import com.bstek.urule.console.repository.model.ResourcePackage;
import com.bstek.urule.console.repository.model.VersionFile;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.runtime.KnowledgePackage;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface KnowledgePackageRepositoryService {
    String BEAN_ID = "urule.knowledgePackageRepositoryService";
    String PACKAGE_FILE_PREFIX = "_package_file_prefix_";

    List<VersionFile> getKnowledgePackges(String var1);

    Map<String, Object> loadKnowledgePackageFiles(String var1, String var2);

    VersionFile getActivedKnowledgePackge(String var1);

    byte[] getKnowledgePackgeBytes(String var1, String var2);

    KnowledgePackage getKnowledgePackge(String var1, String var2);

    List<VariableCategory> getKnowledgePackgeLib(String var1, String var2);

    InputStream getKnowledgePackgeData(String var1, String var2);

    void removeKnowledgePackge(String var1, String var2);

    void removeDeployKnowledgePackge(String var1);

    void activeKnowledgePackage(String var1, String var2);

    void knowledgePackageStateChange(String var1, boolean var2);

    void projectRename(String var1, String var2);

    void resetProjectResourcePackagesTag(String var1);

    String getProjectResourcePackagesTag(String var1);

    List<ResourcePackage> loadProjectResourcePackages(String var1);

    List<ResourcePackage> loadResourcePackagesByFile(String var1);

    void saveKnowledgePackage(String var1, KnowledgePackage var2, String var3, String var4, boolean var5);
}
