//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.rete.Rete;
import com.bstek.urule.runtime.monitor.MonitorObject;
import com.bstek.urule.runtime.rete.ReteInstance;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface KnowledgePackage {
    Rete getRete();

    List<Rete> getAloneRetes();

    String getPackageInfo();

    boolean isMonitor();

    boolean isShowLog();

    boolean isShowMatchedRuleList();

    boolean isShowNotMatchRuleList();

    boolean isShowFiredFlowNodeList();

    String getVersion();

    String getVersionComment();

    Date getVersionCreateDate();

    String getVersionCreateUser();

    List<MonitorObject> getInputData();

    List<MonitorObject> getOutputData();

    Map<String, String> getVariableCateogoryMap();

    Map<String, FlowDefinition> getFlowMap();

    Map<String, String> getParameters();

    ReteInstance newReteInstance();

    List<ReteInstance> newAloneReteInstances();

    long getTimestamp();

    void resetTimestamp();

    String getId();
}
