//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.library;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.action.ActionLibrary;
import com.bstek.urule.model.library.constant.ConstantCategory;
import com.bstek.urule.model.library.constant.ConstantLibrary;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.library.variable.VariableLibrary;
import com.bstek.urule.model.template.ActionTemplate;
import com.bstek.urule.model.template.ActionTemplateUnit;
import com.bstek.urule.model.template.ConditionTemplate;
import com.bstek.urule.model.template.ConditionTemplateUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceLibrary {
    private List<ConditionTemplateUnit> conditionTemplateUnits;
    private List<ActionTemplateUnit> actionTemplateUnits;
    private List<ConstantCategory> constantCategories;
    private List<ActionLibrary> actionLibraries;
    private List<VariableCategory> variableCategories;

    public ResourceLibrary() {
    }

    public ResourceLibrary(List<VariableLibrary> var1, List<ActionLibrary> var2, List<ConstantLibrary> var3, List<ConditionTemplate> var4, List<ActionTemplate> var5) {
        this.variableCategories = new ArrayList();
        this.actionLibraries = new ArrayList();
        this.constantCategories = new ArrayList();
        this.conditionTemplateUnits = new ArrayList();
        this.actionTemplateUnits = new ArrayList();
        Iterator var6 = var1.iterator();

        Iterator var8;
        while(var6.hasNext()) {
            VariableLibrary var7 = (VariableLibrary)var6.next();
            var8 = var7.getVariableCategories().iterator();

            while(var8.hasNext()) {
                VariableCategory var9 = (VariableCategory)var8.next();
                this.variableCategories.add(var9);
            }
        }

        this.actionLibraries.addAll(var2);
        var6 = var3.iterator();

        while(var6.hasNext()) {
            ConstantLibrary var10 = (ConstantLibrary)var6.next();
            var8 = var10.getCategories().iterator();

            while(var8.hasNext()) {
                ConstantCategory var13 = (ConstantCategory)var8.next();
                this.constantCategories.add(var13);
            }
        }

        var6 = var4.iterator();

        while(var6.hasNext()) {
            ConditionTemplate var11 = (ConditionTemplate)var6.next();
            this.conditionTemplateUnits.addAll(var11.getTemplates());
        }

        var6 = var5.iterator();

        while(var6.hasNext()) {
            ActionTemplate var12 = (ActionTemplate)var6.next();
            this.actionTemplateUnits.addAll(var12.getTemplates());
        }

    }

    public ConditionTemplateUnit getConditionTemplateUnit(String var1) {
        Iterator var2 = this.conditionTemplateUnits.iterator();

        ConditionTemplateUnit var3;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (ConditionTemplateUnit)var2.next();
        } while(!var3.getId().equals(var1));

        return var3;
    }

    public ActionTemplateUnit getActionTemplateUnit(String var1) {
        Iterator var2 = this.actionTemplateUnits.iterator();

        ActionTemplateUnit var3;
        do {
            if (!var2.hasNext()) {
                return null;
            }

            var3 = (ActionTemplateUnit)var2.next();
        } while(!var3.getId().equals(var1));

        return var3;
    }

    public VariableCategory getVariableCategory(String var1) {
        Iterator var2 = this.variableCategories.iterator();

        VariableCategory var3;
        do {
            if (!var2.hasNext()) {
                throw new RuleException("Variable category [" + var1 + "] not exist");
            }

            var3 = (VariableCategory)var2.next();
        } while(!var3.getName().equals(var1));

        return var3;
    }

    public List<ActionLibrary> getActionLibraries() {
        return this.actionLibraries;
    }

    public List<VariableCategory> getVariableCategories() {
        return this.variableCategories;
    }

    public List<ConstantCategory> getConstantCategories() {
        return this.constantCategories;
    }
}
