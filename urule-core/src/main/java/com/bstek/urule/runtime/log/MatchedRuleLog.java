//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.lhs.Criteria;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MatchedRuleLog extends DataLog {
    private Integer a;
    private String b;
    private String c;
    private Date d;
    private Date e;
    private Boolean f;
    private Boolean g;
    private String h;
    private String i;

    public MatchedRuleLog(Rule var1, Set<Criteria> var2) {
        this.b = var1.getName();
        this.c = var1.getFile();
        this.a = var1.getSalience();
        this.d = var1.getEffectiveDate();
        this.e = var1.getExpiresDate();
        this.f = var1.getEnabled();
        this.g = var1.getDebug();
        this.h = var1.getMutexGroup();
        this.i = var1.getPendedGroup();
        this.msg = "√√规则【" + var1.getName() + "】匹配，(" + this.c + ")";
        if (var2.size() > 0) {
            this.msg = this.msg + "，条件：" + this.a(var2);
        } else {
            this.msg = this.msg + "，条件：无";
        }

    }

    private String a(Set<Criteria> var1) {
        StringBuilder var2 = new StringBuilder();

        Criteria var4;
        for(Iterator var3 = var1.iterator(); var3.hasNext(); var2.append(var4.getId())) {
            var4 = (Criteria)var3.next();
            if (var2.length() > 0) {
                var2.append("◆");
            }
        }

        return var2.toString();
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
        return "MatchedRuleLog [ruleName=" + this.b + ", ruleFile=" + this.c + ", salience=" + this.a + "]";
    }
}
