//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.exception.RuleException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RemoteDynamicJarsBuilder implements InitializingBean, ApplicationContextAware {
    private String a;
    private String b;
    private String c;
    private String d;
    private int e;
    private String f;
    private Timer g;
    public static final String NONE = "n";
    private Logger h = Logger.getGlobal();
    private ApplicationContext i;
    public static final String BEAN_ID = "urule.remoteDynamicJarsBuilder";

    public RemoteDynamicJarsBuilder() {
    }

    public boolean startIntervalLoadRemoteJars(DynamicSpringConfigLoader var1) throws Exception {
        if (this.e == 0) {
            return false;
        } else {
            if (this.g == null) {
                this.g = new Timer();
            }

            this.h.info("Start loading dynamic jars from server [" + this.c + "] every " + this.e + " minutes.");
            int var2 = this.e * 1000 * 60;
            this.g.schedule(new IntervalTask(var1, this), (long)var2, (long)var2);
            return true;
        }
    }

    public boolean requestRemoteJars(String var1) throws Exception {
        String var2 = this.a();
        if (var2.equals("n")) {
            this.f = var2;
            return false;
        } else if (this.f != null && this.f.equals(var2)) {
            return false;
        } else {
            System.out.println("Start pulling server side dynamic jars");
            this.f = var2;
            HttpURLConnection var3 = null;
            OutputStreamWriter var4 = null;
            InputStream var5 = null;
            Object var6 = null;
            Object var7 = null;

            try {
                String var8 = "_u=" + URLEncoder.encode(this.a, "utf-8") + "&_p=" + URLEncoder.encode(this.b, "utf-8") + "";
                URL var9 = new URL(this.c);
                var3 = (HttpURLConnection)var9.openConnection();
                var3.setRequestMethod("POST");
                var3.setRequestProperty("Accept-Charset", "utf-8");
                var3.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                var3.setRequestProperty("Content-Length", String.valueOf(var8.length()));
                var3.setUseCaches(false);
                var3.setDoOutput(true);
                var3.connect();
                var4 = new OutputStreamWriter(var3.getOutputStream());
                var4.write(var8);
                var4.flush();
                if (var3.getResponseCode() != 200) {
                    throw new RuleException("Server request was failed, Response message : " + var3.getResponseMessage());
                }

                var5 = var3.getInputStream();
                this.unzipDynamicJars(var5, var1);
            } catch (Exception var17) {
                throw new RuleException(var17);
            } finally {
                try {
                    if (var4 != null) {
                        var4.close();
                    }

                    if (var7 != null) {
                        ((BufferedReader)var7).close();
                    }

                    if (var6 != null) {
                        ((InputStreamReader)var6).close();
                    }

                    if (var5 != null) {
                        var5.close();
                    }
                } catch (IOException var16) {
                    var16.printStackTrace();
                }

                if (var3 != null) {
                    var3.disconnect();
                }

            }

            return true;
        }
    }

    private String a() {
        try {
            String var1 = "_u=" + this.a + "&_p=" + this.b + "";
            URL var2 = new URL(this.d);
            HttpURLConnection var3 = (HttpURLConnection)var2.openConnection();
            var3.setRequestMethod("POST");
            var3.setRequestProperty("Accept-Charset", "utf-8");
            var3.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            var3.setRequestProperty("Content-Length", String.valueOf(var1.length()));
            var3.setUseCaches(false);
            var3.setDoOutput(true);
            var3.connect();
            OutputStreamWriter var4 = new OutputStreamWriter(var3.getOutputStream());
            var4.write(var1);
            var4.flush();
            if (var3.getResponseCode() != 200) {
                throw new RuleException("Server request was failed, Response message : " + var3.getResponseMessage());
            } else {
                InputStream var5 = var3.getInputStream();
                ObjectMapper var6 = new ObjectMapper();
                Map var7 = (Map)var6.readValue(var5, HashMap.class);
                IOUtils.closeQuietly(var5);
                var3.disconnect();
                var4.close();
                String var8 = (String)var7.get("dir");
                return var8;
            }
        } catch (Exception var9) {
            throw new RuleException("当前环境配置了【urule.resporityServerUrl】参数，但访问对应的服务器时出现错误", var9);
        }
    }

    public void unzipDynamicJars(InputStream var1, String var2) throws Exception {
        File var3 = new File(var2);
        if (!var3.exists()) {
            var3.mkdirs();
        }

        ZipInputStream var4 = new ZipInputStream(var1);

        for(ZipEntry var5 = var4.getNextEntry(); var5 != null; var5 = var4.getNextEntry()) {
            String var6 = var2 + "/" + var5.getName();
            File var7 = new File(var6);
            FileOutputStream var8 = new FileOutputStream(var7);
            boolean var9 = false;
            byte[] var10 = new byte[1024];

            int var11;
            while((var11 = var4.read(var10)) != -1) {
                var8.write(var10, 0, var11);
            }

            IOUtils.closeQuietly(var8);
        }

        var4.closeEntry();
        var4.close();
    }

    public void destroy() {
        if (this.g != null) {
            this.g.cancel();
        }

    }

    public String getResporityServerUrl() {
        return this.c;
    }

    public void setRemoteLoadInterval(int var1) {
        this.e = var1;
    }

    public void setResporityServerUrl(String var1) {
        if (!StringUtils.isEmpty(var1) && !var1.equals("urule.resporityServerUrl")) {
            this.a(var1);
        }
    }

    public void afterPropertiesSet() throws Exception {
        Collection var1 = this.i.getBeansOfType(ArgumentsProvider.class).values();
        if (var1.size() != 0) {
            ArgumentsProvider var2 = (ArgumentsProvider)var1.iterator().next();
            String var3 = var2.resporityServerUrl();
            if (StringUtils.isNotEmpty(var3)) {
                this.a(var3);
            }

        }
    }

    private void a(String var1) {
        if (var1.endsWith("/")) {
            this.d = var1 + "urule/dynamic/checkLatestJarsDir";
            var1 = var1 + "urule/dynamic/loadDynamicJars";
        } else {
            this.d = var1 + "/urule/dynamic/checkLatestJarsDir";
            var1 = var1 + "/urule/dynamic/loadDynamicJars";
        }

        this.c = var1;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.i = var1;
    }

    public String getUser() {
        return this.a;
    }

    public String getPwd() {
        return this.b;
    }

    public void setUser(String var1) {
        this.a = var1;
    }

    public void setPwd(String var1) {
        this.b = var1;
    }
}
