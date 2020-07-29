//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

import com.bstek.urule.model.rule.Rule;
import java.util.Date;

public class AddRuleToExecuteQueueLog extends DataLog {
    private Integer a;
    private String b;
    private String c;
    private Date d;
    private Date e;
    private Boolean f;
    private Boolean g;
    private String h;
    private String i;
    private boolean j;

    public AddRuleToExecuteQueueLog(Rule var1, boolean var2) {
        this.b = var1.getName();
        this.c = var1.getFile();
        this.a = var1.getSalience();
        this.d = var1.getEffectiveDate();
        this.e = var1.getExpiresDate();
        this.f = var1.getEnabled();
        this.g = var1.getDebug();
        this.h = var1.getMutexGroup();
        this.i = var1.getPendedGroup();
        this.j = var2;
        if (var2) {
            this.msg = "》》》规则【" + var1.getName() + "】(" + this.c + ")，已被添加到执行队列";
        } else {
            this.msg = "》》》规则【" + var1.getName() + "】(" + this.c + ")，未被添加到执行队列,因当前匹配操作在工作区更新情况下发生，且当前规则在执行队列中已存在！";
        }

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

    public boolean isAdd() {
        return this.j;
    }
}
