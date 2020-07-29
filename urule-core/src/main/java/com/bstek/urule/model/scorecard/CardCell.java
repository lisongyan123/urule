//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.scorecard;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.table.Joint;

public class CardCell implements Comparable<CardCell> {
    private String variableLabel;
    private String variableName;
    private String keyLabel;
    private String keyName;
    private Datatype datatype;
    private CellType type;
    private String weight;
    private Joint joint;
    private Value value;
    private int row;
    private int col;

    public CardCell() {
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

    public String getKeyLabel() {
        return this.keyLabel;
    }

    public void setKeyLabel(String var1) {
        this.keyLabel = var1;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public void setKeyName(String var1) {
        this.keyName = var1;
    }

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setDatatype(Datatype var1) {
        this.datatype = var1;
    }

    public CellType getType() {
        return this.type;
    }

    public void setType(CellType var1) {
        this.type = var1;
    }

    public String getWeight() {
        return this.weight;
    }

    public void setWeight(String var1) {
        this.weight = var1;
    }

    public Joint getJoint() {
        return this.joint;
    }

    public void setJoint(Joint var1) {
        this.joint = var1;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
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

    public int compareTo(CardCell var1) {
        return this.row - var1.getRow();
    }
}
