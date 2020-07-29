//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

public class Library {
    private String path;
    private String version;
    private LibraryType type;

    public Library() {
    }

    public Library(String var1, String var2, LibraryType var3) {
        this.path = var1;
        this.version = var2;
        this.type = var3;
    }

    public void setPath(String var1) {
        this.path = var1;
    }

    public String getPath() {
        return this.path;
    }

    public LibraryType getType() {
        return this.type;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String var1) {
        this.version = var1;
    }

    public void setType(LibraryType var1) {
        this.type = var1;
    }
}
