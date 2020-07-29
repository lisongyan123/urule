//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.model.Node;
import com.bstek.urule.runtime.rete.Activity;
import java.util.Map;

public abstract class ReteNode implements Node {
    private int id;

    public ReteNode(int var1) {
        this.id = var1;
    }

    public abstract NodeType getNodeType();

    public abstract Activity newActivity(Map<Object, Object> var1);

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }
}
