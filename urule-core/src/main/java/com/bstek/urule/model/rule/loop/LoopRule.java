//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.loop;

import com.bstek.urule.action.Action;
import com.bstek.urule.model.GeneralEntity;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.runtime.FactManager;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.KnowledgeSessionFactory;
import com.bstek.urule.runtime.rete.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class LoopRule extends Rule {
    private LoopStart loopStart;
    private LoopEnd loopEnd;
    private LoopTargetType loopTargetType;
    private LoopTarget loopTarget;
    private List<LoopRuleUnit> units;
    private KnowledgePackageWrapper knowledgePackageWrapper;
    private Logger log;

    public LoopRule() {
        this.loopTargetType = LoopTargetType.list;
        this.log = Logger.getGlobal();
        this.setLoopRule(true);
    }

    public void execute(Context var1, Map<String, Object> var2) {
        Object var3 = this.buildLoopTarget(var1, var2);
        if (var3 == null) {
            this.log.warning("Loop rule [" + this.getName() + "] target value is null,cannot be executed.");
        } else {
            KnowledgeSession var4 = (KnowledgeSession)var1.getWorkingMemory();
            Map var5 = var4.getParameters();
            if (this.loopStart != null) {
                this.doActions(this.loopStart.getActions(), var1, var2, true);
            }

            boolean var6 = true;
            KnowledgeSession var7 = KnowledgeSessionFactory.newKnowledgeSession(this.knowledgePackageWrapper, var1, var4);
            List var8 = var4.getFactList();
            if (var3 instanceof Collection) {
                Collection var9 = (Collection)var3;

                for(Iterator var10 = var9.iterator(); var10.hasNext(); var6 = false) {
                    Object var11 = var10.next();
                    var5 = this.doLoop(var7, var5, var11, var8, var6);
                    if (this.breakLoop(var5)) {
                        break;
                    }
                }
            } else if (var3 instanceof Object[]) {
                Object[] var14 = (Object[])((Object[])var3);
                Object[] var15 = var14;
                int var16 = var14.length;

                for(int var12 = 0; var12 < var16; ++var12) {
                    Object var13 = var15[var12];
                    var5 = this.doLoop(var7, var5, var13, var8, var6);
                    if (this.breakLoop(var5)) {
                        break;
                    }

                    var6 = false;
                }
            }

            var4.getParameters().putAll(var5);
            if (this.loopEnd != null) {
                this.doActions(this.loopEnd.getActions(), var1, var2, false);
            }

        }
    }

    private Map<String, Object> doLoop(KnowledgeSession var1, Map<String, Object> var2, Object var3, List<Object> var4, boolean var5) {
        FactManager var6 = var1.getFactManager();
        String var7 = this.getClass(var3);
        if (var5) {
            var6.clean();
        }

        Iterator var8 = var4.iterator();

        while(var8.hasNext()) {
            Object var9 = var8.next();
            String var10 = this.getClass(var9);
            if (!var10.equals(HashMap.class.getName()) && !var10.equals(var7)) {
                var6.insertLoopFact(var9);
            }
        }

        var6.insertLoopFact(var3);
        var1.fireRules(var2);
        Map var12 = var1.getParameters();
        HashMap var11 = new HashMap();
        var11.putAll(var12);
        return var11;
    }

    private boolean breakLoop(Map<String, Object> var1) {
        boolean var2 = false;
        if (var1.containsKey("_loop_rule_break_tag__")) {
            var1.remove("_loop_rule_break_tag__");
            var2 = true;
        }

        return var2;
    }

    private void doActions(List<Action> var1, Context var2, Map<String, Object> var3, boolean var4) {
        if (var1 != null && var1.size() != 0) {
            if (var4) {
                var2.addTipMsg("执行【" + this.getName() + "】开始前动作");
                var2.getLogger().logMessage("==执行循环规则规则【" + this.getName() + "】的开始前动作==");
            } else {
                var2.addTipMsg("执行【" + this.getName() + "】结束后动作");
                var2.getLogger().logMessage("==执行循环规则规则【" + this.getName() + "】的结束后动作==");
            }

            Action var6;
            for(Iterator var5 = var1.iterator(); var5.hasNext(); var6.execute(var2, var3)) {
                var6 = (Action)var5.next();
                if (this.getDebug() != null) {
                    var6.setDebug(this.getDebug());
                }
            }

            var2.cleanTipMsg();
        }
    }

    private Object buildLoopTarget(Context var1, Map<String, Object> var2) {
        Object var3 = var1.getValueCompute().complexValueCompute(this.loopTarget.getValue(), var1, var2);
        if (this.loopTargetType.equals(LoopTargetType.list)) {
            if (var3 instanceof Collection) {
                ArrayList var11 = new ArrayList();
                Collection var12 = (Collection)var3;
                var11.addAll(var12);
                return var11;
            } else if (!(var3 instanceof Object[])) {
                throw new RuntimeException("循环对象必须是一个Collection类型的集合对象或一个数组对象，当前对象为：" + var3 + ".");
            } else {
                return var3;
            }
        } else {
            KnowledgeSession var4 = (KnowledgeSession)var1.getWorkingMemory();
            List var5 = var4.getFactList();
            String var6 = this.getClass(var3);
            ArrayList var7 = new ArrayList();
            Iterator var8 = var5.iterator();

            while(var8.hasNext()) {
                Object var9 = var8.next();
                String var10 = this.getClass(var9);
                if (var10.equals(var6)) {
                    var7.add(var9);
                }
            }

            return var7;
        }
    }

    private String getClass(Object var1) {
        String var2 = null;
        if (var1 instanceof GeneralEntity) {
            var2 = ((GeneralEntity)var1).getTargetClass();
        } else {
            var2 = var1.getClass().getName();
        }

        return var2;
    }

    public List<LoopRuleUnit> getUnits() {
        return this.units;
    }

    public void setUnits(List<LoopRuleUnit> var1) {
        this.units = var1;
    }

    public LoopStart getLoopStart() {
        return this.loopStart;
    }

    public void setLoopStart(LoopStart var1) {
        this.loopStart = var1;
    }

    public LoopEnd getLoopEnd() {
        return this.loopEnd;
    }

    public void setLoopEnd(LoopEnd var1) {
        this.loopEnd = var1;
    }

    public LoopTargetType getLoopTargetType() {
        return this.loopTargetType;
    }

    public void setLoopTargetType(LoopTargetType var1) {
        this.loopTargetType = var1;
    }

    public LoopTarget getLoopTarget() {
        return this.loopTarget;
    }

    public void setLoopTarget(LoopTarget var1) {
        this.loopTarget = var1;
    }

    public KnowledgePackageWrapper getKnowledgePackageWrapper() {
        return this.knowledgePackageWrapper;
    }

    public void setKnowledgePackageWrapper(KnowledgePackageWrapper var1) {
        this.knowledgePackageWrapper = var1;
    }
}
