//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.scorecard;

import com.bstek.urule.model.scorecard.CustomCol;
import com.bstek.urule.parse.Parser;
import org.dom4j.Element;

public class CustomColParser implements Parser<CustomCol> {
    public CustomColParser() {
    }

    public CustomCol parse(Element var1) {
        CustomCol var2 = new CustomCol();
        var2.setColNumber(Integer.parseInt(var1.attributeValue("col-number")));
        var2.setName(var1.attributeValue("name"));
        var2.setWidth(var1.attributeValue("width"));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("custom-col");
    }
}
