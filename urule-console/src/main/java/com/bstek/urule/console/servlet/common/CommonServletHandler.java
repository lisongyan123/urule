package com.bstek.urule.console.servlet.common;

import com.bstek.urule.Utils;
import com.bstek.urule.action.Action;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.repository.Repository;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.authority.Authority;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.refactor.BeanItem;
import com.bstek.urule.console.repository.refactor.BeanMethodItem;
import com.bstek.urule.console.repository.refactor.ConstCategoryItem;
import com.bstek.urule.console.repository.refactor.ConstItem;
import com.bstek.urule.console.repository.refactor.Item;
import com.bstek.urule.console.repository.refactor.VariableCategoryItem;
import com.bstek.urule.console.repository.refactor.VariableItem;
import com.bstek.urule.console.repository.reference.ActionSearchItem;
import com.bstek.urule.console.repository.reference.ConstantSearchItem;
import com.bstek.urule.console.repository.reference.ParameterSearchItem;
import com.bstek.urule.console.repository.reference.ReferenceService;
import com.bstek.urule.console.repository.reference.SearchItem;
import com.bstek.urule.console.repository.reference.VariableSearchItem;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.console.servlet.SessionStore;
import com.bstek.urule.dsl.RuleParserLexer;
import com.bstek.urule.dsl.RuleParserParser;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.model.library.action.ActionLibrary;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.RuleSet;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.loop.LoopRule;
import com.bstek.urule.model.table.Joint;
import com.bstek.urule.parse.ActionParser;
import com.bstek.urule.parse.ExecuteMethodActionParser;
import com.bstek.urule.parse.JunctionParser;
import com.bstek.urule.parse.LoopRuleParser;
import com.bstek.urule.parse.RuleParser;
import com.bstek.urule.parse.ValueParser;
import com.bstek.urule.parse.deserializer.ActionLibraryDeserializer;
import com.bstek.urule.parse.deserializer.ActionTemplateDeserializer;
import com.bstek.urule.parse.deserializer.ComplexScorecardDeserializer;
import com.bstek.urule.parse.deserializer.ConditionTemplateDeserializer;
import com.bstek.urule.parse.deserializer.ConstantLibraryDeserializer;
import com.bstek.urule.parse.deserializer.CrosstableDeserializer;
import com.bstek.urule.parse.deserializer.DecisionTableDeserializer;
import com.bstek.urule.parse.deserializer.DecisionTreeDeserializer;
import com.bstek.urule.parse.deserializer.Deserializer;
import com.bstek.urule.parse.deserializer.ParameterLibraryDeserializer;
import com.bstek.urule.parse.deserializer.RuleSetDeserializer;
import com.bstek.urule.parse.deserializer.ScorecardDeserializer;
import com.bstek.urule.parse.deserializer.ScriptDecisionTableDeserializer;
import com.bstek.urule.parse.deserializer.VariableLibraryDeserializer;
import com.bstek.urule.parse.table.JointParser;
import com.bstek.urule.runtime.BuiltInActionLibraryBuilder;
import com.bstek.urule.runtime.ProxyUtils;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public class CommonServletHandler extends RenderPageServletHandler {
	private ReferenceService a;
	private RuleParser b;
	private LoopRuleParser c;
	private ValueParser d;
	private ExecuteMethodActionParser e;
	private JointParser f;
	private JunctionParser g;
	private RepositoryService h;
	private BuiltInActionLibraryBuilder i;
	private Collection<ActionParser> j;
	private List<Deserializer<?>> k = new ArrayList();
	private List<FunctionDescriptor> l = new ArrayList();
	public static final String IMPORT_DATA = "_import_data_";
	public static final String ACTION_CELL_DATA = "__action_cell_data_";
	public static final String ACTION_RULE_DATA = "__action_rule_data_";
	public static final String CONDITION_RULE_DATA = "_condition_rule_data_";
	public static final String CONDITION_CELL_DATA = "_condition_cell_data_";
	public static final String VALUE_CELL_DATA = "_value_cell_data_";
	public static final String LOOP_RULE_FOR_COPY = "_loop_rule_for_copy_";
	public static final String RULE_FOR_COPY = "_rule_for_copy_";

	public CommonServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = this.retriveMethod(var1);
		if (var3 != null) {
			this.invokeMethod(var3, var1, var2);
		} else {
			throw new ServletException("Unsupport this operation.");
		}
	}

	public void copyRule(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("type");
		String var4 = var1.getParameter("xml");
		Document var5 = DocumentHelper.parseText(var4);
		Element var6 = var5.getRootElement();
		LoopRule var7 = null;
		if (var3.equals("loop")) {
			var7 = this.c.parse(var6);
			SessionStore.setAttribute("_loop_rule_for_copy_", var7);
		} else {
			Rule var8 = this.b.parse(var6);
			SessionStore.setAttribute("_rule_for_copy_", var8);
		}

	}

	public void pasteRule(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("type");
		Rule var4 = null;
		if (var3.equals("loop")) {
			var4 = (Rule)SessionStore.getAttribute("_loop_rule_for_copy_");
			this.writeObjectToJson(var2, var4);
		} else {
			var4 = (Rule)SessionStore.getAttribute("_rule_for_copy_");
			this.writeObjectToJson(var2, var4);
		}

	}

	public void parseRuleData(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("type");
		String var4 = var1.getParameter("xml");
		Document var5 = DocumentHelper.parseText(var4);
		Element var6;
		if (var3.equals("condition")) {
			var6 = var5.getRootElement();
			if (this.g.support(var6.getName())) {
				Criterion var7 = this.g.parse(var6);
				SessionStore.setAttribute("_condition_rule_data_", var7);
			} else {
				List var10 = this.g.parseCriterion(var6);
				SessionStore.setAttribute("_condition_rule_data_", var10.get(0));
			}
		} else if (var3.equals("action")) {
			var6 = var5.getRootElement();
			Iterator var11 = this.j.iterator();

			while(var11.hasNext()) {
				ActionParser var8 = (ActionParser)var11.next();
				if (var8.support(var6.getName())) {
					Action var9 = (Action)var8.parse(var6);
					SessionStore.setAttribute("__action_rule_data_", var9);
					break;
				}
			}
		}

	}

	public void loadRuleData(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("type");
		Object var4 = null;
		if (var3.equals("condition")) {
			var4 = SessionStore.getAttribute("_condition_rule_data_");
		} else if (var3.equals("action")) {
			var4 = SessionStore.getAttribute("__action_rule_data_");
		}

		if (var4 != null) {
			this.writeObjectToJson(var2, var4);
		}

	}

	public void parseCellData(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("type");
		String var4 = var1.getParameter("xml");

		Document var5;
		try {
			var5 = DocumentHelper.parseText(var4);
		} catch (DocumentException var7) {
			throw new ServletException(var7);
		}

		if (var3.equals("condition")) {
			Joint var6 = this.f.parse(var5.getRootElement());
			SessionStore.setAttribute("_condition_cell_data_", var6);
		} else if (var3.equals("value")) {
			Value var8 = this.d.parse(var5.getRootElement());
			SessionStore.setAttribute("_value_cell_data_", var8);
		} else if (var3.equals("action")) {
			Action var9 = this.e.parse(var5.getRootElement());
			SessionStore.setAttribute("__action_cell_data_", var9);
		}

	}

	public void loadCellData(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("type");
		Object var4 = null;
		if (var3.equals("condition")) {
			var4 = SessionStore.getAttribute("_condition_cell_data_");
		} else if (var3.equals("value")) {
			var4 = SessionStore.getAttribute("_value_cell_data_");
		} else if (var3.equals("action")) {
			var4 = SessionStore.getAttribute("__action_cell_data_");
		}

		if (var4 != null) {
			this.writeObjectToJson(var2, var4);
		}

	}

	public void refactorContent(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = var1.getParameter("type");
			if (var4 != null) {
				Object var5 = null;
				String var6;
				String var7;
				if (var4.equals("const-category")) {
					var6 = var1.getParameter("newvalue");
					var7 = var1.getParameter("oldvalue");
					ConstCategoryItem var8 = new ConstCategoryItem();
					var8.setNewCategory(var6);
					var8.setOldCategory(var7);
					var5 = var8;
				} else if (var4.equals("var-category")) {
					var6 = var1.getParameter("newvalue");
					var7 = var1.getParameter("oldvalue");
					VariableCategoryItem var15 = new VariableCategoryItem();
					var15.setNewCategory(var6);
					var15.setOldCategory(var7);
					var5 = var15;
				} else {
					String var9;
					String var10;
					String var11;
					String var12;
					String var16;
					if (var4.equals("var")) {
						var6 = var1.getParameter("olddatatype");
						var7 = var1.getParameter("newdatatype");
						var16 = var1.getParameter("oldlabel");
						var9 = var1.getParameter("newlabel");
						var10 = var1.getParameter("newname");
						var11 = var1.getParameter("oldname");
						var12 = var1.getParameter("category");
						VariableItem var13 = new VariableItem();
						var13.setCategory(var12);
						var13.setNewDatatype(var7);
						var13.setOldDatatype(var6);
						var13.setNewLabel(var9);
						var13.setOldLabel(var16);
						var13.setNewName(var10);
						var13.setOldName(var11);
						var5 = var13;
					} else if (var4.equals("const")) {
						var6 = var1.getParameter("oldlabel");
						var7 = var1.getParameter("oldname");
						var16 = var1.getParameter("newlabel");
						var9 = var1.getParameter("newname");
						var10 = var1.getParameter("category");
						var11 = var1.getParameter("olddatatype");
						var12 = var1.getParameter("newdatatype");
						ConstItem var19 = new ConstItem();
						var19.setOldDatatype(var11);
						var19.setNewDatatype(var12);
						var19.setCategory(var10);
						var19.setNewLabel(var16);
						var19.setOldLabel(var6);
						var19.setNewName(var9);
						var19.setOldName(var7);
						var5 = var19;
					} else if (var4.equals("bean")) {
						var6 = var1.getParameter("oldbeanname");
						var7 = var1.getParameter("oldbeanlabel");
						var16 = var1.getParameter("newbeanname");
						var9 = var1.getParameter("newbeanlabel");
						BeanItem var17 = new BeanItem();
						var17.setNewBeanId(var16);
						var17.setNewBeanLabel(var9);
						var17.setOldBeanId(var6);
						var17.setOldBeanLabel(var7);
						var5 = var17;
					} else if (var4.equals("method")) {
						var6 = var1.getParameter("beanname");
						var7 = var1.getParameter("beanlabel");
						var16 = var1.getParameter("oldmethodname");
						var9 = var1.getParameter("oldmethodlabel");
						var10 = var1.getParameter("newmethodname");
						var11 = var1.getParameter("newmethodlabel");
						BeanMethodItem var18 = new BeanMethodItem();
						var18.setBeanId(var6);
						var18.setBeanLabel(var7);
						var18.setOldMethodLabel(var9);
						var18.setOldMethodName(var16);
						var18.setNewMethodLabel(var11);
						var18.setNewMethodName(var10);
						var5 = var18;
					}
				}

				if (var5 != null) {
					var6 = var1.getParameter("path");
					var6 = URLDecoder.decode(var6, "UTF-8");
					var6 = URLDecoder.decode(var6, "UTF-8");

					try {
						this.h.refactorContent(var6, (Item)var5);
					} catch (Exception var14) {
						throw new RuleException(var14);
					}
				}
			}
		}
	}

	public void saveTempFile(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("file");
		var3 = Utils.decodeURL(var3);
		String var4 = var1.getParameter("content");
		var4 = Utils.decodeContent(var4);
		SessionStore.setAttribute(var3, var4);
	}

	public void saveFile(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("file");
		var3 = Utils.decodeURL(var3);
		Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		this.checkFullPermission(var4, var3);
		String var5 = var1.getParameter("content");
		var5 = Utils.decodeContent(var5);
		String var6 = var1.getParameter("versionComment");
		Boolean var7 = Boolean.valueOf(var1.getParameter("newVersion"));

		try {
			this.a(var4, var3, var7);
			this.h.saveFile(var3, var5, var7, var6, var4);
		} catch (Exception var9) {
			throw new RuleException(var9);
		}
	}

	private void a(Principal var1, String var2, boolean var3) {
		Authority var4 = this.authorityService.getAuthority(var1, var2);
		if (var4 != null) {
			if (var3) {
				if (!var4.isSaveas() && var4.isSave()) {
					throw new NoPermissionException();
				}
			} else if (!var4.isSave() && var4.isSaveas()) {
				throw new NoPermissionException();
			}
		}

	}

	public void loadReferenceFiles(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("path");
		var3 = Utils.decodeURL(var3);
		SearchItem var4 = this.a(var3, var1);
		List var5;
		if (var4 == null) {
			var5 = this.a.loadReferenceFiles(var3);
			this.writeObjectToJson(var2, var5);
		} else {
			var5 = this.a.loadReferenceFiles(var3, var4);
			this.writeObjectToJson(var2, var5);
		}

	}

	private SearchItem a(String var1, HttpServletRequest var2) {
		if ("true".equals(var2.getParameter("onlyfile"))) {
			return null;
		} else if (var1.endsWith(FileType.ActionLibrary.toString())) {
			ActionSearchItem var6 = new ActionSearchItem();
			var6.setBeanName(var2.getParameter("beanName"));
			var6.setBeanLabel(var2.getParameter("beanLabel"));
			var6.setMethodLabel(var2.getParameter("methodLabel"));
			var6.setMethodName(var2.getParameter("methodName"));
			return var6;
		} else if (var1.endsWith(FileType.ConstantLibrary.toString())) {
			ConstantSearchItem var5 = new ConstantSearchItem();
			var5.setConstCategoryLabel(var2.getParameter("constCategoryLabel"));
			var5.setConstLabel(var2.getParameter("constLabel"));
			var5.setConstName(var2.getParameter("constName"));
			return var5;
		} else if (var1.endsWith(FileType.ParameterLibrary.toString())) {
			ParameterSearchItem var4 = new ParameterSearchItem();
			var4.setVarLabel(var2.getParameter("varLabel"));
			var4.setVarName(var2.getParameter("varName"));
			return var4;
		} else if (var1.endsWith(FileType.VariableLibrary.toString())) {
			VariableSearchItem var3 = new VariableSearchItem();
			var3.setVarCategory(var2.getParameter("varCategory"));
			var3.setVarLabel(var2.getParameter("varLabel"));
			var3.setVarName(var2.getParameter("varName"));
			return var3;
		} else {
			throw new RuleException("Unknow file : " + var1);
		}
	}

	public void loadResourceTreeData(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("project");
		var3 = Utils.decodeURL(var3);
		String var4 = var1.getParameter("forLib");
		String var5 = var1.getParameter("fileType");
		String var6 = var1.getParameter("searchFileName");
		String var7 = var1.getParameter("authority");
		Principal var8 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		FileType[] var9 = null;
		if (StringUtils.isNotBlank(var4) && var4.equals("true")) {
			var9 = new FileType[]{FileType.ActionLibrary, FileType.ConstantLibrary, FileType.VariableLibrary, FileType.ParameterLibrary};
		} else if (StringUtils.isNotBlank(var5)) {
			String[] var10 = var5.split(",");
			var9 = new FileType[var10.length];

			for(int var11 = 0; var11 < var10.length; ++var11) {
				var9[var11] = FileType.valueOf(var10[var11]);
			}
		} else if (var7 != null && var7.equals("true")) {
			var9 = new FileType[]{FileType.ActionLibrary, FileType.ConstantLibrary, FileType.VariableLibrary, FileType.ParameterLibrary, FileType.UL, FileType.Ruleset, FileType.RuleFlow, FileType.DecisionTable, FileType.Crosstab, FileType.ScriptDecisionTable, FileType.DecisionTree, FileType.Scorecard, FileType.ComplexScorecard};
		} else {
			var9 = new FileType[]{FileType.UL, FileType.Ruleset, FileType.RuleFlow, FileType.DecisionTable, FileType.Crosstab, FileType.ScriptDecisionTable, FileType.DecisionTree, FileType.Scorecard, FileType.ComplexScorecard};
		}

		try {
			Repository var13 = this.h.loadRepository(var3, var8, false, var9, var6);
			Repository var14 = this.buildRepositoryAuthority(var8, var13);
			this.writeObjectToJson(var2, var14.getRootFile());
		} catch (Exception var12) {
			throw new RuleException(var12);
		}
	}

	public void loadFunctions(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		this.writeObjectToJson(var2, this.l);
	}

	public void scriptValidation(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("content");
		if (StringUtils.isNotBlank(var3)) {
			ScriptType var4 = ScriptType.valueOf(var1.getParameter("type"));
			ANTLRInputStream var5 = new ANTLRInputStream(var3);
			RuleParserLexer var6 = new RuleParserLexer(var5);
			CommonTokenStream var7 = new CommonTokenStream(var6);
			RuleParserParser var8 = new RuleParserParser(var7);
			var8.removeErrorListeners();
			ScriptErrorListener var9 = new ScriptErrorListener();
			var8.addErrorListener(var9);
			switch(var4) {
				case Script:
					var8.ruleSet();
					break;
				case DecisionNode:
					var8.condition();
					break;
				case ScriptNode:
					var8.actions();
			}

			List var10 = var9.getInfos();
			this.writeObjectToJson(var2, var10);
		}

	}

	public void loadXml(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("doImport");
		Object var4 = SessionStore.getAttribute("_import_data_");
		if (var3 != null && var3.equals("true") && var4 != null) {
			SessionStore.removeAttribute("_import_data_");
			ArrayList var7 = new ArrayList();
			var7.add(var4);
			this.writeObjectToJson(var2, var7);
		} else {
			String var5 = var1.getParameter("files");
			var5 = Utils.decodeURL(var5);
			List var6 = this.buildData(var5);
			this.writeObjectToJson(var2, var6);
		}

	}

	public List<Object> buildData(String var1) {
		ArrayList var2 = new ArrayList();
		boolean var3 = false;
		if (var1 != null) {
			if (var1.startsWith("builtinactions")) {
				var3 = true;
			} else {
				String[] var4 = var1.split(";");
				String[] var5 = var4;
				int var6 = var4.length;

				for(int var7 = 0; var7 < var6; ++var7) {
					String var8 = var5[var7];
					if (var8.startsWith("jcr:")) {
						var8 = var8.substring(4, var8.length());
					}

					String[] var9 = var8.split(",");
					var8 = var9[0];
					String var10 = null;
					if (var9.length == 2) {
						var10 = var9[1];
					}

					try {
						InputStream var11 = null;
						if (StringUtils.isEmpty(var10)) {
							var11 = this.h.readFile(var8, (String)null);
						} else {
							var11 = this.h.readFile(var8, var10);
						}

						Element var12 = this.parseXml(var11);
						Iterator var13 = this.k.iterator();

						while(var13.hasNext()) {
							Deserializer var14 = (Deserializer)var13.next();
							if (var14.support(var12)) {
								var2.add(var14.deserialize(var12));
								if (var14 instanceof ActionLibraryDeserializer) {
									var3 = true;
								}

								if (var14 instanceof RuleSetDeserializer) {
									this.a(var2);
								}
								break;
							}
						}

						var11.close();
					} catch (Exception var15) {
						throw new RuleException(var15);
					}
				}
			}
		}

		if (var3) {
			List var16 = this.i.getBuiltInActions();
			if (var16.size() > 0) {
				ActionLibrary var17 = new ActionLibrary();
				var17.setSpringBeans(var16);
				var2.add(var17);
			}
		}

		return var2;
	}

	private void a(List<Object> var1) {
		if (var1.size() != 0) {
			RuleSet var2 = (RuleSet)var1.get(0);
			Iterator var3 = var2.getRules().iterator();

			while(var3.hasNext()) {
				Rule var4 = (Rule)var3.next();
				if (var4.isDebugFromGlobal()) {
					var4.setDebug((Boolean)null);
				}
			}

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

	public void setRuleParser(RuleParser var1) {
		this.b = var1;
	}

	public void setLoopRuleParser(LoopRuleParser var1) {
		this.c = var1;
	}

	public void setValueParser(ValueParser var1) {
		this.d = var1;
	}

	public void setJointParser(JointParser var1) {
		this.f = var1;
	}

	public void setExecuteMethodActionParser(ExecuteMethodActionParser var1) {
		this.e = var1;
	}

	public void setBuiltInActionLibraryBuilder(BuiltInActionLibraryBuilder var1) {
		this.i = var1;
	}

	public void setRepositoryService(RepositoryService var1) {
		this.h = var1;
	}

	public void setJunctionParser(JunctionParser var1) {
		this.g = var1;
	}

	public String url() {
		return "/common";
	}

	public void setApplicationContext(ApplicationContext var1) throws BeansException {
		super.setApplicationContext(var1);
		this.a = (ReferenceService)var1.getBean("urule.referenceService");
		this.j = var1.getBeansOfType(ActionParser.class).values();
		ActionLibraryDeserializer var2 = (ActionLibraryDeserializer)var1.getBean("urule.actionLibraryDeserializer");
		VariableLibraryDeserializer var3 = (VariableLibraryDeserializer)var1.getBean("urule.variableLibraryDeserializer");
		ConstantLibraryDeserializer var4 = (ConstantLibraryDeserializer)var1.getBean("urule.constantLibraryDeserializer");
		RuleSetDeserializer var5 = (RuleSetDeserializer)var1.getBean("urule.ruleSetDeserializer");
		DecisionTableDeserializer var6 = (DecisionTableDeserializer)var1.getBean("urule.decisionTableDeserializer");
		ScriptDecisionTableDeserializer var7 = (ScriptDecisionTableDeserializer)var1.getBean("urule.scriptDecisionTableDeserializer");
		DecisionTreeDeserializer var8 = (DecisionTreeDeserializer)var1.getBean("urule.decisionTreeDeserializer");
		ScorecardDeserializer var9 = (ScorecardDeserializer)var1.getBean("urule.scorecardDeserializer");
		ComplexScorecardDeserializer var10 = (ComplexScorecardDeserializer)var1.getBean("urule.complexScorecardDeserializer");
		ParameterLibraryDeserializer var11 = (ParameterLibraryDeserializer)var1.getBean("urule.parameterLibraryDeserializer");
		CrosstableDeserializer var12 = (CrosstableDeserializer)var1.getBean("urule.crosstableDeserializer");
		ConditionTemplateDeserializer var13 = (ConditionTemplateDeserializer)var1.getBean("urule.conditionTemplateDeserializer");
		ActionTemplateDeserializer var14 = (ActionTemplateDeserializer)var1.getBean("urule.actionTemplateDeserializer");
		this.k.add(var2);
		this.k.add(var3);
		this.k.add(var4);
		this.k.add(var5);
		this.k.add(var6);
		this.k.add(var7);
		this.k.add(var8);
		this.k.add(var11);
		this.k.add(var9);
		this.k.add(var10);
		this.k.add(var12);
		this.k.add(var13);
		this.k.add(var14);
		Collection var15 = var1.getBeansOfType(FunctionDescriptor.class).values();
		Iterator var16 = var15.iterator();

		while(var16.hasNext()) {
			FunctionDescriptor var17 = (FunctionDescriptor)var16.next();
			if (!var17.isDisabled()) {
				this.l.add((FunctionDescriptor)ProxyUtils.getTargetObject(var17));
			}
		}

	}
}
