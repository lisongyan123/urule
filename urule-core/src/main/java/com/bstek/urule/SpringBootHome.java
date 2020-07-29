//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

public class SpringBootHome {
    public SpringBootHome() {
    }

    public File findSpringbootJarHomeDir(Class<?> var1) {
        File var2 = this.a(var1 != null ? var1 : this.b());
        return this.a(var2);
    }

    private File a(File var1) {
        File var2 = var1 != null ? var1 : this.a();
        if (var2.isFile()) {
            var2 = var2.getParentFile();
        }

        var2 = var2.exists() ? var2 : new File(".");
        return var2.getAbsoluteFile();
    }

    private File a() {
        String var1 = System.getProperty("user.dir");
        return new File(StringUtils.hasLength(var1) ? var1 : ".");
    }

    private Class<?> b() {
        try {
            ClassLoader var1 = this.getClass().getClassLoader();
            return this.a(var1.getResources("META-INF/MANIFEST.MF"));
        } catch (Exception var2) {
            return null;
        }
    }

    private Class<?> a(Enumeration<URL> var1) {
        while(var1.hasMoreElements()) {
            try {
                InputStream var2 = ((URL)var1.nextElement()).openStream();
                Throwable var3 = null;

                Class var6;
                try {
                    Manifest var4 = new Manifest(var2);
                    String var5 = var4.getMainAttributes().getValue("Start-Class");
                    if (var5 == null) {
                        continue;
                    }

                    var6 = ClassUtils.forName(var5, this.getClass().getClassLoader());
                } catch (Throwable var17) {
                    var3 = var17;
                    throw var17;
                } finally {
                    if (var2 != null) {
                        if (var3 != null) {
                            try {
                                var2.close();
                            } catch (Throwable var16) {
                                var3.addSuppressed(var16);
                            }
                        } else {
                            var2.close();
                        }
                    }

                }

                return var6;
            } catch (Exception var19) {
            }
        }

        return null;
    }

    private File a(Class<?> var1) {
        try {
            ProtectionDomain var2 = var1 != null ? var1.getProtectionDomain() : null;
            CodeSource var3 = var2 != null ? var2.getCodeSource() : null;
            URL var4 = var3 != null ? var3.getLocation() : null;
            File var5 = var4 != null ? this.a(var4) : null;
            return var5 != null && var5.exists() && !this.c() ? var5.getAbsoluteFile() : null;
        } catch (Exception var6) {
            return null;
        }
    }

    private File a(URL var1) throws IOException {
        URLConnection var2 = var1.openConnection();
        return var2 instanceof JarURLConnection ? this.a(((JarURLConnection)var2).getJarFile()) : new File(var1.getPath());
    }

    private File a(JarFile var1) {
        String var2 = var1.getName();
        int var3 = var2.indexOf("!/");
        if (var3 > 0) {
            var2 = var2.substring(0, var3);
        }

        return new File(var2);
    }

    private boolean c() {
        try {
            StackTraceElement[] var1 = Thread.currentThread().getStackTrace();

            for(int var2 = var1.length - 1; var2 >= 0; --var2) {
                if (var1[var2].getClassName().startsWith("org.junit.")) {
                    return true;
                }
            }
        } catch (Exception var3) {
        }

        return false;
    }
}
