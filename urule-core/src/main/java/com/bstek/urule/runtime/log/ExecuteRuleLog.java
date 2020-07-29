package com.bstek.urule.runtime.log;

import com.bstek.urule.model.rule.Rule;
import java.util.Date;

public class ExecuteRuleLog extends DataLog {
    private Integer a;
    private String b;
    private String c;
    private Date d;
    private Date e;
    private Boolean f;
    private Boolean g;
    private String h;
    private String i;

    public ExecuteRuleLog(Rule var1) {
        this.b = var1.getName();
        this.c = var1.getFile();
        this.a = var1.getSalience();
        this.d = var1.getEffectiveDate();
        this.e = var1.getExpiresDate();
        this.f = var1.getEnabled();
        this.g = var1.getDebug();
        this.h = var1.getMutexGroup();
        this.i = var1.getPendedGroup();
        this.msg = "----执行规则【" + var1.getName() + "】中动作，位于文件[" + this.c + "]中，优先级为[" + this.a + "]----";
    }

    public String getRuleFile() {
        return this.c;
    }

    public String getRuleName() {
        return this.b;
    }

    public Integer getSalience() {
        return this.a;
    }

    public String getActivationGroup() {
        return this.h;
    }

    public String getAgendaGroup() {
        return this.i;
    }

    public Boolean getDebug() {
        return this.g;
    }

    public Date getEffectiveDate() {
        return this.d;
    }

    public Boolean getEnabled() {
        return this.f;
    }

    public Date getExpiresDate() {
        return this.e;
    }

    public String toString() {
        return "ExecuteRuleLog [salience=" + this.a + ", ruleName=" + this.b + ", ruleFile=" + this.c + "]";
    }
}
