package com.bstek.urule.console.servlet.actiontemplate;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.servlet.DesignerConfigure;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class ActionTemplateServletHandler extends RenderPageServletHandler {
    public ActionTemplateServletHandler() {
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
            var5.put("_lis_", Splash.getFetchVersion());
            String var6 = var1.getParameter("file");
            var6 = Utils.decodeURL(var6);
            String var7 = this.buildProjectNameFromFile(var6);
            if (var7 != null) {
                var5.put("project", var7);
            }

            var5.put("showFileExtensionName", PropertyConfigurer.getProperty("urule.show.fileExtensionName"));
            var5.put("constantLink", DesignerConfigure.constantLink);
            var5.put("variableLink", DesignerConfigure.variableLink);
            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var8 = this.ve.getTemplate("html/action-template.html", "utf-8");
            PrintWriter var9 = var2.getWriter();
            var8.merge(var5, var9);
            var9.close();
        }

    }

    public String url() {
        return "/actiontemplate";
    }
}
