//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.flow;

import com.bstek.urule.model.flow.ActionNode;
import org.dom4j.Element;

public class ActionNodeParser extends FlowNodeParser<ActionNode> {
    public ActionNodeParser() {
    }

    public ActionNode parse(Element var1) {
        ActionNode var2 = new ActionNode(var1.attributeValue("name"));
        var2.setActionBean(var1.attributeValue("action-bean"));
        var2.setEventBean(var1.attributeValue("event-bean"));
        var2.setX(var1.attributeValue("x"));
        var2.setY(var1.attributeValue("y"));
        var2.setWidth(var1.attributeValue("width"));
        var2.setHeight(var1.attributeValue("height"));
        var2.setConnections(this.parseConnections(var1));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("action");
    }
}
