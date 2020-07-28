//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.template;

import com.bstek.urule.Utils;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.repository.TemplateRepositoryService;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class TemplateServletHandler extends RenderPageServletHandler {
    private TemplateRepositoryService a;

    public TemplateServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = this.retriveMethod(var1);
        if (var3 != null) {
            this.invokeMethod(var3, var1, var2);
        } else {
            Principal var4 = EnvironmentUtils.getEnvironmentProvider().getLoginPrincipal(new RequestContext(var1, var2));
            if (!var4.isAdmin()) {
                throw new NoPermissionException();
            }

            VelocityContext var5 = new VelocityContext();
            var5.put("contextPath", var1.getContextPath());
            String var6 = var1.getParameter("project");
            var6 = Utils.decodeURL(var6);
            var5.put("project", var6);
            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var7 = this.ve.getTemplate("html/template-editor.html", "utf-8");
            PrintWriter var8 = var2.getWriter();
            var7.merge(var5, var8);
            var8.close();
        }

    }

    public void removeTemplate(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = this.a(var1);
        String var4 = var1.getParameter("category");
        String var5 = var1.getParameter("name");
        String var6 = var3 + "/" + "__rules_templates__" + "/" + var4 + "/" + var5;
        this.a.removeTemplateFile(var6);
    }

    public void removeCategory(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = this.a(var1);
        String var4 = var1.getParameter("category");
        this.a.removeTemplateCategory(var3, var4);
    }

    public void saveCategory(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = this.a(var1);
        String var4 = var1.getParameter("categoryName");
        var4 = var4.trim();
        Principal var5 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        this.a.createTemplateCategory(var5, var3, var4);
    }

    public void saveTemplate(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = this.a(var1);
        String var4 = var1.getParameter("category");
        String var5 = var1.getParameter("content");
        var5 = Utils.decodeContent(var5);
        String var6 = var1.getParameter("name");
        var6 = var6.trim();
        String var7 = var1.getParameter("comment");
        String var8 = var3 + "/" + "__rules_templates__" + "/" + var4 + "/" + var6;
        Principal var9 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        this.a.createTemplateFile(var9, var8, var7, var5);
    }

    public void loadTemplates(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("project");
        if (var3 == null) {
            var3 = this.a(var1);
        }

        List var4 = this.a.loadTemplates(var3);
        this.writeObjectToJson(var2, var4);
    }

    private String a(HttpServletRequest var1) {
        String var2 = var1.getParameter("file");
        var2 = Utils.decodeURL(var2);
        if (var2.startsWith("/")) {
            var2 = var2.substring(1, var2.length());
        }

        int var3 = var2.indexOf("/");
        String var4 = var2.substring(0, var3);
        return var4;
    }

    public void setTemplateRepositoryService(TemplateRepositoryService var1) {
        this.a = var1;
    }

    public String url() {
        return "/template";
    }
}
