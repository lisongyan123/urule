//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.rule.lhs.Lhs;

public class DecisionItem {
    public static final String RETURN_VALUE_KEY = "return_to__";
    private String conditionType = "script";
    private String script;
    private Lhs lhs;
    private String lhsXml;
    private int percent;
    private String to;

    public DecisionItem() {
    }

    public String getScript() {
        return this.script;
    }

    public void setScript(String var1) {
        this.script = var1;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String var1) {
        this.to = var1;
    }

    public int getPercent() {
        return this.percent;
    }

    public void setPercent(int var1) {
        this.percent = var1;
    }

    public String getConditionType() {
        return this.conditionType;
    }

    public void setConditionType(String var1) {
        this.conditionType = var1;
    }

    public void setLhs(Lhs var1) {
        this.lhs = var1;
    }

    public Lhs getLhs() {
        return this.lhs;
    }

    public String getLhsXml() {
        return this.lhsXml;
    }

    public void setLhsXml(String var1) {
        this.lhsXml = var1;
    }

    public String buildDSLScript(int var1, boolean var2, String var3, String var4) {
        StringBuffer var5 = new StringBuffer();
        var5.append("rule \"" + var3 + "-" + var4 + "-decision" + var1 + "\"");
        if (var2) {
            var5.append(" debug=true ");
        }

        var5.append(" ");
        var5.append("if");
        var5.append(" ");
        var5.append(this.script);
        var5.append(" ");
        var5.append("then");
        var5.append(" ");
        var5.append("parameter.return_to__=\"" + this.to + "\"");
        var5.append(" ");
        var5.append("end");
        return var5.toString();
    }
}
