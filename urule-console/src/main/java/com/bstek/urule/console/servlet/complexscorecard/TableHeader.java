package com.bstek.urule.console.servlet.complexscorecard;

public class TableHeader {
    private String a;

    public TableHeader() {
    }

    public boolean isScore() {
        return this.a.equals("分值");
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }
}
