package com.bstek.urule.console.servlet.client;

import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.exception.RuleException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class ClientConfigServletHandler extends RenderPageServletHandler {
    private RepositoryService a;

    public ClientConfigServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = this.retriveMethod(var1);
        if (var3 != null) {
            this.invokeMethod(var3, var1, var2);
        } else {
            VelocityContext var4 = new VelocityContext();
            var4.put("contextPath", var1.getContextPath());
            var4.put("version", Splash.getVersion());
            var4.put("clientProvider", this.a.getClientProvider() != null);
            var4.put("_date_", _DATE);
            var4.put("_lis_", Splash.getFetchVersion());
            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var5 = this.ve.getTemplate("html/client-config-editor.html", "utf-8");
            PrintWriter var6 = var2.getWriter();
            var5.merge(var4, var6);
            var6.close();
        }

    }

    public void loadData(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("project");
        var3 = Utils.decodeURL(var3);
        this.writeObjectToJson(var2, this.a.loadClientConfigs(var3, true));
    }

    public void save(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = var1.getParameter("project");
        var3 = Utils.decodeURL(var3);
        String var4 = var3 + "/" + "___client_config__file__";
        String var5 = var1.getParameter("content");
        var5 = Utils.decodeURL(var5);
        Principal var6 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));

        try {
            this.a.saveFile(var4, var5, false, (String)null, var6);
        } catch (Exception var8) {
            throw new RuleException(var8);
        }
    }

    public void setRepositoryService(RepositoryService var1) {
        this.a = var1;
    }

    public String url() {
        return "/clientconfig";
    }
}
