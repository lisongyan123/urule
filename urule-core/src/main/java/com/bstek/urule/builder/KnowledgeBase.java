//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder;

import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.rete.Rete;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgePackageImpl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class KnowledgeBase {
    private ResourceLibrary a;
    private Map<String, FlowDefinition> b;
    private Rete c;
    private List<Rete> d;
    private KnowledgePackageImpl e;

    public KnowledgeBase(Rete var1) {
        this(var1, (List)null, (Map)null);
    }

    protected KnowledgeBase(Rete var1, List<Rete> var2, Map<String, FlowDefinition> var3) {
        this.c = var1;
        this.d = var2;
        this.a = var1.getResourceLibrary();
        this.b = var3;
    }

    public KnowledgePackage getKnowledgePackage() {
        if (this.e != null) {
            return this.e;
        } else {
            this.e = new KnowledgePackageImpl();
            this.e.setRete(this.c);
            this.e.setAloneRetes(this.d);
            this.e.setFlowMap(this.b);
            HashMap var1 = new HashMap();
            this.e.setVariableCategoryMap(var1);
            List var2 = this.a.getVariableCategories();
            HashMap var3 = new HashMap();
            this.e.setParameters(var3);
            Iterator var4 = var2.iterator();

            while(true) {
                List var7;
                do {
                    do {
                        VariableCategory var5;
                        String var6;
                        do {
                            if (!var4.hasNext()) {
                                return this.e;
                            }

                            var5 = (VariableCategory)var4.next();
                            var6 = var5.getName();
                            var1.put(var6, var5.getClazz());
                        } while(!var6.equals("参数"));

                        var7 = var5.getVariables();
                    } while(var7 == null);
                } while(var7.size() == 0);

                Iterator var8 = var7.iterator();

                while(var8.hasNext()) {
                    Variable var9 = (Variable)var8.next();
                    var3.put(var9.getName(), var9.getType().name());
                }
            }
        }
    }

    public Rete getRete() {
        return this.c;
    }

    public ResourceLibrary getResourceLibrary() {
        return this.a;
    }

    public Map<String, FlowDefinition> getFlowMap() {
        return this.b;
    }
}
