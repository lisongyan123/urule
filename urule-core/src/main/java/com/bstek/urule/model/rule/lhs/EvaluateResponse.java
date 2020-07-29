//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.model.rule.Op;

public class EvaluateResponse {
    private Op op;
    private boolean result;
    private Object leftResult;
    private Object rightResult;

    public EvaluateResponse() {
    }

    public Op getOp() {
        return this.op;
    }

    public void setOp(Op var1) {
        this.op = var1;
    }

    public void setLeftResult(Object var1) {
        this.leftResult = var1;
    }

    public void setRightResult(Object var1) {
        this.rightResult = var1;
    }

    public Object getLeftResult() {
        return this.leftResult;
    }

    public Object getRightResult() {
        return this.rightResult;
    }

    public void setResult(boolean var1) {
        this.result = var1;
    }

    public boolean getResult() {
        return this.result;
    }
}
