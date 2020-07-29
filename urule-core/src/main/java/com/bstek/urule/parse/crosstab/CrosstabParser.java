//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.crosstab;

import com.bstek.urule.Configure;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.crosstab.CrosstabDefinition;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.parse.Parser;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class CrosstabParser implements Parser<CrosstabDefinition> {
    private CrossRowParser a;
    private CrossColumnParser b;
    private HeaderCellParser c;
    private ConditionCrossCellParser d;
    private ValueCrossCellParser e;

    public CrosstabParser() {
    }

    public CrosstabDefinition parse(Element var1) {
        CrosstabDefinition var2 = new CrosstabDefinition();
        var2.setAssignTargetType(var1.attributeValue("assign-target-type"));
        var2.setAssignVariableCategory(var1.attributeValue("var-category"));
        var2.setAssignVariable(var1.attributeValue("var"));
        var2.setAssignVariableLabel(var1.attributeValue("var-label"));
        var2.setKeyLabel(var1.attributeValue("key-label"));
        var2.setKeyName(var1.attributeValue("key-name"));
        String var3 = var1.attributeValue("datatype");
        if (StringUtils.isNotBlank(var3)) {
            var2.setAssignDatatype(Datatype.valueOf(var3));
        }

        String var4 = var1.attributeValue("salience");
        if (StringUtils.isNotEmpty(var4)) {
            var2.setSalience(Integer.valueOf(var4));
        }

        String var5 = var1.attributeValue("effective-date");
        SimpleDateFormat var6 = new SimpleDateFormat(Configure.getDateFormat());
        if (StringUtils.isNotEmpty(var5)) {
            try {
                var2.setEffectiveDate(var6.parse(var5));
            } catch (ParseException var19) {
                throw new RuleException(var19);
            }
        }

        String var7 = var1.attributeValue("expires-date");
        if (StringUtils.isNotEmpty(var7)) {
            try {
                var2.setExpiresDate(var6.parse(var7));
            } catch (ParseException var18) {
                throw new RuleException(var18);
            }
        }

        String var8 = var1.attributeValue("enabled");
        if (StringUtils.isNotEmpty(var8)) {
            var2.setEnabled(Boolean.valueOf(var8));
        }

        String var9 = var1.attributeValue("debug");
        if (StringUtils.isNotEmpty(var9)) {
            var2.setDebug(Boolean.valueOf(var9));
        }

        ArrayList var10 = new ArrayList();
        ArrayList var11 = new ArrayList();
        ArrayList var12 = new ArrayList();
        var2.setCells(var10);
        var2.setRows(var11);
        var2.setColumns(var12);
        Iterator var13 = var1.elements().iterator();

        while(var13.hasNext()) {
            Object var14 = var13.next();
            if (var14 != null && var14 instanceof Element) {
                Element var15 = (Element)var14;
                String var16 = var15.getName();
                if (this.d.support(var16)) {
                    var10.add(this.d.parse(var15));
                } else if (var16.equals("quick-test-data")) {
                    String var17 = var15.getTextTrim();
                    var2.setQuickTestData(var17);
                } else if (this.e.support(var16)) {
                    var10.add(this.e.parse(var15));
                } else if (this.a.support(var16)) {
                    var11.add(this.a.parse(var15));
                } else if (this.b.support(var16)) {
                    var12.add(this.b.parse(var15));
                } else if (var16.equals("import-variable-library")) {
                    var2.addLibrary(new Library(var15.attributeValue("path"), (String)null, LibraryType.Variable));
                } else if (var16.equals("import-constant-library")) {
                    var2.addLibrary(new Library(var15.attributeValue("path"), (String)null, LibraryType.Constant));
                } else if (var16.equals("import-action-library")) {
                    var2.addLibrary(new Library(var15.attributeValue("path"), (String)null, LibraryType.Action));
                } else if (var16.equals("import-parameter-library")) {
                    var2.addLibrary(new Library(var15.attributeValue("path"), (String)null, LibraryType.Parameter));
                } else if (this.c.support(var16)) {
                    var2.setHeaderCell(this.c.parse(var15));
                } else if (var16.equals("remark")) {
                    var2.setRemark(var15.getText());
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return "crosstab".equals(var1);
    }

    public void setConditionCrossCellParser(ConditionCrossCellParser var1) {
        this.d = var1;
    }

    public void setCrossColumnParser(CrossColumnParser var1) {
        this.b = var1;
    }

    public void setCrossRowParser(CrossRowParser var1) {
        this.a = var1;
    }

    public void setHeaderCellParser(HeaderCellParser var1) {
        this.c = var1;
    }

    public void setValueCrossCellParser(ValueCrossCellParser var1) {
        this.e = var1;
    }
}
