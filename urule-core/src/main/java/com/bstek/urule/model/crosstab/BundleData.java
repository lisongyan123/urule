//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.crosstab;

import com.bstek.urule.model.library.Datatype;

public abstract class BundleData {
    private String keyName;
    private String keyLabel;
    private String bundleDataType;
    private String variableCategory;
    private String variableLabel;
    private String variableName;
    private Datatype datatype;

    public BundleData() {
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

    public String getBundleDataType() {
        return this.bundleDataType;
    }

    public void setBundleDataType(String var1) {
        this.bundleDataType = var1;
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

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setDatatype(Datatype var1) {
        this.datatype = var1;
    }
}
