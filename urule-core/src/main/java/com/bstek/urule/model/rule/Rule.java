//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.model.rule.lhs.Lhs;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Rule implements Comparable<Rule> {
    private String id;
    private String name;
    private String file;
    private Integer salience;
    private Date effectiveDate;
    private Date expiresDate;
    private Boolean enabled;
    private Boolean debug;
    @JsonIgnore
    private boolean debugFromGlobal;
    private String mutexGroup;
    private String pendedGroup;
    private Boolean autoFocus;
    private Lhs lhs;
    private Rhs rhs;
    private Other other;
    private Boolean loop;
    private Boolean loopRule = false;
    private String remark;
    private boolean withElse;
    @JsonIgnore
    private Rule elseRule;

    public Rule() {
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

    public String getFile() {
        return this.file;
    }

    public void setFile(String var1) {
        this.file = var1;
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public Integer getSalience() {
        return this.salience;
    }

    public void setSalience(Integer var1) {
        this.salience = var1;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean var1) {
        this.enabled = var1;
    }

    public Boolean getDebug() {
        return this.debug;
    }

    public void setDebug(Boolean var1) {
        this.debug = var1;
    }

    public boolean isDebugFromGlobal() {
        return this.debugFromGlobal;
    }

    public void setDebugFromGlobal(boolean var1) {
        this.debugFromGlobal = var1;
    }

    public Boolean getAutoFocus() {
        return this.autoFocus;
    }

    public void setAutoFocus(Boolean var1) {
        this.autoFocus = var1;
    }

    public void setEffectiveDate(Date var1) {
        this.effectiveDate = var1;
    }

    public Date getExpiresDate() {
        return this.expiresDate;
    }

    public void setExpiresDate(Date var1) {
        this.expiresDate = var1;
    }

    public String getMutexGroup() {
        return this.mutexGroup;
    }

    public void setMutexGroup(String var1) {
        this.mutexGroup = var1;
    }

    public String getPendedGroup() {
        return this.pendedGroup;
    }

    public void setPendedGroup(String var1) {
        this.pendedGroup = var1;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String var1) {
        this.remark = var1;
    }

    public Lhs getLhs() {
        return this.lhs;
    }

    public void setLhs(Lhs var1) {
        this.lhs = var1;
    }

    public Rhs getRhs() {
        return this.rhs;
    }

    public void setRhs(Rhs var1) {
        this.rhs = var1;
    }

    public Other getOther() {
        return this.other;
    }

    public void setOther(Other var1) {
        this.other = var1;
    }

    public Boolean getLoop() {
        return this.loop;
    }

    public void setLoop(Boolean var1) {
        this.loop = var1;
    }

    public Boolean isLoopRule() {
        return this.loopRule;
    }

    public void setLoopRule(Boolean var1) {
        this.loopRule = var1;
    }

    public boolean isWithElse() {
        return this.withElse;
    }

    public void setWithElse(boolean var1) {
        this.withElse = var1;
    }

    public Rule getElseRule() {
        return this.elseRule;
    }

    public void setElseRule(Rule var1) {
        this.elseRule = var1;
    }

    public int compareTo(Rule var1) {
        Integer var2 = var1.getSalience();
        Integer var3 = this.getSalience();
        if (var2 != null && var3 != null) {
            return var2 - var3;
        } else if (var2 != null) {
            return 1;
        } else {
            return var3 != null ? -1 : 0;
        }
    }
}
