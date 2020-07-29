//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.library.variable.CategoryType;
import com.bstek.urule.model.library.variable.VariableCategory;
import java.util.Iterator;
import org.dom4j.Element;

public class VariableCategoryParser implements Parser<VariableCategory> {
    private VariableParser a;

    public VariableCategoryParser() {
    }

    public VariableCategory parse(Element var1) {
        VariableCategory var2 = new VariableCategory();
        var2.setName(var1.attributeValue("name"));
        var2.setClazz(var1.attributeValue("clazz"));
        var2.setType(CategoryType.valueOf(var1.attributeValue("type")));
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                String var6 = var5.getName();
                if (this.a.support(var6)) {
                    var2.addVariable(this.a.parse(var5));
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("category");
    }

    public void setVariableParser(VariableParser var1) {
        this.a = var1;
    }
}
