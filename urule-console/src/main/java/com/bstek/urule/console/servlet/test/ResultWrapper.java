//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.test;

import java.util.List;

public class ResultWrapper {
    private String a;
    private String b;
    private String c;
    private long d;
    private long e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private List<TestResult> o;

    public ResultWrapper() {
    }

    public String getProject() {
        return this.a;
    }

    public void setProject(String var1) {
        this.a = var1;
    }

    public String getScenarioName() {
        return this.b;
    }

    public void setScenarioName(String var1) {
        this.b = var1;
    }

    public String getPackageName() {
        return this.c;
    }

    public void setPackageName(String var1) {
        this.c = var1;
    }

    public long getTotalTime() {
        return this.d;
    }

    public void setTotalTime(long var1) {
        this.d = var1;
    }

    public long getPrepareTime() {
        return this.e;
    }

    public void setPrepareTime(long var1) {
        this.e = var1;
    }

    public List<TestResult> getResultList() {
        return this.o;
    }

    public void setResultList(List<TestResult> var1) {
        this.o = var1;
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
}
