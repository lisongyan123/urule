//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.resource;

public class Resource {
    private String a;
    private String b;
    private String c;

    public Resource(String var1, String var2, String var3) {
        this.b = var1;
        this.a = var2;
        this.c = var3;
    }

    public String getPath() {
        return this.a;
    }

    public String getContent() {
        return this.b;
    }

    public String getVersion() {
        return this.c;
    }
}
