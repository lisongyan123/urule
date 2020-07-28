package com.bstek.urule.console.servlet.complexscorecard;

public class CellData {
    private int a;
    private int b;
    private int c;
    private String d;
    private TableHeader e;
    private String f;

    public CellData() {
    }

    public int getSpan() {
        return this.a;
    }

    public void setSpan(int var1) {
        this.a = var1;
    }

    public int getRow() {
        return this.b;
    }

    public void setRow(int var1) {
        this.b = var1;
    }

    public int getCol() {
        return this.c;
    }

    public void setCol(int var1) {
        this.c = var1;
    }

    public String getProperty() {
        return this.d;
    }

    public void setProperty(String var1) {
        this.d = var1;
    }

    public TableHeader getHeader() {
        return this.e;
    }

    public void setHeader(TableHeader var1) {
        this.e = var1;
    }

    public String getContent() {
        return this.f;
    }

    public void setContent(String var1) {
        this.f = var1;
    }
}
