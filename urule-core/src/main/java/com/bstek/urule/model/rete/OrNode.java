//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.runtime.rete.Activity;
import com.bstek.urule.runtime.rete.OrActivity;
import java.util.Iterator;
import java.util.Map;

public class OrNode extends JunctionNode {
    private NodeType nodeType;

    public OrNode() {
        super(0);
        this.nodeType = NodeType.or;
    }

    public OrNode(int var1) {
        super(var1);
        this.nodeType = NodeType.or;
    }

    public NodeType getNodeType() {
        return this.nodeType;
    }

    public Activity newActivity(Map<Object, Object> var1) {
        if (var1.containsKey(this)) {
            return (OrActivity)var1.get(this);
        } else {
            OrActivity var2 = new OrActivity();
            Iterator var3 = this.lines.iterator();

            while(var3.hasNext()) {
                Line var4 = (Line)var3.next();
                var2.addPath(var4.newPath(var1));
            }

            var1.put(this, var2);
            return var2;
        }
    }
}
