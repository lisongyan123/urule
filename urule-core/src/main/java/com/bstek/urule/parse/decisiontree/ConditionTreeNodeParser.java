//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.decisiontree;

import com.bstek.urule.model.decisiontree.ActionTreeNode;
import com.bstek.urule.model.decisiontree.ConditionTreeNode;
import com.bstek.urule.model.decisiontree.TreeNodeType;
import com.bstek.urule.model.decisiontree.VariableTreeNode;
import com.bstek.urule.model.rule.Op;
import com.bstek.urule.parse.Parser;
import com.bstek.urule.parse.ValueParser;
import java.util.ArrayList;
import java.util.Iterator;
import org.dom4j.Element;

public class ConditionTreeNodeParser implements Parser<ConditionTreeNode> {
    private ValueParser a;
    private VariableTreeNodeParser b;
    private ActionTreeNodeParser c;

    public ConditionTreeNodeParser() {
    }

    public ConditionTreeNode parse(Element var1) {
        ConditionTreeNode var2 = new ConditionTreeNode();
        var2.setNodeType(TreeNodeType.condition);
        var2.setOp(Op.valueOf(var1.attributeValue("op")));
        ArrayList var3 = new ArrayList();
        ArrayList var4 = new ArrayList();
        ArrayList var5 = new ArrayList();
        Iterator var6 = var1.elements().iterator();

        while(var6.hasNext()) {
            Object var7 = var6.next();
            if (var7 != null && var7 instanceof Element) {
                Element var8 = (Element)var7;
                String var9 = var8.getName();
                if (this.a.support(var9)) {
                    var2.setValue(this.a.parse(var8));
                } else if (this.support(var9)) {
                    ConditionTreeNode var10 = this.parse(var8);
                    var10.setParentNode(var2);
                    var3.add(var10);
                } else if (this.b.support(var9)) {
                    VariableTreeNode var11 = this.b.parse(var8);
                    var11.setParentNode(var2);
                    var5.add(var11);
                } else if (this.c.support(var9)) {
                    ActionTreeNode var12 = this.c.parse(var8);
                    var12.setParentNode(var2);
                    var4.add(var12);
                }
            }
        }

        if (var3.size() > 0) {
            var2.setConditionTreeNodes(var3);
        }

        if (var4.size() > 0) {
            var2.setActionTreeNodes(var4);
        }

        if (var5.size() > 0) {
            var2.setVariableTreeNodes(var5);
        }

        return var2;
    }

    public void setValueParser(ValueParser var1) {
        this.a = var1;
    }

    public void setActionTreeNodeParser(ActionTreeNodeParser var1) {
        this.c = var1;
    }

    public void setVariableTreeNodeParser(VariableTreeNodeParser var1) {
        this.b = var1;
    }

    public boolean support(String var1) {
        return var1.equals("condition-tree-node");
    }
}
