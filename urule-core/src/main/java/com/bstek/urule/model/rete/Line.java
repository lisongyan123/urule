//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.model.Node;
import com.bstek.urule.runtime.rete.Path;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Line {
    private int fromNodeId;
    private int toNodeId;
    @JsonIgnore
    private ReteNode from;
    @JsonIgnore
    private ReteNode to;

    public Line() {
    }

    public Line(ReteNode var1, ReteNode var2) {
        this.from = var1;
        this.to = var2;
        this.fromNodeId = var1.getId();
        this.toNodeId = var2.getId();
    }

    public void setTo(ReteNode var1) {
        this.to = var1;
    }

    public Node getFrom() {
        return this.from;
    }

    public void setFrom(ReteNode var1) {
        this.from = var1;
    }

    public Node getTo() {
        return this.to;
    }

    public Path newPath(Map<Object, Object> var1) {
        return new Path(this.to.newActivity(var1));
    }

    public int getFromNodeId() {
        return this.fromNodeId;
    }

    public void setFromNodeId(int var1) {
        this.fromNodeId = var1;
    }

    public int getToNodeId() {
        return this.toNodeId;
    }

    public void setToNodeId(int var1) {
        this.toNodeId = var1;
    }
}
