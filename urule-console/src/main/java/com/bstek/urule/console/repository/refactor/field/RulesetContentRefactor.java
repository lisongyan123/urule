package com.bstek.urule.console.repository.refactor.field;

import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.refactor.Item;

public class RulesetContentRefactor extends ContentRefactor {
    public RulesetContentRefactor() {
    }

    public String doRefactor(String var1, String var2, Item var3) {
        return this.doXmlContentRefactor(var1, var2, var3);
    }

    public boolean support(String var1) {
        var1 = var1.toLowerCase();
        return var1.endsWith(FileType.Ruleset.toString()) || var1.indexOf("__rules_templates__") > 0;
    }
}