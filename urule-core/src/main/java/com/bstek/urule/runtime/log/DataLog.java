//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

public abstract class DataLog implements Log {
    protected String msg;

    public DataLog() {
    }

    public String getMsg() {
        return this.msg;
    }

    public String getHtmlMsg() {
        String var1 = "#000";
        if (this instanceof CriteriaLog) {
            var1 = "#6495ED";
        } else if (this instanceof ConsoleOutputLog) {
            var1 = "#000";
        } else if (this instanceof ExecuteBeanMethodLog) {
            var1 = "#8A2BE2";
        } else if (this instanceof ExecuteFunctionLog) {
            var1 = "#008B8B";
        } else if (this instanceof FlowNodeLog) {
            var1 = "#9932CC";
        } else if (this instanceof ValueAssignLog) {
            var1 = "#FF7F50";
        } else if (this instanceof ScoreCardBean) {
            var1 = "#40E0D0";
        } else if (this instanceof ExcecuteScoreCardLog) {
            var1 = "#40E0D0";
        } else if (this instanceof ScoreCardSumLog) {
            var1 = "#40E0D0";
        } else if (this instanceof MatchedRuleLog) {
            var1 = "#d48746";
        } else if (this instanceof AddRuleToExecuteQueueLog) {
            AddRuleToExecuteQueueLog var2 = (AddRuleToExecuteQueueLog)this;
            if (var2.isAdd()) {
                var1 = "#13d8c6";
            } else {
                var1 = "#FF5722";
            }
        }

        return "<div style=\"color:" + var1 + ";margin-top:2px\">" + this.msg + "</div>";
    }
}
