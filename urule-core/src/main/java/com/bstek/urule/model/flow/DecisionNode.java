//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.Utils;
import com.bstek.urule.action.VariableAssignAction;
import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.RuleSet;
import com.bstek.urule.model.rule.SimpleValue;
import com.bstek.urule.model.rule.lhs.LeftType;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.percent.PercentDataStore;
import com.bstek.urule.runtime.percent.PercentUnit;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class DecisionNode extends BindingNode {
    private final Logger log = Logger.getLogger(DecisionNode.class.getName());
    private List<DecisionItem> items;
    private FlowNodeType type;
    private DecisionType decisionType;
    private PercentScope percentScope;

    public DecisionNode() {
        this.type = FlowNodeType.Decision;
        this.decisionType = DecisionType.Criteria;
    }

    public DecisionNode(String var1) {
        super(var1);
        this.type = FlowNodeType.Decision;
        this.decisionType = DecisionType.Criteria;
    }

    public void enterNode(FlowContext var1, FlowInstance var2) {
        var2.setCurrentNode(this);
        if (this.decisionType.equals(DecisionType.Criteria)) {
            this.doCriteria(var1, var2);
        } else {
            this.doPercent(var1, var2);
        }

        this.executeNodeEvent(EventType.enter, var1, var2);
    }

    private void doPercent(FlowContext var1, FlowInstance var2) {
        if (this.percentScope != null && !this.percentScope.equals(PercentScope.batch)) {
            this.doGlobalScope(var1, var2);
        } else {
            this.doBatchScope(var1, var2);
        }

    }

    private void doBatchScope(FlowContext var1, FlowInstance var2) {
        String var3 = var2.getProcessDefinition().getId() + "_" + this.name;
        long var4 = this.getAmount(var3, var1) + 1L;
        ArrayList var6 = new ArrayList();
        Iterator var7 = this.items.iterator();

        while(var7.hasNext()) {
            DecisionItem var8 = (DecisionItem)var7.next();
            PercentItem var9 = new PercentItem();
            var9.setName(var8.getTo());
            var9.setPercent((long)var8.getPercent());
            String var10 = var3 + "." + var8.getTo();
            long var11 = this.getAmount(var10, var1);
            var9.setTotal(var11);
            var6.add(var9);
        }

        PercentItem var13 = this.computePercent(var6, var4);
        this.setAmount(var3, var4, var1);
        this.setAmount(var3 + "." + var13.getName(), var13.getTotal() + 1L, var1);
        this.executeNodeEvent(EventType.leave, var1, var2);
        this.leave(var13.getName(), var1, var2);
    }

    private void doGlobalScope(FlowContext var1, FlowInstance var2) {
        PercentDataStore var3 = (PercentDataStore)Utils.getApplicationContext().getBean("urule.percentDataStore");
        PercentUnit var4 = var3.getDecisionNodePercent(var2.getProcessDefinition(), this.name);
        ArrayList var5 = new ArrayList();
        Iterator var6 = this.items.iterator();

        PercentItem var8;
        while(var6.hasNext()) {
            DecisionItem var7 = (DecisionItem)var6.next();
            var8 = new PercentItem();
            var8.setName(var7.getTo());
            var8.setPercent((long)var7.getPercent());
            long var9 = var4.getBranch(var7).getTotal();
            var8.setTotal(var9);
            var8.setItem(var7);
            var5.add(var8);
        }

        long var11 = var4.getTotal() + 1L;
        var8 = this.computePercent(var5, var11);
        var4.setTotal(var11);
        Branch var12 = var4.getBranch(var8.getItem());
        var12.setTotal(var8.getTotal() + 1L);
        this.executeNodeEvent(EventType.leave, var1, var2);
        this.leave(var8.getName(), var1, var2);
    }

    private long getAmount(String var1, FlowContext var2) {
        Object var3 = var2.getWorkingMemory().getSessionValue(var1);
        return var3 == null ? 0L : (Long)var3;
    }

    private void setAmount(String var1, long var2, FlowContext var4) {
        var4.getWorkingMemory().setSessionValue(var1, var2);
    }

    private void doCriteria(FlowContext var1, FlowInstance var2) {
        KnowledgeSession var3 = this.executeKnowledgePackage(var1, var2);
        this.executeNodeEvent(EventType.leave, var1, var2);
        Object var4 = var3.getParameter("return_to__");
        String var5 = var2.getProcessDefinition().getFile();
        if (var4 == null) {
            var1.getLogger().logDecisionNodeMatch(this, var5, (String)null);
            this.log.info("Decision node [" + this.getName() + "] no matching conditions.");
        } else {
            var1.getLogger().logDecisionNodeMatch(this, var5, var4.toString());
            var3.getParameters().remove("return_to__");
            this.leave(var4.toString(), var1, var2);
        }
    }

    private PercentItem computePercent(List<PercentItem> var1, long var2) {
        BigDecimal var4 = new BigDecimal(var2);
        Iterator var5 = var1.iterator();

        PercentItem var6;
        int var12;
        do {
            if (!var5.hasNext()) {
                return (PercentItem)var1.get(0);
            }

            var6 = (PercentItem)var5.next();
            long var7 = var6.getTotal();
            BigDecimal var9 = new BigDecimal(var7);
            BigDecimal var10 = var9.divide(var4, 20, 6);
            BigDecimal var11 = new BigDecimal(var6.getPercent());
            var11 = var11.divide(new BigDecimal(100), 2, 6);
            var12 = var10.compareTo(var11);
        } while(var12 != -1);

        return var6;
    }

    public FlowNodeType getType() {
        return this.type;
    }

    public List<DecisionItem> getItems() {
        return this.items;
    }

    public void setItems(List<DecisionItem> var1) {
        this.items = var1;
    }

    public RuleSet buildRuleSet(List<Library> var1, FlowDefinition var2) {
        RuleSet var3 = new RuleSet();
        var3.setLibraries(var1);
        ArrayList var4 = new ArrayList();
        var3.setRules(var4);
        int var5 = 0;
        Iterator var6 = this.items.iterator();

        while(var6.hasNext()) {
            DecisionItem var7 = (DecisionItem)var6.next();
            ++var5;
            if (var7.getConditionType() != null && !var7.getConditionType().equals("script")) {
                Rule var8 = new Rule();
                var4.add(var8);
                var8.setFile(var2.getFile());
                var8.setLhs(var7.getLhs());
                var8.setDebug(var2.isDebug());
                var8.setName("决策节点[" + this.getName() + "]-" + var5);
                Rhs var9 = new Rhs();
                var8.setRhs(var9);
                ArrayList var10 = new ArrayList();
                var9.setActions(var10);
                VariableAssignAction var11 = new VariableAssignAction();
                var10.add(var11);
                var11.setDatatype(Datatype.String);
                var11.setDebug(var2.isDebug());
                var11.setVariableCategory("parameter");
                var11.setVariableLabel("return_to__");
                var11.setVariableName("return_to__");
                var11.setType(LeftType.variable);
                SimpleValue var12 = new SimpleValue();
                var12.setContent(var7.getTo());
                var11.setValue(var12);
            }
        }

        return var3;
    }

    public String buildDSLScript(List<Library> var1, boolean var2, String var3) {
        StringBuffer var4 = new StringBuffer();
        if (var1 != null) {
            Iterator var5 = var1.iterator();

            while(var5.hasNext()) {
                Library var6 = (Library)var5.next();
                String var7 = var6.getPath();
                if (var6.getVersion() != null) {
                    var7 = var7 + ":" + var6.getVersion();
                }

                LibraryType var8 = var6.getType();
                switch(var8) {
                    case Action:
                        var4.append("importActionLibrary \"" + var7 + "\"");
                        var4.append("\r\n");
                        break;
                    case Constant:
                        var4.append("importConstantLibrary \"" + var7 + "\"");
                        var4.append("\r\n");
                        break;
                    case Parameter:
                        var4.append("importParameterLibrary \"" + var7 + "\"");
                        var4.append("\r\n");
                        break;
                    case Variable:
                        var4.append("importVariableLibrary \"" + var7 + "\"");
                        var4.append("\r\n");
                }
            }
        }

        boolean var9 = false;

        for(int var10 = 0; var10 < this.items.size(); ++var10) {
            DecisionItem var11 = (DecisionItem)this.items.get(var10);
            if (var11.getConditionType() == null || !var11.getConditionType().equals("config")) {
                var9 = true;
                var4.append(var11.buildDSLScript(var10, var2, var3, this.getName()));
                var4.append("\r\n");
            }
        }

        if (var9) {
            return var4.toString();
        } else {
            return null;
        }
    }

    public PercentScope getPercentScope() {
        return this.percentScope;
    }

    public void setPercentScope(PercentScope var1) {
        this.percentScope = var1;
    }

    public DecisionType getDecisionType() {
        return this.decisionType;
    }

    public void setDecisionType(DecisionType var1) {
        this.decisionType = var1;
    }
}
