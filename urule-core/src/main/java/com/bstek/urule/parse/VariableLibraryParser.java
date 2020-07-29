//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.library.variable.VariableCategory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Element;

public class VariableLibraryParser implements Parser<List<VariableCategory>> {
    private VariableCategoryParser a;

    public VariableLibraryParser() {
    }

    public List<VariableCategory> parse(Element var1) {
        ArrayList var2 = new ArrayList();
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                String var6 = var5.getName();
                if (this.a.support(var6)) {
                    var2.add(this.a.parse(var5));
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("variable-library");
    }

    public void setVariableCategoryParser(VariableCategoryParser var1) {
        this.a = var1;
    }
}
