//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.table;

import com.bstek.urule.model.library.Datatype;

public class Column implements Comparable<Column> {
    private int num;
    private int width;
    private String keyName;
    private String keyLabel;
    private String variableCategory;
    private String variableLabel;
    private String variableName;
    private Datatype datatype;
    private ColumnType type;

    public Column() {
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int var1) {
        this.num = var1;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int var1) {
        this.width = var1;
    }

    public String getVariableCategory() {
        return this.variableCategory;
    }

    public void setVariableCategory(String var1) {
        this.variableCategory = var1;
    }

    public String getVariableLabel() {
        return this.variableLabel;
    }

    public void setVariableLabel(String var1) {
        this.variableLabel = var1;
    }

    public String getVariableName() {
        return this.variableName;
    }

    public void setVariableName(String var1) {
        this.variableName = var1;
    }

    public ColumnType getType() {
        return this.type;
    }

    public void setType(ColumnType var1) {
        this.type = var1;
    }

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setDatatype(Datatype var1) {
        this.datatype = var1;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public void setKeyName(String var1) {
        this.keyName = var1;
    }

    public String getKeyLabel() {
        return this.keyLabel;
    }

    public void setKeyLabel(String var1) {
        this.keyLabel = var1;
    }

    public int compareTo(Column var1) {
        return var1.getNum() - this.num;
    }
}
