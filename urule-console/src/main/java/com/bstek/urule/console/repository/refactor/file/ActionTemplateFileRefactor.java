package com.bstek.urule.console.repository.refactor.file;

import com.bstek.urule.console.repository.model.FileType;

public class ActionTemplateFileRefactor extends FileRefactor {
    public ActionTemplateFileRefactor() {
    }

    public String doRefactor(String var1, String var2, String var3) {
        var1 = "jcr:" + this.perfectPath(var1);
        var2 = "jcr:" + this.perfectPath(var2);
        return var3.replaceAll(var1, var2);
    }

    public boolean support(String var1) {
        return var1.toLowerCase().endsWith(FileType.ActionTemplate.toString());
    }
}