//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.builtinaction;

import com.bstek.urule.action.ActionId;
import com.bstek.urule.model.library.action.annotation.ActionBean;
import com.bstek.urule.model.library.action.annotation.ActionMethod;
import com.bstek.urule.model.library.action.annotation.ActionMethodParameter;

@ActionBean(
        name = "循环操作"
)
public class LoopAction {
    public static final String BREAK_LOOP_ACTION_ID = "_loop_rule_break_tag__";

    public LoopAction() {
    }

    @ActionMethod(
            name = "中断循环"
    )
    @ActionMethodParameter(
            names = {}
    )
    @ActionId("_loop_rule_break_tag__")
    public String breakLoop() {
        return "break";
    }
}
