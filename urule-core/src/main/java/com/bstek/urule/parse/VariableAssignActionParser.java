//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.action.Action;
import com.bstek.urule.action.VariableAssignAction;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.lhs.LeftType;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class VariableAssignActionParser extends ActionParser {
    public VariableAssignActionParser() {
    }

    public Action parse(Element var1) {
        VariableAssignAction var2 = new VariableAssignAction();
        String var3 = var1.attributeValue("var");
        if (StringUtils.isEmpty(var3)) {
            var3 = var1.attributeValue("property-name");
        }

        var2.setVariableName(var3);
        String var4 = var1.attributeValue("var-label");
        if (StringUtils.isEmpty(var4)) {
            var4 = var1.attributeValue("property-label");
        }

        var2.setVariableLabel(var4);
        String var5 = var1.attributeValue("var-category");
        var2.setVariableCategory(var5);
        String var6 = var1.attributeValue("datatype");
        if (StringUtils.isNotEmpty(var6)) {
            var2.setDatatype(Datatype.valueOf(var6));
        }

        String var7 = var1.attributeValue("type");
        if (StringUtils.isNotEmpty(var7)) {
            var2.setType(LeftType.valueOf(var7));
        }

        var2.setKeyLabel(var1.attributeValue("key-label"));
        var2.setKeyName(var1.attributeValue("key-name"));
        Iterator var8 = var1.elements().iterator();

        while(var8.hasNext()) {
            Object var9 = var8.next();
            if (var9 != null && var9 instanceof Element) {
                Element var10 = (Element)var9;
                if (this.valueParser.support(var10.getName())) {
                    var2.setValue(this.valueParser.parse(var10));
                    break;
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("var-assign");
    }
}
