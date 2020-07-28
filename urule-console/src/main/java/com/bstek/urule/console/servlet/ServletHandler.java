//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ServletHandler {
	long _DATE = System.currentTimeMillis();

	boolean anonymousAccess();

	void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException;

	String url();
}
