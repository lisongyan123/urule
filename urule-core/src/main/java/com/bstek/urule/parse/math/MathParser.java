//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.math;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.parse.Parser;
import com.bstek.urule.parse.ValueParser;
import java.util.Iterator;
import org.dom4j.Element;

public abstract class MathParser implements Parser<MathSign> {
    protected ValueParser valueParser;

    public MathParser(ValueParser var1) {
        this.valueParser = var1;
    }

    protected Value parseValue(Element var1) {
        Iterator var2 = var1.elements().iterator();

        while(var2.hasNext()) {
            Object var3 = var2.next();
            if (var3 != null && var3 instanceof Element) {
                Element var4 = (Element)var3;
                if (this.valueParser.support(var4.getName())) {
                    return this.valueParser.parse(var4);
                }
            }
        }

        throw new RuleException("Unknow value element[" + var1.asXML() + "]");
    }
}
