//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.flow;

import com.bstek.urule.model.flow.Connection;
import com.bstek.urule.parse.CriterionParser;
import com.bstek.urule.parse.Parser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class FlowNodeParser<T> implements Parser<T>, ApplicationContextAware {
    protected Collection<CriterionParser> criterionParsers;

    public FlowNodeParser() {
    }

    protected List<Connection> parseConnections(Element var1) {
        ArrayList var2 = new ArrayList();
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (var5.getName().equals("connection")) {
                    var2.add(this.a(var5));
                }
            }
        }

        return var2;
    }

    private Connection a(Element var1) {
        Connection var2 = new Connection();
        var2.setName(var1.attributeValue("name"));
        var2.setToName(var1.attributeValue("to"));
        var2.setG(var1.attributeValue("g"));
        String var3 = var1.getStringValue();
        if (StringUtils.isNotEmpty(var3)) {
            var2.setScript(var3);
        }

        return var2;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.criterionParsers = var1.getBeansOfType(CriterionParser.class).values();
    }
}
