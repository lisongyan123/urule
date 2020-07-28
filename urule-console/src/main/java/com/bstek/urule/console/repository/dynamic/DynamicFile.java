package com.bstek.urule.console.repository.dynamic;

import java.util.Date;
import java.util.List;

public class DynamicFile {
    private String a;
    private String b;
    private String c;
    private String d;
    private Date e;
    private List<DynamicJar> f;

    public DynamicFile() {
    }

    public String getPath() {
        return this.a;
    }

    public void setPath(String var1) {
        this.a = var1;
    }

    public String getName() {
        return this.b;
    }

    public void setName(String var1) {
        this.b = var1;
    }

    public String getComment() {
        return this.c;
    }

    public void setComment(String var1) {
        this.c = var1;
    }

    public String getCreateUser() {
        return this.d;
    }

    public void setCreateUser(String var1) {
        this.d = var1;
    }

    public Date getCreateDate() {
        return this.e;
    }

    public void setCreateDate(Date var1) {
        this.e = var1;
    }

    public List<DynamicJar> getJars() {
        return this.f;
    }

    public void setJars(List<DynamicJar> var1) {
        this.f = var1;
    }
}
