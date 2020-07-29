//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.scorecard;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.table.Cell;
import com.bstek.urule.model.table.Row;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComplexScorecardDefinition {
    private Integer salience;
    private Date effectiveDate;
    private Date expiresDate;
    private Boolean enabled;
    private Boolean debug;
    private String remark;
    private String quickTestData;
    private String variableCategory;
    private String variableName;
    private String variableLabel;
    private String keyLabel;
    private String keyName;
    private Datatype datatype;
    private ScoringType scoringType;
    private String scoringBean;
    private AssignTargetType assignTargetType;
    private List<Row> rows;
    private List<com.bstek.urule.model.scorecard.ComplexColumn> columns;
    private Map<String, Cell> cellMap;
    private List<Library> libraries;

    public ComplexScorecardDefinition() {
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

    public String getVariableCategory() {
        return this.variableCategory;
    }

    public void setVariableCategory(String var1) {
        this.variableCategory = var1;
    }

    public String getVariableName() {
        return this.variableName;
    }

    public void setVariableName(String var1) {
        this.variableName = var1;
    }

    public String getVariableLabel() {
        return this.variableLabel;
    }

    public void setVariableLabel(String var1) {
        this.variableLabel = var1;
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

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setDatatype(Datatype var1) {
        this.datatype = var1;
    }

    public ScoringType getScoringType() {
        return this.scoringType;
    }

    public void setScoringType(ScoringType var1) {
        this.scoringType = var1;
    }

    public String getScoringBean() {
        return this.scoringBean;
    }

    public void setScoringBean(String var1) {
        this.scoringBean = var1;
    }

    public AssignTargetType getAssignTargetType() {
        return this.assignTargetType;
    }

    public void setAssignTargetType(AssignTargetType var1) {
        this.assignTargetType = var1;
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

    public void addColumn(com.bstek.urule.model.scorecard.ComplexColumn var1) {
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

    public List<com.bstek.urule.model.scorecard.ComplexColumn> getColumns() {
        return this.columns;
    }

    public void setColumns(List<com.bstek.urule.model.scorecard.ComplexColumn> var1) {
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
