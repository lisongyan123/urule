//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.ul;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.builder.ResourceLibraryBuilder;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.dsl.DSLRuleSetBuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.rule.RuleSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class ULEditorServletHandler extends RenderPageServletHandler {
	private DSLRuleSetBuilder a;
	private ResourceLibraryBuilder b;
	private RepositoryService c;

	public ULEditorServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = this.retriveMethod(var1);
		if (var3 != null) {
			this.invokeMethod(var3, var1, var2);
		} else {
			VelocityContext var4 = new VelocityContext();
			var4.put("contextPath", var1.getContextPath());
			var4.put("version", Splash.getVersion());
			var4.put("_lis_", Splash.getFetchVersion());
			String var5 = var1.getParameter("file");
			String var6 = this.buildProjectNameFromFile(var5);
			if (var6 != null) {
				var4.put("project", var6);
			}

			var4.put("showFileExtensionName", PropertyConfigurer.getProperty("urule.show.fileExtensionName"));
			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var7 = this.ve.getTemplate("html/ul-editor.html", "utf-8");
			PrintWriter var8 = var2.getWriter();
			var7.merge(var4, var8);
			var8.close();
		}

	}

	public void loadUL(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("file");
		var3 = Utils.decodeURL(var3);
		ServletOutputStream var4 = var2.getOutputStream();
		String var5 = var1.getParameter("version");
		InputStream var6 = null;

		try {
			if (StringUtils.isEmpty(var5)) {
				var6 = this.c.readFile(var3, (String)null);
			} else {
				var6 = this.c.readFile(var3, var5);
			}

			IOUtils.copy(var6, var4);
		} catch (Exception var11) {
			throw new RuleException(var11);
		} finally {
			var4.close();
			var6.close();
		}

	}

	public void loadULLibs(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("content");
		RuleSet var4 = this.a.build(var3, (String)null);
		ResourceLibrary var5 = this.b.buildResourceLibrary(var4.getLibraries());
		this.writeObjectToJson(var2, var5);
	}

	public void setDslRuleSetBuilder(DSLRuleSetBuilder var1) {
		this.a = var1;
	}

	public void setResourceLibraryBuilder(ResourceLibraryBuilder var1) {
		this.b = var1;
	}

	public void setRepositoryService(RepositoryService var1) {
		this.c = var1;
	}

	public String url() {
		return "/uleditor";
	}
}
