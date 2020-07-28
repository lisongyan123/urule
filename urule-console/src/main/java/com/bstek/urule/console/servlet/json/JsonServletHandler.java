//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.json;

import com.bstek.urule.Splash;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.exception.RuleException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonServletHandler extends RenderPageServletHandler {
    public JsonServletHandler() {
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
            Template var5 = this.ve.getTemplate("html/json-tool.html", "utf-8");
            PrintWriter var6 = var2.getWriter();
            var5.merge(var4, var6);
            var6.close();
        }

    }

    public void convert(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("json");
        if (StringUtils.isBlank(var3)) {
            throw new RuleException("JSON不能为空！");
        } else {
            var3 = var3.trim();
            StringBuilder var4 = new StringBuilder();

            try {
                ObjectMapper var5 = new ObjectMapper();
                if (var3.startsWith("[") && var3.endsWith("]")) {
                    List var9 = (List)var5.readValue(var3, ArrayList.class);
                    var4.append(this.a(var9));
                } else {
                    if (!var3.startsWith("{") || !var3.endsWith("}")) {
                        throw new RuleException("不支持的JSON格式");
                    }

                    Map var6 = (Map)var5.readValue(var3, HashMap.class);
                    var4.append(this.a(var6));
                }
            } catch (Exception var7) {
                throw new RuleException("不支持的JSON格式");
            }

            HashMap var8 = new HashMap();
            var8.put("json", var4.toString());
            this.writeObjectToJson(var2, var8);
        }
    }

    public void gzip(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("json");
        if (StringUtils.isBlank(var3)) {
            throw new RuleException("JSON不能为空！");
        } else {
            var3 = var3.trim();
            HashMap var4 = new HashMap();
            var4.put("data", this.doGzip(var3));
            this.writeObjectToJson(var2, var4);
        }
    }

    public String doGzip(String var1) throws Exception {
        ByteArrayOutputStream var2 = new ByteArrayOutputStream();
        GZIPOutputStream var3 = new GZIPOutputStream(var2);
        var3.write(var1.getBytes("utf-8"));
        var3.close();
        Base64 var4 = new Base64();
        return var4.encodeAsString(var2.toByteArray());
    }

    private String a(Map<String, Object> var1) {
        StringBuilder var2 = new StringBuilder();
        var2.append("{");
        var2.append("\"name\"");
        var2.append(":");
        var2.append("\"待填写的变量分类名\"");
        var2.append(",");
        var2.append("\"fields\"");
        var2.append(":");
        var2.append("{");
        int var3 = 0;
        Iterator var4 = var1.keySet().iterator();

        while(var4.hasNext()) {
            String var5 = (String)var4.next();
            Object var6 = var1.get(var5);
            if (var6 != null) {
                if (var3 > 0) {
                    var2.append(",");
                }

                var2.append("\"" + var5 + "\"");
                var2.append(":");
                if (var6 instanceof Map) {
                    var2.append(this.a((Map)var6));
                } else if (var6 instanceof List) {
                    var2.append(this.a((List)var6));
                } else if (var6 instanceof Number) {
                    var2.append(var6);
                } else {
                    var2.append("\"" + var6 + "\"");
                }

                ++var3;
            }
        }

        var2.append("}");
        var2.append("}");
        return var2.toString();
    }

    private String a(List<Map<String, Object>> var1) {
        StringBuilder var2 = new StringBuilder();
        var2.append("[");
        int var3 = 0;

        for(Iterator var4 = var1.iterator(); var4.hasNext(); ++var3) {
            Map var5 = (Map)var4.next();
            if (var3 > 0) {
                var2.append(",");
            }

            var2.append(this.a(var5));
        }

        var2.append("]");
        return var2.toString();
    }

    public String url() {
        return "/json";
    }
}
