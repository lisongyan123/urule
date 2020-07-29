//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.builtinaction;

import com.bstek.urule.Utils;
import com.bstek.urule.model.library.action.annotation.ActionBean;
import com.bstek.urule.model.library.action.annotation.ActionMethod;
import com.bstek.urule.model.library.action.annotation.ActionMethodParameter;
import java.math.BigDecimal;
import java.math.RoundingMode;

@ActionBean(
        name = "数学函数"
)
public class MathAction {
    public MathAction() {
    }

    @ActionMethod(
            name = "求绝对值"
    )
    @ActionMethodParameter(
            names = {"数字"}
    )
    public Number abs(Object var1) {
        BigDecimal var2 = Utils.toBigDecimal(var1);
        return Math.abs(var2.doubleValue());
    }

    @ActionMethod(
            name = "求最大值"
    )
    @ActionMethodParameter(
            names = {"数字1", "数字2"}
    )
    public Number max(Object var1, Object var2) {
        BigDecimal var3 = Utils.toBigDecimal(var1);
        BigDecimal var4 = Utils.toBigDecimal(var2);
        return Math.max(var3.doubleValue(), var4.doubleValue());
    }

    @ActionMethod(
            name = "求最小值"
    )
    @ActionMethodParameter(
            names = {"数字1", "数字2"}
    )
    public Number min(Object var1, Object var2) {
        BigDecimal var3 = Utils.toBigDecimal(var1);
        BigDecimal var4 = Utils.toBigDecimal(var2);
        return Math.min(var3.doubleValue(), var4.doubleValue());
    }

    @ActionMethod(
            name = "求正弦"
    )
    @ActionMethodParameter(
            names = {"数字"}
    )
    public Number in(Object var1) {
        BigDecimal var2 = Utils.toBigDecimal(var1);
        return Math.sin(var2.doubleValue());
    }

    @ActionMethod(
            name = "求余弦"
    )
    @ActionMethodParameter(
            names = {"数字"}
    )
    public Number cos(Object var1) {
        BigDecimal var2 = Utils.toBigDecimal(var1);
        return Math.cos(var2.doubleValue());
    }

    @ActionMethod(
            name = "求正切"
    )
    @ActionMethodParameter(
            names = {"数字"}
    )
    public Number tan(Object var1) {
        BigDecimal var2 = Utils.toBigDecimal(var1);
        return Math.tan(var2.doubleValue());
    }

    @ActionMethod(
            name = "求余切"
    )
    @ActionMethodParameter(
            names = {"数字"}
    )
    public Number cot(Object var1) {
        BigDecimal var2 = Utils.toBigDecimal(var1);
        return 1.0D / Math.tan(var2.doubleValue());
    }

    @ActionMethod(
            name = "求e为底的对数"
    )
    @ActionMethodParameter(
            names = {"数字"}
    )
    public Number log(Object var1) {
        BigDecimal var2 = Utils.toBigDecimal(var1);
        return Math.log(var2.doubleValue());
    }

    @ActionMethod(
            name = "求10为底的对数"
    )
    @ActionMethodParameter(
            names = {"数字"}
    )
    public Number log10(Object var1) {
        BigDecimal var2 = Utils.toBigDecimal(var1);
        return Math.log10(var2.doubleValue());
    }

    @ActionMethod(
            name = "向上取整"
    )
    @ActionMethodParameter(
            names = {"数字"}
    )
    public Number round(Object var1) {
        BigDecimal var2 = Utils.toBigDecimal(var1);
        return (new BigDecimal(Math.round(var2.doubleValue()))).stripTrailingZeros();
    }

    @ActionMethod(
            name = "四舍五入"
    )
    @ActionMethodParameter(
            names = {"数字", "小数位数"}
    )
    public Number halfUp(Object var1, int var2) {
        BigDecimal var3 = Utils.toBigDecimal(var1);
        return var3.setScale(var2, RoundingMode.HALF_UP);
    }
}
