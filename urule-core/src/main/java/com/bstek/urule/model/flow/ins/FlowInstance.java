//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow.ins;

import com.bstek.urule.model.flow.FlowNode;
import com.bstek.urule.model.flow.ProcessDefinition;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class FlowInstance implements ProcessInstance {
    private String id;
    private int parallelInstanceCount;
    private ProcessDefinition flowDefinition;
    private FlowInstance parent;
    private BranchCounter branchCounter;
    private List<ProcessInstance> children = new ArrayList();
    private FlowNode currentNode;
    private boolean debug;
    private Map<String, Object> variablesMap = new HashMap();

    public FlowInstance(ProcessDefinition var1, boolean var2) {
        this.flowDefinition = var1;
        this.id = UUID.randomUUID().toString();
        this.debug = var2;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public Object getVariable(String var1) {
        return this.variablesMap.get(var1);
    }

    public void addVariable(String var1, Object var2) {
        this.variablesMap.put(var1, var2);
    }

    public void removeVariable(String var1) {
        this.variablesMap.remove(var1);
    }

    public int riseArrivedBranch() {
        return this.branchCounter.rise();
    }

    public int getArrivedBranchSize() {
        return this.branchCounter.getCount();
    }

    public BranchCounter getBranchCounter() {
        return this.branchCounter;
    }

    public void setBranchCounter(BranchCounter var1) {
        this.branchCounter = var1;
    }

    public ProcessDefinition getProcessDefinition() {
        return this.flowDefinition;
    }

    public List<ProcessInstance> getChildren() {
        return this.children;
    }

    public void addChild(FlowInstance var1) {
        this.children.add(var1);
    }

    public int getParallelInstanceCount() {
        return this.parallelInstanceCount;
    }

    public void setParallelInstanceCount(int var1) {
        this.parallelInstanceCount = var1;
    }

    public String getId() {
        return this.id;
    }

    public FlowNode getCurrentNode() {
        return this.currentNode;
    }

    public void setCurrentNode(FlowNode var1) {
        this.currentNode = var1;
    }

    public void setParent(FlowInstance var1) {
        this.parent = var1;
    }

    public FlowInstance getParent() {
        return this.parent;
    }

    public FlowInstance newChildInstance(BranchCounter var1, int var2) {
        FlowInstance var3 = new FlowInstance(this.flowDefinition, this.debug);
        var3.setBranchCounter(var1);
        var3.setParallelInstanceCount(var2);
        var3.setParent(this);
        this.addChild(var3);
        return var3;
    }
}
