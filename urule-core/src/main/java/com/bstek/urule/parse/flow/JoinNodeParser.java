//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.flow;

import com.bstek.urule.model.flow.JoinNode;
import org.dom4j.Element;

public class JoinNodeParser extends FlowNodeParser<JoinNode> {
    public JoinNodeParser() {
    }

    public JoinNode parse(Element var1) {
        JoinNode var2 = new JoinNode(var1.attributeValue("name"));
        var2.setConnections(this.parseConnections(var1));
        var2.setEventBean(var1.attributeValue("event-bean"));
        var2.setX(var1.attributeValue("x"));
        var2.setY(var1.attributeValue("y"));
        var2.setWidth(var1.attributeValue("width"));
        var2.setHeight(var1.attributeValue("height"));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("join");
    }
}
