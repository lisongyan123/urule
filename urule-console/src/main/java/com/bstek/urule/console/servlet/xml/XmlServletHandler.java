//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.xml;

import com.bstek.urule.Utils;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.servlet.WriteJsonServletHandler;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.action.ActionLibrary;
import com.bstek.urule.parse.deserializer.ActionLibraryDeserializer;
import com.bstek.urule.parse.deserializer.ConstantLibraryDeserializer;
import com.bstek.urule.parse.deserializer.DecisionTableDeserializer;
import com.bstek.urule.parse.deserializer.DecisionTreeDeserializer;
import com.bstek.urule.parse.deserializer.Deserializer;
import com.bstek.urule.parse.deserializer.ParameterLibraryDeserializer;
import com.bstek.urule.parse.deserializer.ScriptDecisionTableDeserializer;
import com.bstek.urule.parse.deserializer.VariableLibraryDeserializer;
import com.bstek.urule.runtime.BuiltInActionLibraryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class XmlServletHandler extends WriteJsonServletHandler implements ApplicationContextAware {
	private RepositoryService a;
	private BuiltInActionLibraryBuilder b;
	protected List<Deserializer<?>> deserializers = new ArrayList();

	public XmlServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = this.retriveMethod(var1);
		if (var3 != null) {
			this.invokeMethod(var3, var1, var2);
		} else {
			this.loadXml(var1, var2);
		}

	}

	public void loadXml(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		ArrayList var3 = new ArrayList();
		String var4 = var1.getParameter("files");
		boolean var5 = false;
		if (var4 != null) {
			if (var4.startsWith("builtinactions")) {
				var5 = true;
			} else {
				var4 = Utils.decodeURL(var4);
				String[] var6 = var4.split(";");
				String[] var7 = var6;
				int var8 = var6.length;

				for(int var9 = 0; var9 < var8; ++var9) {
					String var10 = var7[var9];
					var10 = Utils.toUTF8(var10);
					if (var10.startsWith("jcr:")) {
						var10 = var10.substring(4, var10.length());
					}

					String[] var11 = var10.split(",");
					var10 = var11[0];
					String var12 = null;
					if (var11.length == 2) {
						var12 = var11[1];
					}

					try {
						InputStream var13 = null;
						if (StringUtils.isEmpty(var12)) {
							var13 = this.a.readFile(var10, (String)null);
						} else {
							var13 = this.a.readFile(var10, var12);
						}

						try {
							Element var14 = this.parseXml(var13);
							Iterator var15 = this.deserializers.iterator();

							while(var15.hasNext()) {
								Deserializer var16 = (Deserializer)var15.next();
								if (var16.support(var14)) {
									var3.add(var16.deserialize(var14));
									if (var16 instanceof ActionLibraryDeserializer) {
										var5 = true;
									}
									break;
								}
							}
						} finally {
							var13.close();
						}
					} catch (Exception var21) {
						throw new RuleException(var21);
					}
				}
			}
		}

		if (var5) {
			List var22 = this.b.getBuiltInActions();
			if (var22.size() > 0) {
				ActionLibrary var23 = new ActionLibrary();
				var23.setSpringBeans(var22);
				var3.add(var23);
			}
		}

		this.writeObjectToJson(var2, var3);
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

	public String url() {
		return "/xml";
	}

	public void setBuiltInActionLibraryBuilder(BuiltInActionLibraryBuilder var1) {
		this.b = var1;
	}

	public void setRepositoryService(RepositoryService var1) {
		this.a = var1;
	}

	public void setApplicationContext(ApplicationContext var1) throws BeansException {
		ActionLibraryDeserializer var2 = (ActionLibraryDeserializer)var1.getBean("urule.actionLibraryDeserializer");
		VariableLibraryDeserializer var3 = (VariableLibraryDeserializer)var1.getBean("urule.variableLibraryDeserializer");
		ConstantLibraryDeserializer var4 = (ConstantLibraryDeserializer)var1.getBean("urule.constantLibraryDeserializer");
		RuleSetDeserializer var5 = (RuleSetDeserializer)var1.getBean("urule.ruleSetDeserializer");
		DecisionTableDeserializer var6 = (DecisionTableDeserializer)var1.getBean("urule.decisionTableDeserializer");
		ScriptDecisionTableDeserializer var7 = (ScriptDecisionTableDeserializer)var1.getBean("urule.scriptDecisionTableDeserializer");
		DecisionTreeDeserializer var8 = (DecisionTreeDeserializer)var1.getBean("urule.decisionTreeDeserializer");
		ParameterLibraryDeserializer var9 = (ParameterLibraryDeserializer)var1.getBean("urule.parameterLibraryDeserializer");
		this.deserializers.add(var2);
		this.deserializers.add(var3);
		this.deserializers.add(var4);
		this.deserializers.add(var5);
		this.deserializers.add(var6);
		this.deserializers.add(var7);
		this.deserializers.add(var8);
		this.deserializers.add(var9);
	}
}

