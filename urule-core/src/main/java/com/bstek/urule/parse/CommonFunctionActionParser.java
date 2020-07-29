//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.action.Action;
import com.bstek.urule.action.ExecuteCommonFunctionAction;
import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import java.util.Iterator;
import org.dom4j.Element;

public class CommonFunctionActionParser extends ActionParser {
    public CommonFunctionActionParser() {
    }

    public Action parse(Element var1) {
        ExecuteCommonFunctionAction var2 = new ExecuteCommonFunctionAction();
        var2.setLabel(var1.attributeValue("function-label"));
        var2.setName(var1.attributeValue("function-name"));
        Iterator var3 = var1.elements().iterator();

        while(true) {
            Element var5;
            do {
                Object var4;
                do {
                    if (!var3.hasNext()) {
                        return var2;
                    }

                    var4 = var3.next();
                } while(!(var4 instanceof Element));

                var5 = (Element)var4;
            } while(!var5.getName().equals("function-parameter"));

            CommonFunctionParameter var6 = new CommonFunctionParameter();
            var6.setName(var5.attributeValue("name"));
            var6.setProperty(var5.attributeValue("property-name"));
            var6.setPropertyLabel(var5.attributeValue("property-label"));
            Iterator var7 = var5.elements().iterator();

            while(var7.hasNext()) {
                Object var8 = var7.next();
                if (var8 instanceof Element) {
                    Element var9 = (Element)var8;
                    if (var9.getName().equals("value")) {
                        var6.setObjectParameter(this.valueParser.parse(var9));
                    }
                }
            }

            var2.setParameter(var6);
        }
    }

    public boolean support(String var1) {
        return var1.equals("execute-function");
    }
}
