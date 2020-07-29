//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.crosstab;

import com.bstek.urule.model.crosstab.CrossColumn;
import com.bstek.urule.model.crosstab.LeftColumn;
import com.bstek.urule.model.crosstab.TopColumn;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.parse.Parser;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class CrossColumnParser implements Parser<CrossColumn> {
    public CrossColumnParser() {
    }

    public CrossColumn parse(Element var1) {
        String var2 = var1.attributeValue("type");
        if (var2.equals("left")) {
            LeftColumn var5 = new LeftColumn();
            var5.setColumnNumber(Integer.valueOf(var1.attributeValue("number")));
            String var4 = var1.attributeValue("bundle-data-type");
            if (StringUtils.isNotBlank(var4)) {
                var5.setBundleDataType(var4);
                var5.setVariableCategory(var1.attributeValue("var-category"));
                var5.setVariableName(var1.attributeValue("var"));
                var5.setVariableLabel(var1.attributeValue("var-label"));
                var5.setDatatype(Datatype.valueOf(var1.attributeValue("datatype")));
                var5.setKeyLabel(var1.attributeValue("key-label"));
                var5.setKeyName(var1.attributeValue("key-name"));
            }

            return var5;
        } else {
            TopColumn var3 = new TopColumn();
            var3.setColumnNumber(Integer.valueOf(var1.attributeValue("number")));
            return var3;
        }
    }

    public boolean support(String var1) {
        return "column".equals(var1);
    }
}
