//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.scriptdecisiontable;

import com.bstek.urule.console.servlet.RenderPageServletHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class ScriptDecisiontableEditorServletHandler extends RenderPageServletHandler {
	public ScriptDecisiontableEditorServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = this.retriveMethod(var1);
		if (var3 != null) {
			this.invokeMethod(var3, var1, var2);
		} else {
			VelocityContext var4 = new VelocityContext();
			var4.put("contextPath", var1.getContextPath());
			String var5 = var1.getParameter("file");
			String var6 = this.buildProjectNameFromFile(var5);
			if (var6 != null) {
				var4.put("project", var6);
			}

			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var7 = this.ve.getTemplate("html/scriptdecisiontable-editor.html", "utf-8");
			PrintWriter var8 = var2.getWriter();
			var7.merge(var4, var8);
			var8.close();
		}

	}

	public String url() {
		return "/scriptdecisiontableeditor";
	}
}
