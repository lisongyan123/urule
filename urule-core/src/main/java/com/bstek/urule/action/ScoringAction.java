//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.scorecard.runtime.ScoreRuntimeValue;
import com.bstek.urule.runtime.rete.Context;
import com.bstek.urule.runtime.rete.ValueCompute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ScoringAction extends AbstractAction {
    private Value a;
    private int b;
    private String c;
    private String d;
    private ActionType e;
    public static final String SCORE_CARD_RUNTIME_VALUE = "_score_card_runtime_value_";

    public ScoringAction(int var1, String var2, String var3) {
        this.e = ActionType.Scoring;
        this.b = var1;
        this.c = var2;
        this.d = var3;
    }

    public ActionValue execute(Context var1, Map<String, Object> var2) {
        ValueCompute var3 = (ValueCompute)var1.getApplicationContext().getBean("urule.valueCompute");
        Object var4 = var3.complexValueCompute(this.a, var1, var2);
        ScoreRuntimeValue var5 = new ScoreRuntimeValue(this.b, this.c, this.d, var4);
        Map var6 = var1.getWorkingMemory().getParameters();
        Object var7 = null;
        if (var6.containsKey("_score_card_runtime_value_")) {
            var7 = (List)var6.get("_score_card_runtime_value_");
        } else {
            var7 = new ArrayList();
            var6.put("_score_card_runtime_value_", var7);
        }

        ((List)var7).add(var5);
        return null;
    }

    public Value getValue() {
        return this.a;
    }

    public void setValue(Value var1) {
        this.a = var1;
    }

    public String getName() {
        return this.c;
    }

    public String getWeight() {
        return this.d;
    }

    public ActionType getActionType() {
        return this.e;
    }

    public int getRowNumber() {
        return this.b;
    }
}
