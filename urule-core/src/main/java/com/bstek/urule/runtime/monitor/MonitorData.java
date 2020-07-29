//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.monitor;

import com.bstek.urule.model.rete.RuleData;
import com.bstek.urule.runtime.log.FlowNodeLog;
import com.bstek.urule.runtime.log.Log;
import com.bstek.urule.runtime.log.MatchedRuleLog;
import java.util.Date;
import java.util.List;

public interface MonitorData {
    String getPackageInfo();

    long getTotalDuration();

    String getVersion();

    String getVersionComment();

    Date getVersionCreateDate();

    String getVersionCreateUser();

    List<MatchedRuleLog> getMatchedRuleList();

    List<RuleData> getNotMatchRuleList();

    List<FlowNodeLog> getFiredFlowNodeList();

    boolean isShowLog();

    boolean isShowMatchedRuleList();

    boolean isShowNotMatchRuleList();

    boolean isShowFiredFlowNodeList();

    List<Log> getLogs();

    List<IOData> getInputData();

    List<IOData> getOutputData();
}
