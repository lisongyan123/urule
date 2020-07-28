package com.bstek.urule.console.repository.refactor;

import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.Repository;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.TemplateRepositoryService;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.repository.model.Type;
import com.bstek.urule.console.repository.refactor.field.ContentRefactor;
import com.bstek.urule.console.repository.refactor.file.FileRefactor;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.console.servlet.RequestHolder;
import org.apache.commons.io.IOUtils;
import org.springframework.context.ApplicationContext;

public class RefactorServiceImpl implements RefactorService {
    private RepositoryService a;
    private TemplateRepositoryService b;
    private Collection<FileRefactor> c;
    private Collection<ContentRefactor> d;

    public RefactorServiceImpl(RepositoryService var1, ApplicationContext var2) {
        this.a = var1;
        this.b = (TemplateRepositoryService)var2.getBean("urule.templateRepositoryService");
        this.c = var2.getBeansOfType(FileRefactor.class).values();
        this.d = var2.getBeansOfType(ContentRefactor.class).values();
    }

    public void refactorFile(String var1, String var2, Repository var3) throws Exception {
        String var4 = var1.substring(1, var1.length()) + "/" + "___res__package__file__";
        Principal var5 = EnvironmentUtils.getLoginPrincipal(new RequestContext(RequestHolder.getRequest(), RequestHolder.getResponse()));
        RepositoryFile var6 = var3.getRootFile();
        List var7 = var6.getChildren();
        if (var1.indexOf("___temp_mount_project_node_for_import__") > -1) {
            var1 = var1.substring("___temp_mount_project_node_for_import__".length() + 1, var1.length());
        }

        if (var2.indexOf("___temp_mount_project_node_for_import__") > -1) {
            var2 = var2.substring("___temp_mount_project_node_for_import__".length() + 1, var2.length());
        }

        this.a(var1, var2, var5, var7);
        if (this.a.fileExist(var4)) {
            InputStream var8 = this.a.readFile(var4);
            String var9 = IOUtils.toString(var8, "utf-8");
            var8.close();
            Iterator var10 = this.c.iterator();

            while(var10.hasNext()) {
                FileRefactor var11 = (FileRefactor)var10.next();
                if (var11.support(var4)) {
                    String var12 = var11.doRefactor(var1, var2, var9);
                    if (var12 != null) {
                        this.a.saveFile(var4, var12, false, (String)null, var5);
                    }
                    break;
                }
            }
        }

    }

    public void refactorFile(String var1, String var2) throws Exception {
        String var3 = this.a.getProject(var1);
        Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(RequestHolder.getRequest(), RequestHolder.getResponse()));
        FileType[] var5 = new FileType[]{FileType.DecisionTable, FileType.Crosstab, FileType.DecisionTree, FileType.RuleFlow, FileType.Ruleset, FileType.UL, FileType.Scorecard, FileType.ComplexScorecard, FileType.ScriptDecisionTable, FileType.ConditionTemplate, FileType.ActionTemplate};
        Repository var6 = this.a.loadRepository(var3, var4, false, var5, (String)null);
        RepositoryFile var7 = var6.getRootFile();
        List var8 = var7.getChildren();
        this.a(var1, var2, var4, var8);
        String var9 = var3 + "/" + "___res__package__file__";
        if (this.a.fileExist(var9)) {
            InputStream var10 = this.a.readFile(var9);
            String var11 = IOUtils.toString(var10, "utf-8");
            var10.close();
            Iterator var12 = this.c.iterator();

            while(var12.hasNext()) {
                FileRefactor var13 = (FileRefactor)var12.next();
                if (var13.support(var9)) {
                    String var14 = var13.doRefactor(var1, var2, var11);
                    if (var14 != null) {
                        this.a.saveFile(var9, var14, false, (String)null, var4);
                    }
                    break;
                }
            }
        }

    }

    private void a(String var1, String var2, Principal var3, List<RepositoryFile> var4) throws Exception, IOException {
        if (var4 != null && var4.size() != 0) {
            Iterator var5 = var4.iterator();

            while(true) {
                while(var5.hasNext()) {
                    RepositoryFile var6 = (RepositoryFile)var5.next();
                    Type var7 = var6.getType();
                    if (!Type.rule.equals(var7) && !Type.ul.equals(var7) && !Type.decisionTable.equals(var7) && !Type.crosstab.equals(var7) && !Type.decisionTree.equals(var7) && !Type.flow.equals(var7) && !Type.scorecard.equals(var7) && !Type.complexscorecard.equals(var7) && !var7.equals(Type.conditionTemplate) && !var7.equals(Type.actionTemplate)) {
                        this.a(var1, var2, var3, var6.getChildren());
                    } else {
                        String var8 = var6.getFullPath();
                        InputStream var9 = this.a.readFile(var8);
                        String var10 = IOUtils.toString(var9, "utf-8");
                        var9.close();
                        Iterator var11 = this.c.iterator();

                        while(var11.hasNext()) {
                            FileRefactor var12 = (FileRefactor)var11.next();
                            if (var12.support(var8)) {
                                String var13 = var12.doRefactor(var1, var2, var10);
                                if (var13 != null) {
                                    this.a.saveFile(var8, var13, false, (String)null, var3);
                                }
                                break;
                            }
                        }
                    }
                }

                return;
            }
        }
    }

    public void refactorItem(String var1, Item var2) throws Exception {
        String var3 = this.a.getProject(var1);
        Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(RequestHolder.getRequest(), RequestHolder.getResponse()));
        FileType[] var5 = new FileType[]{FileType.DecisionTable, FileType.Crosstab, FileType.DecisionTree, FileType.RuleFlow, FileType.Ruleset, FileType.UL, FileType.Scorecard, FileType.ComplexScorecard, FileType.ScriptDecisionTable, FileType.ConditionTemplate, FileType.ActionTemplate};
        Repository var6 = this.a.loadRepository(var3, var4, false, var5, (String)null);
        RepositoryFile var7 = var6.getRootFile();
        List var8 = var7.getChildren();
        this.a(var1, var2, var4, var8);
        List var9 = this.b.loadTemplates(var3);
        Iterator var10 = var9.iterator();

        while(var10.hasNext()) {
            RepositoryFile var11 = (RepositoryFile)var10.next();
            this.a(var1, var2, var4, var11.getChildren());
        }

    }

    private void a(String var1, Item var2, Principal var3, List<RepositoryFile> var4) throws Exception, IOException {
        if (var4 != null && var4.size() != 0) {
            Iterator var5 = var4.iterator();

            while(true) {
                while(var5.hasNext()) {
                    RepositoryFile var6 = (RepositoryFile)var5.next();
                    Type var7 = var6.getType();
                    if (!var7.equals(Type.decisionTable) && !var7.equals(Type.crosstab) && !var7.equals(Type.decisionTree) && !var7.equals(Type.rule) && !var7.equals(Type.flow) && !var7.equals(Type.scorecard) && !var7.equals(Type.complexscorecard) && !var7.equals(Type.ul) && !var7.equals(Type.conditionTemplate) && !var7.equals(Type.actionTemplate)) {
                        this.a(var1, var2, var3, var6.getChildren());
                    } else {
                        String var8 = var6.getFullPath();
                        InputStream var9 = this.a.readFile(var8);
                        String var10 = IOUtils.toString(var9, "utf-8");
                        var9.close();
                        Iterator var11 = this.d.iterator();

                        while(var11.hasNext()) {
                            ContentRefactor var12 = (ContentRefactor)var11.next();
                            if (var12.support(var8)) {
                                String var13 = var12.doRefactor(var1, var10, var2);
                                if (var13 != null) {
                                    if (var8.indexOf("__rules_templates__") > 0) {
                                        this.b.saveTemplateFile(var8, var13);
                                    } else {
                                        this.a.saveFile(var8, var13, false, (String)null, var3);
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

                return;
            }
        }
    }
}
