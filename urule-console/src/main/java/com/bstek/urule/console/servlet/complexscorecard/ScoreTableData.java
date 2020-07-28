package com.bstek.urule.console.servlet.complexscorecard;

import com.bstek.urule.console.servlet.complexscorecard.RowData;

import java.util.List;

public class ScoreTableData {
    private List<TableHeader> a;
    private List<RowData> b;

    public ScoreTableData(List<TableHeader> var1, List<RowData> var2) {
        this.a = var1;
        this.b = var2;
    }

    public List<TableHeader> getHeaders() {
        return this.a;
    }

    public void setHeaders(List<TableHeader> var1) {
        this.a = var1;
    }

    public List<RowData> getRows() {
        return this.b;
    }

    public void setRows(List<RowData> var1) {
        this.b = var1;
    }
}