//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.flow;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.repository.KnowledgePackageRepositoryService;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.model.ResourcePackage;
import com.bstek.urule.console.servlet.DesignerConfigure;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.rule.lhs.Lhs;
import com.bstek.urule.parse.ActionParser;
import com.bstek.urule.parse.LhsParser;
import com.bstek.urule.parse.deserializer.FlowDeserializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class RuleFlowDesignerServletHandler extends RenderPageServletHandler {
	private RepositoryService a;
	private KnowledgePackageRepositoryService b;
	private FlowDeserializer c;
	private LhsParser d;
	private Collection<ActionParser> e;

	public RuleFlowDesignerServletHandler() {
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

			var4.put("showFileExtensionName", PropertyConfigurer.getProperty("urule.show.fileExtensionName"));
			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var7 = this.ve.getTemplate("html/rule-flow-designer.html", "utf-8");
			PrintWriter var8 = var2.getWriter();
			var7.merge(var4, var8);
			var8.close();
		}

	}

	public void loadFlowDefinition(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var4 = var1.getParameter("file");
		String var5 = var1.getParameter("version");
		var4 = Utils.decodeURL(var4);

		try {
			InputStream var3;
			if (StringUtils.isEmpty(var5)) {
				var3 = this.a.readFile(var4, (String)null);
			} else {
				var3 = this.a.readFile(var4, var5);
			}

			Element var6 = this.parseXml(var3);
			FlowDefinition var7 = this.c.deserialize(var6);
			var3.close();
			this.writeObjectToJson(var2, new FlowDefinitionWrapper(var7));
		} catch (Exception var8) {
			throw new RuleException(var8);
		}
	}

	public void parseJoint(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("content");
		var3 = Utils.decodeContent(var3);
		Element var4 = this.parseXml(var3);
		Lhs var5 = this.d.parse(var4);
		this.writeObjectToJson(var2, var5);
	}

	public void parseActions(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("content");
		var3 = Utils.decodeContent(var3);
		Element var4 = this.parseXml(var3);
		ArrayList var5 = new ArrayList();
		Iterator var6 = var4.elements().iterator();

		while(true) {
			while(true) {
				Object var7;
				do {
					do {
						if (!var6.hasNext()) {
							this.writeObjectToJson(var2, var5);
							return;
						}

						var7 = var6.next();
					} while(var7 == null);
				} while(!(var7 instanceof Element));

				Element var8 = (Element)var7;
				String var9 = var8.getName();
				Iterator var10 = this.e.iterator();

				while(var10.hasNext()) {
					ActionParser var11 = (ActionParser)var10.next();
					if (var11.support(var9)) {
						var5.add(var11.parse(var8));
						break;
					}
				}
			}
		}
	}

	protected Element parseXml(String var1) {
		try {
			Document var2 = DocumentHelper.parseText(var1);
			Element var3 = var2.getRootElement();
			return var3;
		} catch (DocumentException var4) {
			throw new RuleException(var4);
		}
	}

	protected Element parseXml(InputStream var1) {
		SAXReader var2 = new SAXReader();

		try {
			Document var3 = var2.read(var1);
			Element var4 = var3.getRootElement();
			return var4;
		} catch (DocumentException var5) {
			throw new RuleException(var5);
		}
	}

	public void loadPackages(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("project");

		try {
			List var4 = this.b.loadProjectResourcePackages(var3);
			ArrayList var5 = new ArrayList();
			Iterator var6 = var4.iterator();

			while(var6.hasNext()) {
				ResourcePackage var7 = (ResourcePackage)var6.next();
				if (var7.isCheck()) {
					var5.add(var7);
				}
			}

			this.writeObjectToJson(var2, var5);
		} catch (Exception var8) {
			throw new RuleException(var8);
		}
	}

	public void setLhsParser(LhsParser var1) {
		this.d = var1;
	}

	public void setRepositoryService(RepositoryService var1) {
		this.a = var1;
	}

	public void setKnowledgePackageRepositoryService(KnowledgePackageRepositoryService var1) {
		this.b = var1;
	}

	public void setFlowDeserializer(FlowDeserializer var1) {
		this.c = var1;
	}

	public void setApplicationContext(ApplicationContext var1) throws BeansException {
		super.setApplicationContext(var1);
		this.e = var1.getBeansOfType(ActionParser.class).values();
	}

	public String url() {
		return "/ruleflowdesigner";
	}
}
