//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule;

import java.lang.reflect.Method;

public class Splash {
    public static String version;

    public Splash() {
    }

    public static String getFetchVersion() {
        String var0 = null;

        try {
            Class var1 = Class.forName("URuleProLicenseProvider");
            Method var2 = var1.getMethod("getVersion");
            Object var3 = var2.invoke(var1.newInstance());
            if (var3 != null) {
                var0 = var3.toString();
            }
        } catch (Exception var4) {
        }

        return var0;
    }

    public static String getVersion() {
        return version;
    }
}
