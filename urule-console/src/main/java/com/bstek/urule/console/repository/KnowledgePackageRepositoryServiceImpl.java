//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.repository;

import com.bstek.urule.Utils;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.model.ResourceItem;
import com.bstek.urule.console.repository.model.ResourcePackage;
import com.bstek.urule.console.repository.model.ServiceConfig;
import com.bstek.urule.console.repository.model.VersionFile;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgePackageImpl;
import com.bstek.urule.runtime.monitor.MonitorObject;
import com.bstek.urule.runtime.monitor.MonitorObjectField;
import java.io.InputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.value.BinaryImpl;
import org.apache.jackrabbit.value.DateValue;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class KnowledgePackageRepositoryServiceImpl extends BaseRepositoryService implements KnowledgePackageRepositoryService {
    public KnowledgePackageRepositoryServiceImpl() {
    }

    public void knowledgePackageStateChange(String var1, boolean var2) {
        PackageData var3 = new PackageData(var1);

        try {
            String var4 = "___knowledge_package_data__" + var3.getProject() + "/" + var3.getPackageId();
            if (this.getRootNode().hasNode(var4)) {
                Node var5 = this.getRootNode().getNode(var4);
                NodeIterator var6 = var5.getNodes();

                while(var6.hasNext()) {
                    Node var7 = var6.nextNode();
                    if (var7.hasProperty("_kp_state")) {
                        var7.getProperty("_kp_state").setValue(var2);
                    } else {
                        var7.setProperty("_kp_state", var2);
                    }
                }

                this.session.save();
            }
        } catch (Exception var8) {
            throw new RuleException(var8);
        }
    }

    public List<ResourcePackage> loadProjectResourcePackages(String var1) {
        try {
            Node var2 = this.getRootNode();
            String var3 = this.processPath(var1) + "/" + "___res__package__file__";
            if (!var2.hasNode(var3)) {
                return new ArrayList();
            } else {
                Node var4 = var2.getNode(var3);
                Property var5 = var4.getProperty("_data");
                Binary var6 = var5.getBinary();
                InputStream var7 = var6.getStream();
                String var8 = IOUtils.toString(var7, "utf-8");
                var7.close();
                return this.parseResourcePackages(var1, var8);
            }
        } catch (Exception var9) {
            throw new RuleException(var9);
        }
    }

    public List<ResourcePackage> loadResourcePackagesByFile(String var1) {
        String var2 = var1;
        int var3;
        if (var1.startsWith("/")) {
            var2 = var1.substring(1, var1.length());
            var3 = var2.indexOf("/");
            var2 = var2.substring(0, var3);
        } else if (var1.startsWith("jcr:")) {
            var2 = var1.substring(4, var1.length());
            if (var2.startsWith("/")) {
                var2 = var2.substring(1, var2.length());
                var3 = var2.indexOf("/");
                var2 = var2.substring(0, var3);
            } else {
                var3 = var2.indexOf("/");
                var2 = var2.substring(0, var3);
            }
        }

        try {
            Node var17 = this.getRootNode();
            String var4 = var2 + "/" + "___res__package__file__";
            if (!var17.hasNode(var4)) {
                return new ArrayList();
            } else {
                Node var5 = var17.getNode(var4);
                Property var6 = var5.getProperty("_data");
                Binary var7 = var6.getBinary();
                InputStream var8 = var7.getStream();
                String var9 = IOUtils.toString(var8, "utf-8");
                var8.close();
                ArrayList var10 = new ArrayList();
                List var11 = this.parseResourcePackages(var2, var9);
                Iterator var12 = var11.iterator();

                while(true) {
                    while(true) {
                        ResourcePackage var13;
                        do {
                            if (!var12.hasNext()) {
                                return var10;
                            }

                            var13 = (ResourcePackage)var12.next();
                        } while(var13.getResourceItems() == null);

                        Iterator var14 = var13.getResourceItems().iterator();

                        while(var14.hasNext()) {
                            ResourceItem var15 = (ResourceItem)var14.next();
                            if (var1.equals(var15.getPath())) {
                                var10.add(var13);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (Exception var16) {
            throw new RuleException(var16);
        }
    }

    public List<ResourcePackage> parseResourcePackages(String var1, String var2) throws DocumentException, ParseException {
        ArrayList var3 = new ArrayList();
        SimpleDateFormat var4 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Document var5 = DocumentHelper.parseText(var2);
        Element var6 = var5.getRootElement();
        Iterator var7 = var6.elements().iterator();

        while(true) {
            Element var9;
            do {
                Object var8;
                do {
                    if (!var7.hasNext()) {
                        return var3;
                    }

                    var8 = var7.next();
                } while(!(var8 instanceof Element));

                var9 = (Element)var8;
            } while(!var9.getName().equals("res-package"));

            ResourcePackage var10 = new ResourcePackage();
            String var11 = var9.attributeValue("create_date");
            if (var11 != null) {
                var10.setCreateDate(var4.parse(var11));
            }

            var10.setId(var9.attributeValue("id"));
            var10.setName(var9.attributeValue("name"));
            String var12 = var9.attributeValue("check");
            if (StringUtils.isNotBlank(var12)) {
                var10.setCheck(Boolean.valueOf(var12));
            } else {
                var10.setCheck(true);
            }

            if (var10.isCheck()) {
                var10.setCheckState("启用");
            } else {
                var10.setCheckState("已停用");
            }

            var10.setProject(var1);
            String var13 = var9.attributeValue("monitor");
            if (StringUtils.isNotBlank(var13)) {
                var10.setMonitor(Boolean.valueOf(var13));
            }

            String var14 = var9.attributeValue("show-log");
            if (StringUtils.isNotBlank(var14)) {
                var10.setShowLog(Boolean.valueOf(var14));
            }

            String var15 = var9.attributeValue("show-fired-flow-node-list");
            if (StringUtils.isNotBlank(var15)) {
                var10.setShowFiredFlowNodeList(Boolean.valueOf(var15));
            }

            String var16 = var9.attributeValue("show-matched-rule-list");
            if (StringUtils.isNotBlank(var16)) {
                var10.setShowMatchedRuleList(Boolean.valueOf(var16));
            }

            String var17 = var9.attributeValue("show-not-match-rule-list");
            if (StringUtils.isNotBlank(var17)) {
                var10.setShowNotMatchRuleList(Boolean.valueOf(var17));
            }

            ArrayList var18 = new ArrayList();
            ArrayList var19 = new ArrayList();
            ArrayList var20 = new ArrayList();
            Iterator var21 = var9.elements().iterator();

            while(var21.hasNext()) {
                Object var22 = var21.next();
                if (var22 instanceof Element) {
                    Element var23 = (Element)var22;
                    String var24 = var23.getName();
                    if (var24.equals("res-package-item")) {
                        ResourceItem var25 = new ResourceItem();
                        var25.setName(var23.attributeValue("name"));
                        var25.setPackageId(var10.getId());
                        var25.setPath(var23.attributeValue("path"));
                        var25.setVersion(var23.attributeValue("version"));
                        var20.add(var25);
                    } else if (var24.equals("input")) {
                        var18.add(this.b(var23));
                    } else if (var24.equals("output")) {
                        var19.add(this.b(var23));
                    } else if (var24.equals("service")) {
                        var10.setService(this.a(var23));
                    } else if (var24.equals("quick-test-data")) {
                        var10.setQuickTestData(var23.getText());
                    }
                }
            }

            var10.setInputData(var18);
            var10.setOutputData(var19);
            var10.setResourceItems(var20);
            var3.add(var10);
        }
    }

    private ServiceConfig a(Element var1) {
        ServiceConfig var2 = new ServiceConfig();
        String var3 = var1.attributeValue("security");
        if (StringUtils.isNotBlank(var3)) {
            var2.setSecurity(Boolean.valueOf(var3));
        }

        if (var2.isSecurity()) {
            var2.setUsername(var1.attributeValue("username"));
            var2.setPassword(var1.attributeValue("password"));
        }

        ArrayList var4 = new ArrayList();
        ArrayList var5 = new ArrayList();
        Iterator var6 = var1.elements().iterator();

        while(var6.hasNext()) {
            Object var7 = var6.next();
            if (var7 instanceof Element) {
                Element var8 = (Element)var7;
                String var9 = var8.getName();
                if (var9.equals("input")) {
                    var4.add(this.b(var8));
                } else if (var9.equals("output")) {
                    var5.add(this.b(var8));
                }
            }
        }

        var2.setInputData(var4);
        var2.setOutputData(var5);
        return var2;
    }

    private MonitorObject b(Element var1) {
        MonitorObject var2 = new MonitorObject();
        ArrayList var3 = new ArrayList();
        var2.setFields(var3);
        var2.setName(var1.attributeValue("name"));
        Iterator var4 = var1.elements().iterator();

        while(var4.hasNext()) {
            Object var5 = var4.next();
            if (var5 instanceof Element) {
                Element var6 = (Element)var5;
                String var7 = var6.getName();
                if (var7.equals("field")) {
                    MonitorObjectField var8 = new MonitorObjectField();
                    var8.setName(var6.attributeValue("name"));
                    var8.setLabel(var6.attributeValue("label"));
                    var3.add(var8);
                }
            }
        }

        return var2;
    }

    public void resetProjectResourcePackagesTag(String var1) {
        try {
            var1 = Utils.decodeURL(var1);
            String var2 = var1 + "/" + "___res__package__file__";
            var2 = this.processPath(var2);
            Node var3 = this.getRootNode();
            Node var4 = null;
            if (!var3.hasNode(var2)) {
                var4 = var3.addNode(var2);
            } else {
                var4 = var3.getNode(var2);
            }

            var4.setProperty("___res__package__file__tag__data_", String.valueOf((new Date()).getTime()));
        } catch (Exception var5) {
            throw new RuleException(var5);
        }
    }

    public String getProjectResourcePackagesTag(String var1) {
        try {
            String var2 = var1 + "/" + "___res__package__file__";
            var2 = this.processPath(var2);
            Node var3 = this.getRootNode();
            if (!var3.hasNode(var2)) {
                return "";
            } else {
                Node var4 = var3.getNode(var2);
                return var4.hasProperty("___res__package__file__tag__data_") ? var4.getProperty("___res__package__file__tag__data_").getString() : "";
            }
        } catch (Exception var5) {
            throw new RuleException(var5);
        }
    }

    public List<VersionFile> getKnowledgePackges(String var1) {
        ArrayList var2 = new ArrayList();
        PackageData var3 = new PackageData(var1);

        try {
            String var4 = "___knowledge_package_data__" + var3.getProject() + "/" + var3.getPackageId();
            if (!this.getRootNode().hasNode(var4)) {
                return var2;
            } else {
                Node var5 = this.getRootNode().getNode(var4);
                NodeIterator var6 = var5.getNodes();

                while(var6.hasNext()) {
                    Node var7 = var6.nextNode();
                    VersionFile var8 = new VersionFile();
                    var8.setActived(var7.getProperty("_actived").getBoolean());
                    var8.setComment(var7.getProperty("_version_comment").getString());
                    var8.setCreateDate(var7.getProperty("_create_date").getDate().getTime());
                    var8.setCreateUser(var7.getProperty("_create_user").getString());
                    var8.setTimestamp(var7.getProperty("_timestamp").getLong());
                    var8.setName(var7.getName());
                    var8.setPath(var7.getPath());
                    var2.add(0, var8);
                }

                return var2;
            }
        } catch (Exception var9) {
            throw new RuleException(var9);
        }
    }

    public byte[] getKnowledgePackgeBytes(String var1, String var2) {
        PackageData var3 = new PackageData(var1);
        InputStream var4 = null;

        byte[] var7;
        try {
            String var5 = "___knowledge_package_data__" + var3.getProject() + "/" + var3.getPackageId() + "/" + var2;
            if (!this.getRootNode().hasNode(var5)) {
                throw new RuleException("Knowledge Package [" + var1 + ":" + var2 + "] not exist");
            }

            var4 = this.readFile(var5);
            byte[] var6 = IOUtils.toByteArray(var4);
            var7 = var6;
        } catch (Exception var11) {
            throw new RuleException(var11);
        } finally {
            IOUtils.closeQuietly(var4);
        }

        return var7;
    }

    public KnowledgePackage getKnowledgePackge(String var1, String var2) {
        PackageData var3 = new PackageData(var1);
        InputStream var4 = null;

        KnowledgePackage var11;
        try {
            String var5 = "___knowledge_package_data__" + var3.getProject() + "/" + var3.getPackageId() + "/" + var2;
            if (!this.getRootNode().hasNode(var5)) {
                throw new RuleException("Knowledge Package [" + var1 + ":" + var2 + "] not exist");
            }

            var4 = this.readFile(var5);
            byte[] var6 = IOUtils.toByteArray(var4);
            String var7 = Utils.uncompress(var6);
            KnowledgePackage var8 = Utils.stringToKnowledgePackage(var7);
            KnowledgePackageImpl var9 = (KnowledgePackageImpl)var8;
            var9.initForActiveVersion();
            var9.setPackageInfo(var1);
            Node var10 = this.getRootNode().getNode(var5);
            var9.setVersionComment(var10.getProperty("_version_comment").getString());
            var9.setVersionCreateDate(var10.getProperty("_create_date").getDate().getTime());
            var9.setVersionCreateUser(var10.getProperty("_create_user").getString());
            var9.setVersion(var2);
            var9.setVariableCategories(this.getKnowledgePackgeLib(var1, var2));
            var11 = var8;
        } catch (Exception var15) {
            throw new RuleException(var15);
        } finally {
            IOUtils.closeQuietly(var4);
        }

        return var11;
    }

    public List<VariableCategory> getKnowledgePackgeLib(String var1, String var2) {
        PackageData var3 = new PackageData(var1);
        InputStream var4 = null;

        try {
            String var5 = "___knowledge_package_data__" + var3.getProject() + "/" + var3.getPackageId() + "/" + var2 + "/lib";
            if (this.getRootNode().hasNode(var5)) {
                var4 = this.readFile(var5);
                byte[] var6 = IOUtils.toByteArray(var4);
                String var7 = Utils.uncompress(var6);
                List var8 = Utils.stringToKnowledgePackageLib(var7);
                List var9 = var8;
                return var9;
            }
        } catch (Exception var13) {
            throw new RuleException(var13);
        } finally {
            IOUtils.closeQuietly(var4);
        }

        return null;
    }

    public InputStream getKnowledgePackgeData(String var1, String var2) {
        PackageData var3 = new PackageData(var1);
        Object var4 = null;

        InputStream var6;
        try {
            String var5 = "___knowledge_package_data__" + var3.getProject() + "/" + var3.getPackageId() + "/" + var2;
            if (!this.getRootNode().hasNode(var5)) {
                throw new RuleException("Knowledge Package [" + var1 + ":" + var2 + "] not exist");
            }

            var6 = this.readFile(var5);
        } catch (Exception var10) {
            throw new RuleException(var10);
        } finally {
            IOUtils.closeQuietly((InputStream)var4);
        }

        return var6;
    }

    public void projectRename(String var1, String var2) {
        var1 = this.processPath(var1);
        var2 = this.processPath(var2);

        try {
            String var3 = "___knowledge_package_data__" + var1;
            Node var4 = this.getRootNode();
            if (var4.hasNode(var3)) {
                String var5 = "___knowledge_package_data__" + var2;
                this.session.getWorkspace().move("/" + var3, "/" + var5);
                this.session.save();
            }
        } catch (Exception var6) {
            throw new RuleException(var6);
        }
    }

    public void removeDeployKnowledgePackge(String var1) {
        PackageData var2 = new PackageData(var1);

        try {
            Node var3 = this.getRootNode();
            String var4 = "___knowledge_package_data__" + var2.getProject() + "/" + var2.getPackageId();
            if (var3.hasNode(var4)) {
                Node var5 = this.getRootNode().getNode(var4);
                NodeIterator var6 = var5.getNodes();

                while(var6.hasNext()) {
                    Node var7 = var6.nextNode();
                    var7.remove();
                }

                var5.remove();
                this.session.save();
            }
        } catch (Exception var8) {
            throw new RuleException(var8);
        }
    }

    public Map<String, Object> loadKnowledgePackageFiles(String var1, String var2) {
        PackageData var3 = new PackageData(var1);

        try {
            String var4 = "___knowledge_package_data__" + var3.getProject() + "/" + var3.getPackageId() + "/" + var2;
            Map var5 = this.a(var4);
            return var5;
        } catch (Exception var6) {
            throw new RuleException(var6);
        }
    }

    private Map<String, Object> a(String var1) throws Exception {
        HashMap var2 = new HashMap();
        if (this.getRootNode().hasNode(var1)) {
            Node var3 = this.getRootNode().getNode(var1);
            PropertyIterator var4 = var3.getProperties();

            while(var4.hasNext()) {
                Property var5 = var4.nextProperty();
                String var6 = var5.getName();
                var6 = URLDecoder.decode(var6, "utf-8");
                if (var6.startsWith("_package_file_prefix_")) {
                    var6 = var6.substring("_package_file_prefix_".length(), var6.length());
                    Binary var7 = var5.getBinary();
                    InputStream var8 = var7.getStream();
                    String var9 = IOUtils.toString(var8, "utf-8");
                    Document var10 = DocumentHelper.parseText(var9);
                    OutputFormat var11 = OutputFormat.createPrettyPrint();
                    StringWriter var12 = new StringWriter();
                    XMLWriter var13 = new XMLWriter(var12, var11);
                    var13.write(var10);
                    int var14 = var6.lastIndexOf(":");
                    if (var14 > -1) {
                        var6 = var6.substring(0, var14);
                    }

                    if (var6.endsWith(FileType.RuleFlow.toString())) {
                        String var15 = this.b(var6);
                        String var16 = var1 + "/" + var15;
                        Map var17 = this.a(var16);
                        if (var17.size() > 0) {
                            HashMap var18 = new HashMap();
                            var18.put("value", var12.toString());
                            var18.put("children", var17);
                            var2.put(var6, var18);
                        } else {
                            var2.put(var6, var12.toString());
                        }
                    } else {
                        var2.put(var6, var12.toString());
                    }

                    IOUtils.closeQuietly(var8);
                }
            }
        }

        return var2;
    }

    public void removeKnowledgePackge(String var1, String var2) {
        PackageData var3 = new PackageData(var1);

        try {
            String var4 = "___knowledge_package_data__" + var3.getProject() + "/" + var3.getPackageId() + "/" + var2;
            if (this.getRootNode().hasNode(var4)) {
                Node var5 = this.getRootNode().getNode(var4);
                var5.remove();
                this.session.save();
            }

        } catch (Exception var6) {
            throw new RuleException(var6);
        }
    }

    public VersionFile getActivedKnowledgePackge(String var1) {
        PackageData var2 = new PackageData(var1);

        try {
            String var3 = "___knowledge_package_data__" + var2.getProject() + "/" + var2.getPackageId();
            if (!this.getRootNode().hasNode(var3)) {
                return null;
            } else {
                Node var4 = this.getRootNode().getNode(var3);
                NodeIterator var5 = var4.getNodes();
                VersionFile var6 = null;

                while(var5.hasNext()) {
                    Node var7 = var5.nextNode();
                    if (var7.hasProperty("_actived") && var7.getProperty("_actived").getBoolean()) {
                        var6 = new VersionFile();
                        var6.setActived(true);
                        var6.setComment(var7.getProperty("_version_comment").getString());
                        var6.setCreateDate(var7.getProperty("_create_date").getDate().getTime());
                        var6.setCreateUser(var7.getProperty("_create_user").getString());
                        var6.setTimestamp(var7.getProperty("_timestamp").getLong());
                        if (var7.hasProperty("_kp_state")) {
                            Property var8 = var7.getProperty("_kp_state");
                            if (var8 != null) {
                                boolean var9 = var8.getBoolean();
                                var6.setState(var9);
                            }
                        }

                        var6.setName(var7.getName());
                        var6.setPath(var7.getPath());
                        break;
                    }
                }

                return var6;
            }
        } catch (Exception var10) {
            throw new RuleException(var10);
        }
    }

    public void activeKnowledgePackage(String var1, String var2) {
        PackageData var3 = new PackageData(var1);

        try {
            String var4 = "___knowledge_package_data__" + var3.getProject() + "/" + var3.getPackageId();
            Node var5 = this.getRootNode().getNode(var4);
            NodeIterator var6 = var5.getNodes();

            while(var6.hasNext()) {
                Node var7 = var6.nextNode();
                String var8 = var7.getName();
                if (var8.equals(var2)) {
                    var7.getProperty("_actived").setValue(true);
                } else {
                    var7.getProperty("_actived").setValue(false);
                }
            }

            this.session.save();
        } catch (Exception var9) {
            throw new RuleException(var9);
        }
    }

    public void saveKnowledgePackage(String var1, KnowledgePackage var2, String var3, String var4, boolean var5) {
        PackageData var6 = new PackageData(var1);

        try {
            if (var5) {
                this.a(var6);
            }

            Node[] var7 = this.b(var6);
            Node var8 = var7[0];
            Node var9 = var7[1];
            String var10 = Utils.knowledgePackageToString(var2);
            String var11 = Utils.knowledgePackageLibToString(var2);
            byte[] var12 = Utils.compress(var10);
            byte[] var13 = Utils.compress(var11);
            BinaryImpl var14 = new BinaryImpl(var13);
            var9.setProperty("_data", var14);
            BinaryImpl var15 = new BinaryImpl(var12);
            var8.setProperty("_data", var15);
            var8.setProperty("_file", true);
            var8.setProperty("_create_user", var4);
            var8.setProperty("_actived", var5);
            var8.setProperty("_kp_state", true);
            var8.setProperty("_timestamp", var2.getTimestamp());
            List var16 = this.c(var1);
            this.a(var8, var16);
            Calendar var17 = Calendar.getInstance();
            var17.setTime(new Date());
            DateValue var18 = new DateValue(var17);
            var8.setProperty("_create_date", var18);
            var8.setProperty("_version_comment", var3);
            this.session.save();
        } catch (Exception var19) {
            throw new RuleException(var19);
        }
    }

    private void a(Node var1, List<ResourceItem> var2) throws Exception {
        Iterator var3 = var2.iterator();

        while(var3.hasNext()) {
            ResourceItem var4 = (ResourceItem)var3.next();
            String var5 = var4.getPath();
            if (var5.startsWith("jcr:")) {
                var5 = var5.substring(4, var5.length());
            }

            String var6 = var4.getVersion();
            if (var6 != null && var6.equals("LATEST")) {
                var6 = null;
            }

            InputStream var7 = this.readFile(var5, var6);
            BinaryImpl var8 = new BinaryImpl(var7);
            String var9 = var4.getPath();
            if (var4.getVersion() != null) {
                var9 = var9 + ":" + var4.getVersion();
            }

            var9 = URLEncoder.encode(var9, "utf-8");
            var1.setProperty("_package_file_prefix_" + var9, var8);
            IOUtils.closeQuietly(var7);
            if (var5.endsWith(FileType.RuleFlow.toString())) {
                String var10 = this.b(var5);
                List var11 = this.a(var5, var6);
                Node var12 = var1.addNode(var10);
                this.a(var12, var11);
            }
        }

    }

    private String b(String var1) {
        int var2 = var1.lastIndexOf("/");
        int var3 = var1.indexOf(".");
        String var4 = var1.substring(var2 + 1, var3);
        return var4;
    }

    private List<ResourceItem> a(String var1, String var2) throws Exception {
        ArrayList var3 = new ArrayList();
        InputStream var4 = this.readFile(var1, var2);
        String var5 = IOUtils.toString(var4, "utf-8");
        var4.close();
        Document var6 = DocumentHelper.parseText(var5);
        Element var7 = var6.getRootElement();
        Iterator var8 = var7.elements().iterator();

        while(var8.hasNext()) {
            Object var9 = var8.next();
            if (var9 != null && var9 instanceof Element) {
                Element var10 = (Element)var9;
                if (var10.getName().equals("rule")) {
                    String var11 = var10.attributeValue("file");
                    String var12 = var10.attributeValue("version");
                    ResourceItem var13 = new ResourceItem();
                    var13.setPath(var11);
                    var13.setVersion(var12);
                    var3.add(var13);
                }
            }
        }

        return var3;
    }

    private List<ResourceItem> c(String var1) {
        String[] var2 = var1.split("/");
        String var3 = var2[0];
        String var4 = var2[1];
        List var5 = this.loadProjectResourcePackages(var3);
        List var6 = null;
        Iterator var7 = var5.iterator();

        while(var7.hasNext()) {
            ResourcePackage var8 = (ResourcePackage)var7.next();
            if (var8.getId().equals(var4)) {
                var6 = var8.getResourceItems();
                break;
            }
        }

        if (var6 == null) {
            throw new RuleException("项目【" + var3 + "】下知识包【" + var4 + "】不存在！");
        } else {
            return var6;
        }
    }

    private void a(PackageData var1) throws Exception {
        String var2 = "___knowledge_package_data__" + var1.getProject() + "/" + var1.getPackageId();
        if (this.getRootNode().hasNode(var2)) {
            Node var3 = this.getRootNode().getNode(var2);
            NodeIterator var4 = var3.getNodes();

            while(var4.hasNext()) {
                Node var5 = var4.nextNode();
                var5.setProperty("_actived", false);
            }

            this.session.save();
        }
    }

    private Node[] b(PackageData var1) throws Exception {
        String var2 = "___knowledge_package_data__" + var1.getProject();
        Node var3 = this.getRootNode();
        Node var4 = null;
        if (!var3.hasNode(var2)) {
            var3.addNode(var2);
            this.session.save();
        }

        String var5 = var2 + "/" + var1.getPackageId();
        if (var3.hasNode(var5)) {
            var4 = var3.getNode(var5);
        } else {
            var4 = var3.addNode(var5);
            this.session.save();
        }

        BigDecimal var6 = new BigDecimal((double)this.a(var4));
        if (var6.compareTo(new BigDecimal(0)) == 0) {
            var6 = new BigDecimal(1.0D);
        } else {
            var6 = var6.add(new BigDecimal(0.1D));
        }

        String var7 = var6.setScale(1, 4).toPlainString();
        var5 = var5 + "/" + var7;
        var4 = var3.addNode(var5);
        String var8 = var5 + "/lib";
        Node var9 = var3.addNode(var8);
        this.session.save();
        return new Node[]{var4, var9};
    }

    private float a(Node var1) throws Exception {
        float var2 = 0.0F;
        NodeIterator var3 = var1.getNodes();

        while(var3.hasNext()) {
            Node var4 = var3.nextNode();
            String var5 = var4.getName();
            float var6 = 0.0F;
            if (StringUtils.isNotBlank(var5)) {
                var6 = Float.valueOf(var5);
            }

            if (var6 > var2) {
                var2 = var6;
            }
        }

        return var2;
    }
}
