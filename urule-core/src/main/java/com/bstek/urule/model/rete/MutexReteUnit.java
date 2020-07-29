//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import java.util.List;
import org.codehaus.jackson.annotate.JsonTypeName;

@JsonTypeName("mutex")
public class MutexReteUnit extends ReteUnit {
    private String mutexGroupName;
    private List<ReteUnit> list;

    public MutexReteUnit() {
    }

    public MutexReteUnit(String var1, List<ReteUnit> var2) {
        this.mutexGroupName = var1;
        this.list = var2;
    }

    public List<ReteUnit> getList() {
        return this.list;
    }

    public void setList(List<ReteUnit> var1) {
        this.list = var1;
    }

    public String getMutexGroupName() {
        return this.mutexGroupName;
    }

    public void setMutexGroupName(String var1) {
        this.mutexGroupName = var1;
    }
}