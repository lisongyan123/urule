//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule;

import org.apache.commons.lang.StringUtils;

public class Configure {
    private static String a;
    private static String b;

    public Configure() {
    }

    public void setDateFormat(String var1) {
        if (!StringUtils.isEmpty(var1) && !var1.equals("${urule.dateFormat}")) {
            a = var1;
        } else {
            a = "yyyy-MM-dd HH:mm:ss";
        }

    }

    public void setTempStorePath(String var1) {
        if (!var1.equals("${urule.tempStorePath}")) {
            b = var1;
        }

    }

    public static String getTempStorePath() {
        return b;
    }

    public static String getDateFormat() {
        return a;
    }
}
