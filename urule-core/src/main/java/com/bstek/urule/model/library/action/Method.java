//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.library.action;

import java.util.ArrayList;
import java.util.List;

public class Method {
    private String name;
    private String methodName;
    private List<Parameter> parameters;

    public Method() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String var1) {
        this.methodName = var1;
    }

    public void addParameter(Parameter var1) {
        if (this.parameters == null) {
            this.parameters = new ArrayList();
        }

        this.parameters.add(var1);
    }

    public void setParameters(List<Parameter> var1) {
        this.parameters = var1;
    }
}
