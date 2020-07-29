//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.crosstab;

import com.bstek.urule.model.crosstab.HeaderCell;
import com.bstek.urule.parse.Parser;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class HeaderCellParser implements Parser<HeaderCell> {
    public HeaderCellParser() {
    }

    public HeaderCell parse(Element var1) {
        HeaderCell var2 = new HeaderCell();
        String var3 = var1.attributeValue("rowspan");
        if (StringUtils.isNotBlank(var3)) {
            int var4 = Integer.valueOf(var3);
            var2.setRowspan(var4);
        }

        String var7 = var1.attributeValue("colspan");
        if (StringUtils.isNotBlank(var7)) {
            int var5 = Integer.valueOf(var7);
            var2.setColspan(var5);
        }

        String var6 = var1.getText();
        var2.setText(var6);
        return var2;
    }

    public boolean support(String var1) {
        return "header".equals(var1);
    }
}
