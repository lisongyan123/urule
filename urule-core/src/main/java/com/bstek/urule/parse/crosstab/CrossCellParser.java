//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.crosstab;

import com.bstek.urule.model.crosstab.CrossCell;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public abstract class CrossCellParser {
    public CrossCellParser() {
    }

    protected void parseCrossCell(CrossCell var1, Element var2) {
        int var3 = Integer.valueOf(var2.attributeValue("row"));
        int var4 = Integer.valueOf(var2.attributeValue("col"));
        String var5 = var2.attributeValue("rowspan");
        if (StringUtils.isNotBlank(var5)) {
            int var6 = Integer.valueOf(var5);
            var1.setRowspan(var6);
        }

        String var8 = var2.attributeValue("colspan");
        if (StringUtils.isNotBlank(var8)) {
            int var7 = Integer.valueOf(var8);
            var1.setColspan(var7);
        }

        var1.setRow(var3);
        var1.setCol(var4);
    }
}
