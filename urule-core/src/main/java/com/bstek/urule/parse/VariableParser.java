//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.variable.Act;
import com.bstek.urule.model.library.variable.Variable;
import org.dom4j.Element;

public class VariableParser implements Parser<Variable> {
    public VariableParser() {
    }

    public Variable parse(Element var1) {
        Variable var2 = new Variable();
        var2.setName(var1.attributeValue("name"));
        var2.setLabel(var1.attributeValue("label"));
        var2.setDefaultValue(var1.attributeValue("default-value"));
        String var3 = var1.attributeValue("type");
        var2.setType(Datatype.parse(var3));
        var2.setDataType(var3);
        var2.setAct(Act.valueOf(var1.attributeValue("act")));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("var");
    }
}
