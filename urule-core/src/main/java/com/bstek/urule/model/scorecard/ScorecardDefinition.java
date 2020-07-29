//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.scorecard;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Library;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ScorecardDefinition {
    private String name;
    private Integer salience;
    private Date effectiveDate;
    private Date expiresDate;
    private Boolean enabled;
    private Boolean debug;
    private String attributeColName;
    private String attributeColWidth;
    private String attributeColVariableCategory;
    private String conditionColName;
    private String conditionColWidth;
    private String scoreColName;
    private String scoreColWidth;
    private boolean weightSupport;
    private ScoringType scoringType;
    private String scoringBean;
    private AssignTargetType assignTargetType;
    private String remark;
    private String quickTestData;
    private String keyLabel;
    private String keyName;
    private String variableCategory;
    private String variableName;
    private String variableLabel;
    private Datatype datatype;
    private List<com.bstek.urule.model.scorecard.CardCell> cells;
    private List<com.bstek.urule.model.scorecard.CustomCol> customCols;
    private List<com.bstek.urule.model.scorecard.AttributeRow> rows;
    private List<Library> libraries;

    public ScorecardDefinition() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
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

    public String getAttributeColName() {
        return this.attributeColName;
    }

    public void setAttributeColName(String var1) {
        this.attributeColName = var1;
    }

    public String getAttributeColWidth() {
        return this.attributeColWidth;
    }

    public void setAttributeColWidth(String var1) {
        this.attributeColWidth = var1;
    }

    public String getAttributeColVariableCategory() {
        return this.attributeColVariableCategory;
    }

    public void setAttributeColVariableCategory(String var1) {
        this.attributeColVariableCategory = var1;
    }

    public String getConditionColName() {
        return this.conditionColName;
    }

    public void setConditionColName(String var1) {
        this.conditionColName = var1;
    }

    public String getConditionColWidth() {
        return this.conditionColWidth;
    }

    public void setConditionColWidth(String var1) {
        this.conditionColWidth = var1;
    }

    public String getScoreColName() {
        return this.scoreColName;
    }

    public void setScoreColName(String var1) {
        this.scoreColName = var1;
    }

    public String getScoreColWidth() {
        return this.scoreColWidth;
    }

    public void setScoreColWidth(String var1) {
        this.scoreColWidth = var1;
    }

    public boolean isWeightSupport() {
        return this.weightSupport;
    }

    public void setWeightSupport(boolean var1) {
        this.weightSupport = var1;
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

    public AssignTargetType getAssignTargetType() {
        return this.assignTargetType;
    }

    public void setAssignTargetType(AssignTargetType var1) {
        this.assignTargetType = var1;
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

    public Datatype getDatatype() {
        return this.datatype;
    }

    public void setDatatype(Datatype var1) {
        this.datatype = var1;
    }

    public List<com.bstek.urule.model.scorecard.CustomCol> getCustomCols() {
        return this.customCols;
    }

    public void setCustomCols(List<com.bstek.urule.model.scorecard.CustomCol> var1) {
        this.customCols = var1;
    }

    public List<com.bstek.urule.model.scorecard.AttributeRow> getRows() {
        return this.rows;
    }

    public void setRows(List<com.bstek.urule.model.scorecard.AttributeRow> var1) {
        this.rows = var1;
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

    public List<com.bstek.urule.model.scorecard.CardCell> getCells() {
        return this.cells;
    }

    public void setCells(List<com.bstek.urule.model.scorecard.CardCell> var1) {
        this.cells = var1;
    }
}
