//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

import com.bstek.urule.model.library.Datatype;

public class ParametersWrap {
    private Datatype[] a;
    private Object[] b;

    ParametersWrap() {
    }

    public Datatype[] getDatatypes() {
        return this.a;
    }

    public void setDatatypes(Datatype[] var1) {
        this.a = var1;
    }

    public Object[] getValues() {
        return this.b;
    }

    public void setValues(Object[] var1) {
        this.b = var1;
    }

    public String valuesToString() {
        if (this.b == null) {
            return "";
        } else {
            StringBuffer var1 = new StringBuffer();
            Object[] var2 = this.b;
            int var3 = var2.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Object var5 = var2[var4];
                if (var1.length() > 0) {
                    var1.append(",");
                }

                if (var5 == null) {
                    var1.append("null");
                } else {
                    var1.append(var5);
                }
            }

            return var1.toString();
        }
    }
}
