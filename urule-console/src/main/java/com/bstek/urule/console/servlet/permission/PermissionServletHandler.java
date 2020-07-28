//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.permission;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Splash;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.repository.BaseRepositoryService;
import com.bstek.urule.console.repository.OperateType;
import com.bstek.urule.console.repository.authority.Authority;
import com.bstek.urule.console.repository.authority.AuthorityRepositoryService;
import com.bstek.urule.console.repository.authority.AuthorityUnit;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.jcr.Node;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class PermissionServletHandler extends RenderPageServletHandler {
	private AuthorityRepositoryService a;
	private String b;

	public PermissionServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = this.retriveMethod(var1);
			if (var4 != null) {
				this.invokeMethod(var4, var1, var2);
			} else {
				VelocityContext var5 = new VelocityContext();
				var5.put("contextPath", var1.getContextPath());
				var5.put("version", Splash.getVersion());
				var5.put("_date_", _DATE);
				var5.put("_lis_", Splash.getFetchVersion());
				var5.put("authorityType", this.b);
				var5.put("showFileExtensionName", PropertyConfigurer.getProperty("urule.show.fileExtensionName"));
				var2.setContentType("text/html");
				var2.setCharacterEncoding("utf-8");
				Template var6 = this.ve.getTemplate("html/permission-config-editor.html", "utf-8");
				PrintWriter var7 = var2.getWriter();
				var6.merge(var5, var7);
				var7.close();
			}

		}
	}

	public void userList(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		List var3 = EnvironmentUtils.getEnvironmentProvider().getPrincipals();
		this.writeObjectToJson(var2, var3);
	}

	public void save(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
		if (!var3.isAdmin()) {
			throw new NoPermissionException();
		} else {
			String var4 = var1.getParameter("user");
			String var5 = var1.getParameter("path");
			String var6 = var1.getParameter("state");
			OperateType var7 = OperateType.add;
			if (var6.equals("0")) {
				var7 = OperateType.delete;
			} else if (var6.equals("1")) {
				var7 = OperateType.update;
			}

			Authority var8 = new Authority();
			var8.setPath(var5);
			if (!var7.equals(OperateType.delete)) {
				String var9 = var1.getParameter("read");
				if (var9.equals("true")) {
					var8.setRead(true);
				} else {
					var8.setRead(false);
				}

				String var10 = var1.getParameter("write");
				if (var10.equals("true")) {
					var8.setWrite(true);
				} else {
					var8.setWrite(false);
				}

				String var11 = var1.getParameter("save");
				if (var11.equals("true")) {
					var8.setSave(true);
				} else {
					var8.setSave(false);
				}

				String var12 = var1.getParameter("saveas");
				if (var12.equals("true")) {
					var8.setSaveas(true);
				} else {
					var8.setSaveas(false);
				}
			}

			this.a.saveAuthority(var3, var4, var8, var7);
		}
	}

	public void search(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("user");
		String var4 = var1.getParameter("url");
		String var5 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2)).getCompanyId();
		List var6 = this.a.loadAuthorityUnits(var5);
		ArrayList var7 = new ArrayList();
		Node var8 = ((BaseRepositoryService)this.a).getRootNode();
		Iterator var9 = var6.iterator();

		label41:
		while(true) {
			AuthorityUnit var10;
			do {
				if (!var9.hasNext()) {
					this.writeObjectToJson(var2, var7);
					return;
				}

				var10 = (AuthorityUnit)var9.next();
			} while(StringUtils.isNotBlank(var3) && !var3.equals(var10.getPrincipalName()));

			Iterator var11 = var10.getAuthorities().iterator();

			while(true) {
				Authority var12;
				String var13;
				do {
					if (!var11.hasNext()) {
						continue label41;
					}

					var12 = (Authority)var11.next();
					var13 = var12.getPath().toLowerCase();
				} while(StringUtils.isNotBlank(var4) && var13.indexOf(var4.toLowerCase()) == -1);

				HashMap var14 = new HashMap();
				var14.put("user", var10.getPrincipalName());
				var14.put("path", var12.getPath());
				var14.put("read", var12.isRead());
				var14.put("write", var12.isWrite());
				var14.put("save", var12.isSave());
				var14.put("saveas", var12.isSaveas());
				if (var8.hasNode(this.a(var12.getPath()))) {
					var14.put("exist", true);
				} else {
					var14.put("exist", false);
				}

				var7.add(var14);
			}
		}
	}

	private String a(String var1) {
		return var1.startsWith("/") ? var1.substring(1, var1.length()) : var1;
	}

	public void setAuthorityType(String var1) {
		this.b = var1;
	}

	public void setAuthorityRepositoryService(AuthorityRepositoryService var1) {
		this.a = var1;
	}

	public String url() {
		return "/permission";
	}
}
