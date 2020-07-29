//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.decisiontree;

import com.bstek.urule.model.rule.Op;
import com.bstek.urule.model.rule.Value;
import java.util.List;

public class ConditionTreeNode extends TreeNode {
    private Value value;
    private Op op;
    private List<ConditionTreeNode> conditionTreeNodes;
    private List<VariableTreeNode> variableTreeNodes;
    private List<ActionTreeNode> actionTreeNodes;

    public ConditionTreeNode() {
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }

    public Op getOp() {
        return this.op;
    }

    public void setOp(Op var1) {
        this.op = var1;
    }

    public List<ConditionTreeNode> getConditionTreeNodes() {
        return this.conditionTreeNodes;
    }

    public void setConditionTreeNodes(List<ConditionTreeNode> var1) {
        this.conditionTreeNodes = var1;
    }

    public List<VariableTreeNode> getVariableTreeNodes() {
        return this.variableTreeNodes;
    }

    public void setVariableTreeNodes(List<VariableTreeNode> var1) {
        this.variableTreeNodes = var1;
    }

    public List<ActionTreeNode> getActionTreeNodes() {
        return this.actionTreeNodes;
    }

    public void setActionTreeNodes(List<ActionTreeNode> var1) {
        this.actionTreeNodes = var1;
    }
}
