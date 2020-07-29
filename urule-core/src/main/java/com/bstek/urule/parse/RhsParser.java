//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.action.Action;
import com.bstek.urule.model.rule.Rhs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RhsParser implements Parser<Rhs>, ApplicationContextAware {
    private Collection<ActionParser> a;

    public RhsParser() {
    }

    public Rhs parse(Element var1) {
        Rhs var2 = new Rhs();
        var2.setActions(this.parseActions(var1));
        return var2;
    }

    public List<Action> parseActions(Element var1) {
        ArrayList var2 = new ArrayList();
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
                Iterator var7 = this.a.iterator();

                while(var7.hasNext()) {
                    ActionParser var8 = (ActionParser)var7.next();
                    if (var8.support(var6)) {
                        var2.add(var8.parse(var5));
                        break;
                    }
                }
            }
        }
    }

    public boolean support(String var1) {
        return var1.equals("then");
    }

    public Collection<ActionParser> getActionParsers() {
        return this.a;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.a = var1.getBeansOfType(ActionParser.class).values();
    }
}
