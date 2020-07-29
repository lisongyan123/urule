//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.table;

import com.bstek.urule.Utils;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.table.Column;
import com.bstek.urule.model.table.ColumnType;
import com.bstek.urule.parse.Parser;
import java.math.BigDecimal;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class ColumnParser implements Parser<Column> {
    public ColumnParser() {
    }

    public Column parse(Element var1) {
        Column var2 = new Column();
        var2.setKeyName(var1.attributeValue("key-name"));
        var2.setKeyLabel(var1.attributeValue("key-label"));
        var2.setNum(Integer.valueOf(var1.attributeValue("num")));
        var2.setType(ColumnType.valueOf(var1.attributeValue("type")));
        var2.setVariableCategory(var1.attributeValue("var-category"));
        var2.setVariableLabel(var1.attributeValue("var-label"));
        var2.setVariableName(var1.attributeValue("var"));
        String var3 = var1.attributeValue("width");
        BigDecimal var4 = Utils.toBigDecimal(var3);
        var2.setWidth(var4.intValue());
        String var5 = var1.attributeValue("datatype");
        if (StringUtils.isNotEmpty(var5)) {
            var2.setDatatype(Datatype.valueOf(var5));
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("col");
    }
}
