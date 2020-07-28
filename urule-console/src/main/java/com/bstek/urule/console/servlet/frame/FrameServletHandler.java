//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.frame;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.repository.KnowledgePackageRepositoryService;
import com.bstek.urule.console.repository.Repository;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.repository.model.Type;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.cache.CacheUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class FrameServletHandler extends RenderPageServletHandler {
	private RepositoryService a;
	private KnowledgePackageRepositoryService b;
	private String c;
	private String d;
	private String e;
	private boolean f;
	private static final String g = "_lib_classify";

	public FrameServletHandler() {
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
			var4.put("welcomePage", this.c);
			var4.put("securityEnable", this.f);
			var4.put("title", this.e);
			var4.put("fileMenuExtension", PropertyConfigurer.getProperty("urule.file.menuExtension"));
			var4.put("fileMenuExtensionUrl", PropertyConfigurer.getProperty("urule.file.menuExtensionUrl"));
			var4.put("showFileExtensionName", PropertyConfigurer.getProperty("urule.show.fileExtensionName"));
			var4.put("cluster", PropertyConfigurer.getProperty("urule.cluster"));
			var4.put("logoutURL", this.d);
			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var5 = this.ve.getTemplate("html/frame.html", "utf-8");
			PrintWriter var6 = var2.getWriter();
			var5.merge(var4, var6);
			var6.close();
		}

	}

	public void fileVersions(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("path");
		var3 = Utils.decodeURL(var3);
		List var4 = this.a.getVersionFiles(var3);
		this.writeObjectToJson(var2, var4);
	}

	public void fileSource(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("path");
		var3 = Utils.decodeURL(var3);
		InputStream var4 = this.a.readFile(var3, (String)null);
		String var5 = IOUtils.toString(var4, "utf-8");
		var4.close();
		String var6 = null;

		try {
			Document var7 = DocumentHelper.parseText(var5);
			OutputFormat var8 = OutputFormat.createPrettyPrint();
			StringWriter var9 = new StringWriter();
			XMLWriter var10 = new XMLWriter(var9, var8);
			var10.write(var7);
			var6 = var9.toString();
		} catch (Exception var11) {
			var6 = var5;
		}

		HashMap var12 = new HashMap();
		var12.put("content", var6);
		this.writeObjectToJson(var2, var12);
	}

	public void importProject(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			PrintWriter var4 = var2.getWriter();
			var4.println("Permission denied!");
			var4.flush();
			var4.close();
		} else {
			DiskFileItemFactory var15 = new DiskFileItemFactory();
			ServletContext var5 = var1.getSession().getServletContext();
			File var6 = (File)var5.getAttribute("javax.servlet.context.tempdir");
			var15.setRepository(var6);
			ServletFileUpload var7 = new ServletFileUpload(var15);
			InputStream var8 = null;
			boolean var9 = true;
			List var10 = var7.parseRequest(var1);
			if (var10.size() == 0) {
				throw new ServletException("Upload file is invalid.");
			}

			Iterator var11 = var10.iterator();

			while(var11.hasNext()) {
				FileItem var12 = (FileItem)var11.next();
				String var13 = var12.getFieldName();
				if (var13.equals("overwriteProject")) {
					String var14 = new String(var12.get());
					var9 = Boolean.valueOf(var14);
				} else if (var13.equals("file")) {
					var8 = var12.getInputStream();
				}
			}

			this.a.importXml(var8, var9);
			IOUtils.closeQuietly(var8);
			CacheUtils.getKnowledgeCache().clean();
			var2.sendRedirect(var1.getContextPath() + "/urule/frame");
		}

	}

	public void loadFileVersions(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("file");
		var3 = Utils.decodeURL(var3);
		List var4 = this.a.getVersionFiles(var3);
		this.writeObjectToJson(var2, var4);
	}

	public void createFolder(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("fullFolderName");
		var3 = Utils.decodeURL(var3);
		Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		int var5 = var3.lastIndexOf(47);
		String var6 = var3.substring(0, var5);
		this.checkFullPermission(var4, var6);
		this.a.createDir(var3, var4);
		this.loadProjects(var1, var2);
	}

	public void copyFile(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("newFullPath");
		String var4 = var1.getParameter("oldFullPath");
		var3 = Utils.decodeURL(var3);
		var4 = Utils.decodeURL(var4);

		try {
			Principal var5 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
			this.checkFullPermission(var5, var4);
			InputStream var6 = this.a.readFile(var4, (String)null);
			String var7 = IOUtils.toString(var6, "utf-8");
			var6.close();
			this.a.createFile(var3, var7, var5);
			this.loadProjects(var1, var2);
		} catch (Exception var8) {
			throw new RuleException(var8);
		}
	}

	public void createFile(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("path");
		var3 = Utils.decodeURL(var3);
		Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		int var5 = var3.lastIndexOf(47);
		String var6 = var3.substring(0, var5);
		this.checkFullPermission(var4, var6);
		String var7 = var1.getParameter("type");
		FileType var8 = FileType.parse(var7);
		StringBuilder var9 = new StringBuilder();
		if (var8.equals(FileType.UL)) {
			var9.append("rule \"rule01\"");
			var9.append("\n");
			var9.append("if");
			var9.append("\r\n");
			var9.append("then");
			var9.append("\r\n");
			var9.append("end");
		} else if (var8.equals(FileType.DecisionTable)) {
			var9.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			var9.append("<decision-table>");
			var9.append("<cell row=\"0\" col=\"2\" rowspan=\"1\"></cell>");
			var9.append("<cell row=\"0\" col=\"1\" rowspan=\"1\">");
			var9.append("<joint type=\"and\"/>");
			var9.append("</cell>");
			var9.append("<cell row=\"0\" col=\"0\" rowspan=\"1\">");
			var9.append("<joint type=\"and\"/>");
			var9.append("</cell>");
			var9.append("<cell row=\"1\" col=\"2\" rowspan=\"1\">");
			var9.append("</cell>");
			var9.append("<cell row=\"1\" col=\"1\" rowspan=\"1\">");
			var9.append("<joint type=\"and\"/>");
			var9.append("</cell>");
			var9.append("<cell row=\"1\" col=\"0\" rowspan=\"1\">");
			var9.append("<joint type=\"and\"/>");
			var9.append("</cell>");
			var9.append("<row num=\"0\" height=\"40\"/>");
			var9.append("<row num=\"1\" height=\"40\"/>");
			var9.append("<col num=\"0\" width=\"150\" type=\"Criteria\"/>");
			var9.append("<col num=\"1\" width=\"150\" type=\"Criteria\"/>");
			var9.append("<col num=\"2\" width=\"200\" type=\"Assignment\"/>");
			var9.append("</decision-table>");
		} else if (var8.equals(FileType.DecisionTree)) {
			var9.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			var9.append("<decision-tree>");
			var9.append("<variable-tree-node></variable-tree-node>");
			var9.append("</decision-tree>");
		} else if (var8.equals(FileType.ScriptDecisionTable)) {
			var9.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			var9.append("<script-decision-table>");
			var9.append("<script-cell row=\"0\" col=\"2\" rowspan=\"1\"></script-cell>");
			var9.append("<script-cell row=\"0\" col=\"1\" rowspan=\"1\"></script-cell>");
			var9.append("<script-cell row=\"0\" col=\"0\" rowspan=\"1\"></script-cell>");
			var9.append("<script-cell row=\"1\" col=\"2\" rowspan=\"1\"></script-cell>");
			var9.append("<script-cell row=\"1\" col=\"1\" rowspan=\"1\"></script-cell>");
			var9.append("<script-cell row=\"1\" col=\"0\" rowspan=\"1\"></script-cell>");
			var9.append("<row num=\"0\" height=\"40\"/>");
			var9.append("<row num=\"1\" height=\"40\"/>");
			var9.append("<col num=\"0\" width=\"120\" type=\"Criteria\"/>");
			var9.append("<col num=\"1\" width=\"120\" type=\"Criteria\"/>");
			var9.append("<col num=\"2\" width=\"200\" type=\"Assignment\"/>");
			var9.append("</script-decision-table>");
		} else if (var8.equals(FileType.Scorecard)) {
			var9.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			var9.append("<scorecard scoring-type=\"sum\" assign-target-type=\"none\">");
			var9.append("</scorecard>");
		} else if (var8.equals(FileType.Crosstab)) {
			var9.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			var9.append("<crosstab>");
			var9.append("<header>LEFT &amp;&amp; TOP</header>");
			var9.append("<row number=\"1\" type=\"top\"/>");
			var9.append("<column number=\"1\" type=\"left\"/>");
			var9.append("<column number=\"2\" type=\"top\"/>");
			var9.append("<row number=\"2\" type=\"left\"/>");
			var9.append("<condition-cell row=\"1\" col=\"2\"/>");
			var9.append("<condition-cell row=\"2\" col=\"1\"/>");
			var9.append("<value-cell row=\"2\" col=\"2\"/>");
			var9.append("</crosstab>");
		} else if (var8.equals(FileType.ComplexScorecard)) {
			var9.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			var9.append("<complex-scorecard scoring-type=\"sum\" assign-target-type=\"none\">");
			var9.append("<cell row=\"0\" col=\"2\" rowspan=\"1\"></cell>");
			var9.append("<cell row=\"0\" col=\"1\" rowspan=\"1\">");
			var9.append("<joint type=\"and\"/>");
			var9.append("</cell>");
			var9.append("<cell row=\"0\" col=\"0\" rowspan=\"1\">");
			var9.append("<joint type=\"and\"/>");
			var9.append("</cell>");
			var9.append("<cell row=\"1\" col=\"2\" rowspan=\"1\">");
			var9.append("</cell>");
			var9.append("<cell row=\"1\" col=\"1\" rowspan=\"1\">");
			var9.append("<joint type=\"and\"/>");
			var9.append("</cell>");
			var9.append("<cell row=\"1\" col=\"0\" rowspan=\"1\">");
			var9.append("<joint type=\"and\"/>");
			var9.append("</cell>");
			var9.append("<row num=\"0\" height=\"40\"/>");
			var9.append("<row num=\"1\" height=\"40\"/>");
			var9.append("<col num=\"0\" width=\"150\" type=\"Criteria\"/>");
			var9.append("<col num=\"1\" width=\"150\" type=\"Criteria\"/>");
			var9.append("<col num=\"2\" width=\"120\" type=\"Score\"/>");
			var9.append("</complex-scorecard>");
		} else {
			String var10 = this.a(var8);
			var9.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
			var9.append("<" + var10 + ">");
			var9.append("</" + var10 + ">");
		}

		try {
			this.a.createFile(var3, var9.toString(), var4);
		} catch (Exception var11) {
			throw new RuleException(var11);
		}

		RepositoryFile var12 = new RepositoryFile();
		var12.setFullPath(var3);
		if (var8.equals(FileType.VariableLibrary)) {
			var12.setType(Type.variable);
		} else if (var8.equals(FileType.ActionLibrary)) {
			var12.setType(Type.action);
		} else if (var8.equals(FileType.ConstantLibrary)) {
			var12.setType(Type.constant);
		} else if (var8.equals(FileType.ParameterLibrary)) {
			var12.setType(Type.parameter);
		} else if (var8.equals(FileType.DecisionTable)) {
			var12.setType(Type.decisionTable);
		} else if (var8.equals(FileType.ScriptDecisionTable)) {
			var12.setType(Type.scriptDecisionTable);
		} else if (var8.equals(FileType.Ruleset)) {
			var12.setType(Type.rule);
		} else if (var8.equals(FileType.UL)) {
			var12.setType(Type.ul);
		} else if (var8.equals(FileType.DecisionTree)) {
			var12.setType(Type.decisionTree);
		} else if (var8.equals(FileType.RuleFlow)) {
			var12.setType(Type.flow);
		} else if (var8.equals(FileType.Scorecard)) {
			var12.setType(Type.scorecard);
		} else if (var8.equals(FileType.ComplexScorecard)) {
			var12.setType(Type.complexscorecard);
		} else if (var8.equals(FileType.Crosstab)) {
			var12.setType(Type.crosstab);
		} else if (var8.equals(FileType.ConditionTemplate)) {
			var12.setType(Type.conditionTemplate);
		} else if (var8.equals(FileType.ActionTemplate)) {
			var12.setType(Type.actionTemplate);
		}

		this.writeObjectToJson(var2, var12);
	}

	private String a(FileType var1) {
		String var2 = null;
		switch(var1) {
			case ActionLibrary:
				var2 = "action-library";
				break;
			case ConstantLibrary:
				var2 = "constant-library";
				break;
			case DecisionTable:
				var2 = "decision-table";
				break;
			case Crosstab:
				var2 = "cross-table";
				break;
			case DecisionTree:
				var2 = "decision-tree";
				break;
			case ParameterLibrary:
				var2 = "parameter-library";
				break;
			case RuleFlow:
				var2 = "rule-flow";
				break;
			case Ruleset:
				var2 = "rule-set";
				break;
			case ConditionTemplate:
				var2 = "templates";
				break;
			case ActionTemplate:
				var2 = "action-templates";
				break;
			case ScriptDecisionTable:
				var2 = "script-decision-table";
				break;
			case VariableLibrary:
				var2 = "variable-library";
				break;
			case UL:
				var2 = "script";
				break;
			case Scorecard:
				var2 = "scorecard";
				break;
			case ComplexScorecard:
				var2 = "complex-scorecard";
				break;
			case DIR:
				throw new IllegalArgumentException("Unsupport filetype : " + var1);
		}

		return var2;
	}

	public void projectExistCheck(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("newProjectName");
		if (!StringUtils.isEmpty(var3)) {
			var3 = Utils.decodeURL(var3);
			var3 = var3.trim();
			HashMap var4 = new HashMap();

			try {
				var4.put("valid", !this.a.fileExistCheck(var3));
				this.writeObjectToJson(var2, var4);
			} catch (Exception var6) {
				throw new RuleException(var6);
			}
		}
	}

	public void fileExistCheck(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("fullFileName");
		if (!StringUtils.isEmpty(var3)) {
			var3 = Utils.decodeURL(var3);
			var3 = var3.trim();
			HashMap var4 = new HashMap();

			try {
				var4.put("valid", !this.a.fileExistCheck(var3));
				this.writeObjectToJson(var2, var4);
			} catch (Exception var6) {
				throw new RuleException(var6);
			}
		}
	}

	public void deleteFile(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("path");
		var3 = Utils.decodeURL(var3);
		Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		int var5 = var3.lastIndexOf(47);
		if (var5 == 0 && !var4.isAdmin()) {
			throw new NoPermissionException();
		} else {
			this.checkFullPermission(var4, var3);
			this.a.deleteFile(var3, var4);
			String var6 = var1.getParameter("isFolder");
			if (StringUtils.isNotBlank(var6) && var6.equals("true")) {
				this.loadProjects(var1, var2);
			}

		}
	}

	public void lockFile(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("file");
		var3 = Utils.decodeURL(var3);
		Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		this.checkFullPermission(var4, var3);
		this.a.lockPath(var3, var4);
		this.loadProjects(var1, var2);
	}

	public void unlockFile(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("file");
		var3 = Utils.decodeURL(var3);
		Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		this.checkFullPermission(var4, var3);
		this.a.unlockPath(var3, var4);
		this.loadProjects(var1, var2);
	}

	public void exportProjectBackupFile(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			PrintWriter var4 = var2.getWriter();
			var4.println("Permission denied!");
			var4.flush();
			var4.close();
		} else {
			String var10 = var1.getParameter("path");
			String var5 = Utils.decodeURL(var10);
			SimpleDateFormat var6 = new SimpleDateFormat("yyyyMMddHHmmss");
			String var7 = null;
			if (StringUtils.isBlank(var5)) {
				var7 = "all";
			} else {
				var7 = var5.substring(1, var5.length());
			}

			String var8 = var7 + "-urule-repo-" + var6.format(new Date()) + ".bak";
			var2.setContentType("application/octet-stream");
			var2.setHeader("Content-Disposition", "attachment; filename=\"" + new String(var8.getBytes("utf-8"), "iso-8859-1") + "\"");
			var2.setHeader("content-type", "application/octet-stream");
			ServletOutputStream var9 = var2.getOutputStream();
			if (StringUtils.isBlank(var5)) {
				this.a.exportXml(var9);
			} else {
				this.a.exportXml(var5, var9);
			}

			var9.flush();
			var9.close();
		}

	}

	public void createProject(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = var1.getParameter("newProjectName");
			var4 = Utils.decodeURL(var4);
			boolean var5 = this.a(var1, var2);
			this.checkFullPermission(var3, var4);
			RepositoryFile var6 = this.a.createProject(var4, var3, var5);
			this.writeObjectToJson(var2, var6);
		}
	}

	public void loadProjects(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		boolean var4 = this.a(var1, var2);
		String var5 = var1.getParameter("projectName");
		String var6 = var1.getParameter("searchFileName");
		var5 = Utils.decodeURL(var5);
		String var7 = var1.getParameter("types");
		FileType[] var8 = null;
		if (StringUtils.isNotBlank(var7) && !var7.equals("all")) {
			if (var7.equals("lib")) {
				var8 = new FileType[]{FileType.VariableLibrary, FileType.ConstantLibrary, FileType.ParameterLibrary, FileType.ActionLibrary};
			} else if (var7.equals("rule")) {
				var8 = new FileType[]{FileType.Ruleset, FileType.UL};
			} else if (var7.equals("table")) {
				var8 = new FileType[]{FileType.DecisionTable, FileType.ScriptDecisionTable, FileType.Crosstab};
			} else if (var7.equals("tree")) {
				var8 = new FileType[]{FileType.DecisionTree};
			} else if (var7.equals("flow")) {
				var8 = new FileType[]{FileType.RuleFlow};
			} else if (var7.equals("card")) {
				var8 = new FileType[]{FileType.Scorecard, FileType.ComplexScorecard};
			}
		}

		Repository var9 = this.a.loadRepository(var5, var3, var4, var8, var6);
		Repository var10 = this.buildRepositoryAuthority(var3, var9);
		HashMap var11 = new HashMap();
		var11.put("repo", var10);
		var11.put("classify", var4);
		this.writeObjectToJson(var2, var11);
	}

	private boolean a(HttpServletRequest var1, HttpServletResponse var2) {
		String var3 = var1.getParameter("classify");
		if (StringUtils.isBlank(var3)) {
			Cookie[] var4 = var1.getCookies();
			if (var4 != null) {
				Cookie[] var5 = var4;
				int var6 = var4.length;

				for(int var7 = 0; var7 < var6; ++var7) {
					Cookie var8 = var5[var7];
					if ("_lib_classify".equals(var8.getName())) {
						var3 = var8.getValue();
						break;
					}
				}
			}
		} else {
			Cookie var9 = new Cookie("_lib_classify", var3);
			var9.setMaxAge(2100000000);
			var2.addCookie(var9);
		}

		boolean var10 = true;
		if (StringUtils.isNotBlank(var3)) {
			var10 = Boolean.valueOf(var3);
		}

		return var10;
	}

	public void fileRename(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("path");
		var3 = Utils.decodeURL(var3);
		Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		this.checkFullPermission(var4, var3);
		String var5 = var1.getParameter("newPath");
		var5 = Utils.decodeURL(var5);
		this.a.fileRename(var3, var5);
		String var6 = var1.getParameter("project");
		if (var6 != null && var6.equals("true")) {
			this.b.projectRename(var3, var5);
		}

		this.loadProjects(var1, var2);
	}

	public void setRepositoryService(RepositoryService var1) {
		this.a = var1;
	}

	public void setKnowledgePackageRepositoryService(KnowledgePackageRepositoryService var1) {
		this.b = var1;
	}

	public void setWelcomePage(String var1) {
		this.c = var1;
	}

	public void setTitle(String var1) {
		this.e = var1;
	}

	public void setLogoutURL(String var1) {
		this.d = var1;
	}

	public void setSecurityEnable(boolean var1) {
		this.f = var1;
	}

	public String url() {
		return "/frame";
	}
}
