//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

import com.bstek.urule.runtime.rete.Context;
import java.util.Map;

public class TemplateAction extends AbstractAction {
    private String a;
    private String b;

    public TemplateAction() {
    }

    public ActionValue execute(Context var1, Map<String, Object> var2) {
        return null;
    }

    public ActionType getActionType() {
        return ActionType.TemplateAction;
    }

    public String getId() {
        return this.a;
    }

    public void setId(String var1) {
        this.a = var1;
    }

    public String getName() {
        return this.b;
    }

    public void setName(String var1) {
        this.b = var1;
    }
}
