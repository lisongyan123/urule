//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.builtinaction;

import com.bstek.urule.model.library.action.annotation.ActionBean;
import com.bstek.urule.model.library.action.annotation.ActionMethod;
import com.bstek.urule.model.library.action.annotation.ActionMethodParameter;

@ActionBean(
        name = "调用"
)
public class InvokeAction {
    public InvokeAction() {
    }

    @ActionMethod(
            name = "知识包"
    )
    @ActionMethodParameter(
            names = {"知识包"}
    )
    public void invokeKnowledgePackage(String var1) {
        System.out.println("invoke knowledge package : " + var1);
    }
}
