package com.bstek.urule.console.servlet.compare;

import com.bstek.urule.Splash;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class CompareServletHandler extends RenderPageServletHandler {
    public CompareServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = this.retriveMethod(var1);
        if (var3 != null) {
            this.invokeMethod(var3, var1, var2);
        } else {
            VelocityContext var4 = new VelocityContext();
            var4.put("contextPath", var1.getContextPath());
            var4.put("version", Splash.getVersion());
            var4.put("_lis_", Splash.getFetchVersion());
            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var5 = this.ve.getTemplate("html/version-compare.html", "utf-8");
            PrintWriter var6 = var2.getWriter();
            var5.merge(var4, var6);
            var6.close();
        }

    }

    public String url() {
        return "/compare";
    }
}
