//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.Splash;
import com.bstek.urule.exception.RuleException;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.UrlResource;

public class DynamicSpringConfigLoaderImpl implements DynamicSpringConfigLoader, ApplicationContextAware {
    private Logger a = Logger.getGlobal();
    private String b;
    private String c;
    private String d;
    private String e;
    private ApplicationContext f;
    private ClassLoader g;
    private URLClassLoader h;
    private DynamicJarCreator i;
    private BuiltInActionLibraryBuilder j;
    private RemoteDynamicJarsBuilder k;

    public DynamicSpringConfigLoaderImpl() {
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.g = var1.getClassLoader();
        this.f = var1;
        Collection var2 = var1.getBeansOfType(DynamicJarCreator.class).values();
        if (var2.size() > 0) {
            this.i = (DynamicJarCreator)var2.iterator().next();
        }

        try {
            this.a();
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }

    private void a() throws Exception {
        String var1 = this.buildDynamicJarsStoreDirectPath();
        File var2 = new File(this.c);
        this.a(var2, true);
        boolean var3 = false;
        if (StringUtils.isNotBlank(this.k.getResporityServerUrl())) {
            var3 = this.k.requestRemoteJars(var1);
            this.k.startIntervalLoadRemoteJars(this);
        } else if (this.i != null) {
            var3 = this.i.doCreate(var1);
        }

        if (var3) {
            this.loadDynamicJars(var1);
        }

    }

    private void a(File var1, boolean var2) {
        File[] var3 = var1.listFiles();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            File var6 = var3[var5];
            if (var6.isFile()) {
                var6.delete();
            } else {
                this.a(var6, false);
            }
        }

        if (!var2) {
            var1.delete();
        }

    }

    public void loadDynamicJars(String var1) throws Exception {
        if (var1 == null) {
            this.a.warning("Dynamic jars store path not specify,so do not load jars...");
        } else {
            this.d = var1;
            AutowireCapableBeanFactory var2 = this.f.getAutowireCapableBeanFactory();
            if (!(var2 instanceof DefaultListableBeanFactory)) {
                this.a.warning("Current \"" + var2 + "\" is not DefaultListableBeanFactory type,so can not loading dynamic jars.");
            } else {
                System.out.println("Start loading dynamic jars,this will take a faw seconds...");
                File var3 = new File(this.d);
                this.e = var3.getName();
                File[] var4 = var3.listFiles();
                if (var4 == null) {
                    this.a.warning("Dynamic dir [" + this.d + "] has no files.");
                } else {
                    DefaultListableBeanFactory var5 = (DefaultListableBeanFactory)var2;
                    ArrayList var6 = new ArrayList();
                    ArrayList var7 = new ArrayList();
                    File[] var8 = var4;
                    int var9 = var4.length;

                    for(int var10 = 0; var10 < var9; ++var10) {
                        File var11 = var8[var10];
                        String var12 = var11.getName();
                        if (var12.toLowerCase().endsWith(".jar")) {
                            URL var13 = var11.toURI().toURL();
                            var6.add(var13);
                            String var14 = var11.getAbsolutePath() + "!/urule-spring-context.xml";
                            if (var14.startsWith("/")) {
                                var14 = var14.substring(1, var14.length());
                            }

                            String var15 = "jar:file:/" + var14;
                            UrlResource var16 = new UrlResource(new URL(var15));
                            if (var16.exists()) {
                                var7.add(var16);
                            }
                        }
                    }

                    URL[] var18 = (URL[])var6.toArray(new URL[var6.size()]);
                    URLClassLoader var19 = new URLClassLoader(var18, this.g);

                    try {
                        var5.setBeanClassLoader(var19);
                        Iterator var20 = var7.iterator();

                        while(var20.hasNext()) {
                            UrlResource var21 = (UrlResource)var20.next();
                            XmlBeanDefinitionReader var22 = new XmlBeanDefinitionReader(var5);
                            var22.loadBeanDefinitions(var21);
                        }
                    } catch (Exception var17) {
                        var5.setBeanClassLoader(this.g);
                        throw new RuleException(var17);
                    }

                    if (this.h != null) {
                        this.h.close();
                    }

                    this.h = var19;
                    System.out.println("Loading dynamic jars successfully...");
                    this.j.buildActions(this.f);
                    this.a(var3.getName());
                }
            }
        }
    }

    private void a(String var1) {
        try {
            File var2 = new File(this.c);
            File[] var9 = var2.listFiles();
            if (var9 == null) {
                return;
            }

            File[] var4 = var9;
            int var5 = var9.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                File var7 = var4[var6];
                if (!var7.getName().equals(var1)) {
                    this.a(var7, false);
                }
            }
        } catch (Exception var8) {
            String var3 = var8.getMessage();
            if (var3 == null) {
                var3 = NullPointerException.class.getName();
            }

            this.a.warning("Clean dynamic jars store path was fail:" + var3);
        }

    }

    public String buildDynamicJarsStoreDirectPath() {
        SimpleDateFormat var1 = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
        String var2 = this.b() + "/" + var1.format(new Date());
        return var2;
    }

    private final String b() {
        if (this.c != null) {
            return this.c;
        } else {
            String var1 = this.b;
            if (StringUtils.isBlank(var1)) {
                var1 = System.getProperty("java.io.tmpdir") + "/urule-jars";
            }

            String var2 = System.getProperty("urule.instance.id");
            if (StringUtils.isNotBlank(var2)) {
                var1 = var1 + "/" + var2;
            }

            File var3 = new File(var1);
            if (!var3.exists()) {
                var3.mkdirs();
            }

            this.c = var1;
            return var1;
        }
    }

    public void setRemoteDynamicJarsBuilder(RemoteDynamicJarsBuilder var1) {
        this.k = var1;
    }

    public String getDynamicJarsStoreDirectPath() {
        return this.d;
    }

    public String getDynamicJarsStoreDirectDirName() {
        return this.e;
    }

    public void setDynamicJarsPath(String var1) {
        this.b = var1;
    }

    public void setBuiltInActionLibraryBuilder(BuiltInActionLibraryBuilder var1) {
        this.j = var1;
    }

}
