//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.table;

import com.bstek.urule.model.table.Row;
import com.bstek.urule.parse.Parser;
import org.dom4j.Element;

public class RowParser implements Parser<Row> {
    public RowParser() {
    }

    public Row parse(Element var1) {
        Row var2 = new Row();
        var2.setHeight(Integer.valueOf(var1.attributeValue("height")));
        var2.setNum(Integer.valueOf(var1.attributeValue("num")));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("row");
    }
}
