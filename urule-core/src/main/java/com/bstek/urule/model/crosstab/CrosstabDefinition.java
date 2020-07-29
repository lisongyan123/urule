//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.crosstab;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Library;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CrosstabDefinition {
    private Integer salience;
    private Date effectiveDate;
    private Date expiresDate;
    private Boolean enabled;
    private Boolean debug;
    private String remark;
    private String quickTestData;
    private String assignTargetType;
    private String assignVariableCategory;
    private String assignVariable;
    private String assignVariableLabel;
    private Datatype assignDatatype;
    private String keyLabel;
    private String keyName;
    private HeaderCell headerCell;
    private List<com.bstek.urule.model.crosstab.CrossCell> cells;
    private List<com.bstek.urule.model.crosstab.CrossRow> rows;
    private List<com.bstek.urule.model.crosstab.CrossColumn> columns;
    private List<Library> libraries;

    public CrosstabDefinition() {
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

    public String getQuickTestData() {
        return this.quickTestData;
    }

    public void setQuickTestData(String var1) {
        this.quickTestData = var1;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String var1) {
        this.remark = var1;
    }

    public String getKeyLabel() {
        return this.keyLabel;
    }

    public void setKeyLabel(String var1) {
        this.keyLabel = var1;
    }

    public String getKeyName() {
        return this.keyName;
    }

    public void setKeyName(String var1) {
        this.keyName = var1;
    }

    public String getAssignTargetType() {
        return this.assignTargetType;
    }

    public void setAssignTargetType(String var1) {
        this.assignTargetType = var1;
    }

    public String getAssignVariableCategory() {
        return this.assignVariableCategory;
    }

    public void setAssignVariableCategory(String var1) {
        this.assignVariableCategory = var1;
    }

    public String getAssignVariable() {
        return this.assignVariable;
    }

    public void setAssignVariable(String var1) {
        this.assignVariable = var1;
    }

    public String getAssignVariableLabel() {
        return this.assignVariableLabel;
    }

    public void setAssignVariableLabel(String var1) {
        this.assignVariableLabel = var1;
    }

    public Datatype getAssignDatatype() {
        return this.assignDatatype;
    }

    public void setAssignDatatype(Datatype var1) {
        this.assignDatatype = var1;
    }

    public HeaderCell getHeaderCell() {
        return this.headerCell;
    }

    public void setHeaderCell(HeaderCell var1) {
        this.headerCell = var1;
    }

    public List<com.bstek.urule.model.crosstab.CrossCell> getCells() {
        return this.cells;
    }

    public void setCells(List<com.bstek.urule.model.crosstab.CrossCell> var1) {
        this.cells = var1;
    }

    public List<com.bstek.urule.model.crosstab.CrossRow> getRows() {
        return this.rows;
    }

    public void setRows(List<com.bstek.urule.model.crosstab.CrossRow> var1) {
        this.rows = var1;
    }

    public List<com.bstek.urule.model.crosstab.CrossColumn> getColumns() {
        return this.columns;
    }

    public void setColumns(List<com.bstek.urule.model.crosstab.CrossColumn> var1) {
        this.columns = var1;
    }

    public List<Library> getLibraries() {
        return this.libraries;
    }

    public void setLibraries(List<Library> var1) {
        this.libraries = var1;
    }

    public void addLibrary(Library var1) {
        if (this.libraries == null) {
            this.libraries = new ArrayList();
        }

        this.libraries.add(var1);
    }
}
