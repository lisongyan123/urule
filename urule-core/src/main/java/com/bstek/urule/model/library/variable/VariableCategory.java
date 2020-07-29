//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.library.variable;

import java.util.ArrayList;
import java.util.List;

public class VariableCategory {
    public static final String PARAM_CATEGORY = "参数";
    private String name;
    private CategoryType type;
    private String clazz;
    private String file;
    private List<Variable> variables;

    public VariableCategory() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public CategoryType getType() {
        return this.type;
    }

    public void setType(CategoryType var1) {
        this.type = var1;
    }

    public String getClazz() {
        return this.clazz;
    }

    public void setClazz(String var1) {
        this.clazz = var1;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String var1) {
        this.file = var1;
    }

    public List<Variable> getVariables() {
        return this.variables;
    }

    public void setVariables(List<Variable> var1) {
        this.variables = var1;
    }

    public void addVariable(Variable var1) {
        if (this.variables == null) {
            this.variables = new ArrayList();
        }

        this.variables.add(var1);
    }
}
