//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.parameter;

import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.Repository;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.console.servlet.common.CommonServletHandler;
import com.bstek.urule.model.library.variable.VariableCategory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class ParameterServletHandler extends RenderPageServletHandler {
	private RepositoryService a;
	private CommonServletHandler b;

	public ParameterServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = this.retriveMethod(var1);
		if (var3 != null) {
			this.invokeMethod(var3, var1, var2);
		} else {
			VelocityContext var4 = new VelocityContext();
			var4.put("contextPath", var1.getContextPath());
			var4.put("version", Splash.getVersion());
			var4.put("_date_", _DATE);
			var4.put("_lis_", Splash.getFetchVersion());
			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var5 = this.ve.getTemplate("html/parameter-editor.html", "utf-8");
			PrintWriter var6 = var2.getWriter();
			var5.merge(var4, var6);
			var6.close();
		}

	}

	public void loadProjectVariableCategories(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("file");
		var3 = Utils.decodeURL(var3);
		String var4 = this.buildProjectNameFromFile(var3);
		var4 = Utils.decodeURL(var4);
		Principal var5 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		Repository var6 = this.a.loadRepository(var4, var5, false, new FileType[]{FileType.VariableLibrary}, (String)null);
		ArrayList var7 = new ArrayList();
		this.a(var6.getRootFile().getChildren(), var7);
		this.writeObjectToJson(var2, var7);
	}

	private void a(List<RepositoryFile> var1, List<VariableCategory> var2) {
		if (var1 != null) {
			RepositoryFile var4;
			for(Iterator var3 = var1.iterator(); var3.hasNext(); this.a(var4.getChildren(), var2)) {
				var4 = (RepositoryFile)var3.next();
				String var5 = var4.getFullPath();
				if (var5.endsWith(FileType.VariableLibrary.toString())) {
					List var6 = this.b.buildData("jcr:" + var5);
					Iterator var7 = var6.iterator();

					while(var7.hasNext()) {
						Object var8 = var7.next();
						var2.addAll((List)var8);
					}
				}
			}

		}
	}

	public void setRepositoryService(RepositoryService var1) {
		this.a = var1;
	}

	public void setCommonServletHandler(CommonServletHandler var1) {
		this.b = var1;
	}

	public String url() {
		return "/parametereditor";
	}
}
