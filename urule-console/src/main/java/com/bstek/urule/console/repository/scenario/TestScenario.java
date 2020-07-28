package com.bstek.urule.console.repository.scenario;

import java.util.Date;
import java.util.List;

public class TestScenario {
    private String a;
    private String b;
    private String c;
    private Date d;
    private String e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private List<SimulateData> o;
    private List<SimulateData> p;

    public TestScenario() {
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }

    public String getDesc() {
        return this.b;
    }

    public void setDesc(String var1) {
        this.b = var1;
    }

    public String getExcelId() {
        return this.c;
    }

    public void setExcelId(String var1) {
        this.c = var1;
    }

    public Date getCreateDate() {
        return this.d;
    }

    public void setCreateDate(Date var1) {
        this.d = var1;
    }

    public String getCreateUser() {
        return this.e;
    }

    public void setCreateUser(String var1) {
        this.e = var1;
    }

    public boolean isShowInputData() {
        return this.f;
    }

    public void setShowInputData(boolean var1) {
        this.f = var1;
    }

    public boolean isShowOutputData() {
        return this.g;
    }

    public void setShowOutputData(boolean var1) {
        this.g = var1;
    }

    public boolean isShowLog() {
        return this.h;
    }

    public void setShowLog(boolean var1) {
        this.h = var1;
    }

    public boolean isShowMatchedRuleList() {
        return this.i;
    }

    public void setShowMatchedRuleList(boolean var1) {
        this.i = var1;
    }

    public boolean isShowMatchedRuleSize() {
        return this.j;
    }

    public void setShowMatchedRuleSize(boolean var1) {
        this.j = var1;
    }

    public boolean isShowNotMatchRuleList() {
        return this.k;
    }

    public void setShowNotMatchRuleList(boolean var1) {
        this.k = var1;
    }

    public boolean isShowNotMatchRuleSize() {
        return this.l;
    }

    public void setShowNotMatchRuleSize(boolean var1) {
        this.l = var1;
    }

    public boolean isShowFiredFlowNodeList() {
        return this.m;
    }

    public void setShowFiredFlowNodeList(boolean var1) {
        this.m = var1;
    }

    public boolean isShowFiredFlowNodeSize() {
        return this.n;
    }

    public void setShowFiredFlowNodeSize(boolean var1) {
        this.n = var1;
    }

    public List<SimulateData> getInputData() {
        return this.o;
    }

    public void setInputData(List<SimulateData> var1) {
        this.o = var1;
    }

    public List<SimulateData> getOutputData() {
        return this.p;
    }

    public void setOutputData(List<SimulateData> var1) {
        this.p = var1;
    }
}
