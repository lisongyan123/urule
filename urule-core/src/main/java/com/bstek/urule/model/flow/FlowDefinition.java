//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.builder.KnowledgeBase;
import com.bstek.urule.builder.KnowledgeBuilder;
import com.bstek.urule.builder.ResourceBase;
import com.bstek.urule.dsl.DSLRuleSetBuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;
import com.bstek.urule.model.flow.ins.ProcessInstance;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.RuleSet;
import com.bstek.urule.model.rule.lhs.Lhs;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgePackageImpl;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import com.bstek.urule.runtime.response.ExecutionResponseImpl;
import com.bstek.urule.runtime.service.KnowledgePackageService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

public class FlowDefinition implements ProcessDefinition {
    public static final String FLOW_SUFFIX = ".rl.xml";
    private String id;
    private boolean debug;
    private String file;
    @JsonIgnore
    private String quickTestData;
    @JsonIgnore
    private StartNode startNode;
    @JsonIgnore
    private List<Library> libraries;
    @JsonIgnore
    private List<ResourceLibrary> relationResourceLibraries = new ArrayList();
    @JsonDeserialize(
            using = FlowNodeJsonDeserializer.class
    )
    private List<FlowNode> nodes;

    public FlowDefinition() {
    }

    public ProcessInstance newInstance(FlowContext var1) {
        long var2 = System.currentTimeMillis();
        ExecutionResponseImpl var4 = (ExecutionResponseImpl)var1.getResponse();
        var4.setFlowId(this.id);
        if (this.startNode == null) {
            throw new RuleException("StartNode must be define.");
        } else {
            FlowInstance var5 = new FlowInstance(this, this.debug);
            this.startNode.enter(var1, var5);
            var4.setDuration(System.currentTimeMillis() - var2);
            return var5;
        }
    }

    public void buildConnectionToNode() {
        Iterator var1 = this.nodes.iterator();

        while(true) {
            List var3;
            do {
                do {
                    if (!var1.hasNext()) {
                        return;
                    }

                    FlowNode var2 = (FlowNode)var1.next();
                    var3 = var2.getConnections();
                } while(var3 == null);
            } while(var3.size() == 0);

            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
                Connection var5 = (Connection)var4.next();
                String var6 = var5.getToName();
                var5.setTo(this.getFlowNode(var6));
            }
        }
    }

    public void initForActiveVersion() {
        if (this.nodes != null) {
            Iterator var1 = this.nodes.iterator();

            while(var1.hasNext()) {
                FlowNode var2 = (FlowNode)var1.next();
                if (var2 instanceof BindingNode) {
                    BindingNode var3 = (BindingNode)var2;
                    KnowledgePackageImpl var4 = (KnowledgePackageImpl)var3.getKnowledgePackageWrapper().getKnowledgePackage();
                    var4.initForActiveVersion();
                }
            }

        }
    }

    private FlowNode getFlowNode(String var1) {
        Iterator var2 = this.nodes.iterator();

        FlowNode var3;
        do {
            if (!var2.hasNext()) {
                throw new RuleException("Flow node [" + var1 + "] not found.");
            }

            var3 = (FlowNode)var2.next();
        } while(!var3.getName().equals(var1));

        return var3;
    }

    public FlowDefinition newFlowDefinitionForSerialize(KnowledgeBuilder var1, KnowledgePackageService var2, DSLRuleSetBuilder var3) throws IOException {
        this.initNodeKnowledgePackage(var1, var2, var3);
        FlowDefinition var4 = new FlowDefinition();
        var4.setLibraries(this.libraries);
        var4.setId(this.id);
        var4.setFile(this.file);
        var4.setDebug(this.debug);
        var4.setNodes(this.nodes);
        Iterator var5 = this.nodes.iterator();

        while(var5.hasNext()) {
            FlowNode var6 = (FlowNode)var5.next();
            var6.setX((String)null);
            var6.setY((String)null);
            var6.setWidth((String)null);
            var6.setHeight((String)null);
            if (var6 instanceof DecisionNode) {
                DecisionNode var10 = (DecisionNode)var6;
                Iterator var8 = var10.getItems().iterator();

                while(var8.hasNext()) {
                    DecisionItem var9 = (DecisionItem)var8.next();
                    var9.setLhs((Lhs)null);
                    var9.setLhsXml((String)null);
                    var9.setScript((String)null);
                }
            } else if (var6 instanceof ScriptNode) {
                ScriptNode var7 = (ScriptNode)var6;
                var7.setScript((String)null);
                var7.setActionType((ActionType)null);
                var7.setActionXml((String)null);
                var7.setActionsData((List)null);
            }

            if (var6 instanceof StartNode) {
                var4.setStartNode((StartNode)var6);
            }
        }

        var4.setRelationResourceLibraries(this.relationResourceLibraries);
        return var4;
    }

    private void initNodeKnowledgePackage(KnowledgeBuilder var1, KnowledgePackageService var2, DSLRuleSetBuilder var3) throws IOException {
        Iterator var4 = this.nodes.iterator();

        while(true) {
            while(var4.hasNext()) {
                FlowNode var5 = (FlowNode)var4.next();
                KnowledgeBase var18;
                if (var5 instanceof RuleNode) {
                    ResourceBase var15 = var1.newResourceBase();
                    RuleNode var20 = (RuleNode)var5;
                    var15.addResource(var20.getFile(), var20.getVersion());
                    var18 = var1.buildKnowledgeBase(var15);
                    KnowledgePackage var24 = var18.getKnowledgePackage();
                    if (var18.getResourceLibrary() != null) {
                        this.relationResourceLibraries.add(var18.getResourceLibrary());
                    }

                    var20.setKnowledgePackageWrapper(new KnowledgePackageWrapper(var24));
                } else {
                    KnowledgeBase var23;
                    if (var5 instanceof RulePackageNode) {
                        RulePackageNode var14 = (RulePackageNode)var5;
                        String var19 = var14.getProject() + "/" + var14.getPackageId();
                        KnowledgePackage var21 = var2.buildKnowledgePackage(var19);
                        var23 = var2.buildKnowledgeBase(var19);
                        if (var23.getResourceLibrary() != null) {
                            this.relationResourceLibraries.add(var23.getResourceLibrary());
                        }

                        var14.setKnowledgePackageWrapper(new KnowledgePackageWrapper(var21));
                    } else {
                        RuleSet var16;
                        String var17;
                        if (var5 instanceof DecisionNode) {
                            DecisionNode var13 = (DecisionNode)var5;
                            if (var13.getDecisionType().equals(DecisionType.Criteria)) {
                                var16 = var13.buildRuleSet(this.libraries, this);
                                var17 = var13.buildDSLScript(this.libraries, this.debug, this.id);
                                if (var17 != null) {
                                    RuleSet var22 = var3.build(var17, "规则流[" + this.id + "]中决策节点[" + var5.getName() + "]");
                                    var16.getRules().addAll(var22.getRules());
                                }

                                var23 = var1.buildKnowledgeBase(var16);
                                var13.setKnowledgePackageWrapper(new KnowledgePackageWrapper(var23.getKnowledgePackage()));
                            }
                        } else {
                            Iterator var7;
                            if (var5 instanceof ScriptNode) {
                                ScriptNode var12 = (ScriptNode)var5;
                                var7 = null;
                                if (var12.getActionType().equals(ActionType.script)) {
                                    var17 = var12.buildDSLScript(this.libraries);
                                    var16 = var3.build(var17, this.file);
                                } else {
                                    var16 = var12.buildRuleSet(this.libraries, this);
                                }

                                var18 = var1.buildKnowledgeBase(var16);
                                var12.setKnowledgePackageWrapper(new KnowledgePackageWrapper(var18.getKnowledgePackage()));
                            } else if (var5 instanceof ForkNode) {
                                List var6 = var5.getConnections();
                                var7 = var6.iterator();

                                while(var7.hasNext()) {
                                    Connection var8 = (Connection)var7.next();
                                    String var9 = var8.getScript();
                                    if (var9 != null) {
                                        var9 = var8.buildDSLScript(this.libraries);
                                        RuleSet var10 = var3.build(var9, "规则流[" + this.id + "]中分支节点[" + var5.getName() + "]");
                                        KnowledgeBase var11 = var1.buildKnowledgeBase(var10);
                                        var8.setKnowledgePackageWrapper(new KnowledgePackageWrapper(var11.getKnowledgePackage()));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return;
        }
    }

    public List<ResourceLibrary> getRelationResourceLibraries() {
        return this.relationResourceLibraries;
    }

    public void setRelationResourceLibraries(List<ResourceLibrary> var1) {
        this.relationResourceLibraries = var1;
    }

    public void addLibrary(Library var1) {
        if (this.libraries == null) {
            this.libraries = new ArrayList();
        }

        this.libraries.add(var1);
    }

    public List<Library> getLibraries() {
        return this.libraries;
    }

    public void setLibraries(List<Library> var1) {
        this.libraries = var1;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String var1) {
        this.id = var1;
    }

    public boolean isDebug() {
        return this.debug;
    }

    public void setDebug(boolean var1) {
        this.debug = var1;
    }

    public String getFile() {
        return this.file;
    }

    public void setFile(String var1) {
        this.file = var1;
    }

    public String getQuickTestData() {
        return this.quickTestData;
    }

    public void setQuickTestData(String var1) {
        this.quickTestData = var1;
    }

    public List<FlowNode> getNodes() {
        return this.nodes;
    }

    public void setNodes(List<FlowNode> var1) {
        if (this.startNode == null) {
            Iterator var2 = var1.iterator();

            while(var2.hasNext()) {
                FlowNode var3 = (FlowNode)var2.next();
                if (var3 instanceof StartNode) {
                    this.setStartNode((StartNode)var3);
                    break;
                }
            }
        }

        this.nodes = var1;
    }

    public StartNode getStartNode() {
        return this.startNode;
    }

    public void setStartNode(StartNode var1) {
        this.startNode = var1;
    }
}
