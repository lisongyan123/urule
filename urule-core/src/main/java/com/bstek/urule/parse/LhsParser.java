//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Lhs;
import java.util.Collection;
import java.util.Iterator;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class LhsParser implements Parser<Lhs>, ApplicationContextAware {
    private Collection<CriterionParser> a;

    public LhsParser() {
    }

    public Lhs parse(Element var1) {
        Lhs var2 = new Lhs();
        var2.setCriterion(this.parseCriterion(var1));
        return var2;
    }

    public Criterion parseCriterion(Element var1) {
        Criterion var2 = null;
        Iterator var3 = var1.elements().iterator();

        while(true) {
            Object var4;
            do {
                do {
                    if (!var3.hasNext()) {
                        return var2;
                    }

                    var4 = var3.next();
                } while(var4 == null);
            } while(!(var4 instanceof Element));

            Element var5 = (Element)var4;
            String var6 = var5.getName();
            Iterator var7 = this.a.iterator();

            while(var7.hasNext()) {
                CriterionParser var8 = (CriterionParser)var7.next();
                if (var8.support(var6)) {
                    var2 = (Criterion)var8.parse(var5);
                    if (var2 != null) {
                        break;
                    }
                }
            }
        }
    }

    public boolean support(String var1) {
        return var1.equals("if");
    }

    public Collection<CriterionParser> getCriterionParsers() {
        return this.a;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.a = var1.getBeansOfType(CriterionParser.class).values();
    }
}
