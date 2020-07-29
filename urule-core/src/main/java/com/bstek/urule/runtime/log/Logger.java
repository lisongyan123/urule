//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

import com.bstek.urule.Utils;
import com.bstek.urule.model.flow.DecisionNode;
import com.bstek.urule.model.flow.FlowNode;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.EvaluateResponse;
import com.bstek.urule.runtime.KnowledgeSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Logger {
    private UnitLog a;
    private List<Log> b = new ArrayList();

    public Logger(KnowledgeSession var1) {
        if (var1 != null) {
            Logger var2 = var1.getLogManager().getLogger();
            this.a = new UnitLog();
            var2.a(this.a);
        }

    }

    public void logCriteria(Criteria var1, EvaluateResponse var2) {
        if (Utils.isDebug()) {
            this.a(new CriteriaLog(var1, var2));
        }
    }

    public void logMatchRule(Rule var1, Set<Criteria> var2) {
        if (Utils.isDebug()) {
            if (!var1.getFile().endsWith(".rl.xml")) {
                this.a(new MatchedRuleLog(var1, var2));
            }
        }
    }

    public void logExecuteRule(Rule var1) {
        if (Utils.isDebug()) {
            this.a(new ExecuteRuleLog(var1));
        }
    }

    public void logDecisionNodeMatch(DecisionNode var1, String var2, String var3) {
        if (Utils.isDebug()) {
            this.a(new DecisionNodeMatchLog(var1, var2, var3));
        }
    }

    public void logFlowNode(FlowNode var1, String var2, boolean var3) {
        if (Utils.isDebug()) {
            this.a(new FlowNodeLog(var1, var2, var3));
        }
    }

    public void logConsoleOutput(Object var1) {
        if (Utils.isDebug()) {
            this.a(new ConsoleOutputLog(var1));
        }
    }

    public void logMessage(String var1) {
        if (Utils.isDebug()) {
            this.a(new MessageLog(var1));
        }
    }

    public void logAddRuleToExecuteQueue(Rule var1, boolean var2) {
        if (Utils.isDebug()) {
            this.a(new AddRuleToExecuteQueueLog(var1, var2));
        }
    }

    public void logExecuteFunction(String var1, Object var2) {
        if (Utils.isDebug()) {
            this.a(new ExecuteFunctionLog(var1, var2));
        }
    }

    public void logExecuteBeanMethod(String var1, String var2) {
        if (Utils.isDebug()) {
            this.a(new ExecuteBeanMethodLog(var1, var2));
        }
    }

    public void logValueAssign(String var1, Object var2) {
        if (Utils.isDebug()) {
            this.a(new ValueAssignLog(var1, var2));
        }
    }

    public void logScoreCard(String var1, String var2) {
        if (Utils.isDebug()) {
            this.a(new ScoreCardLog(var1, var2));
        }
    }

    public void logExecuteScoreCard(int var1, Object var2) {
        if (Utils.isDebug()) {
            this.a(new ExcecuteScoreCardLog(var1, var2));
        }
    }

    public void logScoreCardSum(String var1, Object var2) {
        if (Utils.isDebug()) {
            this.a(new ScoreCardSumLog(var1, var2));
        }
    }

    public void logScoreCardBean(String var1) {
        if (Utils.isDebug()) {
            this.a(new ScoreCardBean(var1));
        }
    }

    private void a(Log var1) {
        this.b.add(var1);
        if (this.a != null) {
            this.a.addLog(var1);
        }

    }

    public UnitLog getLogUnit() {
        return this.a;
    }

    public List<Log> getLogs() {
        return this.b;
    }
}
