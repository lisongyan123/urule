package com.bstek.urule.console.servlet;

import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rete.RuleDueException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServletHandler implements ServletHandler {
	public BaseServletHandler() {
	}

	protected void invokeMethod(String var1, HttpServletRequest var2, HttpServletResponse var3) {
		try {
			Method var4 = this.getClass().getMethod(var1, HttpServletRequest.class, HttpServletResponse.class);
			var4.invoke(this, var2, var3);
		} catch (Exception var7) {
			Throwable var6 = this.a(var7);
			if (var6 instanceof NoPermissionException) {
				throw (NoPermissionException)var6;
			} else if (var6 instanceof RuleDueException) {
				throw (RuleDueException)var6;
			} else if (var6 instanceof RuleException) {
				throw (RuleException)var6;
			} else {
				throw new RuleException(var7);
			}
		}
	}

	private Throwable a(Throwable var1) {
		return var1.getCause() == null ? var1 : this.a(var1.getCause());
	}

	public boolean anonymousAccess() {
		return false;
	}

	protected String retriveMethod(HttpServletRequest var1) throws ServletException {
		String var2 = var1.getContextPath() + "/urule";
		String var3 = var1.getRequestURI();
		String var4 = var3.substring(var2.length());
		int var5 = var4.indexOf("/", 1);
		if (var5 > -1) {
			String var6 = var4.substring(var5 + 1).trim();
			return var6.length() > 0 ? var6 : null;
		} else {
			return null;
		}
	}
}
