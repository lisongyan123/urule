//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.flow;

import com.bstek.urule.model.flow.EndNode;
import org.dom4j.Element;

public class EndNodeParser extends FlowNodeParser<EndNode> {
    public EndNodeParser() {
    }

    public EndNode parse(Element var1) {
        EndNode var2 = new EndNode(var1.attributeValue("name"));
        var2.setConnections(this.parseConnections(var1));
        var2.setEventBean(var1.attributeValue("event-bean"));
        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("end");
    }
}
