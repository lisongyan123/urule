package com.bstek.urule.console.servlet.complexscorecard;

import java.util.List;

public class RowData {
    private List<CellData> a;

    public RowData() {
    }

    public List<CellData> getCells() {
        return this.a;
    }

    public void setCells(List<CellData> var1) {
        this.a = var1;
    }
}
