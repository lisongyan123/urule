//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.ruleset;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.Repository;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.repository.model.Type;
import com.bstek.urule.console.servlet.DesignerConfigure;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.tika.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class RuleSetServletEditorHandler extends RenderPageServletHandler {
	private RepositoryService a;

	public RuleSetServletEditorHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = this.retriveMethod(var1);
		if (var3 != null) {
			this.invokeMethod(var3, var1, var2);
		} else {
			Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
			VelocityContext var5 = new VelocityContext();
			var5.put("isAdmin", var4.isAdmin());
			var5.put("contextPath", var1.getContextPath());
			var5.put("version", Splash.getVersion());
			var5.put("constantLink", DesignerConfigure.constantLink);
			var5.put("variableLink", DesignerConfigure.variableLink);
			var5.put("_date_", _DATE);
			var5.put("_lis_", Splash.getFetchVersion());
			String var6 = var1.getParameter("file");
			var6 = Utils.decodeURL(var6);
			String var7 = this.buildProjectNameFromFile(var6);
			if (var7 != null) {
				var5.put("project", var7);
			}

			var5.put("showFileExtensionName", PropertyConfigurer.getProperty("urule.show.fileExtensionName"));
			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var8 = this.ve.getTemplate("html/ruleset-editor.html", "utf-8");
			PrintWriter var9 = var2.getWriter();
			var8.merge(var5, var9);
			var9.close();
		}

	}

	public void loadProjectPendedGroups(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("project");
		var3 = Utils.decodeURL(var3);
		Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		Repository var5 = this.a.loadRepository(var3, var4, false, new FileType[]{FileType.Ruleset}, (String)null);
		RepositoryFile var6 = var5.getRootFile();
		HashMap var7 = new HashMap();
		this.a(var6, var7);
		this.writeObjectToJson(var2, var7);
	}

	private void a(RepositoryFile var1, Map<String, String> var2) throws Exception {
		if (var1.getType().equals(Type.rule)) {
			String var3 = var1.getFullPath();
			this.parseResource(var3, var2);
		}

		List var6 = var1.getChildren();
		if (var6 != null) {
			Iterator var4 = var6.iterator();

			while(var4.hasNext()) {
				RepositoryFile var5 = (RepositoryFile)var4.next();
				this.a(var5, var2);
			}

		}
	}

	protected void parseResource(String var1, Map<String, String> var2) throws Exception {
		InputStream var3 = this.a.readFile(var1);
		SAXReader var4 = new SAXReader();
		Document var5 = var4.read(var3);
		Element var6 = var5.getRootElement();
		Iterator var7 = var6.elements().iterator();

		while(true) {
			Element var9;
			String var10;
			do {
				Object var8;
				do {
					do {
						if (!var7.hasNext()) {
							IOUtils.closeQuietly(var3);
							return;
						}

						var8 = var7.next();
					} while(var8 == null);
				} while(!(var8 instanceof Element));

				var9 = (Element)var8;
				var10 = var9.getName();
			} while(!var10.equals("rule") && !var10.equals("loop-rule"));

			String var11 = var9.attributeValue("pended-group");
			if (StringUtils.isNotBlank(var11)) {
				if (var2.containsKey(var11)) {
					String var12 = (String)var2.get(var11);
					if (var12.indexOf(var1) == -1) {
						var2.put(var11, var12 + ",<br>" + var1);
					}
				} else {
					var2.put(var11, var1);
				}
			}
		}
	}

	public void setRepositoryService(RepositoryService var1) {
		this.a = var1;
	}

	public String url() {
		return "/ruleseteditor";
	}
}
