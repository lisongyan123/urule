package com.bstek.urule.console.repository.authority;
import java.io.Serializable;
public class Authority implements Serializable {
    private static final long a = 2105456131874934568L;
    private String b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;

    public Authority() {
    }

    public boolean hasFullPermission() {
        return this.c && this.d;
    }

    public String getPath() {
        return this.b;
    }

    public void setPath(String var1) {
        this.b = var1;
    }

    public boolean isRead() {
        return this.c;
    }

    public void setRead(boolean var1) {
        this.c = var1;
    }

    public boolean isWrite() {
        return this.d;
    }

    public void setWrite(boolean var1) {
        this.d = var1;
    }

    public boolean isSave() {
        return this.e;
    }

    public void setSave(boolean var1) {
        this.e = var1;
    }

    public boolean isSaveas() {
        return this.f;
    }

    public void setSaveas(boolean var1) {
        this.f = var1;
    }
}

