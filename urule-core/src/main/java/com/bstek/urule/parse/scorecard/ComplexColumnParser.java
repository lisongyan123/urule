//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.scorecard;

import com.bstek.urule.model.scorecard.ComplexColumn;
import com.bstek.urule.model.scorecard.ComplexColumnType;
import com.bstek.urule.parse.Parser;
import org.dom4j.Element;

public class ComplexColumnParser implements Parser<ComplexColumn> {
    public ComplexColumnParser() {
    }

    public ComplexColumn parse(Element var1) {
        ComplexColumn var2 = new ComplexColumn();
        var2.setNum(Integer.valueOf(var1.attributeValue("num")));
        var2.setType(ComplexColumnType.valueOf(var1.attributeValue("type")));
        var2.setVariableCategory(var1.attributeValue("var-category"));
        var2.setWidth(Integer.valueOf(var1.attributeValue("width")));
        var2.setCustomLabel(var1.attributeValue("custom-label"));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("col");
    }
}
