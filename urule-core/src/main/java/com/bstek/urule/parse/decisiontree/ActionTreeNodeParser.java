//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.decisiontree;

import com.bstek.urule.model.decisiontree.ActionTreeNode;
import com.bstek.urule.model.decisiontree.TreeNodeType;
import com.bstek.urule.parse.ActionParser;
import com.bstek.urule.parse.Parser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ActionTreeNodeParser implements Parser<ActionTreeNode>, ApplicationContextAware {
    private Collection<ActionParser> a;

    public ActionTreeNodeParser() {
    }

    public ActionTreeNode parse(Element var1) {
        ActionTreeNode var2 = new ActionTreeNode();
        var2.setNodeType(TreeNodeType.action);
        ArrayList var3 = new ArrayList();
        Iterator var4 = var1.elements().iterator();

        while(true) {
            while(true) {
                Object var5;
                do {
                    do {
                        if (!var4.hasNext()) {
                            var2.setActions(var3);
                            return var2;
                        }

                        var5 = var4.next();
                    } while(var5 == null);
                } while(!(var5 instanceof Element));

                Element var6 = (Element)var5;
                String var7 = var6.getName();
                Iterator var8 = this.a.iterator();

                while(var8.hasNext()) {
                    ActionParser var9 = (ActionParser)var8.next();
                    if (var9.support(var7)) {
                        var3.add(var9.parse(var6));
                        break;
                    }
                }
            }
        }
    }

    public boolean support(String var1) {
        return var1.equals("action-tree-node");
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.a = var1.getBeansOfType(ActionParser.class).values();
    }
}
