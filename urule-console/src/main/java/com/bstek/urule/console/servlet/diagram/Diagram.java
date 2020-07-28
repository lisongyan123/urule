//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.diagram;

import java.util.List;

public class Diagram {
    private List<Edge> a;
    private NodeInfo b;
    private int c;
    private int d;

    public Diagram(List<Edge> var1, NodeInfo var2) {
        this.a = var1;
        this.b = var2;
    }

    public List<Edge> getEdges() {
        return this.a;
    }

    public void setEdges(List<Edge> var1) {
        this.a = var1;
    }

    public NodeInfo getRootNode() {
        return this.b;
    }

    public void setRootNode(NodeInfo var1) {
        this.b = var1;
    }

    public int getWidth() {
        return this.c;
    }

    public void setWidth(int var1) {
        this.c = var1;
    }

    public int getHeight() {
        return this.d;
    }

    public void setHeight(int var1) {
        this.d = var1;
    }
}
