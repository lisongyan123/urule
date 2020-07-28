package com.bstek.urule.console.servlet.decisiontable;

import java.util.List;

public class TableData {
    private List<Header> a;
    private List<ContentRow> b;

    public TableData(List<Header> var1, List<ContentRow> var2) {
        this.a = var1;
        this.b = var2;
    }

    public List<Header> getHeaders() {
        return this.a;
    }

    public List<ContentRow> getRows() {
        return this.b;
    }
}