//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.model.library.Datatype;
import org.codehaus.jackson.annotate.JsonIgnore;

public class VariableLeftPart implements LeftPart {
    @JsonIgnore
    private String id;
    private String keyName;
    private String keyLabel;
    private String variableName;
    private String variableLabel;
    private String variableCategory;
    private Datatype datatype;

    public VariableLeftPart() {
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

    public String getVariableName() {
        return this.variableName;
    }

    public void setVariableName(String var1) {
        this.variableName = var1;
    }

    public String getVariableLabel() {
        return this.variableLabel;
    }

    public void setVariableLabel(String var1) {
        this.variableLabel = var1;
    }

    public String getVariableCategory() {
        return this.variableCategory;
    }

    public void setVariableCategory(String var1) {
        this.variableCategory = var1;
    }

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setDatatype(Datatype var1) {
        this.datatype = var1;
    }

    public String getId() {
        if (this.id == null) {
            this.id = "[变量]" + this.getVariableCategory();
            if (this.keyLabel != null) {
                this.id = this.id + "." + this.keyLabel + "." + this.getVariableLabel();
            } else {
                this.id = this.id + "." + this.getVariableLabel();
            }
        }

        return this.id;
    }
}
