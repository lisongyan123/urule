//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.diagram;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

public class NodeInfo {
    private int a;
    @JsonIgnore
    private int b;
    private String c;
    private String d;
    private String e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private List<NodeInfo> k;

    public NodeInfo() {
    }

    public int getId() {
        return this.a;
    }

    public void setId(int var1) {
        this.a = var1;
    }

    public int getLevel() {
        return this.b;
    }

    public void setLevel(int var1) {
        this.b = var1;
    }

    public String getLabel() {
        return this.c;
    }

    public void setLabel(String var1) {
        this.c = var1;
    }

    public String getColor() {
        return this.e;
    }

    public void setColor(String var1) {
        this.e = var1;
    }

    public String getTitle() {
        return this.d;
    }

    public void setTitle(String var1) {
        this.d = var1;
    }

    public int getX() {
        return this.f;
    }

    public void setX(int var1) {
        this.f = var1;
    }

    public int getY() {
        return this.g;
    }

    public void setY(int var1) {
        this.g = var1;
    }

    public int getWidth() {
        return this.h;
    }

    public void setWidth(int var1) {
        this.h = var1;
    }

    public int getHeight() {
        return this.i;
    }

    public void setHeight(int var1) {
        this.i = var1;
    }

    public int getRoundCorner() {
        return this.j;
    }

    public void setRoundCorner(int var1) {
        this.j = var1;
    }

    public List<NodeInfo> getChildren() {
        return this.k;
    }

    public void addChild(NodeInfo var1) {
        if (this.k == null) {
            this.k = new ArrayList();
        }

        this.k.add(var1);
    }
}
