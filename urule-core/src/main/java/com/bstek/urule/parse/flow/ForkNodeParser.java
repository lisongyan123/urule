//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.flow;

import com.bstek.urule.model.flow.ForkNode;
import org.dom4j.Element;

public class ForkNodeParser extends FlowNodeParser<ForkNode> {
    public ForkNodeParser() {
    }

    public ForkNode parse(Element var1) {
        ForkNode var2 = new ForkNode(var1.attributeValue("name"));
        var2.setMultipleThread(var1.attributeValue("multiple-thread"));
        var2.setConnections(this.parseConnections(var1));
        var2.setEventBean(var1.attributeValue("event-bean"));
        var2.setX(var1.attributeValue("x"));
        var2.setY(var1.attributeValue("y"));
        var2.setWidth(var1.attributeValue("width"));
        var2.setHeight(var1.attributeValue("height"));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("fork");
    }
}
