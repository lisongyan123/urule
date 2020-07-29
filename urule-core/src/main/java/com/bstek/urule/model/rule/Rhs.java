//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.action.Action;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rhs {
    private List<Action> actions;

    public Rhs() {
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public void setActions(List<Action> var1) {
        this.actions = var1;
        Collections.sort(var1);
    }

    public void addAction(Action var1) {
        if (this.actions == null) {
            this.actions = new ArrayList();
        }

        this.actions.add(var1);
        Collections.sort(this.actions);
    }
}
