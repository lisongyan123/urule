//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.crosstab;

import com.bstek.urule.model.crosstab.ValueCrossCell;
import com.bstek.urule.parse.Parser;
import com.bstek.urule.parse.ValueParser;
import java.util.Iterator;
import org.dom4j.Element;

public class ValueCrossCellParser extends CrossCellParser implements Parser<ValueCrossCell> {
    private ValueParser a;

    public ValueCrossCellParser() {
    }

    public ValueCrossCell parse(Element var1) {
        ValueCrossCell var2 = new ValueCrossCell();
        this.parseCrossCell(var2, var1);
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (this.a.support(var5.getName())) {
                    var2.setValue(this.a.parse(var5));
                    break;
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return "value-cell".equals(var1);
    }

    public void setValueParser(ValueParser var1) {
        this.a = var1;
    }
}
