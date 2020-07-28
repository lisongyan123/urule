package com.bstek.urule.console.repository.scenario;

import java.util.List;

public class SimulateData {
    private String a;
    private List<DataField> b;

    public SimulateData() {
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }

    public List<DataField> getFields() {
        return this.b;
    }

    public void setFields(List<DataField> var1) {
        this.b = var1;
    }
}
