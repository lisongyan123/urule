package com.bstek.urule.console.servlet.decisiontable;

import com.bstek.urule.console.servlet.CellContent;
import java.util.List;

public class ContentRow {
    private List<CellContent> a;

    public ContentRow() {
    }

    public List<CellContent> getContents() {
        return this.a;
    }

    public void setContents(List<CellContent> var1) {
        this.a = var1;
    }
}
