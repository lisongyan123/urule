//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.library.action;

import java.util.ArrayList;
import java.util.List;

public class SpringBean {
    private String id;
    private String name;
    private List<Method> methods;

    public SpringBean() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String var1) {
        this.id = var1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public List<Method> getMethods() {
        return this.methods;
    }

    public void addMethod(Method var1) {
        if (this.methods == null) {
            this.methods = new ArrayList();
        }

        this.methods.add(var1);
    }

    public void setMethods(List<Method> var1) {
        this.methods = var1;
    }
}
