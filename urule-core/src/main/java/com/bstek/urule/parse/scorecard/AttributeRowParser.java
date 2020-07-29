//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.scorecard;

import com.bstek.urule.model.scorecard.AttributeRow;
import com.bstek.urule.model.scorecard.ConditionRow;
import com.bstek.urule.parse.Parser;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Element;

public class AttributeRowParser implements Parser<AttributeRow> {
    public AttributeRowParser() {
    }

    public AttributeRow parse(Element var1) {
        AttributeRow var2 = new AttributeRow();
        var2.setRowNumber(Integer.valueOf(var1.attributeValue("row-number")));
        ArrayList var3 = new ArrayList();
        Iterator var4 = var1.elements().iterator();

        while(var4.hasNext()) {
            Object var5 = var4.next();
            if (var5 != null && var5 instanceof Element) {
                Element var6 = (Element)var5;
                if (var6.getName().equals("condition-row")) {
                    ConditionRow var7 = new ConditionRow();
                    var7.setRowNumber(Integer.valueOf(var6.attributeValue("row-number")));
                    var3.add(var7);
                }
            }
        }

        var2.setConditionRows(var3);
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("attribute-row");
    }
}
