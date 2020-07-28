package com.bstek.urule.console.repository.refactor.file;

import com.bstek.urule.console.repository.model.FileType;

public class FlowFileRefactor extends FileRefactor {
    public FlowFileRefactor() {
    }

    public String doRefactor(String var1, String var2, String var3) {
        String var4 = "jcr:" + this.perfectPath(var1);
        String var5 = "jcr:" + this.perfectPath(var2);
        var3 = var3.replaceAll(var4, var5);
        String var6 = this.a(var1);
        String var7 = this.a(var2);
        if (!var6.equals(var7)) {
            String var8 = " project=\"" + var6 + "\"";
            String var9 = " project=\"" + var7 + "\"";
            var3 = var3.replaceAll(var8, var9);
        }

        return var3;
    }

    private String a(String var1) {
        if (var1.startsWith("/")) {
            var1 = var1.substring(1, var1.length());
        }

        int var2 = var1.indexOf("/");
        if (var2 > 0) {
            var1 = var1.substring(0, var2);
        }

        return var1;
    }

    public boolean support(String var1) {
        return var1.toLowerCase().endsWith(FileType.RuleFlow.toString());
    }
}
