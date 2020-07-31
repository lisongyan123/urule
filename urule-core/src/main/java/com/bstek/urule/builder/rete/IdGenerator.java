//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.rete;

public class IdGenerator {
    private int a = 1;
    private static final ThreadLocal<Integer> b = new ThreadLocal();

    public IdGenerator() {
        Integer var1 = (Integer)b.get();
        if (var1 != null) {
            this.a = var1;
        }

    }

    public static final void clean() {
        b.remove();
    }

    public int nextId() {
        b.set(++this.a);
        return this.a;
    }
}
