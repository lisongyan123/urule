//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.decisiontree;

import com.bstek.urule.model.rule.Library;
import java.util.Date;
import java.util.List;

public class DecisionTree {
    private Integer salience;
    private Date effectiveDate;
    private Date expiresDate;
    private Boolean enabled;
    private Boolean debug;
    private String remark;
    private String quickTestData;
    private List<Library> libraries;
    private VariableTreeNode variableTreeNode;

    public DecisionTree() {
    }

    public Integer getSalience() {
        return this.salience;
    }

    public void setSalience(Integer var1) {
        this.salience = var1;
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String var1) {
        this.remark = var1;
    }

    public String getQuickTestData() {
        return this.quickTestData;
    }

    public void setQuickTestData(String var1) {
        this.quickTestData = var1;
    }

    public List<Library> getLibraries() {
        return this.libraries;
    }

    public void setLibraries(List<Library> var1) {
        this.libraries = var1;
    }

    public VariableTreeNode getVariableTreeNode() {
        return this.variableTreeNode;
    }

    public void setVariableTreeNode(VariableTreeNode var1) {
        this.variableTreeNode = var1;
    }
}
