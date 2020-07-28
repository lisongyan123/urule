//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.user;

import com.bstek.urule.Splash;
import com.bstek.urule.console.DefaultPrincipal;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.repository.SecurityRepositoryService;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.exception.RuleException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class UserServletHandler extends RenderPageServletHandler {
    private SecurityRepositoryService a;

    public UserServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = this.retriveMethod(var1);
        if (var3 != null) {
            this.invokeMethod(var3, var1, var2);
        } else {
            Principal var4 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
            if (!var4.isAdmin()) {
                throw new NoPermissionException();
            }

            VelocityContext var5 = new VelocityContext();
            var5.put("contextPath", var1.getContextPath());
            var5.put("version", Splash.getVersion());
            var5.put("_lis_", Splash.getFetchVersion());
            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var6 = this.ve.getTemplate("html/user.html", "utf-8");
            PrintWriter var7 = var2.getWriter();
            var6.merge(var5, var7);
            var7.close();
        }

    }

    public void resetPassword(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("username");
        String var4 = var1.getParameter("newPassword");
        this.a.resetPassword(var3, var4);
    }

    public void changePassword(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        DefaultPrincipal var3 = (DefaultPrincipal)var1.getSession().getAttribute("default_urule_security_login_user");
        if (var3 == null) {
            throw new RuleException("请先登录！");
        } else {
            String var4 = var3.getName();
            String var5 = var1.getParameter("password");
            String var6 = var1.getParameter("newPassword");
            String var7 = null;
            if (var4.equals("admin")) {
                var7 = this.a.changeAdminPassword(var5, var6);
            } else {
                var7 = this.a.changePassword(var4, var5, var6);
            }

            HashMap var8 = new HashMap();
            var8.put("error", var7);
            this.writeObjectToJson(var2, var8);
        }
    }

    public void deleteUser(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("username");
        String var4 = this.a.deleteUser(var3);
        HashMap var5 = new HashMap();
        var5.put("error", var4);
        this.writeObjectToJson(var2, var5);
    }

    public void addUser(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("username");
        String var4 = var1.getParameter("password");
        String var5 = this.a.addUser(var3, var4);
        HashMap var6 = new HashMap();
        var6.put("error", var5);
        this.writeObjectToJson(var2, var6);
    }

    public void loadUsers(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        List var3 = this.a.loadUsers();
        this.writeObjectToJson(var2, var3);
    }

    public void setSecurityRepositoryService(SecurityRepositoryService var1) {
        this.a = var1;
    }

    public String url() {
        return "/user";
    }
}
