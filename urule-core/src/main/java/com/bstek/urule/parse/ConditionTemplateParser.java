//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.template.ConditionTemplate;
import com.bstek.urule.model.template.ConditionTemplateUnit;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Element;

public class ConditionTemplateParser implements Parser<ConditionTemplate> {
    private LhsParser a;

    public ConditionTemplateParser() {
    }

    public ConditionTemplate parse(Element var1) {
        ConditionTemplate var2 = new ConditionTemplate();
        ArrayList var3 = new ArrayList();
        var2.setTemplates(var3);
        Iterator var4 = var1.elements().iterator();

        while(var4.hasNext()) {
            Object var5 = var4.next();
            if (var5 != null && var5 instanceof Element) {
                Element var6 = (Element)var5;
                String var7 = var6.getName();
                if (var7.equals("import-variable-library")) {
                    var2.addLibrary(new Library(var6.attributeValue("path"), (String)null, LibraryType.Variable));
                } else if (var7.equals("import-constant-library")) {
                    var2.addLibrary(new Library(var6.attributeValue("path"), (String)null, LibraryType.Constant));
                } else if (var7.equals("import-action-library")) {
                    var2.addLibrary(new Library(var6.attributeValue("path"), (String)null, LibraryType.Action));
                } else if (var7.equals("import-parameter-library")) {
                    var2.addLibrary(new Library(var6.attributeValue("path"), (String)null, LibraryType.Parameter));
                } else if (var7.equals("template")) {
                    var3.add(this.a(var6));
                }
            }
        }

        return var2;
    }

    private ConditionTemplateUnit a(Element var1) {
        ConditionTemplateUnit var2 = new ConditionTemplateUnit();
        var2.setId(var1.attributeValue("id"));
        var2.setName(var1.attributeValue("name"));
        var2.setCriterion(this.a.parseCriterion(var1));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("templates");
    }

    public void setLhsParser(LhsParser var1) {
        this.a = var1;
    }
}
