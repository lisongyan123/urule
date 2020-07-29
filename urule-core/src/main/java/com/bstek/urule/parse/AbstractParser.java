//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Parameter;
import com.bstek.urule.model.rule.Value;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Element;

public abstract class AbstractParser<T> implements Parser<T> {
    public AbstractParser() {
    }

    protected List<Parameter> parseParameters(Element var1, ValueParser var2) {
        ArrayList var3 = new ArrayList();
        Iterator var4 = var1.elements().iterator();

        while(true) {
            Element var6;
            do {
                Object var5;
                do {
                    do {
                        if (!var4.hasNext()) {
                            return var3;
                        }

                        var5 = var4.next();
                    } while(var5 == null);
                } while(!(var5 instanceof Element));

                var6 = (Element)var5;
            } while(!var6.getName().equals("parameter"));

            Parameter var7 = new Parameter();
            var7.setName(var6.attributeValue("name"));
            var7.setType(Datatype.valueOf(var6.attributeValue("type")));
            Iterator var8 = var6.elements().iterator();

            while(var8.hasNext()) {
                Object var9 = var8.next();
                if (var9 != null && var9 instanceof Element) {
                    Element var10 = (Element)var9;
                    if (var2.support(var10.getName())) {
                        Value var11 = var2.parse(var10);
                        var7.setValue(var11);
                        break;
                    }
                }
            }

            var3.add(var7);
        }
    }
}
