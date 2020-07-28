//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.variable;

import com.bstek.urule.ClassUtils;
import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.variable.Act;
import com.bstek.urule.model.library.variable.Variable;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class VariableEditorServletHandler extends RenderPageServletHandler {
	public VariableEditorServletHandler() {
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
			Template var5 = this.ve.getTemplate("html/variable-editor.html", "utf-8");
			PrintWriter var6 = var2.getWriter();
			var5.merge(var4, var6);
			var6.close();
		}

	}

	public void importXml(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		DiskFileItemFactory var3 = new DiskFileItemFactory();
		ServletFileUpload var4 = new ServletFileUpload(var3);
		InputStream var5 = null;

		try {
			List var6 = var4.parseRequest(var1);
			if (var6.size() != 1) {
				throw new ServletException("Upload xml file is invalid.");
			} else {
				FileItem var7 = (FileItem)var6.get(0);
				var5 = var7.getInputStream();
				String var8 = IOUtils.toString(var5, "utf-8");
				ArrayList var9 = new ArrayList();
				Document var10 = DocumentHelper.parseText(var8);
				Element var11 = var10.getRootElement();
				String var12 = var11.attributeValue("clazz");
				Iterator var13 = var11.elements().iterator();

				while(var13.hasNext()) {
					Object var14 = var13.next();
					if (var14 != null && var14 instanceof Element) {
						Element var15 = (Element)var14;
						Variable var16 = new Variable();
						var16.setAct(Act.InOut);
						var16.setDefaultValue(var15.attributeValue("defaultValue"));
						var16.setLabel(var15.attributeValue("label"));
						var16.setName(var15.attributeValue("name"));
						var16.setType(Datatype.valueOf(var15.attributeValue("type")));
						var9.add(var16);
					}
				}

				HashMap var22 = new HashMap();
				var22.put("clazz", var12);
				var22.put("variables", var9);
				this.writeObjectToJson(var2, var22);
			}
		} catch (Exception var20) {
			throw new ServletException(var20);
		} finally {
			IOUtils.closeQuietly(var5);
		}
	}

	public void generateFields(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("clazz");
		Class var4 = this.a(var3);
		List var5 = ClassUtils.classToVariables(var4);
		this.writeObjectToJson(var2, var5);
	}

	private Class<?> a(String var1) {
		Class var2 = null;

		try {
			var2 = Class.forName(var1);
		} catch (ClassNotFoundException var7) {
			DefaultListableBeanFactory var4 = (DefaultListableBeanFactory)Utils.getApplicationContext().getAutowireCapableBeanFactory();

			try {
				var2 = var4.getBeanClassLoader().loadClass(var1);
			} catch (ClassNotFoundException var6) {
				throw new RuleException(var6);
			}
		}

		return var2;
	}

	public String url() {
		return "/variableeditor";
	}
}
