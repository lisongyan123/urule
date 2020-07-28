package com.bstek.urule.console.servlet.common;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ScriptErrorListener extends BaseErrorListener {
	private List<ErrorInfo> a = new ArrayList();

	public ScriptErrorListener() {
	}

	public void syntaxError(Recognizer<?, ?> var1, Object var2, int var3, int var4, String var5, RecognitionException var6) {
		this.a.add(new ErrorInfo(var3, var4, var5));
	}

	public List<ErrorInfo> getInfos() {
		return this.a;
	}
}
