//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.builder.RulesRebuilder;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.RuleSet;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class RuleSetParser implements Parser<RuleSet> {
    private RuleParser a;
    private LoopRuleParser b;
    private RulesRebuilder c;

    public RuleSetParser() {
    }

    public RuleSet parse(Element var1) {
        RuleSet var2 = new RuleSet();
        String var3 = var1.attributeValue("parameter-library");
        if (StringUtils.isNotEmpty(var3)) {
            var2.addLibrary(new Library(var3, (String)null, LibraryType.Parameter));
        }

        String var4 = var1.attributeValue("alone");
        if (StringUtils.isNotBlank(var4)) {
            var2.setAlone(Boolean.valueOf(var4));
        }

        String var5 = var1.attributeValue("debug");
        if (StringUtils.isNotBlank(var5)) {
            var2.setDebug(Boolean.valueOf(var5));
        }

        ArrayList var6 = new ArrayList();
        Iterator var7 = var1.elements().iterator();

        while(var7.hasNext()) {
            Object var8 = var7.next();
            if (var8 != null && var8 instanceof Element) {
                Element var9 = (Element)var8;
                String var10 = var9.getName();
                if (this.a.support(var10)) {
                    var6.add(this.a.parse(var9));
                } else if (var10.equals("quick-test-data")) {
                    String var11 = var9.getTextTrim();
                    var2.setQuickTestData(var11);
                } else if (this.b.support(var10)) {
                    var6.add(this.b.parse(var9));
                } else if (var10.equals("import-variable-library")) {
                    var2.addLibrary(new Library(var9.attributeValue("path"), (String)null, LibraryType.Variable));
                } else if (var10.equals("import-constant-library")) {
                    var2.addLibrary(new Library(var9.attributeValue("path"), (String)null, LibraryType.Constant));
                } else if (var10.equals("import-action-library")) {
                    var2.addLibrary(new Library(var9.attributeValue("path"), (String)null, LibraryType.Action));
                } else if (var10.equals("import-parameter-library")) {
                    var2.addLibrary(new Library(var9.attributeValue("path"), (String)null, LibraryType.Parameter));
                } else if (var10.equals("condition-template-library")) {
                    var2.addLibrary(new Library(var9.attributeValue("path"), (String)null, LibraryType.ConditionTemplate));
                } else if (var10.equals("action-template-library")) {
                    var2.addLibrary(new Library(var9.attributeValue("path"), (String)null, LibraryType.ActionTemplate));
                } else if (var10.equals("remark")) {
                    var2.setRemark(var9.getText());
                }
            }
        }

        if (var2.isDebug()) {
            var7 = var6.iterator();

            while(var7.hasNext()) {
                Rule var12 = (Rule)var7.next();
                if (var12.getDebug() == null) {
                    var12.setDebug(true);
                    var12.setDebugFromGlobal(true);
                }
            }
        }

        var2.setRules(var6);
        this.c.rebuildRules(var2.getLibraries(), var6);
        return var2;
    }

    public void setRulesRebuilder(RulesRebuilder var1) {
        this.c = var1;
    }

    public boolean support(String var1) {
        return var1.equals("rule-set");
    }

    public void setRuleParser(RuleParser var1) {
        this.a = var1;
    }

    public void setLoopRuleParser(LoopRuleParser var1) {
        this.b = var1;
    }
}
