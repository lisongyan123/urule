//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.model.Node;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.runtime.rete.MutexReteInstanceUnit;
import com.bstek.urule.runtime.rete.ObjectTypeActivity;
import com.bstek.urule.runtime.rete.ReteInstance;
import com.bstek.urule.runtime.rete.ReteInstanceUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Rete implements Node {
    private List<ObjectTypeNode> objectTypeNodes;
    private Map<String, List<ReteUnit>> mutexGroupRetesMap;
    private Map<String, List<ReteUnit>> pendedGroupRetesMap;
    @JsonIgnore
    private List<RuleData> allRuleData;
    @JsonIgnore
    private ResourceLibrary resourceLibrary;

    public Rete() {
    }

    public Rete(List<ObjectTypeNode> var1, ResourceLibrary var2) {
        this.objectTypeNodes = var1;
        this.resourceLibrary = var2;
    }

    public List<ObjectTypeNode> getObjectTypeNodes() {
        return this.objectTypeNodes;
    }

    public ResourceLibrary getResourceLibrary() {
        return this.resourceLibrary;
    }

    public Map<String, List<ReteUnit>> getMutexGroupRetesMap() {
        return this.mutexGroupRetesMap;
    }

    public void setMutexGroupRetesMap(Map<String, List<ReteUnit>> var1) {
        this.mutexGroupRetesMap = var1;
    }

    public Map<String, List<ReteUnit>> getPendedGroupRetesMap() {
        return this.pendedGroupRetesMap;
    }

    public void setPendedGroupRetesMap(Map<String, List<ReteUnit>> var1) {
        this.pendedGroupRetesMap = var1;
    }

    public List<RuleData> getAllRuleData() {
        return this.allRuleData;
    }

    public void setAllRuleData(List<RuleData> var1) {
        this.allRuleData = var1;
    }

    public ReteInstance newReteInstance() {
        ArrayList var1 = new ArrayList();
        HashMap var2 = new HashMap();
        Iterator var3 = this.objectTypeNodes.iterator();

        while(var3.hasNext()) {
            ObjectTypeNode var4 = (ObjectTypeNode)var3.next();
            var1.add((ObjectTypeActivity)var4.newActivity(var2));
        }

        Map var5 = this.buildGroupRetesInstance(this.mutexGroupRetesMap);
        Map var6 = this.buildGroupRetesInstance(this.pendedGroupRetesMap);
        return new ReteInstance(var1, var5, var6, this.allRuleData);
    }

    private Map<String, List<ReteInstanceUnit>> buildGroupRetesInstance(Map<String, List<ReteUnit>> var1) {
        if (var1 == null) {
            return null;
        } else {
            HashMap var2 = new HashMap();
            Iterator var3 = var1.keySet().iterator();

            label46:
            while(var3.hasNext()) {
                String var4 = (String)var3.next();
                List var5 = (List)var1.get(var4);
                Iterator var6 = var5.iterator();

                while(true) {
                    while(true) {
                        if (!var6.hasNext()) {
                            continue label46;
                        }

                        ReteUnit var7 = (ReteUnit)var6.next();
                        Object var8 = (List)var2.get(var4);
                        if (var8 == null) {
                            var8 = new ArrayList();
                            var2.put(var4, var8);
                        }

                        Rete var9 = var7.getRete();
                        if (var9 != null) {
                            ReteInstance var17 = var9.newReteInstance();
                            ReteInstanceUnit var18 = new ReteInstanceUnit(var17, var7.getRuleName());
                            var18.setEffectiveDate(var7.getEffectiveDate());
                            var18.setExpiresDate(var7.getExpiresDate());
                            ((List)var8).add(var18);
                        } else if (var7 instanceof MutexReteUnit) {
                            MutexReteUnit var10 = (MutexReteUnit)var7;
                            List var11 = var10.getList();
                            ArrayList var12 = new ArrayList();
                            Iterator var13 = var11.iterator();

                            while(var13.hasNext()) {
                                ReteUnit var14 = (ReteUnit)var13.next();
                                Rete var15 = var14.getRete();
                                ReteInstance var16 = var15.newReteInstance();
                                var12.add(var16);
                            }

                            String var19 = var10.getMutexGroupName();
                            MutexReteInstanceUnit var20 = new MutexReteInstanceUnit(var19, var12);
                            var20.setEffectiveDate(var7.getEffectiveDate());
                            var20.setExpiresDate(var7.getExpiresDate());
                            ((List)var8).add(var20);
                        }
                    }
                }
            }

            return var2;
        }
    }
}
