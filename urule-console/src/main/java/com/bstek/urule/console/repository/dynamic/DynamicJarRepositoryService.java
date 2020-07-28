package com.bstek.urule.console.repository.dynamic;

import java.io.InputStream;
import java.util.List;

public interface DynamicJarRepositoryService {
    List<DynamicFile> loadFiles() throws Exception;

    void removeFile(String var1) throws Exception;

    DynamicFile getFile(String var1) throws Exception;

    void generateJars(String var1, String var2) throws Exception;

    void removeJar(String var1, String var2) throws Exception;

    InputStream loadJar(String var1, String var2) throws Exception;

    void saveFile(DynamicFile var1) throws Exception;

    void addJar(String var1, String var2, InputStream var3) throws Exception;
}

