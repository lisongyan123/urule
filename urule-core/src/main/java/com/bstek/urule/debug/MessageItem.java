//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.debug;

public class MessageItem {
    private String a;
    private String b;

    public MessageItem(String var1, String var2) {
        this.a = var1;
        this.b = var2;
    }

    public String toHtml() {
        return this.b;
    }

    public String getMsg() {
        return this.a;
    }
}
