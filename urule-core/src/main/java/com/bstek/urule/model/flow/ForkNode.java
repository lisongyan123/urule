//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.model.flow.ins.BranchCounter;
import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;

public class ForkNode extends FlowNode {
    private String multipleThread;
    private FlowNodeType type;

    public ForkNode() {
        this.type = FlowNodeType.Fork;
    }

    public ForkNode(String var1) {
        super(var1);
        this.type = FlowNodeType.Fork;
    }

    public FlowNodeType getType() {
        return this.type;
    }

    public void enterNode(final FlowContext var1, final FlowInstance var2) {
        var2.setCurrentNode(this);
        this.executeNodeEvent(EventType.enter, var1, var2);
        ArrayList var3 = new ArrayList();
        Iterator var4 = this.connections.iterator();

        while(var4.hasNext()) {
            Connection var5 = (Connection)var4.next();
            if (var5.evaluate(var1)) {
                var3.add(var5);
            }
        }

        this.executeNodeEvent(EventType.leave, var1, var2);
        final BranchCounter var12 = new BranchCounter();
        final int var13 = var3.size();
        final boolean var6 = this.forkNodeMultiThreadSupport();
        final ConcurrentHashMap var7 = new ConcurrentHashMap();

        for(int var8 = 0; var8 < var13; ++var8) {
            final Connection var9 = (Connection)var3.get(var8);
            if (!var6) {
                FlowInstance var10 = var2.newChildInstance(var12, var13);
                var10.addVariable("fork_node_multi_thread_support_", var6);
                var9.execute(var1, var10);
            } else {
                String var14;
                if (var8 == var13 - 1) {
                    var14 = Thread.currentThread().getName();
                    if (var14.startsWith("fork_node_sub_thread_")) {
                        Thread.currentThread().setName("Sub_Fork_Main_Thread_" + UUID.randomUUID().toString());
                    }

                    FlowInstance var11 = var2.newChildInstance(var12, var13);
                    var11.addVariable("fork_node_multi_thread_support_", var6);
                    var11.addVariable("fork_node_exception_map_", var7);
                    var9.execute(var1, var11);
                } else {
                    var14 = "fork_node_sub_thread_" + (var9.getName() == null ? "" : var9.getName() + "_") + this.getName() + var8 + "_" + UUID.randomUUID().toString();
                    Thread var15 = new Thread() {
                        public void run() {
                            try {
                                FlowInstance var1x = var2.newChildInstance(var12, var13);
                                var1x.addVariable("fork_node_multi_thread_support_", var6);
                                var1x.addVariable("fork_node_exception_map_", var7);
                                var9.execute(var1, var1x);
                            } catch (Exception var2x) {
                                var7.put(this, var2x);
                            }

                        }
                    };
                    var7.put(var15, 1);
                    var15.setName(var14);
                    var15.setDaemon(true);
                    var15.start();
                }
            }
        }

    }

    private boolean forkNodeMultiThreadSupport() {
        String var1 = PropertyConfigurer.getProperty("urule.flowForkMultiThread");
        boolean var2 = false;
        if (StringUtils.isNotBlank(var1)) {
            var2 = Boolean.valueOf(var1);
        }

        boolean var3 = false;
        if (!StringUtils.isBlank(this.multipleThread) && !this.multipleThread.equals("default")) {
            if (this.multipleThread.equals("yes")) {
                var3 = true;
            } else {
                var3 = false;
            }
        } else {
            var3 = var2;
        }

        return var3;
    }

    public String getMultipleThread() {
        return this.multipleThread;
    }

    public void setMultipleThread(String var1) {
        this.multipleThread = var1;
    }
}
