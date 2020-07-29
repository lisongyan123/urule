//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.model.rule.Rule;
import java.util.Date;

public class RuleData {
    private Integer salience;
    private String name;
    private String file;
    private Date effectiveDate;
    private Date expiresDate;
    private Boolean enabled;
    private Boolean debug;
    private String mutexGroup;
    private String pendedGroup;

    public RuleData(Rule var1) {
        this.name = var1.getName();
        this.file = var1.getFile();
        this.salience = var1.getSalience();
        this.effectiveDate = var1.getEffectiveDate();
        this.expiresDate = var1.getExpiresDate();
        this.enabled = var1.getEnabled();
        this.debug = var1.getDebug();
        this.mutexGroup = var1.getMutexGroup();
        this.pendedGroup = var1.getPendedGroup();
    }

    public String getName() {
        return this.name;
    }

    public String getFile() {
        return this.file;
    }

    public String getMutexGroup() {
        return this.mutexGroup;
    }

    public String getPendedGroup() {
        return this.pendedGroup;
    }

    public Boolean getDebug() {
        return this.debug;
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public Date getExpiresDate() {
        return this.expiresDate;
    }

    public Integer getSalience() {
        return this.salience;
    }

    public String toString() {
        return "RuleData [name=" + this.name + ", file=" + this.file + "]";
    }
}
