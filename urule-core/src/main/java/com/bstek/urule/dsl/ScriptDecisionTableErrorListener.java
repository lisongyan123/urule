//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ScriptDecisionTableErrorListener extends BaseErrorListener {
    private StringBuffer a;

    public ScriptDecisionTableErrorListener() {
    }

    public void syntaxError(Recognizer<?, ?> var1, Object var2, int var3, int var4, String var5, RecognitionException var6) {
        if (this.a == null) {
            this.a = new StringBuffer();
        }

        this.a.append("[" + var2 + "] is invalid:" + var5);
        this.a.append("\r\n");
    }

    public String getErrorMessage() {
        return this.a == null ? null : this.a.toString();
    }
}
