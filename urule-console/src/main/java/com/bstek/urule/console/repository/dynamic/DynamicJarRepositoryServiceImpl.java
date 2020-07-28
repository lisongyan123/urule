package com.bstek.urule.console.repository.dynamic;

import com.bstek.urule.console.repository.BaseRepositoryService;
import com.bstek.urule.exception.RuleException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import org.apache.jackrabbit.value.BinaryImpl;
import org.apache.jackrabbit.value.DateValue;
import org.apache.poi.util.IOUtils;

public class DynamicJarRepositoryServiceImpl extends BaseRepositoryService implements DynamicJarRepositoryService {
    public static final String BEAN_ID = "urule.dynamicJarRepositoryService";

    public DynamicJarRepositoryServiceImpl() {
    }

    public List<DynamicFile> loadFiles() throws Exception {
        ArrayList var1 = new ArrayList();
        Node var2 = this.a();
        NodeIterator var3 = var2.getNodes();

        while(var3.hasNext()) {
            Node var4 = var3.nextNode();
            DynamicFile var5 = this.a(var4);
            var1.add(0, var5);
        }

        return var1;
    }

    public void removeFile(String var1) throws Exception {
        var1 = this.processPath(var1);
        Node var2 = this.getRootNode();
        if (var2.hasNode(var1)) {
            var2.getNode(var1).remove();
            this.session.save();
        }
    }

    public DynamicFile getFile(String var1) throws Exception {
        var1 = this.processPath(var1);
        Node var2 = this.getRootNode();
        if (!var2.hasNode(var1)) {
            return null;
        } else {
            Node var3 = var2.getNode(var1);
            DynamicFile var4 = this.a(var3);
            return var4;
        }
    }

    public void removeJar(String var1, String var2) throws Exception {
        var1 = this.processPath(var1);
        Node var3 = this.getRootNode();
        if (var3.hasNode(var1)) {
            Node var4 = var3.getNode(var1);
            PropertyIterator var5 = var4.getProperties();

            while(var5.hasNext()) {
                Property var6 = var5.nextProperty();
                String var7 = var6.getName();
                if (var7.startsWith("_dynamic_jar_prefix_")) {
                    String var8 = this.a(var7);
                    if (var8.equals(var2)) {
                        var6.remove();
                        this.session.save();
                        break;
                    }
                }
            }

        }
    }

    public void saveFile(DynamicFile var1) throws Exception {
        Node var2 = null;
        Node var3 = this.a();
        if (var3.hasNode(var1.getName())) {
            var2 = var3.getNode(var1.getName());
        } else {
            var2 = var3.addNode(var1.getName());
        }

        var2.setProperty("_create_user", var1.getCreateUser());
        var2.setProperty("_version_comment", var1.getComment());
        Calendar var4 = Calendar.getInstance();
        var4.setTime(new Date());
        DateValue var5 = new DateValue(var4);
        var2.setProperty("_create_date", var5);
        this.session.save();
    }

    public void addJar(String var1, String var2, InputStream var3) throws Exception {
        Node var4 = this.getRootNode();
        var1 = this.processPath(var1);
        if (!var4.hasNode(var1)) {
            throw new RuleException("Path [" + var1 + "] not exist.");
        } else {
            Node var5 = var4.getNode(var1);
            BinaryImpl var6 = new BinaryImpl(IOUtils.toByteArray(var3));
            var5.setProperty("_dynamic_jar_prefix_" + var2, var6);
            this.session.save();
        }
    }

    public InputStream loadJar(String var1, String var2) throws Exception {
        var1 = this.processPath(var1);
        Node var3 = this.getRootNode();
        if (!var3.hasNode(var1)) {
            throw new RuleException("Path [" + var1 + "] not exist.");
        } else {
            InputStream var4 = null;
            Node var5 = var3.getNode(var1);
            PropertyIterator var6 = var5.getProperties();

            while(var6.hasNext()) {
                Property var7 = var6.nextProperty();
                String var8 = var7.getName();
                if (var8.startsWith("_dynamic_jar_prefix_")) {
                    String var9 = this.a(var8);
                    if (var2.equals(var9)) {
                        var4 = var7.getBinary().getStream();
                        break;
                    }
                }
            }

            return var4;
        }
    }

    public void generateJars(String var1, String var2) throws Exception {
        var1 = this.processPath(var1);
        Node var3 = this.getRootNode();
        if (!var3.hasNode(var1)) {
            throw new RuleException("Path [" + var1 + "] not exist.");
        } else {
            Node var4 = var3.getNode(var1);
            PropertyIterator var5 = var4.getProperties();

            while(var5.hasNext()) {
                Property var6 = var5.nextProperty();
                String var7 = var6.getName();
                if (var7.startsWith("_dynamic_jar_prefix_")) {
                    File var8 = new File(var2);
                    if (!var8.exists()) {
                        var8.mkdirs();
                    }

                    String var9 = this.a(var7);
                    String var10 = var2 + "/" + var9;
                    InputStream var11 = var6.getBinary().getStream();
                    File var12 = new File(var10);
                    if (!var12.exists()) {
                        var12.createNewFile();
                    }

                    FileOutputStream var13 = new FileOutputStream(var12);
                    IOUtils.copy(var11, var13);
                    IOUtils.closeQuietly(var13);
                    IOUtils.closeQuietly(var11);
                }
            }

        }
    }

    private DynamicFile a(Node var1) throws Exception {
        DynamicFile var2 = new DynamicFile();
        var2.setName(var1.getName());
        Property var3 = var1.getProperty("_create_user");
        var2.setCreateUser(var3.getString());
        var3 = var1.getProperty("_create_date");
        var2.setCreateDate(var3.getDate().getTime());
        var3 = var1.getProperty("_version_comment");
        var2.setComment(var3.getString());
        var2.setPath(var1.getPath());
        var2.setJars(this.b(var1));
        return var2;
    }

    private List<DynamicJar> b(Node var1) throws Exception {
        PropertyIterator var2 = var1.getProperties();
        ArrayList var3 = new ArrayList();

        while(var2.hasNext()) {
            Property var4 = var2.nextProperty();
            String var5 = var4.getName();
            if (var5.startsWith("_dynamic_jar_prefix_")) {
                String var6 = this.a(var5);
                var3.add(new DynamicJar(var6));
            }
        }

        return var3;
    }

    private String a(String var1) {
        return var1.substring("_dynamic_jar_prefix_".length(), var1.length());
    }

    private Node a() throws Exception {
        Node var1 = this.getRootNode();
        String var2 = "___dynamic__jars__node__";
        Node var3 = null;
        if (var1.hasNode(var2)) {
            var3 = var1.getNode(var2);
        } else {
            var3 = var1.addNode(var2);
            this.session.save();
        }

        return var3;
    }
}
