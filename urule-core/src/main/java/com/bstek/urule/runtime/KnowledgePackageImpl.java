//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.model.Node;
import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.rete.BaseReteNode;
import com.bstek.urule.model.rete.Line;
import com.bstek.urule.model.rete.Rete;
import com.bstek.urule.model.rete.ReteNode;
import com.bstek.urule.model.rete.ReteUnit;
import com.bstek.urule.runtime.monitor.MonitorObject;
import com.bstek.urule.runtime.rete.ReteInstance;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.codehaus.jackson.annotate.JsonIgnore;

public class KnowledgePackageImpl implements KnowledgePackage {
    private String packageInfo;
    private boolean monitor;
    private boolean showLog;
    private boolean showMatchedRuleList;
    private boolean showNotMatchRuleList;
    private boolean showFiredFlowNodeList;
    private List<MonitorObject> inputData;
    private List<MonitorObject> outputData;
    private String version;
    private String versionComment;
    private Date versionCreateDate;
    private String versionCreateUser;
    private Rete rete;
    private List<Rete> aloneRetes;
    private Map<String, String> variableCategoryMap = new HashMap();
    private Map<String, FlowDefinition> flowMap;
    private Map<String, String> parameters;
    private long timestamp = System.currentTimeMillis();
    private String id = UUID.randomUUID().toString();
    @JsonIgnore
    private List<VariableCategory> variableCategories;

    public KnowledgePackageImpl() {
    }

    public void initForActiveVersion() {
        this.initSingleReteForActiveVersion(this.rete);
        if (this.flowMap != null) {
            Iterator var1 = this.flowMap.values().iterator();

            while(var1.hasNext()) {
                FlowDefinition var2 = (FlowDefinition)var1.next();
                var2.initForActiveVersion();
            }

            if (this.aloneRetes != null) {
                var1 = this.aloneRetes.iterator();

                while(var1.hasNext()) {
                    Rete var3 = (Rete)var1.next();
                    this.initSingleReteForActiveVersion(var3);
                }

            }
        }
    }

    private void initSingleReteForActiveVersion(Rete var1) {
        this.initReteForActiveVersion(var1);
        this.initReteUnitsForActiveVersion(var1.getMutexGroupRetesMap());
        this.initReteUnitsForActiveVersion(var1.getPendedGroupRetesMap());
    }

    private void initReteUnitsForActiveVersion(Map<String, List<ReteUnit>> var1) {
        if (var1 != null) {
            Iterator var2 = var1.values().iterator();

            while(var2.hasNext()) {
                List var3 = (List)var2.next();
                Iterator var4 = var3.iterator();

                while(var4.hasNext()) {
                    ReteUnit var5 = (ReteUnit)var4.next();
                    Rete var6 = var5.getRete();
                    this.initReteForActiveVersion(var6);
                }
            }

        }
    }

    private void initReteForActiveVersion(Rete var1) {
        List var2 = var1.getObjectTypeNodes();
        Iterator var3 = var2.iterator();

        while(var3.hasNext()) {
            BaseReteNode var4 = (BaseReteNode)var3.next();
            this.buildChildrenNodes(var4);
        }

        if (this.flowMap != null) {
            var3 = this.flowMap.values().iterator();

            while(var3.hasNext()) {
                FlowDefinition var5 = (FlowDefinition)var3.next();
                var5.initForActiveVersion();
            }

        }
    }

    private void buildChildrenNodes(BaseReteNode var1) {
        List var2 = var1.getLines();
        if (var2 != null) {
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
                Line var4 = (Line)var3.next();
                Node var5 = var4.getTo();
                if (var5 instanceof ReteNode) {
                    var1.getChildrenNodes().add((ReteNode)var5);
                }

                if (var5 instanceof BaseReteNode) {
                    BaseReteNode var6 = (BaseReteNode)var5;
                    this.buildChildrenNodes(var6);
                }
            }

        }
    }

    public String getId() {
        return this.id;
    }

    public Rete getRete() {
        return this.rete;
    }

    public void setRete(Rete var1) {
        this.rete = var1;
    }

    public List<Rete> getAloneRetes() {
        return this.aloneRetes;
    }

    public void setAloneRetes(List<Rete> var1) {
        this.aloneRetes = var1;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long var1) {
        this.timestamp = var1;
    }

    public void resetTimestamp() {
        this.timestamp = System.currentTimeMillis();
    }

    public String getPackageInfo() {
        return this.packageInfo;
    }

    public void setPackageInfo(String var1) {
        this.packageInfo = var1;
    }

    public void setMonitor(boolean var1) {
        this.monitor = var1;
    }

    public boolean isMonitor() {
        return this.monitor;
    }

    public boolean isShowLog() {
        return this.showLog;
    }

    public void setShowLog(boolean var1) {
        this.showLog = var1;
    }

    public boolean isShowMatchedRuleList() {
        return this.showMatchedRuleList;
    }

    public void setShowMatchedRuleList(boolean var1) {
        this.showMatchedRuleList = var1;
    }

    public boolean isShowNotMatchRuleList() {
        return this.showNotMatchRuleList;
    }

    public void setShowNotMatchRuleList(boolean var1) {
        this.showNotMatchRuleList = var1;
    }

    public boolean isShowFiredFlowNodeList() {
        return this.showFiredFlowNodeList;
    }

    public void setShowFiredFlowNodeList(boolean var1) {
        this.showFiredFlowNodeList = var1;
    }

    public List<MonitorObject> getInputData() {
        return this.inputData;
    }

    public void setInputData(List<MonitorObject> var1) {
        this.inputData = var1;
    }

    public List<MonitorObject> getOutputData() {
        return this.outputData;
    }

    public void setOutputData(List<MonitorObject> var1) {
        this.outputData = var1;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String var1) {
        this.version = var1;
    }

    public String getVersionComment() {
        return this.versionComment;
    }

    public void setVersionComment(String var1) {
        this.versionComment = var1;
    }

    public Date getVersionCreateDate() {
        return this.versionCreateDate;
    }

    public void setVersionCreateDate(Date var1) {
        this.versionCreateDate = var1;
    }

    public String getVersionCreateUser() {
        return this.versionCreateUser;
    }

    public void setVersionCreateUser(String var1) {
        this.versionCreateUser = var1;
    }

    public Map<String, String> getVariableCateogoryMap() {
        return this.variableCategoryMap;
    }

    public void setVariableCategoryMap(Map<String, String> var1) {
        this.variableCategoryMap = var1;
    }

    public Map<String, FlowDefinition> getFlowMap() {
        return this.flowMap;
    }

    public void setFlowMap(Map<String, FlowDefinition> var1) {
        this.flowMap = var1;
    }

    public ReteInstance newReteInstance() {
        return this.rete.newReteInstance();
    }

    public List<ReteInstance> newAloneReteInstances() {
        ArrayList var1 = new ArrayList();
        if (this.aloneRetes == null) {
            return var1;
        } else {
            Iterator var2 = this.aloneRetes.iterator();

            while(var2.hasNext()) {
                Rete var3 = (Rete)var2.next();
                var1.add(var3.newReteInstance());
            }

            return var1;
        }
    }

    public void setParameters(Map<String, String> var1) {
        this.parameters = var1;
    }

    public void setVariableCategories(List<VariableCategory> var1) {
        this.variableCategories = var1;
    }

    public List<VariableCategory> getVariableCategories() {
        return this.variableCategories;
    }

    public Map<String, String> getParameters() {
        return this.parameters;
    }
}
