//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.console;

import com.bstek.urule.console.servlet.RenderPageServletHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class ConsoleServletHandler extends RenderPageServletHandler {
	private DebugMessageHolder a;

	public ConsoleServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("key");
		String var4 = null;
		if (StringUtils.isBlank(var3)) {
			var4 = "<h2 style='color:red'>请指定要查看的调试消息的key值</h2>";
		} else {
			var4 = this.a.getDebugMessage(var3);
		}

		VelocityContext var5 = new VelocityContext();
		var5.put("title", "URule Console");
		var5.put("msg", var4);
		var2.setContentType("text/html");
		var2.setCharacterEncoding("utf-8");
		Template var6 = this.ve.getTemplate("html/console.html", "utf-8");
		PrintWriter var7 = var2.getWriter();
		var6.merge(var5, var7);
		var7.close();
	}

	public void setDebugMessageHolder(DebugMessageHolder var1) {
		this.a = var1;
	}

	public String url() {
		return "/console";
	}
}
