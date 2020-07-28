//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.test;

import com.bstek.urule.model.rete.RuleData;
import com.bstek.urule.runtime.log.FlowNodeLog;
import com.bstek.urule.runtime.log.Log;
import com.bstek.urule.runtime.log.MatchedRuleLog;
import java.util.ArrayList;
import java.util.List;

public class TestResult {
    private String a;
    private String b;
    private long c;
    private List<Log> d = new ArrayList();
    private List<MatchedRuleLog> e = new ArrayList();
    private List<RuleData> f = new ArrayList();
    private List<FlowNodeLog> g = new ArrayList();
    private String h;
    private String i;
    private List<ValueCompare> j;

    public TestResult() {
    }

    public String getScenarioId() {
        return this.a;
    }

    public void setScenarioId(String var1) {
        this.a = var1;
    }

    public String getScenarioDesc() {
        return this.b;
    }

    public void setScenarioDesc(String var1) {
        this.b = var1;
    }

    public long getConsumeTime() {
        return this.c;
    }

    public void setConsumeTime(long var1) {
        this.c = var1;
    }

    public List<ValueCompare> getValueCompares() {
        return this.j;
    }

    public void setValueCompares(List<ValueCompare> var1) {
        this.j = var1;
    }

    public List<Log> getLogs() {
        return this.d;
    }

    public void addLogs(List<Log> var1) {
        this.d.addAll(var1);
    }

    public List<MatchedRuleLog> getMatchedRuleList() {
        return this.e;
    }

    public void addMatchedRuleList(List<MatchedRuleLog> var1) {
        this.e.addAll(var1);
    }

    public List<RuleData> getNotMatchedRuleList() {
        return this.f;
    }

    public void addNotMatchedRuleList(List<RuleData> var1) {
        this.f.addAll(var1);
    }

    public List<FlowNodeLog> getFlowNodeList() {
        return this.g;
    }

    public void addFlowNodeList(List<FlowNodeLog> var1) {
        this.g.addAll(var1);
    }

    public String getInputData() {
        return this.h;
    }

    public void setInputData(String var1) {
        this.h = var1;
    }

    public String getOutputData() {
        return this.i;
    }

    public void setOutputData(String var1) {
        this.i = var1;
    }
}
