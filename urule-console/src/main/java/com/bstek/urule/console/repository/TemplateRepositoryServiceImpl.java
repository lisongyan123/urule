package com.bstek.urule.console.repository;

import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.repository.model.Type;
import com.bstek.urule.exception.RuleException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import org.apache.jackrabbit.value.BinaryImpl;
import org.apache.jackrabbit.value.DateValue;

public class TemplateRepositoryServiceImpl extends BaseRepositoryService implements TemplateRepositoryService {
    public TemplateRepositoryServiceImpl() {
    }

    public void removeTemplateCategory(String var1, String var2) throws Exception {
        String var3 = var1 + "/" + "__rules_templates__" + "/" + var2;
        var3 = this.processPath(var3);
        Node var4 = this.getRootNode();
        if (!var4.hasNode(var3)) {
            throw new RuleException("File [" + var3 + "] not exist.");
        } else {
            var4.getNode(var3).remove();
            this.session.save();
        }
    }

    public void removeTemplateFile(String var1) throws Exception {
        var1 = this.processPath(var1);
        Node var2 = this.getRootNode();
        if (!var2.hasNode(var1)) {
            throw new RuleException("File [" + var1 + "] not exist.");
        } else {
            var2.getNode(var1).remove();
            this.session.save();
        }
    }

    public String createTemplateCategory(Principal var1, String var2, String var3) throws Exception {
        String var4 = var1.getName();
        Node var5 = this.getRootNode();
        String var6 = var2 + "/" + "__rules_templates__" + "/" + var3;
        var6 = this.processPath(var6);
        if (var5.hasNode(var6)) {
            throw new RuleException("File [" + var6 + "] already exist.");
        } else {
            Node var7 = var5.addNode(var6);
            var7.setProperty("_create_user", var4);
            var7.setProperty("_template_category", true);
            Calendar var8 = Calendar.getInstance();
            var8.setTime(new Date());
            DateValue var9 = new DateValue(var8);
            var7.setProperty("_create_date", var9);
            this.session.save();
            return var6;
        }
    }

    public String saveTemplateFile(String var1, String var2) throws Exception {
        var1 = this.processPath(var1);
        Node var3 = this.getRootNode();
        if (!var3.hasNode(var1)) {
            throw new RuleException("File [" + var1 + "] not exist.");
        } else {
            Node var4 = var3.getNode(var1);
            BinaryImpl var5 = new BinaryImpl(var2.getBytes("utf-8"));
            var4.setProperty("_data", var5);
            var4.setProperty("_file", true);
            this.session.save();
            return var1;
        }
    }

    public String createTemplateFile(Principal var1, String var2, String var3, String var4) throws Exception {
        String var5 = var1.getName();
        Node var6 = this.getRootNode();
        var2 = this.processPath(var2);
        if (var6.hasNode(var2)) {
            throw new RuleException("File [" + var2 + "] already exist.");
        } else {
            Node var7 = var6.addNode(var2);
            BinaryImpl var8 = new BinaryImpl(var4.getBytes("UTF-8"));
            var7.setProperty("_data", var8);
            var7.setProperty("_file", true);
            var7.setProperty("__rules_template_comment__", var3);
            var7.setProperty("_create_user", var5);
            Calendar var9 = Calendar.getInstance();
            var9.setTime(new Date());
            DateValue var10 = new DateValue(var9);
            var7.setProperty("_create_date", var10);
            this.session.save();
            return var2;
        }
    }

    public List<RepositoryFile> loadTemplates(String var1) throws Exception {
        String var2 = var1 + "/" + "__rules_templates__";
        var2 = this.processPath(var2);
        Node var3 = this.getRootNode();
        Node var4 = null;
        if (!var3.hasNode(var2)) {
            var4 = var3.addNode(var2);
        } else {
            var4 = var3.getNode(var2);
        }

        ArrayList var5 = new ArrayList();
        NodeIterator var6 = var4.getNodes();

        while(var6.hasNext()) {
            Node var7 = var6.nextNode();
            if (var7.hasProperty("_template_category")) {
                RepositoryFile var8 = new RepositoryFile();
                var8.setName(var7.getName());
                var8.setFullPath(var7.getPath());
                var8.setChildren(this.a(var7));
                var5.add(var8);
            }
        }

        return var5;
    }

    private List<RepositoryFile> a(Node var1) throws Exception {
        ArrayList var2 = new ArrayList();
        NodeIterator var3 = var1.getNodes();

        while(var3.hasNext()) {
            Node var4 = var3.nextNode();
            if (var4.hasProperty("_file")) {
                RepositoryFile var5 = new RepositoryFile();
                var5.setType(Type.rule);
                var5.setFullPath(var4.getPath());
                var5.setName(var4.getName());
                if (var4.hasProperty("__rules_template_comment__")) {
                    var5.setComment(var4.getProperty("__rules_template_comment__").getString());
                }

                var2.add(var5);
            }
        }

        return var2;
    }
}
