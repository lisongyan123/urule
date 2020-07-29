//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.template;

import com.bstek.urule.model.rule.Library;
import java.util.ArrayList;
import java.util.List;

public class ActionTemplate {
    private List<Library> libraries;
    private List<ActionTemplateUnit> templates;

    public ActionTemplate() {
    }

    public void addLibrary(Library var1) {
        if (this.libraries == null) {
            this.libraries = new ArrayList();
        }

        this.libraries.add(var1);
    }

    public List<Library> getLibraries() {
        return this.libraries;
    }

    public void setLibraries(List<Library> var1) {
        this.libraries = var1;
    }

    public List<ActionTemplateUnit> getTemplates() {
        return this.templates;
    }

    public void setTemplates(List<ActionTemplateUnit> var1) {
        this.templates = var1;
    }
}
