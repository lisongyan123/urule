package com.bstek.urule.console.repository;

import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.model.RepositoryFile;
import java.util.List;

public interface TemplateRepositoryService {
    String BEAN_ID = "urule.templateRepositoryService";

    String createTemplateCategory(Principal var1, String var2, String var3) throws Exception;

    String createTemplateFile(Principal var1, String var2, String var3, String var4) throws Exception;

    String saveTemplateFile(String var1, String var2) throws Exception;

    List<RepositoryFile> loadTemplates(String var1) throws Exception;

    void removeTemplateCategory(String var1, String var2) throws Exception;

    void removeTemplateFile(String var1) throws Exception;
}