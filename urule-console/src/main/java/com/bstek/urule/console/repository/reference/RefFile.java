package com.bstek.urule.console.repository.reference;

public class RefFile {
    private String a;
    private String b;
    private String c;
    private String d;

    public RefFile() {
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }

    public String getPath() {
        return this.b;
    }

    public void setPath(String var1) {
        this.b = var1;
    }

    public String getEditor() {
        return this.c;
    }

    public void setEditor(String var1) {
        this.c = var1;
    }

    public String getType() {
        return this.d;
    }

    public void setType(String var1) {
        this.d = var1;
    }
}
