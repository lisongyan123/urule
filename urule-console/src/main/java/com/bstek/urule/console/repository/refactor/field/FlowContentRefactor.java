package com.bstek.urule.console.repository.refactor.field;

import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.refactor.Item;

public class FlowContentRefactor extends ContentRefactor {
    public FlowContentRefactor() {
    }

    public String doRefactor(String var1, String var2, Item var3) {
        String var4 = this.doScriptContentRefactor(var1, var2, var3);
        if (var4 == null) {
            var4 = var2;
        }

        return this.doXmlContentRefactor(var1, var4, var3);
    }

    public boolean support(String var1) {
        return var1.toLowerCase().endsWith(FileType.RuleFlow.toString());
    }
}