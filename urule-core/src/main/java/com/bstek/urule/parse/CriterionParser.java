//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.lhs.Criterion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class CriterionParser extends AbstractParser<Criterion> implements ApplicationContextAware {
    protected Collection<CriterionParser> criterionParsers;

    public CriterionParser() {
    }

    public List<Criterion> parseCriterion(Element var1) {
        ArrayList var2 = null;
        Iterator var3 = var1.elements().iterator();

        while(true) {
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
                Iterator var7 = this.criterionParsers.iterator();

                while(var7.hasNext()) {
                    CriterionParser var8 = (CriterionParser)var7.next();
                    if (var8.support(var6)) {
                        if (var2 == null) {
                            var2 = new ArrayList();
                        }

                        Criterion var9 = (Criterion)var8.parse(var5);
                        if (var9 != null) {
                            var2.add(var9);
                        }
                        break;
                    }
                }
            }
        }
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.criterionParsers = var1.getBeansOfType(CriterionParser.class).values();
    }
}
