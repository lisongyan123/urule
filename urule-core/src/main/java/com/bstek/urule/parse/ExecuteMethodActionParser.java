//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.action.Action;
import com.bstek.urule.action.ExecuteMethodAction;
import java.util.List;
import org.dom4j.Element;

public class ExecuteMethodActionParser extends ActionParser {
    public ExecuteMethodActionParser() {
    }

    public Action parse(Element var1) {
        ExecuteMethodAction var2 = new ExecuteMethodAction();
        String var3 = var1.attributeValue("bean");
        var2.setBeanId(var3);
        var2.setBeanLabel(var1.attributeValue("bean-label"));
        var2.setMethodLabel(var1.attributeValue("method-label"));
        var2.setMethodName(var1.attributeValue("method-name"));
        var2.setKnowledgePackage(var1.attributeValue("knowledge-package"));
        List var4 = this.parseParameters(var1, this.valueParser);
        var2.setParameters(var4);
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("execute-method");
    }
}
