//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.rete.BaseReteNode;
import com.bstek.urule.model.rete.Line;
import com.bstek.urule.model.rete.MutexReteUnit;
import com.bstek.urule.model.rete.ObjectTypeNode;
import com.bstek.urule.model.rete.Rete;
import com.bstek.urule.model.rete.ReteNode;
import com.bstek.urule.model.rete.ReteNodeJsonDeserializer;
import com.bstek.urule.model.rete.ReteUnit;
import com.bstek.urule.model.rete.RuleData;
import com.bstek.urule.model.rete.TerminalNode;
import com.bstek.urule.model.rule.Rule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class KnowledgePackageWrapper {
    @JsonDeserialize(
            as = KnowledgePackageImpl.class
    )
    private KnowledgePackage knowledgePackage;
    @JsonDeserialize(
            using = ReteNodeJsonDeserializer.class
    )
    private List<ReteNode> allNodes = new ArrayList();
    private String id;

    public KnowledgePackageWrapper() {
        this.id = UUID.randomUUID().toString();
    }

    public KnowledgePackageWrapper(KnowledgePackage var1) {
        this.knowledgePackage = var1;
        this.id = UUID.randomUUID().toString();
        this.initNodes();
    }

    private void initNodes() {
        Rete var1 = this.knowledgePackage.getRete();
        this.initReteNodes(var1);
        List var2 = this.knowledgePackage.getAloneRetes();
        if (var2 != null) {
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
                Rete var4 = (Rete)var3.next();
                this.initReteNodes(var4);
            }
        }

    }

    private void initReteNodes(Rete var1) {
        List var2 = var1.getObjectTypeNodes();
        ArrayList var3 = new ArrayList();
        var3.addAll(var2);
        this.buildChildrenNodes(var3, var1.getMutexGroupRetesMap());
        this.buildChildrenNodes(var3, var1.getPendedGroupRetesMap());
        this.queryReteNodes(var3);
        this.initAllRuleData(var1);
    }

    private void initAllRuleData(Rete var1) {
        ArrayList var2 = new ArrayList();
        var1.setAllRuleData(var2);
        Iterator var3 = this.allNodes.iterator();

        while(var3.hasNext()) {
            ReteNode var4 = (ReteNode)var3.next();
            if (var4 instanceof TerminalNode) {
                TerminalNode var5 = (TerminalNode)var4;
                Rule var6 = var5.getRule();
                if (!var6.getFile().endsWith(".rl.xml")) {
                    var2.add(new RuleData(var6));
                }
            }
        }

    }

    private void buildChildrenNodes(List<ReteNode> var1, Map<String, List<ReteUnit>> var2) {
        if (var2 != null) {
            Iterator var3 = var2.values().iterator();

            label42:
            while(var3.hasNext()) {
                List var4 = (List)var3.next();
                Iterator var5 = var4.iterator();

                while(true) {
                    while(true) {
                        if (!var5.hasNext()) {
                            continue label42;
                        }

                        ReteUnit var6 = (ReteUnit)var5.next();
                        if (var6.getRete() != null) {
                            var1.addAll(var6.getRete().getObjectTypeNodes());
                        } else if (var6 instanceof MutexReteUnit) {
                            MutexReteUnit var7 = (MutexReteUnit)var6;
                            List var8 = var7.getList();
                            Iterator var9 = var8.iterator();

                            while(var9.hasNext()) {
                                ReteUnit var10 = (ReteUnit)var9.next();
                                if (var10.getRete() != null) {
                                    var1.addAll(var10.getRete().getObjectTypeNodes());
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    private void queryReteNodes(List<ReteNode> var1) {
        if (var1 != null) {
            Iterator var2 = var1.iterator();

            while(var2.hasNext()) {
                ReteNode var3 = (ReteNode)var2.next();
                if (!this.allNodes.contains(var3) && !(var3 instanceof ObjectTypeNode)) {
                    this.allNodes.add(var3);
                }

                if (var3 instanceof BaseReteNode) {
                    BaseReteNode var4 = (BaseReteNode)var3;
                    this.queryReteNodes(var4.getChildrenNodes());
                }
            }

        }
    }

    public void buildDeserialize() {
        Rete var1 = this.knowledgePackage.getRete();
        this.buildDeserialize(var1);
        List var2 = this.knowledgePackage.getAloneRetes();
        if (var2 != null) {
            Iterator var3 = var2.iterator();

            while(var3.hasNext()) {
                Rete var4 = (Rete)var3.next();
                this.buildDeserialize(var4);
            }
        }

    }

    private void buildDeserialize(Rete var1) {
        this.rebuildReteLine(var1);
        this.buildRetesMap(var1.getMutexGroupRetesMap());
        this.buildRetesMap(var1.getPendedGroupRetesMap());
        Map var2 = this.knowledgePackage.getFlowMap();
        if (var2 != null && var2.size() > 0) {
            Iterator var3 = var2.values().iterator();

            while(var3.hasNext()) {
                FlowDefinition var4 = (FlowDefinition)var3.next();
                var4.buildConnectionToNode();
            }
        }

        this.initAllRuleData(var1);
    }

    private void buildRetesMap(Map<String, List<ReteUnit>> var1) {
        if (var1 != null) {
            Iterator var2 = var1.values().iterator();

            label43:
            while(var2.hasNext()) {
                List var3 = (List)var2.next();
                Iterator var4 = var3.iterator();

                while(true) {
                    while(true) {
                        if (!var4.hasNext()) {
                            continue label43;
                        }

                        ReteUnit var5 = (ReteUnit)var4.next();
                        if (var5 instanceof MutexReteUnit) {
                            MutexReteUnit var11 = (MutexReteUnit)var5;
                            List var7 = var11.getList();
                            Iterator var8 = var7.iterator();

                            while(var8.hasNext()) {
                                ReteUnit var9 = (ReteUnit)var8.next();
                                Rete var10 = var9.getRete();
                                if (var10 != null) {
                                    this.rebuildReteLine(var10);
                                }
                            }
                        } else {
                            Rete var6 = var5.getRete();
                            if (var6 != null) {
                                this.rebuildReteLine(var6);
                            }
                        }
                    }
                }
            }

        }
    }

    private void rebuildReteLine(Rete var1) {
        List var2 = var1.getObjectTypeNodes();
        Iterator var3 = var2.iterator();

        while(var3.hasNext()) {
            ObjectTypeNode var4 = (ObjectTypeNode)var3.next();
            List var5 = var4.getLines();
            Iterator var6 = var5.iterator();

            while(var6.hasNext()) {
                Line var7 = (Line)var6.next();
                var7.setFrom(var4);
            }

            this.rebuildLine(var5, this.allNodes);
        }

    }

    private void rebuildLine(List<Line> var1, List<ReteNode> var2) {
        if (var1 != null) {
            Iterator var3 = var1.iterator();

            while(var3.hasNext()) {
                Line var4 = (Line)var3.next();
                int var5;
                ReteNode var6;
                BaseReteNode var7;
                if (var4.getFrom() == null) {
                    var5 = var4.getFromNodeId();
                    var6 = this.findTargetNode(var2, var5);
                    var4.setFrom(var6);
                    if (var6 instanceof BaseReteNode) {
                        var7 = (BaseReteNode)var6;
                        this.rebuildLine(var7.getLines(), var2);
                    }
                }

                if (var4.getTo() == null) {
                    var5 = var4.getToNodeId();
                    var6 = this.findTargetNode(var2, var5);
                    var4.setTo(var6);
                    if (var6 instanceof BaseReteNode) {
                        var7 = (BaseReteNode)var6;
                        this.rebuildLine(var7.getLines(), var2);
                    }
                }
            }

        }
    }

    private ReteNode findTargetNode(List<ReteNode> var1, int var2) {
        Iterator var3 = var1.iterator();

        ReteNode var4;
        do {
            if (!var3.hasNext()) {
                throw new RuleException("Node[" + var2 + "] not exist.");
            }

            var4 = (ReteNode)var3.next();
        } while(var4.getId() != var2);

        return var4;
    }

    public List<ReteNode> getAllNodes() {
        return this.allNodes;
    }

    public KnowledgePackage getKnowledgePackage() {
        return this.knowledgePackage;
    }

    public String getId() {
        return this.id;
    }
}
