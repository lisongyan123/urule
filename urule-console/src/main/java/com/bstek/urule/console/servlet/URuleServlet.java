//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;

import com.bstek.urule.ContextHolder;
import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.console.DefaultPrincipal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.exception.NodeLockException;
import com.bstek.urule.exception.RuleAssertException;
import com.bstek.urule.model.rete.RuleDueException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class URuleServlet extends HttpServlet {
	private boolean a;
	private Map<String, ServletHandler> b = new HashMap();
	public static final String URL = "/urule";
	private static final long c = -5067484267904906233L;

	public URuleServlet() {
	}

	public void init(ServletConfig var1) throws ServletException {
		super.init(var1);
		WebApplicationContext var2 = this.getWebApplicationContext(var1);
		Collection var3 = var2.getBeansOfType(ServletHandler.class).values();
		Iterator var4 = var3.iterator();

		while(var4.hasNext()) {
			ServletHandler var5 = (ServletHandler)var4.next();
			String var6 = var5.url();
			if (this.b.containsKey(var6)) {
				throw new RuntimeException("Handler [" + var6 + "] already exist.");
			}

			this.b.put(var6, var5);
		}

		String var7 = PropertyConfigurer.getProperty("urule.security.enable");
		if (var7 != null && var7.equals("true")) {
			this.a = true;
		}

	}

	protected WebApplicationContext getWebApplicationContext(ServletConfig var1) {
		return WebApplicationContextUtils.getWebApplicationContext(var1.getServletContext());
	}

	protected void service(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		RequestHolder.set(var1, var2);

		try {
			String var10;
			try {
				String var3 = var1.getContextPath() + "/urule";
				String var18 = var1.getRequestURI();
				String var19 = var18.substring(var3.length());
				if (var19.length() >= 1) {
					int var20 = var19.indexOf("/", 1);
					if (var20 > -1) {
						var19 = var19.substring(0, var20);
					}

					ServletHandler var22 = (ServletHandler)this.b.get(var19);
					if (var22 == null) {
						this.a(var2, "Handler [" + var19 + "] not exist.");
						return;
					}

					if (this.a) {
						DefaultPrincipal var23 = (DefaultPrincipal)var1.getSession().getAttribute("default_urule_security_login_user");
						if (var23 == null && !var22.anonymousAccess()) {
							String var25 = var1.getContextPath();
							var10 = null;
							if (var25.endsWith("/")) {
								var10 = var25 + "urule/login";
							} else {
								var10 = var25 + "/urule/login";
							}

							var2.sendRedirect(var10);
						}
					}

					var22.execute(var1, var2);
					return;
				}

				var2.sendRedirect(var1.getContextPath() + "/urule/frame");
			} catch (Exception var16) {
				StringBuilder var4 = new StringBuilder();
				Throwable var5 = this.b(var16, var4);
				var2.setCharacterEncoding("UTF-8");
				PrintWriter var6 = var2.getWriter();
				if (var5 instanceof NoPermissionException) {
					var2.setStatus(401);
					var6.write("<h1>权限不足！</h1>");
					var6.close();
					return;
				} else {
					String var7;
					if (var5 instanceof RuleDueException) {
						var7 = "产品试用时间已到，请购买商用授权！";
						var2.addHeader("errorMsg", URLEncoder.encode(var7, "utf-8"));
						var2.setStatus(500);
						HashMap var8 = new HashMap();
						var8.put("errorMsg", var7);
						var8.put("stack", var7);
						ObjectMapper var9 = new ObjectMapper();
						var10 = var9.writeValueAsString(var8);
						var6.write(var10);
						var6.close();
					} else {
						var7 = NullPointerException.class.getName();
						if (!(var5 instanceof NullPointerException)) {
							var7 = var5.getMessage();
						}

						if (var7 == null) {
							var7 = NullPointerException.class.getName();
						}

						var2.addHeader("errorMsg", URLEncoder.encode(var7, "utf-8"));
						var2.setStatus(500);
						String var21 = var5.getMessage();
						if (StringUtils.isBlank(var21)) {
							var21 = var5.getClass().getName();
						}

						HashMap var24 = new HashMap();
						var10 = this.a(var5, var4);
						var24.put("errorMsg", var21);
						var24.put("stack", var10);
						ObjectMapper var11 = new ObjectMapper();
						String var12 = var11.writeValueAsString(var24);
						var6.write(var12);
						var6.close();
						if (!(var5 instanceof NodeLockException)) {
							throw new ServletException(var16);
						}
					}

					return;
				}
			}
		} finally {
			RequestHolder.reset();
			ContextHolder.clean();
		}

	}

	private String a(Throwable var1, StringBuilder var2) {
		ByteArrayOutputStream var3 = new ByteArrayOutputStream();
		PrintStream var4 = new PrintStream(var3);
		var1.printStackTrace(var4);
		String var5 = new String(var3.toByteArray());
		IOUtils.closeQuietly(var4);
		IOUtils.closeQuietly(var3);
		var5 = var5.replaceAll("\n", "<br>");
		if (var2.length() > 0) {
			var2.append("<br>");
		}

		var2.append(var5);
		return var2.toString();
	}

	private void a(HttpServletResponse var1, String var2) throws IOException {
		var1.setContentType("text/html");
		PrintWriter var3 = var1.getWriter();
		var3.write("<html>");
		var3.write("<header><title>URule Console</title></header>");
		var3.write("<body>");
		var3.write(var2);
		var3.write("</body>");
		var3.write("</html>");
		var3.flush();
		var3.close();
	}

	private Throwable b(Throwable var1, StringBuilder var2) {
		if (var1 instanceof RuleAssertException) {
			RuleAssertException var3 = (RuleAssertException)var1;
			String var4 = var3.getTipMsg();
			if (var4 != null) {
				var2.append(var4);
			}
		}

		return var1.getCause() != null ? this.b(var1.getCause(), var2) : var1;
	}
}
