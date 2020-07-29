//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.library.variable;

import com.bstek.urule.model.library.Datatype;

public class Variable {
    private String name;
    private String label;
    private Datatype type;
    private String dataType;
    private String defaultValue;
    private Act act;

    public Variable() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String var1) {
        this.label = var1;
    }

    public Datatype getType() {
        return this.type;
    }

    public void setType(Datatype var1) {
        this.type = var1;
    }

    public String getDataType() {
        return this.dataType;
    }

    public void setDataType(String var1) {
        this.dataType = var1;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String var1) {
        this.defaultValue = var1;
    }

    public Act getAct() {
        return this.act;
    }

    public void setAct(Act var1) {
        this.act = var1;
    }
}
