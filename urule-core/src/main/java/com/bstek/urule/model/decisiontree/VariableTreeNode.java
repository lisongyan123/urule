//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.decisiontree;

import com.bstek.urule.model.rule.lhs.Left;
import java.util.List;

public class VariableTreeNode extends TreeNode {
    private Left left;
    private List<ConditionTreeNode> conditionTreeNodes;

    public VariableTreeNode() {
    }

    public Left getLeft() {
        return this.left;
    }

    public void setLeft(Left var1) {
        this.left = var1;
    }

    public List<ConditionTreeNode> getConditionTreeNodes() {
        return this.conditionTreeNodes;
    }

    public void setConditionTreeNodes(List<ConditionTreeNode> var1) {
        this.conditionTreeNodes = var1;
    }
}
