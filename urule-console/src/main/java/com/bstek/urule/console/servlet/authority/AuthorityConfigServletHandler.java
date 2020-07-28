package com.bstek.urule.console.servlet.authority;

import com.bstek.urule.Utils;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.repository.authority.AuthorityRepositoryService;
import com.bstek.urule.console.repository.authority.AuthorityService;
import com.bstek.urule.console.repository.authority.AuthorityUnit;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.exception.RuleException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class AuthorityConfigServletHandler extends RenderPageServletHandler {
    private AuthorityRepositoryService a;
    private AuthorityService b;
    private String c;

    public AuthorityConfigServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        Principal var3 = EnvironmentUtils.getEnvironmentProvider().getLoginPrincipal(new RequestContext(var1, var2));
        if (!var3.isAdmin()) {
            throw new NoPermissionException();
        } else {
            String var4 = this.retriveMethod(var1);
            if (var4 != null) {
                this.invokeMethod(var4, var1, var2);
            } else {
                VelocityContext var5 = new VelocityContext();
                var5.put("contextPath", var1.getContextPath());
                var2.setContentType("text/html");
                var2.setCharacterEncoding("utf-8");
                Template var6 = this.ve.getTemplate("html/permission-config-editor.html", "utf-8");
                PrintWriter var7 = var2.getWriter();
                var6.merge(var5, var7);
                var7.close();
            }

        }
    }

    public void loadPrincipals(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        List var3 = EnvironmentUtils.getEnvironmentProvider().getPrincipals();
        HashMap var4 = new HashMap();
        var4.put("principals", var3);
        var4.put("authorityType", this.c);
        this.writeObjectToJson(var2, var4);
    }

    public void loadResourceSecurityConfigs(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2)).getCompanyId();

        try {
            List var4 = EnvironmentUtils.getEnvironmentProvider().getPrincipals();
            List var5 = this.a.loadAuthorityUnits(var3);
            ArrayList var6 = new ArrayList();
            Iterator var7 = var5.iterator();

            while(var7.hasNext()) {
                AuthorityUnit var8 = (AuthorityUnit)var7.next();
                String var9 = var8.getPrincipalName();
                boolean var10 = false;
                Iterator var11 = var4.iterator();

                while(var11.hasNext()) {
                    Principal var12 = (Principal)var11.next();
                    if (var12.getCompanyId().equals(var3) && var9.equals(var12.getName()) && var12.isAdmin()) {
                        var10 = true;
                        break;
                    }
                }

                if (!var10) {
                    var6.add(var8);
                }
            }

            this.writeObjectToJson(var2, var6);
        } catch (Exception var13) {
            throw new RuleException(var13);
        }
    }

    public void saveResourceSecurityConfigs(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        String var4 = var3.getCompanyId();
        String var5 = var1.getParameter("xml");
        var5 = Utils.decodeURL(var5);

        try {
            long var6 = this.a.saveAuthoritiesFile(var5, var3);
            this.b.refreshAuthority(var4);
            this.b.getAuthorityCache().resetTag(var6);
        } catch (Exception var8) {
            throw new RuleException(var8);
        }
    }

    public void setAuthorityRepositoryService(AuthorityRepositoryService var1) {
        this.a = var1;
    }

    public void setAuthorityService(AuthorityService var1) {
        this.b = var1;
    }

    public void setAuthorityType(String var1) {
        this.c = var1;
    }

    public String url() {
        return "/authority";
    }
}