package com.bstek.urule.console.repository.refactor.field;

import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.refactor.Item;

public class DecisionTreeContentRefactor extends ContentRefactor {
    public DecisionTreeContentRefactor() {
    }

    public String doRefactor(String var1, String var2, Item var3) {
        return this.doXmlContentRefactor(var1, var2, var3);
    }

    public boolean support(String var1) {
        return var1.toLowerCase().endsWith(FileType.DecisionTree.toString());
    }
}