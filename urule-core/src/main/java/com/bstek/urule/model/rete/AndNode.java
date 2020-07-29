//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.runtime.rete.Activity;
import com.bstek.urule.runtime.rete.AndActivity;
import java.util.Iterator;
import java.util.Map;

public class AndNode extends JunctionNode {
    private NodeType nodeType;

    public AndNode() {
        super(0);
        this.nodeType = NodeType.and;
    }

    public AndNode(int var1) {
        super(var1);
        this.nodeType = NodeType.and;
    }

    public NodeType getNodeType() {
        return this.nodeType;
    }

    public void setToLineCount(int var1) {
        this.toLineCount = var1;
    }

    public Activity newActivity(Map<Object, Object> var1) {
        if (var1.containsKey(this)) {
            return (AndActivity)var1.get(this);
        } else {
            AndActivity var2 = new AndActivity();
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
