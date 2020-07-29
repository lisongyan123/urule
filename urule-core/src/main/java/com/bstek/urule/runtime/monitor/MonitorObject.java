//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.monitor;

import java.util.List;

public class MonitorObject {
    private String a;
    private List<MonitorObjectField> b;

    public MonitorObject() {
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }

    public List<MonitorObjectField> getFields() {
        return this.b;
    }

    public void setFields(List<MonitorObjectField> var1) {
        this.b = var1;
    }
}
