//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.template.ActionTemplate;
import com.bstek.urule.model.template.ActionTemplateUnit;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Element;

public class ActionTemplateParser implements Parser<ActionTemplate> {
    private RhsParser a;

    public ActionTemplateParser() {
    }

    public ActionTemplate parse(Element var1) {
        ActionTemplate var2 = new ActionTemplate();
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

    private ActionTemplateUnit a(Element var1) {
        ActionTemplateUnit var2 = new ActionTemplateUnit();
        var2.setId(var1.attributeValue("id"));
        var2.setName(var1.attributeValue("name"));
        var2.setActions(this.a.parseActions(var1));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("action-templates");
    }

    public void setRhsParser(RhsParser var1) {
        this.a = var1;
    }
}
