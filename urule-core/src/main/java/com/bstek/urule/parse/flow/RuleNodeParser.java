//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.flow;

import com.bstek.urule.model.flow.RuleNode;
import org.dom4j.Element;

public class RuleNodeParser extends FlowNodeParser<RuleNode> {
    public RuleNodeParser() {
    }

    public RuleNode parse(Element var1) {
        RuleNode var2 = new RuleNode(var1.attributeValue("name"));
        var2.setFile(var1.attributeValue("file"));
        var2.setVersion(var1.attributeValue("version"));
        var2.setX(var1.attributeValue("x"));
        var2.setY(var1.attributeValue("y"));
        var2.setWidth(var1.attributeValue("width"));
        var2.setHeight(var1.attributeValue("height"));
        var2.setEventBean(var1.attributeValue("event-bean"));
        var2.setConnections(this.parseConnections(var1));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("rule");
    }
}
