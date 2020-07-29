//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.flow;

import com.bstek.urule.model.flow.ActionType;
import com.bstek.urule.model.flow.ScriptNode;
import com.bstek.urule.parse.ActionParser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class ScriptNodeParser extends FlowNodeParser<ScriptNode> {
    private Collection<ActionParser> a;

    public ScriptNodeParser() {
    }

    public ScriptNode parse(Element var1) {
        ScriptNode var2 = new ScriptNode();
        var2.setName(var1.attributeValue("name"));
        var2.setEventBean(var1.attributeValue("event-bean"));
        var2.setX(var1.attributeValue("x"));
        var2.setY(var1.attributeValue("y"));
        var2.setWidth(var1.attributeValue("width"));
        var2.setHeight(var1.attributeValue("height"));
        var2.setConnections(this.parseConnections(var1));
        String var3 = var1.attributeValue("action-type");
        if (StringUtils.isBlank(var3)) {
            var3 = "script";
        }

        ActionType var4 = ActionType.valueOf(var3);
        var2.setActionType(var4);
        if (var4.equals(ActionType.script)) {
            String var5 = var1.getStringValue();
            var2.setScript(var5);
            return var2;
        } else {
            ArrayList var13 = new ArrayList();
            var2.setActionsData(var13);
            StringBuilder var6 = new StringBuilder();
            Iterator var7 = var1.elements().iterator();

            while(true) {
                Object var8;
                do {
                    do {
                        if (!var7.hasNext()) {
                            return var2;
                        }

                        var8 = var7.next();
                    } while(var8 == null);
                } while(!(var8 instanceof Element));

                Element var9 = (Element)var8;
                String var10 = var9.getName();
                Iterator var11 = this.a.iterator();

                while(var11.hasNext()) {
                    ActionParser var12 = (ActionParser)var11.next();
                    if (var12.support(var10)) {
                        var6.append(var9.asXML());
                        var13.add(var12.parse(var9));
                        break;
                    }
                }

                var2.setActionXml(var6.toString());
            }
        }
    }

    public boolean support(String var1) {
        return var1.equals("script");
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        super.setApplicationContext(var1);
        this.a = var1.getBeansOfType(ActionParser.class).values();
    }
}
