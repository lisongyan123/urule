//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.table;

import com.bstek.urule.model.rule.Library;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecisionTable {
    private Integer salience;
    private Date effectiveDate;
    private Date expiresDate;
    private Boolean enabled;
    private Boolean debug;
    private String remark;
    private String quickTestData;
    private List<Row> rows;
    private List<Column> columns;
    private Map<String, Cell> cellMap;
    private List<Library> libraries;

    public DecisionTable() {
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

    public List<Row> getRows() {
        return this.rows;
    }

    public void addLibrary(Library var1) {
        if (this.libraries == null) {
            this.libraries = new ArrayList();
        }

        this.libraries.add(var1);
    }

    public void addRow(Row var1) {
        if (this.rows == null) {
            this.rows = new ArrayList();
        }

        this.rows.add(var1);
    }

    public void addColumn(Column var1) {
        if (this.columns == null) {
            this.columns = new ArrayList();
        }

        this.columns.add(var1);
    }

    public void addCell(Cell var1) {
        if (this.cellMap == null) {
            this.cellMap = new HashMap();
        }

        this.cellMap.put(this.buildCellKey(var1.getRow(), var1.getCol()), var1);
    }

    public void setRows(List<Row> var1) {
        this.rows = var1;
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public void setColumns(List<Column> var1) {
        this.columns = var1;
    }

    public Map<String, Cell> getCellMap() {
        return this.cellMap;
    }

    public List<Library> getLibraries() {
        return this.libraries;
    }

    public void setLibraries(List<Library> var1) {
        this.libraries = var1;
    }

    public String buildCellKey(int var1, int var2) {
        return var1 + "," + var2;
    }
}
