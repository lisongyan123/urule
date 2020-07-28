//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;
import com.bstek.urule.console.servlet.decisiontable.Header;
public class CellContent {
    private int a;
    private int b;
    private int c;
    private Header d;
    private String e;
    private String f = "value";

    public CellContent() {
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

    public String getContent() {
        return this.e;
    }

    public void setContent(String var1) {
        this.e = var1;
    }

    public Header getHeader() {
        return this.d;
    }

    public void setHeader(Header var1) {
        this.d = var1;
    }

    public String getType() {
        return this.f;
    }

    public void setType(String var1) {
        this.f = var1;
    }
}
