//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.decisiontree;

import org.codehaus.jackson.annotate.JsonIgnore;

public abstract class TreeNode {
    @JsonIgnore
    private TreeNode parentNode;
    private TreeNodeType nodeType;

    public TreeNode() {
    }

    public void setParentNode(TreeNode var1) {
        this.parentNode = var1;
    }

    public TreeNode getParentNode() {
        return this.parentNode;
    }

    public TreeNodeType getNodeType() {
        return this.nodeType;
    }

    public void setNodeType(TreeNodeType var1) {
        this.nodeType = var1;
    }
}
