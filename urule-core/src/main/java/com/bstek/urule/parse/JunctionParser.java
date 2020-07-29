//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.lhs.And;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Or;
import java.util.List;
import org.dom4j.Element;

public class JunctionParser extends CriterionParser {
    public JunctionParser() {
    }

    public Criterion parse(Element var1) {
        List var2 = this.parseCriterion(var1);
        if (var2 != null && var2.size() != 0) {
            String var3 = var1.getName();
            if (var3.equals("and")) {
                And var5 = new And();
                var5.setCriterions(var2);
                return var5;
            } else {
                Or var4 = new Or();
                var4.setCriterions(var2);
                return var4;
            }
        } else {
            return null;
        }
    }

    public boolean support(String var1) {
        return var1.equals("and") || var1.equals("or");
    }
}
