//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SyntaxErrorReportor {
    private List<String> a = new ArrayList();

    public SyntaxErrorReportor() {
    }

    public void addError(int var1, int var2, Object var3, String var4) {
        this.a.add(var1 + "行," + var2 + "列，" + var3 + "字符处，存在语法错误:" + var4);
    }

    public String getSyntaxErrorMessage() {
        StringBuffer var1 = new StringBuffer();
        Iterator var2 = this.a.iterator();

        while(var2.hasNext()) {
            String var3 = (String)var2.next();
            var1.append(var3);
            var1.append("\n");
        }

        return var1.toString();
    }
}
