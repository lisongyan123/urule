//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.action.Action;
import java.util.ArrayList;
import java.util.List;

public class Other {
    private List<Action> actions;

    public Other() {
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public void setActions(List<Action> var1) {
        this.actions = var1;
    }

    public void addAction(Action var1) {
        if (this.actions == null) {
            this.actions = new ArrayList();
        }

        this.actions.add(var1);
    }
}
