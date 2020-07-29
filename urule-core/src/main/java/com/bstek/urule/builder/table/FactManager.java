//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.table;

import com.bstek.urule.model.crosstab.CrossCell;
import com.bstek.urule.model.crosstab.ValueCrossCell;
import com.bstek.urule.model.library.Datatype;
import java.util.ArrayList;
import java.util.List;

class CellRange {
    private int a;
    private int b;
    private boolean c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private Datatype i;
    private CellRange j;
    private CrossCell k;
    private List<CellRange> l = new ArrayList();

    CellRange() {
    }

    public String getKeyName() {
        return this.d;
    }

    public void setKeyName(String var1) {
        this.d = var1;
    }

    public String getKeyLabel() {
        return this.e;
    }

    public void setKeyLabel(String var1) {
        this.e = var1;
    }

    public int getStart() {
        return this.a;
    }

    public void setStart(int var1) {
        this.a = var1;
    }

    public int getEnd() {
        return this.b;
    }

    public void setEnd(int var1) {
        this.b = var1;
    }

    public String getVariableCategory() {
        return this.f;
    }

    public void setVariableCategory(String var1) {
        this.f = var1;
    }

    public String getVariableName() {
        return this.g;
    }

    public void setVariableName(String var1) {
        this.g = var1;
    }

    public String getVariableLabel() {
        return this.h;
    }

    public void setVariableLabel(String var1) {
        this.h = var1;
    }

    public Datatype getDatatype() {
        return this.i;
    }

    public void setDatatype(Datatype var1) {
        this.i = var1;
    }

    public boolean isValueCell() {
        return this.c;
    }

    public void setValueCell(boolean var1) {
        this.c = var1;
    }

    public CellRange getParentRange() {
        return this.j;
    }

    public void setParentRange(CellRange var1) {
        this.j = var1;
    }

    public CrossCell getCell() {
        return this.k;
    }

    public void setCell(CrossCell var1) {
        if (var1 instanceof ValueCrossCell) {
            this.setValueCell(true);
        }

        this.k = var1;
    }

    public List<CellRange> getChildren() {
        return this.l;
    }

    public void addChildRange(CellRange var1) {
        var1.setParentRange(this);
        this.l.add(var1);
    }
}
