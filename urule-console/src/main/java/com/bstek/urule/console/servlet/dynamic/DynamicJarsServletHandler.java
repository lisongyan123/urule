//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.dynamic;

import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.repository.ClientConfig;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.dynamic.DynamicFile;
import com.bstek.urule.console.repository.dynamic.DynamicJarRepositoryService;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.DynamicSpringConfigLoader;
import com.bstek.urule.runtime.RemoteDynamicJarsBuilder;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class DynamicJarsServletHandler extends RenderPageServletHandler {
    private DynamicJarRepositoryService a;
    private DynamicSpringConfigLoader b;
    private RepositoryService c;
    private RemoteDynamicJarsBuilder d;

    public DynamicJarsServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = this.retriveMethod(var1);
        if (var3 != null) {
            this.invokeMethod(var3, var1, var2);
        } else {
            VelocityContext var4 = new VelocityContext();
            var4.put("contextPath", var1.getContextPath());
            var4.put("version", Splash.getVersion());
            var4.put("_date_", _DATE);
            var4.put("_lis_", Splash.getFetchVersion());
            String var5 = var1.getParameter("file");
            String var6 = this.buildProjectNameFromFile(var5);
            if (var6 != null) {
                var4.put("project", var6);
            }

            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var7 = this.ve.getTemplate("html/dynamic.html", "utf-8");
            PrintWriter var8 = var2.getWriter();
            var7.merge(var4, var8);
            var8.close();
        }

    }

    public void checkLatestJarsDir(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        if (this.validateUserPwd(var1, this.d.getUser(), this.d.getPwd())) {
            String var3 = "n";
            String var4 = this.b.getDynamicJarsStoreDirectDirName();
            if (var4 != null) {
                var3 = var4;
            }

            HashMap var5 = new HashMap();
            String var6 = var1.getParameter("dirName");
            var5.put("dir", var3);
            if (var6 == null) {
                var5.put("match", false);
            } else {
                var5.put("match", var6.equals(var3));
            }

            this.writeObjectToJson(var2, var5);
        }
    }

    public void loadDynamicJars(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        if (this.validateUserPwd(var1, this.d.getUser(), this.d.getPwd())) {
            String var3 = this.b.getDynamicJarsStoreDirectPath();
            if (var3 == null) {
                throw new RuleException("Current jars dir not exist.");
            } else {
                byte[] var4 = this.a();
                ServletOutputStream var5 = var2.getOutputStream();
                DataOutputStream var6 = new DataOutputStream(var5);
                var6.write(var4);
                var6.flush();
                var6.close();
                IOUtils.closeQuietly(var5);
            }
        }
    }

    public void downloadJar(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        if (!var3.isAdmin()) {
            throw new NoPermissionException();
        } else {
            String var4 = var1.getParameter("path");
            String var5 = var1.getParameter("jarName");
            InputStream var6 = this.a.loadJar(var4, var5);
            var2.setContentType("application/octet-stream");
            var2.setCharacterEncoding("utf-8");
            String var7 = URLEncoder.encode(var5, "utf-8");
            var2.setHeader("Content-Disposition", "attachment; filename=" + var7 + "");
            ServletOutputStream var8 = var2.getOutputStream();
            IOUtils.copy(var6, var8);
            var8.flush();
            var8.close();
            var6.close();
        }
    }

    public void sendJarsToClients(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        if (!var3.isAdmin()) {
            throw new NoPermissionException();
        } else {
            String var4 = var1.getParameter("client");
            byte[] var5 = this.a();
            List var6 = this.b();
            ArrayList var7 = new ArrayList();
            Iterator var8 = var6.iterator();

            while(var8.hasNext()) {
                ClientConfig var9 = (ClientConfig)var8.next();
                String var10 = var9.getClient();
                Map var11;
                if (StringUtils.isNotBlank(var4)) {
                    if (var10.equals(var4)) {
                        var11 = this.a(var5, var9);
                        var7.add(var11);
                        break;
                    }
                } else {
                    var11 = this.a(var5, var9);
                    var7.add(var11);
                }
            }

            this.writeObjectToJson(var2, var7);
        }
    }

    private Map<String, Object> a(byte[] var1, ClientConfig var2) {
        String var3 = var2.getClient();
        String var4 = this.a(var1, var3);
        HashMap var5 = new HashMap();
        if (var4 != null) {
            var5.put("resultMsg", "<div style='color:red;word-wrap:break-word'>" + var4 + "</div>");
        }

        var5.put("url", var2.getClient());
        var5.put("name", var2.getName());
        return var5;
    }

    private byte[] a() throws IOException, FileNotFoundException {
        String var1 = this.b.getDynamicJarsStoreDirectPath();
        if (var1 == null) {
            throw new RuleException("Current jars dir not exist.");
        } else {
            ByteArrayOutputStream var2 = new ByteArrayOutputStream();
            ZipOutputStream var3 = new ZipOutputStream(var2);
            File var4 = new File(var1);
            File[] var5 = var4.listFiles();
            int var6 = var5.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                File var8 = var5[var7];
                var3.putNextEntry(new ZipEntry(var8.getName()));
                FileInputStream var9 = new FileInputStream(var8);
                IOUtils.copy(var9, var3);
                IOUtils.closeQuietly(var9);
            }

            var3.finish();
            var3.flush();
            var3.closeEntry();
            var3.close();
            byte[] var10 = var2.toByteArray();
            IOUtils.closeQuietly(var2);
            return var10;
        }
    }

    private String a(byte[] var1, String var2) {
        HttpURLConnection var3 = null;

        String var13;
        try {
            String var5;
            try {
                if (var2.endsWith("/")) {
                    var2 = var2.substring(0, var2.length() - 1);
                }

                String var4 = this.d.getUser();
                var5 = this.d.getPwd();
                String var6 = "_u=" + URLEncoder.encode(var4, "utf-8");
                var6 = var6 + "&_p=" + URLEncoder.encode(var5, "utf-8");
                var6 = var6 + "&dynamicjars=true";
                String var7 = var2 + "/knowledgepackagereceiver" + "?" + var6;
                URL var8 = new URL(var7);
                var3 = (HttpURLConnection)var8.openConnection();
                var3.setRequestMethod("POST");
                var3.setRequestProperty("Charset", "UTF-8");
                var3.setRequestProperty("Accept-Charset", "utf-8");
                var3.setRequestProperty("Content-Type", "text/json");
                var3.setUseCaches(false);
                var3.setDoOutput(true);
                var3.setDoInput(true);
                var3.connect();
                OutputStream var9 = var3.getOutputStream();
                DataOutputStream var10 = new DataOutputStream(var3.getOutputStream());
                var10.write(var1);
                var10.flush();
                var10.close();
                InputStream var11 = var3.getInputStream();
                String var12 = IOUtils.toString(var11, "UTF-8");
                var9.close();
                var11.close();
                if (var12.equals("ok")) {
                    var13 = null;
                    return var13;
                }

                var13 = "<strong>推送操作成功到达客户端，但客户端出错错误：</strong><br>" + var12;
            } catch (Exception var17) {
                var5 = "<strong>服务端推送操作出现错误：</strong><br>" + this.a(var17);
                return var5;
            }
        } finally {
            if (var3 != null) {
                var3.disconnect();
            }

        }

        return var13;
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

    public void deployJars(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        if (!var3.isAdmin()) {
            throw new NoPermissionException();
        } else {
            boolean var4 = false;
            String var5 = this.b.buildDynamicJarsStoreDirectPath();
            List var6 = this.a.loadFiles();

            DynamicFile var8;
            for(Iterator var7 = var6.iterator(); var7.hasNext(); this.a.generateJars(var8.getPath(), var5)) {
                var8 = (DynamicFile)var7.next();
                if (var8.getJars().size() > 0) {
                    var4 = true;
                }
            }

            HashMap var9 = new HashMap();
            var9.put("exist", var4);
            if (var4) {
                this.b.loadDynamicJars(var5);
                List var10 = this.b();
                var9.put("clients", var10);
            }

            this.writeObjectToJson(var2, var9);
        }
    }

    private List<ClientConfig> b() throws Exception {
        ArrayList var1 = new ArrayList();
        List var2 = this.c.loadProjects((String)null);
        Iterator var3 = var2.iterator();

        while(var3.hasNext()) {
            RepositoryFile var4 = (RepositoryFile)var3.next();
            String var5 = var4.getName();
            List var6 = this.c.loadClientConfigs(var5, false);
            var1.addAll(var6);
        }

        return var1;
    }

    public void removeFile(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        if (!var3.isAdmin()) {
            throw new NoPermissionException();
        } else {
            String var4 = var1.getParameter("path");
            this.a.removeFile(var4);
        }
    }

    public void removeJar(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        if (!var3.isAdmin()) {
            throw new NoPermissionException();
        } else {
            String var4 = var1.getParameter("path");
            String var5 = var1.getParameter("jarName");
            this.a.removeJar(var4, var5);
        }
    }

    public void addJar(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        if (!var3.isAdmin()) {
            throw new NoPermissionException();
        } else {
            try {
                DiskFileItemFactory var4 = new DiskFileItemFactory();
                ServletFileUpload var14 = new ServletFileUpload(var4);
                List var15 = var14.parseRequest(var1);
                InputStream var7 = null;
                String var8 = null;
                Iterator var9 = var15.iterator();

                while(var9.hasNext()) {
                    FileItem var10 = (FileItem)var9.next();
                    String var11 = var10.getFieldName();
                    if (var11.equals("jar_file")) {
                        var8 = var10.getName();
                        var7 = var10.getInputStream();
                        break;
                    }
                }

                int var16 = var8.lastIndexOf("\\");
                if (var16 > 0) {
                    var8 = var8.substring(var16 + 1, var8.length());
                }

                HashMap var17 = new HashMap();
                String var12 = var1.getParameter("path");
                var12 = Utils.decodeURL(var12);
                if (var8.endsWith(".jar")) {
                    this.a.addJar(var12, var8, var7);
                    var17.put("jarName", var8);
                } else {
                    var17.put("error", "请选择一个Jar文件上传！");
                }

                IOUtils.closeQuietly(var7);
                this.writeObjectToJson(var2, var17);
            } catch (Exception var13) {
                var13.printStackTrace();
                String var5 = var13.getMessage() == null ? NullPointerException.class.getName() : var13.getMessage();
                HashMap var6 = new HashMap();
                var6.put("error", var5);
                this.writeObjectToJson(var2, var6);
            }

        }
    }

    public void saveFile(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        if (!var3.isAdmin()) {
            throw new NoPermissionException();
        } else {
            String var4 = var1.getParameter("name");
            String var5 = var1.getParameter("comment");
            DynamicFile var6 = new DynamicFile();
            var6.setName(var4);
            var6.setComment(var5);
            var6.setCreateUser(var3.getName());
            this.a.saveFile(var6);
        }
    }

    public void loadFiles(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        Principal var3 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
        if (!var3.isAdmin()) {
            throw new NoPermissionException();
        } else {
            List var4 = this.a.loadFiles();
            this.writeObjectToJson(var2, var4);
        }
    }

    public void check(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        boolean var3 = false;
        List var4 = this.a.loadFiles();
        Iterator var5 = var4.iterator();

        while(var5.hasNext()) {
            DynamicFile var6 = (DynamicFile)var5.next();
            if (var6.getJars().size() > 0) {
                var3 = true;
                break;
            }
        }

        HashMap var7 = new HashMap();
        var7.put("exist", var3);
        this.writeObjectToJson(var2, var7);
    }

    public boolean anonymousAccess() {
        return true;
    }

    public void setDynamicJarRepositoryService(DynamicJarRepositoryService var1) {
        this.a = var1;
    }

    public void setDynamicSpringConfigLoader(DynamicSpringConfigLoader var1) {
        this.b = var1;
    }

    public void setRepositoryService(RepositoryService var1) {
        this.c = var1;
    }

    public void setRemoteDynamicJarsBuilder(RemoteDynamicJarsBuilder var1) {
        this.d = var1;
    }

    public String url() {
        return "/dynamic";
    }
}
