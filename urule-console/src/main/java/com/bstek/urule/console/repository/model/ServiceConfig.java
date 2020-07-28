package com.bstek.urule.console.repository.model;

import com.bstek.urule.runtime.monitor.MonitorObject;
import java.util.List;

public class ServiceConfig {
    private boolean a = true;
    private boolean b;
    private String c;
    private String d;
    private List<MonitorObject> e;
    private List<MonitorObject> f;

    public ServiceConfig() {
    }

    public boolean isEnabled() {
        return this.a;
    }

    public void setEnabled(boolean var1) {
        this.a = var1;
    }

    public boolean isSecurity() {
        return this.b;
    }

    public void setSecurity(boolean var1) {
        this.b = var1;
    }

    public String getUsername() {
        return this.c;
    }

    public void setUsername(String var1) {
        this.c = var1;
    }

    public String getPassword() {
        return this.d;
    }

    public void setPassword(String var1) {
        this.d = var1;
    }

    public List<MonitorObject> getInputData() {
        return this.e;
    }

    public void setInputData(List<MonitorObject> var1) {
        this.e = var1;
    }

    public List<MonitorObject> getOutputData() {
        return this.f;
    }

    public void setOutputData(List<MonitorObject> var1) {
        this.f = var1;
    }
}
