package com.bstek.urule.console.servlet.crosstab;

import com.bstek.urule.console.servlet.CellContent;
import java.util.List;

public class CrossData {
    private CrossHeader a;
    private List<CrossRow> b;
    private List<CrossColumn> c;
    private List<CellContent> d;

    public CrossData() {
    }

    public CrossHeader getHeader() {
        return this.a;
    }

    public void setHeader(CrossHeader var1) {
        this.a = var1;
    }

    public List<CrossRow> getRows() {
        return this.b;
    }

    public void setRows(List<CrossRow> var1) {
        this.b = var1;
    }

    public List<CrossColumn> getColumns() {
        return this.c;
    }

    public void setColumns(List<CrossColumn> var1) {
        this.c = var1;
    }

    public List<CellContent> getCells() {
        return this.d;
    }

    public void setCells(List<CellContent> var1) {
        this.d = var1;
    }
}
