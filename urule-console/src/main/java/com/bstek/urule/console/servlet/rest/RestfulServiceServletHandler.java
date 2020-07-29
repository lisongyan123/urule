//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.rest;

import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.cache.ResourcePackageCache;
import com.bstek.urule.console.repository.model.ResourcePackage;
import com.bstek.urule.console.repository.model.ServiceConfig;
import com.bstek.urule.console.servlet.JsonBuilder;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.GeneralEntity;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.runtime.KnowledgePackageImpl;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.monitor.MonitorObject;
import com.bstek.urule.runtime.monitor.MonitorObjectField;
import com.bstek.urule.runtime.response.ExecutionResponse;
import com.bstek.urule.runtime.service.KnowledgeService;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.codehaus.jackson.map.ObjectMapper;

public class RestfulServiceServletHandler extends RenderPageServletHandler {
    private static final String a = "/rest";
    private static final String b = "desc";
    private static final String c = "mock";
    private ResourcePackageCache d;
    private KnowledgeService e;

    public RestfulServiceServletHandler() {
    }

    private void a(ResourcePackage var1, HttpServletRequest var2, HttpServletResponse var3) throws Exception {
        VelocityContext var4 = new VelocityContext();
        var4.put("contextPath", var2.getContextPath());
        var4.put("version", Splash.getVersion());
        ServiceConfig var5 = var1.getService();
        var4.put("security", var5.isSecurity());
        var4.put("username", var5.getUsername());
        var4.put("password", var5.getPassword());
        String var6 = var2.getRequestURL().toString();
        var6 = Utils.decodeURL(var6);
        var4.put("url", var6);
        ObjectMapper var7 = new ObjectMapper();
        String var8 = var7.writeValueAsString(this.a(var5.getInputData()));
        var4.put("json", var8);
        var3.setContentType("text/html");
        var3.setCharacterEncoding("utf-8");
        Template var9 = this.ve.getTemplate("html/restful-mock.html", "utf-8");
        PrintWriter var10 = var3.getWriter();
        var9.merge(var4, var10);
        var10.close();
    }

    private List<Map<String, Object>> a(List<MonitorObject> var1) {
        ArrayList var2 = new ArrayList();
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            MonitorObject var4 = (MonitorObject)var3.next();
            HashMap var5 = new HashMap();
            var2.add(var5);
            var5.put("name", var4.getName());
            HashMap var6 = new HashMap();
            var5.put("fields", var6);
            Iterator var7 = var4.getFields().iterator();

            while(var7.hasNext()) {
                MonitorObjectField var8 = (MonitorObjectField)var7.next();
                String var9 = var8.getName();
                var6.put(var9, "");
            }
        }

        return var2;
    }

    private void b(ResourcePackage var1, HttpServletRequest var2, HttpServletResponse var3) throws Exception {
        ServiceConfig var4 = var1.getService();
        String var6;
        String var7;
        if (var4.isSecurity()) {
            HashMap var5 = new HashMap();
            var6 = var2.getHeader("Username");
            var7 = var2.getHeader("Password");
            if (!var4.getUsername().equals(var6) || !var4.getPassword().equals(var7)) {
                var5.put("error", "知识包服务需要用户名密码验证，请正确提供用户名密码信息");
                this.writeObjectToJson(var3, var5);
                return;
            }
        }

        ServletInputStream var17 = var2.getInputStream();
        if (var17 == null) {
            throw new RuleException("Input data can not be null.");
        } else {
            var6 = IOUtils.toString(var17, "utf-8");
            if (StringUtils.isBlank(var6)) {
                throw new RuleException("Input data can not be null.");
            } else {
                var6 = var6.trim();
                var7 = var1.getProject();
                if (var7.startsWith("/")) {
                    var7 = var7.substring(1, var7.length());
                }

                KnowledgePackageImpl var8 = (KnowledgePackageImpl)this.e.getKnowledge(var7 + "/" + var1.getId());
                KnowledgeSession var9 = KnowledgeSessionFactory.newKnowledgeSession(var8);
                Object var10 = JsonBuilder.getInstance().buildComplexObject(var6, var8.getVariableCategories());
                if (var10 instanceof MultiData) {
                    MultiData var11 = (MultiData)var10;
                    List var12 = var11.getData();
                    ExecutionResult var13 = new ExecutionResult();
                    Iterator var14 = var12.iterator();

                    while(var14.hasNext()) {
                        Object var15 = var14.next();
                        Map var16 = this.a(var15, var9, var8, var4);
                        var13.addDuration((Long)var16.get("duration"));
                        var13.addOutput((List)var16.get("output"));
                    }

                    this.writeObjectToJson(var3, var13);
                } else {
                    Map var18 = this.a(var10, var9, var8, var4);
                    this.writeObjectToJson(var3, var18);
                }

            }
        }
    }

    private Map<String, Object> a(Object var1, KnowledgeSession var2, KnowledgePackageImpl var3, ServiceConfig var4) throws Exception {
        HashMap var5 = new HashMap();
        List var6;
        if (var1 instanceof List) {
            var6 = (List)var1;
            Iterator var7 = var6.iterator();

            while(var7.hasNext()) {
                Object var8 = var7.next();
                if (var8 instanceof GeneralEntity) {
                    var2.insert(var8);
                } else if (var8 instanceof Map) {
                    var5.putAll((Map)var8);
                }
            }
        } else if (var1 instanceof Map) {
            if (var1 instanceof GeneralEntity) {
                var2.insert(var1);
            } else if (var1 instanceof Map) {
                var5.putAll((Map)var1);
            }
        }

        var6 = null;
        Object var9;
        if (var3.getFlowMap().size() > 0) {
            String var10 = (String)var3.getFlowMap().keySet().iterator().next();
            var9 = var2.startProcess(var10, var5);
        } else {
            var9 = var2.fireRules(var5);
        }

        var2.writeLogFile();
        List var11 = this.a(var4, var1, var2.getParameters(), var3.getVariableCategories());
        HashMap var12 = new HashMap();
        var12.put("output", var11);
        var12.put("duration", ((ExecutionResponse)var9).getDuration());
        return var12;
    }

    private List<Map<String, Object>> a(ServiceConfig var1, Object var2, Map<String, Object> var3, List<VariableCategory> var4) {
        ArrayList var5 = new ArrayList();
        List var6 = var1.getOutputData();
        Iterator var7 = var6.iterator();

        while(true) {
            while(var7.hasNext()) {
                MonitorObject var8 = (MonitorObject)var7.next();
                String var9 = var8.getName();
                if (var9.equals("参数")) {
                    HashMap var17 = new HashMap();
                    var5.add(var17);
                    HashMap var18 = new HashMap();
                    if (var8.getFields() != null) {
                        Iterator var19 = var8.getFields().iterator();

                        while(var19.hasNext()) {
                            MonitorObjectField var20 = (MonitorObjectField)var19.next();
                            String var21 = var20.getName();
                            var18.put(var21, var3.get(var21));
                        }
                    }

                    var17.put(var9, var18);
                } else {
                    VariableCategory var10 = JsonBuilder.getInstance().findVariableCategory(var4, var9);
                    String var11 = var10.getClazz();
                    List var12 = this.a(var2, var11);
                    Iterator var13 = var12.iterator();

                    while(var13.hasNext()) {
                        GeneralEntity var14 = (GeneralEntity)var13.next();
                        HashMap var15 = new HashMap();
                        var5.add(var15);
                        var15.put("name", var9);
                        var15.put("class", var11);
                        Map var16 = this.a(var14, var8.getFields());
                        var15.put("fields", var16);
                    }
                }
            }

            return var5;
        }
    }

    private Map<String, Object> a(GeneralEntity var1, List<MonitorObjectField> var2) {
        HashMap var3 = new HashMap();
        if (var2 == null) {
            return var3;
        } else {
            Iterator var4 = var2.iterator();

            while(true) {
                while(var4.hasNext()) {
                    MonitorObjectField var5 = (MonitorObjectField)var4.next();
                    String var6 = var5.getName();
                    Object var7 = Utils.getObjectProperty(var1, var6);
                    String[] var8 = var6.split("\\.");
                    Object var9 = var3;

                    for(int var10 = 0; var10 < var8.length; ++var10) {
                        String var11 = var8[var10];
                        if (var10 == var8.length - 1) {
                            Utils.setObjectProperty(var9, var11, var7);
                            break;
                        }

                        Object var12 = Utils.getObjectProperty(var9, var11);
                        if (var12 == null) {
                            var12 = new HashMap();
                            Utils.setObjectProperty(var9, var11, var12);
                        }

                        var9 = var12;
                    }
                }

                return var3;
            }
        }
    }

    private List<GeneralEntity> a(Object var1, String var2) {
        ArrayList var3 = new ArrayList();
        if (var1 instanceof GeneralEntity) {
            GeneralEntity var4 = (GeneralEntity)var1;
            if (var4.getTargetClass().equals(var2)) {
                var3.add(var4);
            }
        } else if (var1 instanceof List) {
            Iterator var7 = ((List)var1).iterator();

            while(var7.hasNext()) {
                Object var5 = var7.next();
                if (var5 instanceof GeneralEntity) {
                    GeneralEntity var6 = (GeneralEntity)var5;
                    if (var6.getTargetClass().equals(var2)) {
                        var3.add(var6);
                    }
                }
            }
        }

        return var3;
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        HashMap var3 = new HashMap();
        String var4 = var1.getMethod();
        if (!"POST".equals(var4)) {
            var2.sendError(400);
            var3.put("error", "非法请求");
            this.writeObjectToJson(var2, var3);
        } else {
            String var5 = this.retriveMethod(var1);
            if (StringUtils.isBlank(var5)) {
                var3.put("error", "请指定完整的知识包ID名称");
                this.writeObjectToJson(var2, var3);
            } else {
                var5 = Utils.decodeURL(var5);
                String[] var6 = var5.split("/");
                if (var6.length != 2) {
                    var3.put("error", "知识包名【" + var5 + "】不合法");
                    this.writeObjectToJson(var2, var3);
                } else {
                    ResourcePackage var7 = this.d.loadResourcePackage(var5);
                    if (var7 == null) {
                        var3.put("error", "知识包【" + var5 + "】不存在");
                        this.writeObjectToJson(var2, var3);
                    } else {
                        ServiceConfig var8 = var7.getService();
                        if (var8 == null) {
                            var3.put("error", "知识包【" + var5 + "】未暴露服务");
                            this.writeObjectToJson(var2, var3);
                        } else {
                            String var10;
                            try {
                                String var9 = var1.getQueryString();
                                if (var9 != null) {
                                    if ("desc".equals(var9)) {
                                        var10 = var1.getRequestURL().toString();
                                        var10 = Utils.decodeURL(var10);
                                        var3.put("url", var10);
                                        var3.put("authentication", var8.isSecurity());
                                        var3.put("input", var8.getInputData());
                                        var3.put("output", var8.getOutputData());
                                        this.writeObjectToJson(var2, var3);
                                        return;
                                    }

                                    if ("mock".equals(var9)) {
                                        this.a(var7, var1, var2);
                                        return;
                                    }

                                    var3.put("error", "Unknow parameter [" + var9 + "]");
                                    this.writeObjectToJson(var2, var3);
                                    return;
                                }

                                this.b(var7, var1, var2);
                            } catch (Exception var11) {
                                var10 = this.a((Throwable)var11);
                                var3.put("error", var10);
                                this.writeObjectToJson(var2, var3);
                            }

                        }
                    }
                }
            }
        }
    }

    private String a(Throwable var1) {
        ByteArrayOutputStream var2 = new ByteArrayOutputStream();
        PrintStream var3 = new PrintStream(var2);
        var1.printStackTrace(var3);
        String var4 = new String(var2.toByteArray());
        IOUtils.closeQuietly(var3);
        IOUtils.closeQuietly(var2);
        var4 = var4.replaceAll("\n", "<br>");
        return var4;
    }

    public boolean anonymousAccess() {
        return true;
    }

    public void setKnowledgeService(KnowledgeService var1) {
        this.e = var1;
    }

    public void setResourcePackageCache(ResourcePackageCache var1) {
        this.d = var1;
    }

    public String url() {
        return "/rest";
    }
}

