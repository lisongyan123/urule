//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.repository;

import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.exception.RuleException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

public class ImportProjectProcessor {
    private RepositoryServiceImpl a;

    public ImportProjectProcessor(RepositoryServiceImpl var1) {
        this.a = var1;
    }

    public void doImport(InputStream var1, Repository var2) throws Exception {
        Session var3 = this.a.session;
        Node var4 = var3.getRootNode();
        String var5 = "___temp_mount_project_node_for_import__";
        if (!var4.hasNode(var5)) {
            var4.addNode(var5);
            var3.save();
        }

        Node var6 = var4.getNode(var5);
        var3.importXML(var6.getPath(), var1, 0);
        var3.save();
        String var7 = this.b(var6);
        if (var7 == null) {
            throw new RuleException("Import project name not exist.");
        } else {
            String var8 = var6.getPath() + "/" + var7;
            String var9 = "/" + var7;
            List var10 = var2.getProjectNames();
            if (var10.contains(var7)) {
                String var11 = var7;

                String var13;
                for(int var12 = 0; var12 < 1000000; ++var12) {
                    var13 = var11 + (var12 + 1);
                    if (!var10.contains(var13)) {
                        var11 = var13;
                        break;
                    }
                }

                String var15 = var6.getPath() + "/" + var7;
                var13 = var6.getPath() + "/" + var11;
                Repository var14 = this.a(var6);
                this.a.refactorService.refactorFile(var15, var13, var14);
                var3.getWorkspace().move(var15, var13);
                var3.save();
                var8 = var13;
                var9 = "/" + var11;
            }

            var3.getWorkspace().move(var8, var9);
            var3.save();
        }
    }

    private Repository a(Node var1) throws Exception {
        Repository var2 = new Repository();
        ArrayList var3 = new ArrayList();
        var2.setProjectNames(var3);
        RepositoryFile var4 = new RepositoryFile();
        NodeIterator var5 = var1.getNodes();

        while(var5.hasNext()) {
            Node var6 = var5.nextNode();
            if (var6.hasProperty("_file")) {
                String var7 = var6.getName();
                if (var7.indexOf("___resource_authority_config__file__") <= -1 && var7.indexOf("___temp_mount_project_node_for_import__") <= -1) {
                    var3.add(var7);
                    RepositoryFile var8 = this.a.buildProjectFile(var6, (FileType[])null, false, (String)null);
                    var4.addChild(var8, false);
                }
            }
        }

        var2.setRootFile(var4);
        return var2;
    }

    private String b(Node var1) throws RepositoryException {
        String var2 = null;
        NodeIterator var3 = var1.getNodes();

        while(var3.hasNext()) {
            Node var4 = var3.nextNode();
            if (var4.hasProperty("_file")) {
                String var5 = var4.getName();
                if (var5.indexOf("___resource_authority_config__file__") <= -1) {
                    var2 = var5;
                }
            }
        }

        return var2;
    }
}
