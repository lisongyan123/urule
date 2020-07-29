//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.scorecard.runtime;

import java.util.ArrayList;
import java.util.List;

public class RowItemImpl implements RowItem {
    private int rowNumber;
    private Object score;
    private Object actualScore;
    private String weight;
    private List<CellItem> cellItems;

    public RowItemImpl() {
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public void setRowNumber(int var1) {
        this.rowNumber = var1;
    }

    public Object getScore() {
        return this.score;
    }

    public void setScore(Object var1) {
        this.score = var1;
    }

    public Object getActualScore() {
        return this.actualScore;
    }

    public void setActualScore(Object var1) {
        this.actualScore = var1;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String var1) {
        this.weight = var1;
    }

    public List<CellItem> getCellItems() {
        return this.cellItems;
    }

    public void setCellItems(List<CellItem> var1) {
        this.cellItems = var1;
    }

    public void addCellItem(CellItem var1) {
        if (this.cellItems == null) {
            this.cellItems = new ArrayList();
        }

        this.cellItems.add(var1);
    }
}
