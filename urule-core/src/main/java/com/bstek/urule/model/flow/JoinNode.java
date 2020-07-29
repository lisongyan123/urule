//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;
import java.util.Iterator;
import java.util.Map;

public class JoinNode extends FlowNode {
    private FlowNodeType type;

    public JoinNode() {
        this.type = FlowNodeType.Join;
    }

    public JoinNode(String var1) {
        super(var1);
        this.type = FlowNodeType.Join;
    }

    public FlowNodeType getType() {
        return this.type;
    }

    public void enterNode(FlowContext var1, FlowInstance var2) {
        FlowInstance var3 = var2.getParent();
        if (var3 == null) {
            throw new RuleException("Invalid flow instance.");
        } else {
            int var4 = var2.getParallelInstanceCount();
            boolean var5 = (Boolean)var2.getVariable("fork_node_multi_thread_support_");
            int var6;
            if (var5) {
                var6 = var2.riseArrivedBranch();
                String var7 = Thread.currentThread().getName();
                if (var7 != null && var7.startsWith("fork_node_sub_thread_")) {
                    return;
                }

                if (var6 >= var4) {
                    this.doLeave(var1, var3);
                } else {
                    Map var8 = (Map)var2.getVariable("fork_node_exception_map_");
                    var2.removeVariable("fork_node_exception_map_");

                    while(true) {
                        var6 = var2.getArrivedBranchSize();
                        if (var6 >= var4) {
                            this.doLeave(var1, var3);
                            break;
                        }

                        Exception var9 = null;
                        Iterator var10 = var8.keySet().iterator();

                        Thread var11;
                        while(var10.hasNext()) {
                            var11 = (Thread)var10.next();
                            Object var12 = var8.get(var11);
                            if (var12 instanceof Exception) {
                                var9 = (Exception)var12;
                            }
                        }

                        if (var9 != null) {
                            var10 = var8.keySet().iterator();

                            while(var10.hasNext()) {
                                var11 = (Thread)var10.next();
                                var11.interrupt();
                            }

                            throw new RuleException(var9);
                        }

                        Thread.yield();
                    }
                }
            } else {
                var2.riseArrivedBranch();
                var6 = var2.getArrivedBranchSize();
                if (var6 >= var4) {
                    this.doLeave(var1, var3);
                }
            }

        }
    }

    private void doLeave(FlowContext var1, FlowInstance var2) {
        var2.setCurrentNode(this);
        this.executeNodeEvent(EventType.enter, var1, var2);
        this.executeNodeEvent(EventType.leave, var1, var2);
        String var3 = var2.getId() + this.getName();
        var1.removeVariable(var3);
        this.leave((String)null, var1, var2);
    }
}
