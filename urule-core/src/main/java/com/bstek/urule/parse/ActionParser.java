//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.action.Action;

public abstract class ActionParser extends AbstractParser<Action> {
    protected ValueParser valueParser;

    public ActionParser() {
    }

    public void setValueParser(ValueParser var1) {
        this.valueParser = var1;
    }
}
