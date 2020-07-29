//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.table;

import com.bstek.urule.model.rule.Library;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScriptDecisionTable {
    private List<Row> rows;
    private List<Column> columns;
    private Map<String, ScriptCell> cellMap;
    private List<Library> libraries;

    public ScriptDecisionTable() {
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

    public void addCell(ScriptCell var1) {
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

    public Map<String, ScriptCell> getCellMap() {
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
