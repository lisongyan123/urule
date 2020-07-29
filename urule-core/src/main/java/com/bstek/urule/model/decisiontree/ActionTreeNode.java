//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.decisiontree;

import com.bstek.urule.action.Action;
import java.util.List;

public class ActionTreeNode extends TreeNode {
    private List<Action> actions;

    public ActionTreeNode() {
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public void setActions(List<Action> var1) {
        this.actions = var1;
    }
}
