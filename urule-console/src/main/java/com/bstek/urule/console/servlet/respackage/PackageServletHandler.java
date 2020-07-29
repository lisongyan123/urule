//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.respackage;

import com.bstek.urule.Configure;
import com.bstek.urule.ContextHolder;
import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.builder.KnowledgeBase;
import com.bstek.urule.builder.KnowledgeBuilder;
import com.bstek.urule.builder.ResourceBase;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.cache.ResourcePackageCache;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.repository.ClientConfig;
import com.bstek.urule.console.repository.KnowledgePackageRepositoryService;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.model.VersionFile;
import com.bstek.urule.console.servlet.JsonBuilder;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.console.servlet.console.ConsoleKeyHolder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.GeneralEntity;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.cache.CacheUtils;
import com.bstek.urule.runtime.log.FlowNodeLog;
import com.bstek.urule.runtime.log.MatchedRuleLog;
import com.bstek.urule.runtime.response.ExecutionResponse;
import com.bstek.urule.runtime.service.KnowledgePackageService;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class PackageServletHandler extends RenderPageServletHandler {
	public static final String KB_KEY = "_k_b";
	public static final String VCS_KEY = "_v_c_s";
	public static final String IMPORT_EXCEL_DATA = "_import_excel_data";
	private RepositoryService a;
	private KnowledgePackageService b;
	private KnowledgeBuilder c;
	private RemoteDynamicJarsBuilder d;
	private HttpSessionKnowledgeCache e;
	private KnowledgePackageRepositoryService f;
	private ResourcePackageCache g;

	public PackageServletHandler() {
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
			var4.put("showFileExtensionName", PropertyConfigurer.getProperty("urule.show.fileExtensionName"));
			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var5 = this.ve.getTemplate("html/package-editor.html", "utf-8");
			PrintWriter var6 = var2.getWriter();
			var5.merge(var4, var6);
			var6.close();
		}

	}

	public void compare(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		VelocityContext var3 = new VelocityContext();
		String var4 = var1.getParameter("project");
		String var5 = var1.getParameter("packageId");
		if (var4.startsWith("/")) {
			var4 = var4.substring(1, var4.length());
		}

		String var6 = Utils.decodeURL(var4) + "/" + Utils.decodeURL(var5);
		var3.put("packageInfo", var6);
		var3.put("contextPath", var1.getContextPath());
		var3.put("version", Splash.getVersion());
		var3.put("_lis_", Splash.getFetchVersion());
		var2.setContentType("text/html");
		var2.setCharacterEncoding("utf-8");
		Template var7 = this.ve.getTemplate("html/package-version-compare.html", "utf-8");
		PrintWriter var8 = var2.getWriter();
		var7.merge(var3, var8);
		var8.close();
	}

	public void exportKnowledgePackage(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = Utils.decodeURL(var1.getParameter("project"));
			if (StringUtils.isBlank(var4)) {
				throw new RuleException("项目名不能为空！");
			} else {
				if (var4.startsWith("/")) {
					var4 = var4.substring(1, var4.length());
				}

				String var5 = Utils.decodeURL(var1.getParameter("packageId"));
				if (StringUtils.isBlank(var5)) {
					throw new RuleException("知识包ID不能为空！");
				} else {
					String var6 = var4 + "/" + var5;
					String var7 = var4 + "#" + var5 + ".data";
					var2.setHeader("Content-Disposition", "attachment; filename=" + new String(var7.getBytes("UTF-8"), "ISO8859-1"));
					String var8 = var1.getParameter("version");
					InputStream var9 = this.f.getKnowledgePackgeData(var6, var8);
					ServletOutputStream var10 = var2.getOutputStream();
					IOUtils.copy(var9, var10);
					IOUtils.closeQuietly(var9);
					IOUtils.closeQuietly(var10);
				}
			}
		}
	}

	public void loadPackages(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("project");
		var3 = Utils.decodeURL(var3);
		List var4 = this.f.loadProjectResourcePackages(var3);
		PageResourcePackage var5 = new PageResourcePackage(var1, var4);
		this.writeObjectToJson(var2, var5);
	}

	public void loadFlows(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		KnowledgeBase var3 = (KnowledgeBase)this.e.get(var1, "_k_b");
		Collection var4 = var3.getFlowMap().values();
		this.writeObjectToJson(var2, var4);
	}

	public void pushKnowledgePackageToClients(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("project");
		var3 = Utils.decodeURL(var3);
		String var4 = var3 + "/" + Utils.decodeURL(var1.getParameter("packageId"));
		if (var4.startsWith("/")) {
			var4 = var4.substring(1, var4.length());
		}

		String var5 = var1.getParameter("client");
		String var6 = var1.getParameter("version");
		Object var7 = null;
		byte[] var13;
		if (StringUtils.isNotBlank(var6)) {
			var13 = this.f.getKnowledgePackgeBytes(var4, var6);
		} else {
			KnowledgePackage var8 = CacheUtils.getKnowledgeCache().getKnowledge(var4);
			ObjectMapper var9 = new ObjectMapper();
			var9.setSerializationInclusion(Inclusion.NON_NULL);
			var9.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
			var9.setDateFormat(new SimpleDateFormat(Configure.getDateFormat()));
			String var10 = var9.writeValueAsString(new KnowledgePackageWrapper(var8));
			var13 = Utils.compress(var10);
		}

		List var14 = this.a.loadClientConfigs(var3, false);
		ArrayList var15 = new ArrayList();
		Iterator var16 = var14.iterator();

		while(var16.hasNext()) {
			ClientConfig var11 = (ClientConfig)var16.next();
			Map var12;
			if (StringUtils.isNotBlank(var5)) {
				if (var5.equals(var11.getClient())) {
					var12 = this.a(var4, var13, var11);
					var15.add(var12);
					break;
				}
			} else {
				var12 = this.a(var4, var13, var11);
				var15.add(var12);
			}
		}

		this.writeObjectToJson(var2, var15);
	}

	private Map<String, Object> a(String var1, byte[] var2, ClientConfig var3) {
		String var4 = this.a(var1, var2, var3.getClient());
		HashMap var5 = new HashMap();
		if (var4 == null) {
			var5.put("result", true);
			var5.put("info", "<div class='text-info' style='line-height:30px'>推送到客户端：" + var3.getName() + "：" + var3.getClient() + " 成功</div>");
		} else {
			var5.put("result", false);
			var5.put("info", "<div class='text-danger' style='line-height:30px'>推送到客户端：" + var3.getName() + "：" + var3.getClient() + " 失败</div>");
			var5.put("error", "<div style='margin-left:10px;color:red;word-wrap:break-word'>" + var4 + "</div>");
		}

		return var5;
	}

	public void loadClients(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("project");
		var3 = Utils.decodeURL(var3);
		List var4 = this.a.loadClientConfigs(var3, false);
		this.writeObjectToJson(var2, var4);
	}

	private String a(String var1, byte[] var2, String var3) {
		HttpURLConnection var4 = null;

		String var6;
		try {
			if (var3.endsWith("/")) {
				var3 = var3.substring(0, var3.length() - 1);
			}

			String var5 = "packageId=" + URLEncoder.encode(var1, "utf-8");
			var5 = var5 + "&_u=" + Utils.encodeURL(this.d.getUser()) + "";
			var5 = var5 + "&_p=" + Utils.encodeURL(this.d.getPwd()) + "";
			var6 = var3 + "/knowledgepackagereceiver" + "?" + var5;
			URL var7 = new URL(var6);
			var4 = (HttpURLConnection)var7.openConnection();
			var4.setRequestMethod("POST");
			var4.setRequestProperty("Charset", "UTF-8");
			var4.setRequestProperty("Accept-Charset", "utf-8");
			var4.setRequestProperty("Content-Type", "text/json");
			var4.setUseCaches(false);
			var4.setDoOutput(true);
			var4.setDoInput(true);
			var4.connect();
			OutputStream var8 = var4.getOutputStream();
			DataOutputStream var9 = new DataOutputStream(var4.getOutputStream());
			var9.write(var2);
			var9.flush();
			var9.close();
			InputStream var10 = var4.getInputStream();
			String var11;
			if (var10 == null) {
				var11 = "Unknow client error!";
				return var11;
			}

			var11 = IOUtils.toString(var10, "UTF-8");
			var8.close();
			var10.close();
			String var12;
			if (var11.equals("ok")) {
				var12 = null;
				return var12;
			}

			var12 = "<strong>推送操作成功到达客户端，但客户端出错错误：</strong><br>" + var11;
			return var12;
		} catch (Exception var16) {
			var6 = "<strong>服务端推送操作出现错误：</strong><br>" + this.a((Throwable)var16);
		} finally {
			if (var4 != null) {
				var4.disconnect();
			}

		}

		return var6;
	}

	private String a(Throwable var1) {
		ByteArrayOutputStream var2 = new ByteArrayOutputStream();
		PrintStream var3 = new PrintStream(var2);
		var1.printStackTrace(var3);
		String var4 = new String(var2.toByteArray());
		IOUtils.closeQuietly(var3);
		IOUtils.closeQuietly(var2);
		var4 = var4.replaceAll("\n", "<br>");
		return var4;
	}

	public void removeDeployKnowledgePackage(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = var1.getParameter("project");
			var4 = Utils.decodeURL(var4);
			String var5 = var4 + "/" + Utils.decodeURL(var1.getParameter("packageId"));
			if (var5.startsWith("/")) {
				var5 = var5.substring(1, var5.length());
			}

			this.f.removeDeployKnowledgePackge(var5);
		}
	}

	public void removeKnowledgePackage(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = var1.getParameter("project");
			var4 = Utils.decodeURL(var4);
			String var5 = var4 + "/" + Utils.decodeURL(var1.getParameter("packageId"));
			if (var5.startsWith("/")) {
				var5 = var5.substring(1, var5.length());
			}

			String var6 = var1.getParameter("version");
			this.f.removeKnowledgePackge(var5, var6);
		}
	}

	public void activeKnowledgePackage(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = var1.getParameter("project");
			var4 = Utils.decodeURL(var4);
			String var5 = var4 + "/" + Utils.decodeURL(var1.getParameter("packageId"));
			if (var5.startsWith("/")) {
				var5 = var5.substring(1, var5.length());
			}

			String var6 = var1.getParameter("version");
			this.f.activeKnowledgePackage(var5, var6);
			VersionFile var7 = this.f.getActivedKnowledgePackge(var5);
			KnowledgePackage var8 = this.f.getKnowledgePackge(var5, var7.getName());
			CacheUtils.getKnowledgeCache().putKnowledge(var5, var8);
			HashMap var9 = new HashMap();
			this.a((String)var4, (Map)var9);
			this.writeObjectToJson(var2, var9);
		}
	}

	public void loadVersions(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = var1.getParameter("project");
			var4 = Utils.decodeURL(var4);
			String var5 = var4 + "/" + Utils.decodeURL(var1.getParameter("packageId"));
			if (var5.startsWith("/")) {
				var5 = var5.substring(1, var5.length());
			}

			List var6 = this.f.getKnowledgePackges(var5);
			this.writeObjectToJson(var2, var6);
		}
	}

	public void loadPackageVersionFiles(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = var1.getParameter("project");
			var4 = Utils.decodeURL(var4);
			String var5 = var4 + "/" + Utils.decodeURL(var1.getParameter("packageId"));
			if (var5.startsWith("/")) {
				var5 = var5.substring(1, var5.length());
			}

			String var6 = var1.getParameter("sourceVersion");
			Map var7 = this.f.loadKnowledgePackageFiles(var5, var6);
			String var8 = var1.getParameter("targetVersion");
			Map var9 = this.f.loadKnowledgePackageFiles(var5, var8);
			HashMap var10 = new HashMap();
			var10.put("sourceData", var7);
			var10.put("targetData", var9);
			this.writeObjectToJson(var2, var10);
		}
	}

	public void refreshKnowledgeCache(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = var1.getParameter("active");
			boolean var5 = true;
			if (StringUtils.isNotBlank(var4) && var4.equals("0")) {
				var5 = false;
			}

			String var6 = var1.getParameter("version");
			ContextHolder.putData("_version", var6);
			String var7 = var1.getParameter("project");
			var7 = Utils.decodeURL(var7);
			String var8 = var7 + "/" + Utils.decodeURL(var1.getParameter("packageId"));
			if (var8.startsWith("/")) {
				var8 = var8.substring(1, var8.length());
			}

			KnowledgePackage var9 = this.b.buildKnowledgePackage(var8);
			if (var5) {
				CacheUtils.getKnowledgeCache().putKnowledge(var8, var9);
			}

			String var10 = var1.getParameter("comment");
			this.f.saveKnowledgePackage(var8, var9, var10, var3.getName(), var5);
			HashMap var11 = new HashMap();
			this.a((String)var7, (Map)var11);
			this.writeObjectToJson(var2, var11);
		}
	}

	private void a(String var1, Map<String, Object> var2) throws Exception {
		List var3 = this.a.loadClientConfigs(var1, false);
		if (var3.size() > 0) {
			StringBuffer var4 = new StringBuffer();
			int var5 = 1;

			for(Iterator var6 = var3.iterator(); var6.hasNext(); ++var5) {
				ClientConfig var7 = (ClientConfig)var6.next();
				if (var5 > 1) {
					var4.append("<br>");
				}

				var4.append(var7.getName() + "：" + var7.getClient());
			}

			var2.put("clientInfo", var4.toString());
		}

	}

	public void loadForTestVariableCategories(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("version");
		if (var3 != null) {
			ContextHolder.putData("_version", var3);
		}

		KnowledgeBase var4 = this.a(var1);
		List var5 = var4.getResourceLibrary().getVariableCategories();
		this.e.put(var1, "_v_c_s", var5);
		this.writeObjectToJson(var2, var5);
	}

	private KnowledgeBase a(HttpServletRequest var1) throws IOException {
		String var2 = var1.getParameter("files");
		var2 = Utils.decodeURL(var2);
		ResourceBase var3 = this.c.newResourceBase();
		String[] var4 = var2.split(";");
		String[] var5 = var4;
		int var6 = var4.length;

		for(int var7 = 0; var7 < var6; ++var7) {
			String var8 = var5[var7];
			String[] var9 = var8.split(",");
			var8 = var9[0];
			String var10 = null;
			if (var9.length > 1) {
				var10 = var9[1];
			}

			var3.addResource(var8, var10);
		}

		KnowledgeBase var11 = this.c.buildKnowledgeBase(var3);
		this.e.remove(var1, "_k_b");
		this.e.put(var1, "_k_b", var11);
		return var11;
	}

	public void admin(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		HashMap var4 = new HashMap();
		var4.put("admin", var3.isAdmin());
		this.writeObjectToJson(var2, var4);
	}

	public void saveResourcePackages(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("project");
		var3 = Utils.decodeURL(var3);
		Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		ResourcePackageUpdater var5 = new ResourcePackageUpdater(this.a, this.f);
		var5.doUpdate(var1, var4);
		this.f.resetProjectResourcePackagesTag(var3);
		this.g.storeResourcePackages(this.f.loadProjectResourcePackages(var3));
		this.g.resetResourcePackageTag(this.f.getProjectResourcePackagesTag(var3));
	}

	private List<VariableCategory> a(List<Map<String, Object>> var1) {
		ArrayList var2 = new ArrayList();
		Iterator var3 = var1.iterator();

		label62:
		while(var3.hasNext()) {
			Map var4 = (Map)var3.next();
			VariableCategory var5 = new VariableCategory();
			var2.add(var5);
			Iterator var6 = var4.keySet().iterator();

			while(true) {
				while(true) {
					if (!var6.hasNext()) {
						continue label62;
					}

					String var7 = (String)var6.next();
					if (var7.equals("name")) {
						var5.setName((String)var4.get(var7));
					} else if (var7.equals("clazz")) {
						var5.setClazz((String)var4.get(var7));
					} else if (var7.equals("variables")) {
						List var8 = (List)var4.get(var7);
						if (var8 != null) {
							Iterator var9 = var8.iterator();

							while(var9.hasNext()) {
								Map var10 = (Map)var9.next();
								Variable var11 = new Variable();
								var5.addVariable(var11);
								Iterator var12 = var10.keySet().iterator();

								while(var12.hasNext()) {
									String var13 = (String)var12.next();
									if (var13.equals("name")) {
										var11.setName((String)var10.get(var13));
									} else if (var13.equals("label")) {
										var11.setLabel((String)var10.get(var13));
									} else if (var13.equals("type")) {
										var11.setType(Datatype.valueOf((String)var10.get(var13)));
									} else if (var13.equals("defaultValue")) {
										var11.setDefaultValue((String)var10.get(var13));
									}
								}
							}
						}
					}
				}
			}
		}

		return var2;
	}

	public void doJsonQuickTest(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("input");
		String var4 = var1.getParameter("output");
		ObjectMapper var5 = new ObjectMapper();
		SimpleDateFormat var6 = new SimpleDateFormat(Configure.getDateFormat());
		var5.getDeserializationConfig().withDateFormat(var6);
		var5.setDateFormat(var6);
		KnowledgeBase var7 = this.a(var1);
		ResourceLibrary var8 = var7.getResourceLibrary();
		List var9 = (List)var5.readValue(var3, ArrayList.class);
		List var10 = (List)var5.readValue(var4, ArrayList.class);
		List var11 = this.a(var9, var8.getVariableCategories());
		KnowledgePackage var12 = var7.getKnowledgePackage();
		KnowledgeSession var13 = KnowledgeSessionFactory.newKnowledgeSession(var12);
		Map var14 = null;
		Iterator var15 = var11.iterator();

		Map var16;
		while(var15.hasNext()) {
			var16 = (Map)var15.next();
			if (var16 instanceof GeneralEntity) {
				var13.insert(var16);
			} else if (var14 == null) {
				var14 = var16;
			} else {
				var14.putAll(var16);
			}
		}

		Map var20 = var7.getFlowMap();
		var16 = null;
		Object var21;
		if (var20 != null && var20.size() > 0) {
			String var17 = (String)var20.keySet().iterator().next();
			if (var14 != null) {
				var21 = var13.startProcess(var17, var14);
			} else {
				var21 = var13.startProcess(var17);
			}
		} else if (var14 != null) {
			var21 = var13.fireRules(var14);
		} else {
			var21 = var13.fireRules();
		}

		var13.writeLogFile();
		List var22 = this.a(var10, var11, var13.getParameters());
		HashMap var18 = new HashMap();
		var18.put("output", var22);
		var18.put("time", ((ExecutionResponse)var21).getDuration());
		String var19 = ConsoleKeyHolder.getKey();
		if (var19 != null) {
			var18.put("logUrl", "console?key=" + var19 + "");
		}

		ConsoleKeyHolder.clean();
		this.writeObjectToJson(var2, var18);
	}

	public void packageStateChange(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("packageId");
		String var4 = var1.getParameter("project");
		var4 = Utils.decodeURL(var4);
		if (var4.startsWith("/")) {
			var4 = var4.substring(1, var4.length());
		}

		boolean var5 = Boolean.valueOf(var1.getParameter("state"));
		String var6 = var4 + "/" + var3;
		this.f.knowledgePackageStateChange(var6, var5);
	}

	public void doQuickTest(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("input");
		String var4 = var1.getParameter("output");
		ObjectMapper var5 = new ObjectMapper();
		SimpleDateFormat var6 = new SimpleDateFormat(Configure.getDateFormat());
		var5.getDeserializationConfig().withDateFormat(var6);
		var5.setDateFormat(var6);
		KnowledgeBase var7 = this.a(var1);
		ResourceLibrary var8 = var7.getResourceLibrary();
		List var9 = (List)var5.readValue(var3, ArrayList.class);
		List var10 = (List)var5.readValue(var4, ArrayList.class);
		List var11 = this.b(var9, var8.getVariableCategories());
		KnowledgePackage var12 = var7.getKnowledgePackage();
		KnowledgeSession var13 = KnowledgeSessionFactory.newKnowledgeSession(var12);
		Map var14 = null;
		Iterator var15 = var11.iterator();

		Map var16;
		while(var15.hasNext()) {
			var16 = (Map)var15.next();
			if (var16 instanceof GeneralEntity) {
				var13.insert(var16);
			} else if (var14 == null) {
				var14 = var16;
			} else {
				var14.putAll(var16);
			}
		}

		Map var20 = var7.getFlowMap();
		var16 = null;
		Object var21;
		if (var20 != null && var20.size() > 0) {
			String var17 = (String)var20.keySet().iterator().next();
			if (var14 != null) {
				var21 = var13.startProcess(var17, var14);
			} else {
				var21 = var13.startProcess(var17);
			}
		} else if (var14 != null) {
			var21 = var13.fireRules(var14);
		} else {
			var21 = var13.fireRules();
		}

		var13.writeLogFile();
		List var22 = this.b(var10, var11, var13.getParameters());
		HashMap var18 = new HashMap();
		var18.put("output", var22);
		var18.put("time", ((ExecutionResponse)var21).getDuration());
		String var19 = ConsoleKeyHolder.getKey();
		if (var19 != null) {
			var18.put("logUrl", "console?key=" + var19 + "");
		}

		ConsoleKeyHolder.clean();
		this.writeObjectToJson(var2, var18);
	}

	private List<Map<String, Object>> a(List<Map<String, Object>> var1, List<Map<String, Object>> var2, Map<String, Object> var3) throws Exception {
		ArrayList var4 = new ArrayList();
		Iterator var5 = var1.iterator();

		while(true) {
			while(var5.hasNext()) {
				Map var6 = (Map)var5.next();
				String var7 = (String)var6.get("categoryName");
				if (var7.equals("参数")) {
					HashMap var13 = new HashMap();
					var4.add(var13);
					var13.put("name", var7);
					var13.put("fields", var3);
				} else {
					String var8 = (String)var6.get("categoryClass");
					Iterator var9 = var2.iterator();

					while(var9.hasNext()) {
						Map var10 = (Map)var9.next();
						if (var10 instanceof GeneralEntity) {
							String var11 = ((GeneralEntity)var10).getTargetClass();
							if (var8.equals(var11)) {
								HashMap var12 = new HashMap();
								var4.add(var12);
								var12.put("name", var7);
								var12.put("fields", var10);
							}
						}
					}
				}
			}

			return var4;
		}
	}

	private List<Map<String, Object>> b(List<Map<String, Object>> var1, List<Map<String, Object>> var2, Map<String, Object> var3) throws Exception {
		ArrayList var4 = new ArrayList();
		Iterator var5 = var1.iterator();

		while(true) {
			label55:
			while(true) {
				Map var6;
				List var7;
				do {
					do {
						if (!var5.hasNext()) {
							return var4;
						}

						var6 = (Map)var5.next();
						var7 = (List)var6.get("fields");
					} while(var7 == null);
				} while(var7.size() == 0);

				String var8 = (String)var6.get("categoryName");
				HashMap var13;
				if (var8.equals("参数")) {
					HashMap var20 = new HashMap();
					var4.add(var20);
					var20.put("name", var8);
					ArrayList var21 = new ArrayList();
					var20.put("fields", var21);
					Iterator var22 = var7.iterator();

					while(var22.hasNext()) {
						Map var23 = (Map)var22.next();
						var13 = new HashMap();
						var21.add(var13);
						String var24 = (String)var23.get("name");
						Object var25 = Utils.getObjectProperty(var3, var24);
						var13.put("name", (String)var23.get("label"));
						var13.put("value", var25);
					}
				} else {
					String var9 = (String)var6.get("categoryClass");
					Iterator var10 = var2.iterator();

					while(true) {
						Map var11;
						String var12;
						do {
							do {
								if (!var10.hasNext()) {
									continue label55;
								}

								var11 = (Map)var10.next();
							} while(!(var11 instanceof GeneralEntity));

							var12 = ((GeneralEntity)var11).getTargetClass();
						} while(!var9.equals(var12));

						var13 = new HashMap();
						var4.add(var13);
						var13.put("name", var8);
						ArrayList var14 = new ArrayList();
						var13.put("fields", var14);
						Iterator var15 = var7.iterator();

						while(var15.hasNext()) {
							Map var16 = (Map)var15.next();
							HashMap var17 = new HashMap();
							var14.add(var17);
							String var18 = (String)var16.get("name");
							Object var19 = Utils.getObjectProperty(var11, var18);
							var17.put("name", (String)var16.get("label"));
							var17.put("value", var19);
						}
					}
				}
			}
		}
	}

	private List<Map<String, Object>> a(List<Map<String, Object>> var1, List<VariableCategory> var2) throws Exception {
		HashSet var3 = new HashSet();
		ArrayList var4 = new ArrayList();
		Iterator var5 = var1.iterator();

		label94:
		while(var5.hasNext()) {
			Map var6 = (Map)var5.next();
			Object var7 = null;
			String var8 = (String)var6.get("name");
			List var9 = null;
			Iterator var11;
			if (var8.equals("参数")) {
				var7 = new HashMap();
				var3.add(var8);
			} else {
				String var10 = null;
				var11 = var2.iterator();

				while(var11.hasNext()) {
					VariableCategory var12 = (VariableCategory)var11.next();
					if (var12.getName().equals(var8)) {
						var10 = var12.getClazz();
						var9 = var12.getVariables();
						break;
					}
				}

				if (var10 == null) {
					throw new RuleException("不能找到输入变量【" + var8 + "】在变量库里对应的定义信息！");
				}

				var7 = new GeneralEntity(var10);
				var3.add(var10);
			}

			var4.add(var7);
			Map var22 = (Map)var6.get("fields");
			var11 = var22.keySet().iterator();

			while(true) {
				while(true) {
					if (!var11.hasNext()) {
						continue label94;
					}

					String var23 = (String)var11.next();
					Variable var13 = null;
					Iterator var14 = var9.iterator();

					label72: {
						Variable var15;
						do {
							if (!var14.hasNext()) {
								break label72;
							}

							var15 = (Variable)var14.next();
						} while(!var15.getName().equals(var23) && !var15.getLabel().equals(var23));

						var13 = var15;
					}

					if (var13 == null) {
						throw new RuleException("不能找到输入变量【" + var8 + "】中定义的字段【" + var23 + "】在变量库中对应的定义信息！");
					}

					Datatype var24 = var13.getType();
					Object var25 = var22.get(var23);
					Object var16 = null;
					if (!var24.equals(Datatype.List) && !var24.equals(Datatype.Object) && !var24.equals(Datatype.Set) && !var24.equals(Datatype.Map)) {
						var16 = var24.convert(var25);
					} else {
						var16 = JsonBuilder.getInstance().buildComplexObject(var25, var2);
					}

					String[] var17 = var23.split("\\.");
					Object var18 = var7;

					for(int var19 = 0; var19 < var17.length; ++var19) {
						String var20 = var17[var19];
						if (var19 == var17.length - 1) {
							Utils.setObjectProperty(var18, var20, var16);
							break;
						}

						Object var21 = Utils.getObjectProperty(var18, var20);
						if (var21 == null) {
							var21 = new HashMap();
							Utils.setObjectProperty(var18, var20, var21);
						}

						var18 = var21;
					}
				}
			}
		}

		return var4;
	}

	private List<Map<String, Object>> b(List<Map<String, Object>> var1, List<VariableCategory> var2) throws Exception {
		HashSet var3 = new HashSet();
		ArrayList var4 = new ArrayList();
		Iterator var5 = var1.iterator();

		while(var5.hasNext()) {
			Map var6 = (Map)var5.next();
			Object var7 = null;
			String var8 = (String)var6.get("categoryName");
			if (var8.equals("参数")) {
				var7 = new HashMap();
				var3.add(var8);
			} else {
				String var9 = (String)var6.get("categoryClass");
				var7 = new GeneralEntity(var9);
				var3.add(var9);
			}

			var4.add(var7);
			List var10 = (List)var6.get("fields");
			this.a((List)var10, (Map)var7, (List)var2);
		}

		return var4;
	}

	private void a(List<Map<String, Object>> var1, Map<String, Object> var2, List<VariableCategory> var3) throws Exception {
		if (var1 != null) {
			Iterator var4 = var1.iterator();

			while(true) {
				while(true) {
					Map var5;
					String var6;
					String var7;
					do {
						if (!var4.hasNext()) {
							return;
						}

						var5 = (Map)var4.next();
						var6 = (String)var5.get("name");
						var7 = (String)var5.get("value");
					} while(var7 == null);

					String var8 = (String)var5.get("type");
					Datatype var9 = Datatype.parse(var8);
					Object var10 = null;
					if (!var9.equals(Datatype.List) && !var9.equals(Datatype.Object) && !var9.equals(Datatype.Set) && !var9.equals(Datatype.Map)) {
						var10 = var9.convert(var7);
					} else {
						var10 = JsonBuilder.getInstance().buildComplexObject(var7, var3);
						var2.put(var6, var10);
					}

					String[] var11 = var6.split("\\.");
					Object var12 = var2;

					for(int var13 = 0; var13 < var11.length; ++var13) {
						String var14 = var11[var13];
						if (var13 == var11.length - 1) {
							Utils.setObjectProperty(var12, var14, var10);
							break;
						}

						Object var15 = Utils.getObjectProperty(var12, var14);
						if (var15 == null) {
							var15 = new HashMap();
							Utils.setObjectProperty(var12, var14, var15);
						}

						var12 = var15;
					}
				}
			}
		}
	}

	public void doTest(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("data");
		ObjectMapper var4 = new ObjectMapper();
		SimpleDateFormat var5 = new SimpleDateFormat(Configure.getDateFormat());
		var4.getDeserializationConfig().withDateFormat(var5);
		var4.setDateFormat(var5);
		List var6 = (List)var4.readValue(var3, ArrayList.class);
		List var7 = this.a(var6);
		HashMap var8 = new HashMap();
		Iterator var9 = var7.iterator();

		while(var9.hasNext()) {
			VariableCategory var10 = (VariableCategory)var9.next();
			String var11 = var10.getClazz();
			Object var12 = null;
			if (var10.getName().equals("参数")) {
				var12 = new HashMap();
			} else {
				var12 = new GeneralEntity(var11);
			}

			Iterator var13 = var10.getVariables().iterator();

			while(var13.hasNext()) {
				Variable var14 = (Variable)var13.next();
				this.a(var12, var14);
			}

			var8.put(var10, var12);
		}

		String var25 = var1.getParameter("flowId");
		long var26 = System.currentTimeMillis();
		KnowledgeBase var28 = (KnowledgeBase)this.e.get(var1, "_k_b");
		if (var28 == null) {
			var28 = this.a(var1);
		}

		KnowledgePackage var27 = var28.getKnowledgePackage();
		KnowledgeSession var29 = KnowledgeSessionFactory.newKnowledgeSession(var27);
		Map var15 = null;
		Iterator var16 = var8.values().iterator();

		while(true) {
			while(var16.hasNext()) {
				Object var17 = var16.next();
				if (!(var17 instanceof GeneralEntity) && var17 instanceof HashMap) {
					var15 = (Map)var17;
				} else {
					var29.insert(var17);
				}
			}

			if (StringUtils.isNotEmpty(var25)) {
				if (var15 != null) {
					var29.startProcess(var25, var15);
				} else {
					var29.startProcess(var25);
				}
			} else if (var15 == null) {
				var29.fireRules();
			} else {
				var29.fireRules(var15);
			}

			var16 = var8.keySet().iterator();

			while(true) {
				Object var18;
				VariableCategory var31;
				do {
					if (!var16.hasNext()) {
						long var30 = System.currentTimeMillis();
						long var32 = var30 - var26;
						var29.writeLogFile();
						List var33 = var29.getLogManager().buildMatchedRuleLog();
						StringBuffer var21 = new StringBuffer();
						var21.append("耗时：" + var32 + "ms<br>");
						var21.append("<div style='color:#9E9E9E;font-size:12px'>以下为执行日志信息，日志信息有无取决于urule.debug属性是否置设置为true，且规则的“允许调试信息输出”属性是否设置为true</div>");
						if (var33.size() > 0) {
							var21.append("<br>匹配的规则共" + var33.size() + "个<br>");
							var21.append("<span style='color:blue'>");
							this.a(var33, var21);
							var21.append("</span>");
						}

						List var22 = var29.getLogManager().buildFlowNodeData();
						if (var22.size() > 0) {
							var21.append("<br><br>经过的规则流节点数量为：" + var22.size() + "个<br>");
							var21.append("<span style='color:green'>");
							this.b(var22, var21);
							var21.append("</span>");
						}

						String var23 = ConsoleKeyHolder.getKey();
						if (var23 != null) {
							var21.append("<br><br><a href='console?key=" + var23 + "' target='_blank'>查看详细日志</a>");
						}

						ConsoleKeyHolder.clean();
						HashMap var24 = new HashMap();
						var24.put("info", var21.toString());
						var24.put("data", var7);
						this.writeObjectToJson(var2, var24);
						return;
					}

					var31 = (VariableCategory)var16.next();
					var18 = var8.get(var31);
				} while(var18 == null);

				if (var18 instanceof Map && !(var18 instanceof GeneralEntity)) {
					var18 = var29.getParameters();
				}

				Iterator var19 = var31.getVariables().iterator();

				while(var19.hasNext()) {
					Variable var20 = (Variable)var19.next();
					this.a(var18, var20, var4);
				}
			}
		}
	}

	private void a(Object var1, Variable var2) {
		String var3 = var2.getName();
		if (var3.indexOf(".") != -1) {
			this.a(var1, var3);
		}

		String var4 = var2.getDefaultValue();
		if (!StringUtils.isBlank(var4)) {
			Datatype var5 = var2.getType();
			if (var5.equals(Datatype.List)) {
				Utils.setObjectProperty(var1, var3, this.a(var4));
			} else if (var5.equals(Datatype.Set)) {
				Utils.setObjectProperty(var1, var3, this.b(var4));
			} else {
				if (var5.equals(Datatype.Map)) {
					return;
				}

				Object var6 = var5.convert(var4);
				if (var5.equals(Datatype.Enum) && var6 instanceof String && var1 instanceof GeneralEntity) {
					GeneralEntity var7 = (GeneralEntity)var1;
					String var8 = var7.getTargetClass();
					Class var9 = null;

					try {
						var9 = Class.forName(var8);
					} catch (ClassNotFoundException var14) {
					}

					if (var9 != null) {
						try {
							Field var10 = var9.getDeclaredField(var3);
							String var11 = var10.getType().getName();
							Class var12 = Class.forName(var11);
							var6 = Enum.valueOf(var12, var6.toString());
						} catch (Exception var13) {
							throw new RuleException(var13);
						}
					}
				}

				Utils.setObjectProperty(var1, var3, var6);
			}

		}
	}

	private List<Object> a(String var1) {
		try {
			ObjectMapper var2 = new ObjectMapper();
			if (var1.startsWith("[") && var1.endsWith("]")) {
				return (List)var2.readValue(var1, ArrayList.class);
			} else {
				ArrayList var3 = new ArrayList();
				Map var4 = (Map)var2.readValue(var1, HashMap.class);
				if (!var4.containsKey("rows")) {
					return null;
				} else {
					List var5 = (List)var4.get("rows");
					String var6 = (String)var4.get("type");
					Object var7 = var4.get("rows");
					List var8;
					Iterator var9;
					Map var10;
					Object var11;
					BigDecimal var12;
					if (var6.equals("Integer")) {
						var8 = (List)var7;
						var9 = var8.iterator();

						while(var9.hasNext()) {
							var10 = (Map)var9.next();
							var11 = var10.get("value");
							if (var11 != null) {
								var12 = Utils.toBigDecimal(var11);
								var3.add(var12.intValue());
							}
						}
					} else if (var6.equals("Double")) {
						var8 = (List)var7;
						var9 = var8.iterator();

						while(var9.hasNext()) {
							var10 = (Map)var9.next();
							var11 = var10.get("value");
							if (var11 != null) {
								var12 = Utils.toBigDecimal(var11);
								var3.add(var12.doubleValue());
							}
						}
					} else if (var6.equals("Float")) {
						var8 = (List)var7;
						var9 = var8.iterator();

						while(var9.hasNext()) {
							var10 = (Map)var9.next();
							var11 = var10.get("value");
							if (var11 != null) {
								var12 = Utils.toBigDecimal(var11);
								var3.add(var12.floatValue());
							}
						}
					} else if (var6.equals("String")) {
						var8 = (List)var7;
						var9 = var8.iterator();

						while(var9.hasNext()) {
							var10 = (Map)var9.next();
							var11 = var10.get("value");
							if (var11 != null) {
								var3.add(var11.toString());
							}
						}
					} else {
						Iterator var14 = var5.iterator();

						while(var14.hasNext()) {
							Object var15 = var14.next();
							if (var15 instanceof Map) {
								GeneralEntity var16 = new GeneralEntity(var6);
								var16.putAll((Map)var15);
								var3.add(var16);
							}
						}
					}

					return var3;
				}
			}
		} catch (Exception var13) {
			throw new RuleException(var13);
		}
	}

	private Set<Object> b(String var1) {
		try {
			HashSet var2 = new HashSet();
			ObjectMapper var3 = new ObjectMapper();
			Map var4 = (Map)var3.readValue(var1, HashMap.class);
			if (!var4.containsKey("rows")) {
				return null;
			} else {
				List var5 = (List)var4.get("rows");
				String var6 = (String)var4.get("type");
				Object var7 = var4.get("rows");
				List var8;
				Iterator var9;
				Map var10;
				Object var11;
				BigDecimal var12;
				if (var6.equals("Integer")) {
					var8 = (List)var7;
					var9 = var8.iterator();

					while(var9.hasNext()) {
						var10 = (Map)var9.next();
						var11 = var10.get("value");
						if (var11 != null) {
							var12 = Utils.toBigDecimal(var11);
							var2.add(var12.intValue());
						}
					}
				} else if (var6.equals("Double")) {
					var8 = (List)var7;
					var9 = var8.iterator();

					while(var9.hasNext()) {
						var10 = (Map)var9.next();
						var11 = var10.get("value");
						if (var11 != null) {
							var12 = Utils.toBigDecimal(var11);
							var2.add(var12.doubleValue());
						}
					}
				} else if (var6.equals("Float")) {
					var8 = (List)var7;
					var9 = var8.iterator();

					while(var9.hasNext()) {
						var10 = (Map)var9.next();
						var11 = var10.get("value");
						if (var11 != null) {
							var12 = Utils.toBigDecimal(var11);
							var2.add(var12.floatValue());
						}
					}
				} else if (var6.equals("String")) {
					var8 = (List)var7;
					var9 = var8.iterator();

					while(var9.hasNext()) {
						var10 = (Map)var9.next();
						var11 = var10.get("value");
						if (var11 != null) {
							var2.add(var11.toString());
						}
					}
				} else {
					Iterator var14 = var5.iterator();

					while(var14.hasNext()) {
						Object var15 = var14.next();
						if (var15 instanceof Map) {
							GeneralEntity var16 = new GeneralEntity((String)var4.get("type"));
							var16.putAll((Map)var15);
							var2.add(var16);
						}
					}
				}

				return var2;
			}
		} catch (Exception var13) {
			throw new RuleException(var13);
		}
	}

	private void a(Object var1, String var2) {
		int var3 = var2.indexOf(".");
		if (var3 != -1) {
			String var4 = var2.substring(0, var3);
			var2 = var2.substring(var3 + 1);

			try {
				Object var5 = PropertyUtils.getProperty(var1, var4);
				if (var5 != null) {
					this.a(var5, var2);
				} else {
					GeneralEntity var6 = new GeneralEntity(var4);
					PropertyUtils.setProperty(var1, var4, var6);
					this.a((Object)var6, (String)var2);
				}
			} catch (Exception var7) {
				throw new RuleException(var7);
			}
		}
	}

	private void a(List<MatchedRuleLog> var1, StringBuffer var2) {
		int var3 = 0;

		for(Iterator var4 = var1.iterator(); var4.hasNext(); ++var3) {
			MatchedRuleLog var5 = (MatchedRuleLog)var4.next();
			if (var3 > 0) {
				var2.append("，<br>");
			}

			var2.append(var5.getRuleName() + "(" + var5.getRuleFile() + ")");
		}

	}

	private void b(List<FlowNodeLog> var1, StringBuffer var2) {
		int var3 = 0;

		for(Iterator var4 = var1.iterator(); var4.hasNext(); ++var3) {
			FlowNodeLog var5 = (FlowNodeLog)var4.next();
			if (var3 > 0) {
				var2.append("，<br>");
			}

			var2.append(var5.getNodeName() + "(" + var5.getFile() + ")");
		}

	}

	private void a(Object var1, Variable var2, ObjectMapper var3) {
		try {
			String var4 = var2.getName();
			Object var5 = Utils.getObjectProperty(var1, var4);
			if (var5 != null) {
				Datatype var6 = var2.getType();
				String var7;
				if (!var6.equals(Datatype.List) && !var6.equals(Datatype.Set) && !var6.equals(Datatype.Object) && !var6.equals(Datatype.Map)) {
					var7 = var6.convertObjectToString(var5);
					var2.setDefaultValue(var7);
				} else {
					var7 = var3.writeValueAsString(var5);
					var2.setDefaultValue(var7);
				}
			}

		} catch (Exception var8) {
			throw new RuleException(var8);
		}
	}

	public void setResourcePackageCache(ResourcePackageCache var1) {
		this.g = var1;
	}

	public void setKnowledgePackageService(KnowledgePackageService var1) {
		this.b = var1;
	}

	public void setRepositoryService(RepositoryService var1) {
		this.a = var1;
	}

	public void setKnowledgeBuilder(KnowledgeBuilder var1) {
		this.c = var1;
	}

	public void setHttpSessionKnowledgeCache(HttpSessionKnowledgeCache var1) {
		this.e = var1;
	}

	public void setKnowledgePackageRepositoryService(KnowledgePackageRepositoryService var1) {
		this.f = var1;
	}

	public void setRemoteDynamicJarsBuilder(RemoteDynamicJarsBuilder var1) {
		this.d = var1;
	}

	public String url() {
		return "/packageeditor";
	}
}
