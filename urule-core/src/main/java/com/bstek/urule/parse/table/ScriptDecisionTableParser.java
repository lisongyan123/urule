//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.table;

import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.table.ScriptDecisionTable;
import com.bstek.urule.parse.Parser;
import java.util.Iterator;
import org.dom4j.Element;

public class ScriptDecisionTableParser implements Parser<ScriptDecisionTable> {
    private RowParser a;
    private ColumnParser b;
    private ScriptCellParser c;

    public ScriptDecisionTableParser() {
    }

    public ScriptDecisionTable parse(Element var1) {
        ScriptDecisionTable var2 = new ScriptDecisionTable();
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                String var6 = var5.getName();
                if (this.a.support(var6)) {
                    var2.addRow(this.a.parse(var5));
                } else if (this.b.support(var6)) {
                    var2.addColumn(this.b.parse(var5));
                } else if (this.c.support(var6)) {
                    var2.addCell(this.c.parse(var5));
                }

                if (var6.equals("import-variable-library")) {
                    var2.addLibrary(new Library(var5.attributeValue("path"), (String)null, LibraryType.Variable));
                } else if (var6.equals("import-constant-library")) {
                    var2.addLibrary(new Library(var5.attributeValue("path"), (String)null, LibraryType.Constant));
                } else if (var6.equals("import-action-library")) {
                    var2.addLibrary(new Library(var5.attributeValue("path"), (String)null, LibraryType.Action));
                } else if (var6.equals("import-parameter-library")) {
                    var2.addLibrary(new Library(var5.attributeValue("path"), (String)null, LibraryType.Parameter));
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("script-decision-table");
    }

    public void setColumnParser(ColumnParser var1) {
        this.b = var1;
    }

    public void setRowParser(RowParser var1) {
        this.a = var1;
    }

    public void setScriptCellParser(ScriptCellParser var1) {
        this.c = var1;
    }
}
