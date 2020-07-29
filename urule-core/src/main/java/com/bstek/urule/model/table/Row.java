//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.table;

public class Row implements Comparable<Row> {
    private int num;
    private int height;

    public Row() {
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int var1) {
        this.num = var1;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int var1) {
        this.height = var1;
    }

    public int compareTo(Row var1) {
        return var1.getNum() - this.num;
    }
}
