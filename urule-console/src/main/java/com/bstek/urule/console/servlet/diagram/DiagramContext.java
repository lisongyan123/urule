//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.diagram;

import com.bstek.urule.model.Node;
import java.util.List;
import java.util.Map;

public class DiagramContext {
    private int a;
    private List<Edge> b;
    private Map<Node, NodeInfo> c;

    public DiagramContext(List<Edge> var1, Map<Node, NodeInfo> var2) {
        this.b = var1;
        this.c = var2;
    }

    public List<Edge> getEdges() {
        return this.b;
    }

    public void addEdge(Edge var1) {
        this.b.add(var1);
    }

    public Map<Node, NodeInfo> getNodeMap() {
        return this.c;
    }

    public void setNodeMap(Map<Node, NodeInfo> var1) {
        this.c = var1;
    }

    public int nextId() {
        ++this.a;
        return this.a;
    }
}
