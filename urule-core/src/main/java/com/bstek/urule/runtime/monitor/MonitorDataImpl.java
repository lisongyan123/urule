//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.monitor;

import com.bstek.urule.model.rete.RuleData;
import com.bstek.urule.runtime.log.FlowNodeLog;
import com.bstek.urule.runtime.log.Log;
import com.bstek.urule.runtime.log.MatchedRuleLog;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MonitorDataImpl implements MonitorData {
    private String a;
    private long b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private String g;
    private String h;
    private Date i;
    private String j;
    private List<MatchedRuleLog> k = new ArrayList();
    private List<RuleData> l = new ArrayList();
    private List<FlowNodeLog> m = new ArrayList();
    private List<Log> n = new ArrayList();
    private List<IOData> o;
    private List<IOData> p;

    public MonitorDataImpl() {
    }

    public long getTotalDuration() {
        return this.b;
    }

    public void setTotalDuration(long var1) {
        this.b = var1;
    }

    public List<MatchedRuleLog> getMatchedRuleList() {
        return this.k;
    }

    public void addMatchedRuleList(List<MatchedRuleLog> var1) {
        this.k.addAll(var1);
    }

    public List<RuleData> getNotMatchRuleList() {
        return this.l;
    }

    public void addNotMatchRuleList(List<RuleData> var1) {
        this.l.addAll(var1);
    }

    public List<FlowNodeLog> getFiredFlowNodeList() {
        return this.m;
    }

    public void addFiredFlowNodeList(List<FlowNodeLog> var1) {
        this.m.addAll(var1);
    }

    public String getPackageInfo() {
        return this.a;
    }

    public void setPackageInfo(String var1) {
        this.a = var1;
    }

    public boolean isShowLog() {
        return this.c;
    }

    public void setShowLog(boolean var1) {
        this.c = var1;
    }

    public boolean isShowMatchedRuleList() {
        return this.d;
    }

    public void setShowMatchedRuleList(boolean var1) {
        this.d = var1;
    }

    public boolean isShowNotMatchRuleList() {
        return this.e;
    }

    public void setShowNotMatchRuleList(boolean var1) {
        this.e = var1;
    }

    public boolean isShowFiredFlowNodeList() {
        return this.f;
    }

    public void setShowFiredFlowNodeList(boolean var1) {
        this.f = var1;
    }

    public List<Log> getLogs() {
        return this.n;
    }

    public void addLogs(List<Log> var1) {
        this.n.addAll(var1);
    }

    public List<IOData> getInputData() {
        return this.o;
    }

    public List<IOData> getOutputData() {
        return this.p;
    }

    public void setInputData(List<IOData> var1) {
        this.o = var1;
    }

    public void setOutputData(List<IOData> var1) {
        this.p = var1;
    }

    public String getVersion() {
        return this.g;
    }

    public void setVersion(String var1) {
        this.g = var1;
    }

    public String getVersionComment() {
        return this.h;
    }

    public void setVersionComment(String var1) {
        this.h = var1;
    }

    public Date getVersionCreateDate() {
        return this.i;
    }

    public void setVersionCreateDate(Date var1) {
        this.i = var1;
    }

    public String getVersionCreateUser() {
        return this.j;
    }

    public void setVersionCreateUser(String var1) {
        this.j = var1;
    }

    public String toString() {
        return "MonitorDataImpl [packageInfo=" + this.a + ", totalDuration=" + this.b + ", showLog=" + this.c + ", showMatchedRuleList=" + this.d + ", showNotMatchRuleList=" + this.e + ", showFiredFlowNodeList=" + this.f + ", version=" + this.g + ", versionComment=" + this.h + ", versionCreateDate=" + this.i + ", versionCreateUser=" + this.j + ", matchedRuleList=" + this.k + ", notMatchRuleList=" + this.l + ", firedFlowNodeList=" + this.m + ", logs=" + this.n + ", inputData=" + this.o + ", outputData=" + this.p + "]";
    }
}
