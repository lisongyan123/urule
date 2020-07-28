package com.bstek.urule.console.servlet.decisiontree;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Splash;
import com.bstek.urule.console.servlet.DesignerConfigure;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.SessionStore;
import com.bstek.urule.model.decisiontree.VariableTreeNode;
import com.bstek.urule.parse.decisiontree.ActionTreeNodeParser;
import com.bstek.urule.parse.decisiontree.ConditionTreeNodeParser;
import com.bstek.urule.parse.decisiontree.VariableTreeNodeParser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class DecisionTreeEditorServletHandler extends RenderPageServletHandler {
	public static final String EDITOR_STYLE = "_decision_editor_style";
	public static final String DIAGRAM_EDITOR = "diagram";
	public static final String TREE_EDITOR = "tree";
	public static final String DECISION_TREE_DATA = "_decision_tree_data";
	private VariableTreeNodeParser a;
	private ConditionTreeNodeParser b;
	private ActionTreeNodeParser c;

	public DecisionTreeEditorServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = this.retriveMethod(var1);
		if (var3 != null) {
			this.invokeMethod(var3, var1, var2);
		} else {
			VelocityContext var4 = new VelocityContext();
			var4.put("contextPath", var1.getContextPath());
			var4.put("version", Splash.getVersion());
			var4.put("constantLink", DesignerConfigure.constantLink);
			var4.put("variableLink", DesignerConfigure.variableLink);
			var4.put("_date_", _DATE);
			var4.put("_lis_", Splash.getFetchVersion());
			String var5 = var1.getParameter("file");
			String var6 = this.buildProjectNameFromFile(var5);
			if (var6 != null) {
				var4.put("project", var6);
			}

			String var7 = this.a(var1);
			Cookie var8 = new Cookie("_decision_editor_style", var7);
			var8.setMaxAge(311040000);
			var2.addCookie(var8);
			var4.put("editorStyle", var7);
			var4.put("showFileExtensionName", PropertyConfigurer.getProperty("urule.show.fileExtensionName"));
			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var9 = null;
			if (var7.equals("diagram")) {
				var9 = this.ve.getTemplate("html/decisiontree-editor.html", "utf-8");
			} else {
				var9 = this.ve.getTemplate("html/decisiontree-tree-editor.html", "utf-8");
			}

			PrintWriter var10 = var2.getWriter();
			var9.merge(var4, var10);
			var10.close();
		}

	}

	public void parseTreeNode(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("xml");
		Document var4 = DocumentHelper.parseText(var3);
		Element var5 = var4.getRootElement();
		if (this.a.support(var5.getName())) {
			VariableTreeNode var6 = this.a.parse(var5);
			SessionStore.setAttribute("_decision_tree_data", var6);
		} else if (this.b.support(var5.getName())) {
			SessionStore.setAttribute("_decision_tree_data", this.b.parse(var5));
		} else if (this.c.support(var5.getName())) {
			SessionStore.setAttribute("_decision_tree_data", this.c.parse(var5));
		}

	}

	public void loadTreeNode(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Object var3 = SessionStore.getAttribute("_decision_tree_data");
		this.writeObjectToJson(var2, var3);
	}

	private String a(HttpServletRequest var1) {
		String var2 = var1.getParameter("editor");
		if (StringUtils.isNotBlank(var2)) {
			return var2;
		} else {
			Cookie[] var3 = var1.getCookies();
			if (var3 == null) {
				return "diagram";
			} else {
				Cookie[] var4 = var3;
				int var5 = var3.length;

				for(int var6 = 0; var6 < var5; ++var6) {
					Cookie var7 = var4[var6];
					String var8 = var7.getName();
					if ("_decision_editor_style".equals(var8)) {
						var2 = var7.getValue();
					}
				}

				if (var2 != null) {
					return var2;
				} else {
					return "diagram";
				}
			}
		}
	}

	public void setVariableTreeNodeParser(VariableTreeNodeParser var1) {
		this.a = var1;
	}

	public void setActionTreeNodeParser(ActionTreeNodeParser var1) {
		this.c = var1;
	}

	public void setConditionTreeNodeParser(ConditionTreeNodeParser var1) {
		this.b = var1;
	}

	public String url() {
		return "/decisiontreeeditor";
	}
}
