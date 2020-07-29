//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.rete;

import com.bstek.urule.ContextHolder;
import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.Node;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.rete.BaseReteNode;
import com.bstek.urule.model.rete.CriteriaNode;
import com.bstek.urule.model.rete.JunctionNode;
import com.bstek.urule.model.rete.Line;
import com.bstek.urule.model.rete.MutexReteUnit;
import com.bstek.urule.model.rete.ObjectTypeNode;
import com.bstek.urule.model.rete.Rete;
import com.bstek.urule.model.rete.ReteUnit;
import com.bstek.urule.model.rete.RuleData;
import com.bstek.urule.model.rete.RuleDueException;
import com.bstek.urule.model.rete.TerminalNode;
import com.bstek.urule.model.rule.Other;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.lhs.BaseCriterion;
import com.bstek.urule.model.rule.lhs.Criterion;
import com.bstek.urule.model.rule.lhs.Lhs;
import com.bstek.urule.model.rule.loop.LoopRule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ReteBuilder implements ApplicationContextAware {
    public static final String BEAN_ID = "urule.reteBuilder";
    private static long a = System.currentTimeMillis();
    private static Collection<CriterionBuilder> b;

    public ReteBuilder() {
    }

    public Rete buildRete(List<Rule> var1, ResourceLibrary var2) {
        return this.a(var1, var2);
    }

    public Rete buildRete(Rule var1, ResourceLibrary var2) {
        ArrayList var3 = new ArrayList();
        var3.add(var1);
        return this.a(var3, var2);
    }

    private Rete a(List<Rule> var1, ResourceLibrary var2) {
        ArrayList var3 = new ArrayList();
        Rete var4 = new Rete(var3, var2);
        BuildContextImpl var5 = new BuildContextImpl(var2, var3);
        HashMap var6 = new HashMap();
        HashMap var7 = new HashMap();
        ArrayList var8 = new ArrayList();
        var4.setAllRuleData(var8);
        Iterator var9 = var1.iterator();

        while(var9.hasNext()) {
            Rule var10 = (Rule)var9.next();
            if (!this.a(var10)) {
                Object var11;
                if (StringUtils.isNotBlank(var10.getPendedGroup())) {
                    var11 = (List)var7.get(var10.getPendedGroup());
                    if (var11 == null) {
                        var11 = new ArrayList();
                        var7.put(var10.getPendedGroup(), var11);
                    }

                    ((List)var11).add(var10);
                } else if (StringUtils.isNotBlank(var10.getMutexGroup())) {
                    var11 = (List)var6.get(var10.getMutexGroup());
                    if (var11 == null) {
                        var11 = new ArrayList();
                        var6.put(var10.getMutexGroup(), var11);
                    }

                    ((List)var11).add(var10);
                } else {
                    if (!var10.getFile().endsWith(".rl.xml")) {
                        var8.add(new RuleData(var10));
                    }

                    TerminalNode var19 = new TerminalNode(var10, var5.nextId());
                    this.a((Rule)var10, (BuildContext)var5, (TerminalNode)var19);
                    var10.setLhs((Lhs)null);
                }
            }
        }

        long var18 = System.currentTimeMillis() - a;
        long var20 = var18 / 1000L / 60L / 60L;
        long var13 = var20 / 24L;
        boolean var15 = false;
        if (var13 >= (long)Double.valueOf(20.0D + Math.random() * 10.0D).intValue()) {
            var15 = true;
        }

        if (var15) {
            Object var16 = ContextHolder.getData("_version");
            if (var16 != null) {
                String var17 = Splash.getFetchVersion();
                if (var17 == null) {
                    throw new RuleDueException();
                }

                if (!var16.equals(var17)) {
                    throw new RuleDueException();
                }
            }
        }

        var4.setPendedGroupRetesMap(this.a(var7, var5, true));
        var4.setMutexGroupRetesMap(this.a(var6, var5, false));
        return var4;
    }

    private boolean a(Rule var1) {
        if (var1.getEnabled() != null && !var1.getEnabled()) {
            return true;
        } else {
            Date var2 = var1.getExpiresDate();
            if (var2 != null) {
                Date var3 = new Date();
                if (var2.compareTo(var3) < 0) {
                    return true;
                }
            }

            return false;
        }
    }

    private Map<String, List<ReteUnit>> a(Map<String, List<Rule>> var1, BuildContext var2, boolean var3) {
        if (var1.size() == 0) {
            return null;
        } else {
            ResourceLibrary var4 = ((BuildContext)var2).getResourceLibrary();
            HashMap var5 = new HashMap();
            Iterator var6 = var1.keySet().iterator();

            while(var6.hasNext()) {
                String var7 = (String)var6.next();
                List var8 = (List)var1.get(var7);
                this.a(var8);
                HashMap var9 = new HashMap();
                Iterator var10 = var8.iterator();

                while(true) {
                    while(var10.hasNext()) {
                        Rule var11 = (Rule)var10.next();
                        String var12 = var11.getMutexGroup();
                        Object var13;
                        if (var3 && StringUtils.isNotBlank(var12)) {
                            var13 = (List)var9.get(var12);
                            if (var13 == null) {
                                var13 = new ArrayList();
                                var9.put(var12, var13);
                            }

                            ((List)var13).add(var11);
                        } else {
                            var13 = (List)var5.get(var7);
                            if (var13 == null) {
                                var13 = new ArrayList();
                                var5.put(var7, var13);
                            }

                            ArrayList var14 = new ArrayList();
                            Rete var15 = new Rete(var14, var4);
                            BuildContextImpl var16 = new BuildContextImpl(var14, (BuildContext)var2);
                            TerminalNode var17 = new TerminalNode(var11, var16.nextId());
                            this.a((Rule)var11, (BuildContext)var16, (TerminalNode)var17);
                            ReteUnit var18 = new ReteUnit(var15, var11.getName());
                            var18.setEffectiveDate(var11.getEffectiveDate());
                            var18.setExpiresDate(var11.getExpiresDate());
                            ((List)var13).add(var18);
                            var11.setLhs((Lhs)null);
                            var2 = var16;
                        }
                    }

                    Map var19 = this.a((BuildContext)var2, (ResourceLibrary)var4, (Map)var9);
                    Object var20 = (List)var5.get(var7);
                    if (var20 == null) {
                        var20 = new ArrayList();
                        var5.put(var7, var20);
                    }

                    Iterator var21 = var19.keySet().iterator();

                    while(var21.hasNext()) {
                        String var23 = (String)var21.next();
                        List var22 = (List)var19.get(var23);
                        ((List)var20).add(new MutexReteUnit(var23, var22));
                    }
                    break;
                }
            }

            return var5;
        }
    }

    private Map<String, List<ReteUnit>> a(BuildContext var1, ResourceLibrary var2, Map<String, List<Rule>> var3) {
        HashMap var4 = new HashMap();
        Iterator var5 = var3.keySet().iterator();

        while(var5.hasNext()) {
            String var6 = (String)var5.next();
            Object var7 = (List)var4.get(var6);
            if (var7 == null) {
                var7 = new ArrayList();
                var4.put(var6, var7);
            }

            List var8 = (List)var3.get(var6);

            BuildContextImpl var13;
            for(Iterator var9 = var8.iterator(); var9.hasNext(); var1 = var13) {
                Rule var10 = (Rule)var9.next();
                ArrayList var11 = new ArrayList();
                Rete var12 = new Rete(var11, var2);
                var13 = new BuildContextImpl(var11, (BuildContext)var1);
                TerminalNode var14 = new TerminalNode(var10, var13.nextId());
                this.a((Rule)var10, (BuildContext)var13, (TerminalNode)var14);
                ReteUnit var15 = new ReteUnit(var12, var10.getName());
                ((List)var7).add(var15);
                var10.setLhs((Lhs)null);
            }
        }

        return var4;
    }

    private void a(List<Rule> var1) {
        Collections.sort(var1, new Comparator<Rule>() {
            public int compare(Rule var1, Rule var2) {
                Integer var3 = var1.getSalience();
                Integer var4 = var2.getSalience();
                if (var3 != null && var4 != null) {
                    return var4 - var3;
                } else if (var4 != null) {
                    return -1;
                } else {
                    return var3 != null ? 1 : 0;
                }
            }
        });
    }

    private void a(Rule var1, BuildContext var2, TerminalNode var3) {
        var2.setCurrentRule(var1);
        Lhs var4 = var1.getLhs();
        if (!(var1 instanceof LoopRule) && var4 != null && var4.getCriterion() != null) {
            Criterion var14 = var4.getCriterion();
            List var6 = buildCriterion(var2, var14);

            Object var8;
            for(Iterator var7 = var6.iterator(); var7.hasNext(); ((BaseReteNode)var8).addLine(var3)) {
                var8 = (BaseReteNode)var7.next();
                if (var8 instanceof JunctionNode) {
                    JunctionNode var9 = (JunctionNode)var8;
                    List var10 = var9.getToConnections();
                    if (var10.size() == 1) {
                        Line var11 = (Line)var10.get(0);
                        Node var12 = var11.getFrom();
                        if (var12 instanceof CriteriaNode) {
                            CriteriaNode var13 = (CriteriaNode)var12;
                            var13.getLines().remove(var11);
                            var8 = var13;
                        }
                    }
                }
            }

            Other var15 = var1.getOther();
            if (var15 != null && var15.getActions() != null && var15.getActions().size() > 0) {
                var1.setWithElse(true);
                Utils.buildElseRule(var1);
                ObjectTypeNode var16 = var2.buildObjectTypeNode("__*__");
                var16.addLine(var3);
            }
        } else {
            ObjectTypeNode var5 = var2.buildObjectTypeNode("__*__");
            var5.addLine(var3);
        }

    }

    public static List<BaseReteNode> buildCriterion(BuildContext var0, Criterion var1) {
        Iterator var2 = b.iterator();

        CriterionBuilder var3;
        do {
            if (!var2.hasNext()) {
                throw new RuleException("Unknow criterion : " + var1);
            }

            var3 = (CriterionBuilder)var2.next();
        } while(!var3.support(var1));

        return var3.buildCriterion((BaseCriterion)var1, var0);
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        b = var1.getBeansOfType(CriterionBuilder.class).values();
    }
}
