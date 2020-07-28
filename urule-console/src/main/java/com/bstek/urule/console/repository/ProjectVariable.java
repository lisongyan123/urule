
package com.bstek.urule.console.repository;

import com.bstek.urule.model.library.variable.VariableCategory;
import java.util.List;

public class ProjectVariable {
    private String a;
    private List<VariableCategory> b;

    public ProjectVariable() {
    }

    public String getPath() {
        return this.a;
    }

    public void setPath(String var1) {
        this.a = var1;
    }

    public List<VariableCategory> getVariableCategories() {
        return this.b;
    }

    public void setVariableCategories(List<VariableCategory> var1) {
        this.b = var1;
    }
}