//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.crosstab;

public abstract class CrossCell {
    private int row;
    private int col;
    private int rowspan;
    private int colspan;

    public CrossCell() {
    }

    public abstract String getType();

    public int getRow() {
        return this.row;
    }

    public void setRow(int var1) {
        this.row = var1;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int var1) {
        this.col = var1;
    }

    public int getRowspan() {
        return this.rowspan;
    }

    public void setRowspan(int var1) {
        this.rowspan = var1;
    }

    public int getColspan() {
        return this.colspan;
    }

    public void setColspan(int var1) {
        this.colspan = var1;
    }
}
