//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.table;

import com.bstek.urule.action.Action;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Value;

public class Cell {
    private int row;
    private int col;
    private int rowspan;
    private String keyName;
    private String keyLabel;
    private String variableLabel;
    private String variableName;
    private Datatype datatype;
    private Joint joint;
    private Value value;
    private Action action;

    public Cell() {
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

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setDatatype(Datatype var1) {
        this.datatype = var1;
    }

    public Joint getJoint() {
        return this.joint;
    }

    public void setJoint(Joint var1) {
        this.joint = var1;
    }

    public Action getAction() {
        return this.action;
    }

    public void setAction(Action var1) {
        this.action = var1;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }
}
