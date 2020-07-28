//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.knowledge;

import com.bstek.urule.Configure;
import com.bstek.urule.Utils;
import com.bstek.urule.console.repository.KnowledgePackageRepositoryService;
import com.bstek.urule.console.repository.model.VersionFile;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import com.bstek.urule.runtime.RemoteDynamicJarsBuilder;
import com.bstek.urule.runtime.cache.CacheUtils;
import com.bstek.urule.runtime.service.KnowledgePackageService;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

public class LoadKnowledgeServletHandler extends RenderPageServletHandler {
	private KnowledgePackageService a;
	private KnowledgePackageRepositoryService b;
	private RemoteDynamicJarsBuilder c;

	public LoadKnowledgeServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		if (this.validateUserPwd(var1, this.c.getUser(), this.c.getPwd())) {
			String var3 = var1.getParameter("packageId");
			String var4;
			if (StringUtils.isEmpty(var3)) {
				var4 = "<h1>PackageId can not be null<h1>";
				this.a(var2, var4);
			} else {
				var3 = Utils.decodeURL(var3);
				var4 = var1.getParameter("timestamp");
				KnowledgePackage var5 = CacheUtils.getKnowledgeCache().getKnowledge(var3);
				if (var5 == null) {
					VersionFile var6 = this.b.getActivedKnowledgePackge(var3);
					if (var6 == null) {
						var5 = this.a.buildKnowledgePackage(var3);
					} else {
						var5 = this.b.getKnowledgePackge(var3, var6.getName());
					}

					CacheUtils.getKnowledgeCache().putKnowledge(var3, var5);
				}

				boolean var13 = false;
				String var7 = var1.getParameter("debug");
				if (var7 != null && var7.equals("true") && Utils.isDebug()) {
					var13 = true;
				}

				if (StringUtils.isNotEmpty(var4)) {
					long var8 = Long.valueOf(var4);
					long var10 = var5.getTimestamp();
					if (var10 > var8) {
						KnowledgePackageWrapper var12 = new KnowledgePackageWrapper(var5);
						if (var13) {
							this.writeObjectToJson(var2, var12);
						} else {
							this.a(var2, (Object)var12);
						}
					}
				} else {
					KnowledgePackageWrapper var14 = new KnowledgePackageWrapper(var5);
					if (var13) {
						this.writeObjectToJson(var2, var14);
					} else {
						this.a(var2, (Object)var14);
					}
				}

			}
		}
	}

	public boolean anonymousAccess() {
		return true;
	}

	private void a(HttpServletResponse var1, String var2) throws IOException {
		var1.setContentType("text/html");
		PrintWriter var3 = var1.getWriter();
		var3.write("<html>");
		var3.write("<header>");
		var3.write("</header>");
		var3.write("<body>");
		var3.write(var2);
		var3.write("</body>");
		var3.write("</html>");
		var3.flush();
		var3.close();
	}

	private void a(HttpServletResponse var1, Object var2) throws IOException {
		var1.setContentType("text/json");
		var1.setCharacterEncoding("UTF-8");
		ObjectMapper var3 = new ObjectMapper();
		var3.setSerializationInclusion(Inclusion.NON_NULL);
		var3.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		var3.setDateFormat(new SimpleDateFormat(Configure.getDateFormat()));
		String var4 = var3.writeValueAsString(var2);
		byte[] var5 = Utils.compress(var4);
		ServletOutputStream var6 = var1.getOutputStream();
		var6.write(var5);
		var6.flush();
		var6.close();
	}

	public String url() {
		return "/loadknowledge";
	}

	public void setKnowledgePackageService(KnowledgePackageService var1) {
		this.a = var1;
	}

	public void setKnowledgePackageRepositoryService(KnowledgePackageRepositoryService var1) {
		this.b = var1;
	}

	public void setRemoteDynamicJarsBuilder(RemoteDynamicJarsBuilder var1) {
		this.c = var1;
	}
}
