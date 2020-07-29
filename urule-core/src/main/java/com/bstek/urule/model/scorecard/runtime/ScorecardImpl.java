//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.scorecard.runtime;

import com.bstek.urule.Utils;
import com.bstek.urule.runtime.rete.Context;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class ScorecardImpl implements Scorecard {
    private String name;
    private boolean debug;
    private List<RowItem> rowItems;

    public ScorecardImpl(String var1, List<RowItem> var2, boolean var3) {
        this.name = var1;
        this.rowItems = var2;
        this.debug = var3;
    }

    public BigDecimal executeSum(Context var1) {
        BigDecimal var2 = null;
        Iterator var3 = this.rowItems.iterator();

        while(var3.hasNext()) {
            RowItem var4 = (RowItem)var3.next();
            BigDecimal var5 = Utils.toBigDecimal(var4.getScore());
            var4.setActualScore(var5);
            if (var2 == null) {
                var2 = var5;
            } else {
                var2 = var2.add(var5);
            }
        }

        if (this.debug) {
            var1.getLogger().logScoreCardSum(this.name, var2);
        }

        if (var2 == null) {
            return new BigDecimal(0);
        } else {
            return var2;
        }
    }

    public BigDecimal executeWeightSum(Context var1) {
        BigDecimal var2 = new BigDecimal(0);

        BigDecimal var7;
        for(Iterator var3 = this.rowItems.iterator(); var3.hasNext(); var2 = var2.add(var7)) {
            RowItem var4 = (RowItem)var3.next();
            BigDecimal var5 = Utils.toBigDecimal(var4.getScore());
            BigDecimal var6 = Utils.toBigDecimal(var4.getWeight());
            var7 = var5.multiply(var6);
            var4.setActualScore(var7);
        }

        if (this.debug) {
            var1.getLogger().logScoreCardSum(this.name, var2);
        }

        return var2;
    }

    public String getName() {
        return this.name;
    }

    public List<RowItem> getRowItems() {
        return this.rowItems;
    }
}
