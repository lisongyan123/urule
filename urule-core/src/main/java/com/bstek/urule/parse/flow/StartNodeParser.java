//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.flow;

import com.bstek.urule.model.flow.StartNode;
import org.dom4j.Element;

public class StartNodeParser extends FlowNodeParser<StartNode> {
    public StartNodeParser() {
    }

    public StartNode parse(Element var1) {
        StartNode var2 = new StartNode(var1.attributeValue("name"));
        var2.setConnections(this.parseConnections(var1));
        var2.setEventBean(var1.attributeValue("event-bean"));
        var2.setX(var1.attributeValue("x"));
        var2.setY(var1.attributeValue("y"));
        var2.setWidth(var1.attributeValue("width"));
        var2.setHeight(var1.attributeValue("height"));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("start");
    }
}
