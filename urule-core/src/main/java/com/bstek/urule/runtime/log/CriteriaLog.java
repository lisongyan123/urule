//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.log;

import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.EvaluateResponse;

public class CriteriaLog extends DataLog {
    public CriteriaLog(Criteria var1, EvaluateResponse var2) {
        String var3 = var1.getId();
        StringBuffer var4 = new StringBuffer();
        var4.append("^^条件：" + var3);
        String var5 = var2.getResult() ? "满足" : "不满足";
        var4.append("➤" + var5);
        var4.append(", 左值：" + (var2.getLeftResult() == null ? "null" : var2.getLeftResult()));
        if (var2.getOp() != null) {
            var4.append("【" + var2.getOp().toString() + "】");
        }

        var4.append(" 右值：" + (var2.getRightResult() == null ? "null" : var2.getRightResult()));
        this.msg = var4.toString();
    }
}
