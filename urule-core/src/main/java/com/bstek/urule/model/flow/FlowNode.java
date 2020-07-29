//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.Node;
import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;
import com.bstek.urule.model.flow.ins.ProcessInstance;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;

public abstract class FlowNode implements Node {
    protected String name;
    protected String eventBean;
    protected String x;
    protected String y;
    protected String width;
    protected String height;
    protected List<Connection> connections;

    public FlowNode() {
    }

    public FlowNode(String var1) {
        this.name = var1;
    }

    public final void enter(FlowContext var1, FlowInstance var2) {
        if (var2.isDebug()) {
            var1.getLogger().logFlowNode(this, var2.getProcessDefinition().getFile(), true);
        }

        this.enterNode(var1, var2);
    }

    public abstract void enterNode(FlowContext var1, FlowInstance var2);

    protected void leave(String var1, FlowContext var2, FlowInstance var3) {
        if (var3.isDebug()) {
            var2.getLogger().logFlowNode(this, var3.getProcessDefinition().getFile(), false);
        }

        Iterator var4 = this.connections.iterator();

        while(var4.hasNext()) {
            Connection var5 = (Connection)var4.next();
            if (var1 != null) {
                String var6 = var5.getName();
                var6 = var6 == null ? var6 : var6.trim();
                if (var1.trim().equals(var6)) {
                    var5.execute(var2, var3);
                    break;
                }
            } else if (var5.evaluate(var2)) {
                var5.execute(var2, var3);
                break;
            }
        }

    }

    protected void executeNodeEvent(EventType var1, FlowContext var2, ProcessInstance var3) {
        if (!StringUtils.isEmpty(this.eventBean)) {
            ApplicationContext var4 = var2.getApplicationContext();
            NodeEvent var5 = (NodeEvent)var4.getBean(this.eventBean);
            if (var1.equals(EventType.enter)) {
                var5.enter(this, var3, var2);
            } else {
                var5.leave(this, var3, var2);
            }

        }
    }

    public abstract FlowNodeType getType();

    public List<Connection> getConnections() {
        return this.connections;
    }

    public void setConnections(List<Connection> var1) {
        this.connections = var1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public String getEventBean() {
        return this.eventBean;
    }

    public void setEventBean(String var1) {
        this.eventBean = var1;
    }

    public String getX() {
        return this.x;
    }

    public void setX(String var1) {
        this.x = var1;
    }

    public String getY() {
        return this.y;
    }

    public void setY(String var1) {
        this.y = var1;
    }

    public String getWidth() {
        return this.width;
    }

    public void setWidth(String var1) {
        this.width = var1;
    }

    public String getHeight() {
        return this.height;
    }

    public void setHeight(String var1) {
        this.height = var1;
    }
}
