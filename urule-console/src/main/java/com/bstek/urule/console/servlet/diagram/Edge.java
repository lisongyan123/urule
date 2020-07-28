//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.diagram;

public class Edge {
    private int a;
    private int b;

    public Edge(int var1, int var2) {
        this.a = var1;
        this.b = var2;
    }

    public int getFrom() {
        return this.a;
    }

    public void setFrom(int var1) {
        this.a = var1;
    }

    public int getTo() {
        return this.b;
    }

    public void setTo(int var1) {
        this.b = var1;
    }
}
