//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.service;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.runtime.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RemoteServiceImpl implements RemoteService, InitializingBean, ApplicationContextAware {
    private String a;
    private RemoteDynamicJarsBuilder b;
    private ApplicationContext c;
    private Logger d = Logger.getGlobal();

    public RemoteServiceImpl() {
    }

    public KnowledgePackage getKnowledge(String var1, String var2) {
        if (StringUtils.isEmpty(this.a)) {
            return null;
        } else {
            this.d.info("Load knowledgepackage [" + var1 + "] from remote...");
            String var3 = this.a(var1, var2);
            if (StringUtils.isEmpty(var3)) {
                return null;
            } else {
                KnowledgePackageWrapper var4 = JsonUtils.parseKnowledgePackageWrapper(var3);
                KnowledgePackage var5 = var4.getKnowledgePackage();
                Map var6 = var5.getFlowMap();
                if (var6 != null && var6.size() > 0) {
                    Iterator var7 = var6.values().iterator();

                    while(var7.hasNext()) {
                        FlowDefinition var8 = (FlowDefinition)var7.next();
                        var8.buildConnectionToNode();
                    }
                }

                var5.resetTimestamp();
                ((KnowledgePackageImpl)var5).setPackageInfo(var1);
                return var5;
            }
        }
    }

    private String a(String var1, String var2) {
        HttpURLConnection var3 = null;
        OutputStreamWriter var4 = null;
        InputStream var5 = null;
        Object var6 = null;
        Object var7 = null;

        String var11;
        try {
            var1 = Utils.encodeURL(var1);
            var1 = Utils.encodeURL(var1);
            String var8 = "packageId=" + var1 + "";
            if (StringUtils.isNotEmpty(var2)) {
                var8 = var8 + "&timestamp=" + var2 + "";
            }

            var8 = var8 + "&_u=" + Utils.encodeURL(this.b.getUser()) + "";
            var8 = var8 + "&_p=" + Utils.encodeURL(this.b.getPwd()) + "";
            URL var9 = new URL(this.a);
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
                String var22 = var3.getHeaderField("errorMsg");
                if (var22 != null) {
                    var22 = URLDecoder.decode(var22, "utf-8");
                }

                throw new RuleException("Server request was failed : " + var22);
            }

            var5 = var3.getInputStream();
            byte[] var10 = IOUtils.toByteArray(var5);
            var11 = Utils.uncompress(var10);
        } catch (Exception var20) {
            throw new RuleException(var20);
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
            } catch (IOException var19) {
                var19.printStackTrace();
            }

            if (var3 != null) {
                var3.disconnect();
            }

        }

        return var11;
    }

    public void setResporityServerUrl(String var1) {
        if (!StringUtils.isEmpty(var1) && !var1.equals("urule.resporityServerUrl")) {
            this.a(var1);
        }
    }

    public void afterPropertiesSet() throws Exception {
        Collection var1 = this.c.getBeansOfType(ArgumentsProvider.class).values();
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
            var1 = var1 + "urule/loadknowledge";
        } else {
            var1 = var1 + "/urule/loadknowledge";
        }

        this.a = var1;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.c = var1;
    }

    public void setRemoteDynamicJarsBuilder(RemoteDynamicJarsBuilder var1) {
        this.b = var1;
    }
}
