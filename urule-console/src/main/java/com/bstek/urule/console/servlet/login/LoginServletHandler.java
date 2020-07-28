//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.login;

import com.bstek.urule.Splash;
import com.bstek.urule.console.DefaultPrincipal;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.repository.SecurityRepositoryService;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class LoginServletHandler extends RenderPageServletHandler {
    private String a;
    private String b;
    private SecurityRepositoryService c;

    public LoginServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = this.retriveMethod(var1);
        if (var3 != null) {
            this.invokeMethod(var3, var1, var2);
        } else {
            var1.getSession().removeAttribute("default_urule_security_login_user");
            VelocityContext var4 = new VelocityContext();
            var4.put("consoleTitle", this.a);
            var4.put("contextPath", var1.getContextPath());
            var4.put("version", Splash.getVersion());
            var4.put("_lis_", Splash.getFetchVersion());
            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var5 = this.ve.getTemplate("html/login.html", "utf-8");
            PrintWriter var6 = var2.getWriter();
            var5.merge(var4, var6);
            var6.close();
        }

    }

    public void doLogin(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("username");
        String var4 = var1.getParameter("password");
        String var5 = null;
        if (var3.equals("admin")) {
            var5 = this.c.adminLogin(var4);
        } else {
            var5 = this.c.login(var3, var4);
        }

        if (var5 == null) {
            DefaultPrincipal var6 = new DefaultPrincipal();
            var6.setAdmin(var3.equals("admin"));
            var6.setCompanyId(this.b);
            var6.setName(var3);
            var6.setDisplayName(var3);
            HttpSession var7 = var1.getSession();
            var7.setAttribute("default_urule_security_login_user", var6);
            EnvironmentUtils.initEnvironmentProvider();
        }

        HashMap var8 = new HashMap();
        var8.put("error", var5);
        this.writeObjectToJson(var2, var8);
    }

    public boolean anonymousAccess() {
        return true;
    }

    public void setSecurityRepositoryService(SecurityRepositoryService var1) {
        this.c = var1;
    }

    public void setConsoleTitle(String var1) {
        this.a = var1;
    }

    public void setCompanyId(String var1) {
        this.b = var1;
    }

    public String url() {
        return "/login";
    }
}
