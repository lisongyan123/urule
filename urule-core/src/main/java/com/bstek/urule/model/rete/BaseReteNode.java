//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.runtime.rete.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

public abstract class BaseReteNode extends ReteNode {
    @JsonIgnore
    private List<ReteNode> childrenNodes = new ArrayList();
    protected List<Line> lines;

    public BaseReteNode(int var1) {
        super(var1);
    }

    public List<ReteNode> getChildrenNodes() {
        return this.childrenNodes;
    }

    public void setChildrenNodes(List<ReteNode> var1) {
        this.childrenNodes = var1;
    }

    protected boolean buildVariables(Context var1, Value var2, Map<String, Object> var3) {
        return true;
    }

    protected Object fetchData(Object var1, String var2) {
        try {
            return BeanUtils.getProperty(var1, var2);
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }

    public Line addLine(ReteNode var1) {
        if (this.childrenNodes == null) {
            this.childrenNodes = new ArrayList();
        }

        this.childrenNodes.add(var1);
        Line var2 = new Line(this, var1);
        if (this.lines == null) {
            this.lines = new ArrayList();
        }

        this.lines.add(var2);
        if (var1 instanceof JunctionNode) {
            JunctionNode var3 = (JunctionNode)var1;
            var3.addToConnection(var2);
        }

        return var2;
    }

    public List<Line> getLines() {
        return this.lines;
    }

    public void setLines(List<Line> var1) {
        this.lines = var1;
    }
}
