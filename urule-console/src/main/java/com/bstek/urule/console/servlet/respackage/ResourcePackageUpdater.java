//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.respackage;

import com.bstek.urule.Utils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.KnowledgePackageRepositoryService;
import com.bstek.urule.console.repository.KnowledgePackageRepositoryServiceImpl;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.model.ResourceItem;
import com.bstek.urule.console.repository.model.ResourcePackage;
import com.bstek.urule.console.repository.model.ServiceConfig;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.monitor.MonitorObject;
import com.bstek.urule.runtime.monitor.MonitorObjectField;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ResourcePackageUpdater {
    private RepositoryService a;
    private KnowledgePackageRepositoryService b;

    public ResourcePackageUpdater(RepositoryService var1, KnowledgePackageRepositoryService var2) {
        this.a = var1;
        this.b = var2;
    }

    public void doUpdate(HttpServletRequest var1, Principal var2) throws Exception {
        String var3 = var1.getParameter("op");
        if (var3 == null) {
            throw new RuleException("Unknow operation!");
        } else {
            String var4 = var1.getParameter("project");
            var4 = Utils.decodeURL(var4);
            ResourcePackage var5 = null;
            String var6 = var1.getParameter("xml");
            if (!var3.equals("delete")) {
                var6 = Utils.decodeContent(var6);
                if (var6 != null) {
                    KnowledgePackageRepositoryServiceImpl var7 = (KnowledgePackageRepositoryServiceImpl)this.b;
                    var5 = (ResourcePackage)var7.parseResourcePackages(var4, var6).get(0);
                }
            }

            if (var3.equals("insert")) {
                this.b(var5, var4, var2);
            } else if (var3.equals("update")) {
                this.a(var5, var4, var2);
            } else if (var3.equals("delete")) {
                String var8 = var1.getParameter("packageId");
                if (var8 == null) {
                    throw new RuleException("删除操作时知识包ID不能为空！");
                }

                this.a(var8, var4, var2);
            }

        }
    }

    private void a(String var1, String var2, Principal var3) throws Exception {
        List var4 = this.b.loadProjectResourcePackages(var2);
        ResourcePackage var5 = null;
        Iterator var6 = var4.iterator();

        while(var6.hasNext()) {
            ResourcePackage var7 = (ResourcePackage)var6.next();
            if (var7.getId().equals(var1)) {
                var5 = var7;
                break;
            }
        }

        if (var5 == null) {
            throw new RuleException("知识包[" + var1 + "]不存在!");
        } else {
            int var9 = var4.indexOf(var5);
            var4.remove(var9);
            String var10 = this.a(var4);
            String var8 = var2 + "/" + "___res__package__file__";
            this.a.saveFile(var8, var10, false, (String)null, var3);
        }
    }

    private void a(ResourcePackage var1, String var2, Principal var3) throws Exception {
        List var4 = this.b.loadProjectResourcePackages(var2);
        ResourcePackage var5 = null;
        Iterator var6 = var4.iterator();

        while(var6.hasNext()) {
            ResourcePackage var7 = (ResourcePackage)var6.next();
            if (var7.getId().equals(var1.getId())) {
                var5 = var7;
                break;
            }
        }

        if (var5 == null) {
            throw new RuleException("知识包[" + var1.getId() + "]不存在!");
        } else {
            int var9 = var4.indexOf(var5);
            var4.remove(var9);
            var4.add(var9, var1);
            String var10 = this.a(var4);
            String var8 = var2 + "/" + "___res__package__file__";
            this.a.saveFile(var8, var10, false, (String)null, var3);
        }
    }

    private void b(ResourcePackage var1, String var2, Principal var3) throws Exception {
        List var4 = this.b.loadProjectResourcePackages(var2);
        boolean var5 = false;
        Iterator var6 = var4.iterator();

        while(var6.hasNext()) {
            ResourcePackage var7 = (ResourcePackage)var6.next();
            if (var7.getId().equals(var1.getId())) {
                var5 = true;
                break;
            }
        }

        if (var5) {
            throw new RuleException("知识包ID[" + var1.getId() + "]已存在!");
        } else {
            var4.add(var1);
            String var8 = this.a(var4);
            String var9 = var2 + "/" + "___res__package__file__";
            this.a.saveFile(var9, var8, false, (String)null, var3);
        }
    }

    private String a(List<ResourcePackage> var1) {
        StringBuilder var2 = new StringBuilder();
        var2.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
        var2.append("<res-packages>");
        SimpleDateFormat var3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for(Iterator var4 = var1.iterator(); var4.hasNext(); var2.append("</res-package>")) {
            ResourcePackage var5 = (ResourcePackage)var4.next();
            Date var6 = var5.getCreateDate();
            var2.append("<res-package id=\"" + var5.getId() + "\" name=\"" + var5.getName() + "\" check=\"" + var5.isCheck() + "\" create_date=\"" + var3.format(var6) + "\"");
            if (var5.isMonitor()) {
                var2.append(" monitor=\"true\" show-log=\"" + var5.isShowLog() + "\" show-fired-flow-node-list=\"" + var5.isShowFiredFlowNodeList() + "\"");
                var2.append(" show-matched-rule-list=\"" + var5.isShowMatchedRuleList() + "\" show-not-match-rule-list=\"" + var5.isShowNotMatchRuleList() + "\"");
            }

            var2.append(">");
            if (var5.getQuickTestData() != null) {
                var2.append("<quick-test-data>");
                var2.append("<![CDATA[");
                var2.append(var5.getQuickTestData());
                var2.append("]]>");
                var2.append("</quick-test-data>");
            }

            Iterator var7;
            if (var5.isMonitor()) {
                MonitorObject var8;
                Iterator var9;
                MonitorObjectField var10;
                if (var5.getInputData() != null) {
                    for(var7 = var5.getInputData().iterator(); var7.hasNext(); var2.append("</input>")) {
                        var8 = (MonitorObject)var7.next();
                        var2.append("<input name=\"" + var8.getName() + "\">");
                        if (var8.getFields() != null) {
                            var9 = var8.getFields().iterator();

                            while(var9.hasNext()) {
                                var10 = (MonitorObjectField)var9.next();
                                var2.append("<field name=\"" + var10.getName() + "\" label=\"" + var10.getLabel() + "\"/>");
                            }
                        }
                    }
                }

                if (var5.getOutputData() != null) {
                    for(var7 = var5.getOutputData().iterator(); var7.hasNext(); var2.append("</output>")) {
                        var8 = (MonitorObject)var7.next();
                        var2.append("<output name=\"" + var8.getName() + "\">");
                        if (var8.getFields() != null) {
                            var9 = var8.getFields().iterator();

                            while(var9.hasNext()) {
                                var10 = (MonitorObjectField)var9.next();
                                var2.append("<field name=\"" + var10.getName() + "\" label=\"" + var10.getLabel() + "\"/>");
                            }
                        }
                    }
                }
            }

            if (var5.getService() != null) {
                ServiceConfig var12 = var5.getService();
                var2.append("<service security=\"" + var12.isSecurity() + "\"");
                if (var12.isSecurity()) {
                    var2.append(" username=\"" + var12.getUsername() + "\" password=\"" + var12.getPassword() + "\"");
                }

                var2.append(">");
                MonitorObjectField var11;
                Iterator var13;
                MonitorObject var15;
                Iterator var16;
                if (var12.getInputData() != null) {
                    for(var13 = var12.getInputData().iterator(); var13.hasNext(); var2.append("</input>")) {
                        var15 = (MonitorObject)var13.next();
                        var2.append("<input name=\"" + var15.getName() + "\">");
                        if (var15.getFields() != null) {
                            var16 = var15.getFields().iterator();

                            while(var16.hasNext()) {
                                var11 = (MonitorObjectField)var16.next();
                                var2.append("<field name=\"" + var11.getName() + "\" label=\"" + var11.getLabel() + "\"/>");
                            }
                        }
                    }
                }

                if (var12.getOutputData() != null) {
                    for(var13 = var12.getOutputData().iterator(); var13.hasNext(); var2.append("</output>")) {
                        var15 = (MonitorObject)var13.next();
                        var2.append("<output name=\"" + var15.getName() + "\">");
                        if (var15.getFields() != null) {
                            var16 = var15.getFields().iterator();

                            while(var16.hasNext()) {
                                var11 = (MonitorObjectField)var16.next();
                                var2.append("<field name=\"" + var11.getName() + "\" label=\"" + var11.getLabel() + "\"/>");
                            }
                        }
                    }
                }

                var2.append("</service>");
            }

            if (var5.getResourceItems() != null) {
                var7 = var5.getResourceItems().iterator();

                while(var7.hasNext()) {
                    ResourceItem var14 = (ResourceItem)var7.next();
                    var2.append("<res-package-item  name=\"" + var14.getName() + "\" path=\"" + var14.getPath() + "\" version=\"" + var14.getVersion() + "\"/>");
                }
            }
        }

        var2.append("</res-packages>");
        return var2.toString();
    }
}
