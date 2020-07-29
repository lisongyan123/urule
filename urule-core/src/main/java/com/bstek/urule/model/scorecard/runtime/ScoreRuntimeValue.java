//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.scorecard.runtime;

public class ScoreRuntimeValue {
    public static final String SCORE_VALUE = "scoring_value";
    private int rowNumber;
    private String name;
    private String weight;
    private Object value;

    public ScoreRuntimeValue(int var1, String var2, String var3, Object var4) {
        this.rowNumber = var1;
        this.name = var2;
        this.weight = var3;
        this.value = var4;
    }

    public int getRowNumber() {
        return this.rowNumber;
    }

    public String getName() {
        return this.name;
    }

    public String getWeight() {
        return this.weight;
    }

    public Object getValue() {
        return this.value;
    }
}
