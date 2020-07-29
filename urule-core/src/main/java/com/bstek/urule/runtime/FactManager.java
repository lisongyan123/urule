//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.Utils;
import com.bstek.urule.model.GeneralEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FactManager {
    private FactManager a;
    private List<Object> b = new ArrayList();
    private Map<String, Object> c = new HashMap();
    private Map<String, Object> d = new HashMap();
    private Map<String, Object> e = new HashMap();
    private Map<String, Object> f = new HashMap();

    public FactManager(KnowledgeSession var1) {
        if (var1 != null) {
            Iterator var2 = var1.getFactList().iterator();

            while(var2.hasNext()) {
                Object var3 = var2.next();
                if (!var3.getClass().getName().equals(HashMap.class.getName())) {
                    this.b.add(var3);
                }
            }

            this.c.putAll(var1.getAllFactsMap());
            this.a = var1.getFactManager();
        }
    }

    public void initKnowledgePackageParameters(KnowledgePackage var1) {
        ParameterManager.getInstance().initKnowledgePackageParameters(var1, this.e);
    }

    public Map<String, Object> buildRuntimeParameters(Map<String, Object> var1) {
        this.d.clear();
        ParameterManager.getInstance().clearInitParameters(this.e);
        this.d.putAll(this.e);
        this.d.putAll(this.f);
        if (var1 != null) {
            Iterator var2 = var1.keySet().iterator();

            while(var2.hasNext()) {
                String var3 = (String)var2.next();
                if (!var3.equals("_loop_rule_break_tag__")) {
                    this.d.put(var3, var1.get(var3));
                }
            }
        }

        this.addToFactsMap(this.d);
        return this.d;
    }

    public boolean insert(Object var1) {
        if (this.a != null) {
            this.a.insert(var1);
        }

        if (!(var1 instanceof GeneralEntity) && var1 instanceof Map) {
            Map var2 = (Map)var1;
            Iterator var3 = var2.keySet().iterator();

            while(var3.hasNext()) {
                Object var4 = var3.next();
                if (var4 != null) {
                    this.f.put(var4.toString(), var2.get(var4));
                }
            }

            return false;
        } else {
            this.addToFactsMap(var1);
            return true;
        }
    }

    public void insertLoopFact(Object var1) {
        if (!(var1 instanceof GeneralEntity) && var1 instanceof Map) {
            Map var2 = (Map)var1;
            Iterator var3 = var2.keySet().iterator();

            while(var3.hasNext()) {
                Object var4 = var3.next();
                if (var4 != null) {
                    this.f.put(var4.toString(), var2.get(var4));
                }
            }
        } else {
            this.addToFactsMap(var1);
        }

    }

    public void addToFactsMap(Object var1) {
        if (!this.b.contains(var1)) {
            this.b.add(var1);
        }

        String var2 = Utils.getClassName(var1);
        this.c.put(var2, var1);
    }

    public static void main(String[] var0) {
        ArrayList var1 = new ArrayList();
        GeneralEntity var2 = new GeneralEntity("aaaa");
        var1.add(var2);
        GeneralEntity var3 = new GeneralEntity("aaaa");
        if (var1.contains(var3)) {
            System.out.println("++++");
        }

    }

    public void clean() {
        this.c.clear();
        this.b.clear();
        this.f.clear();
    }

    public Map<String, Object> getParameters() {
        return this.d;
    }

    public Map<String, Object> getFactMap() {
        return this.c;
    }

    public List<Object> getFactList() {
        ArrayList var1 = new ArrayList(this.b.size());
        var1.addAll(this.b);
        return var1;
    }
}
