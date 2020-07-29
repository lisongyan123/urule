//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.crosstab;

public class TopColumn implements com.bstek.urule.model.crosstab.CrossColumn {
    private int columnNumber;

    public TopColumn() {
    }

    public int getColumnNumber() {
        return this.columnNumber;
    }

    public void setColumnNumber(int var1) {
        this.columnNumber = var1;
    }

    public String getType() {
        return "top";
    }
}
