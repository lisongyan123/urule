//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model;

import com.bstek.urule.Configure;
import com.bstek.urule.Utils;
import com.bstek.urule.action.Action;
import com.bstek.urule.action.ActionType;
import com.bstek.urule.action.ConsolePrintAction;
import com.bstek.urule.action.ExecuteCommonFunctionAction;
import com.bstek.urule.action.ExecuteMethodAction;
import com.bstek.urule.action.ScoringAction;
import com.bstek.urule.action.VariableAssignAction;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.Other;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.lhs.LeftType;
import com.bstek.urule.model.rule.loop.LoopEnd;
import com.bstek.urule.model.rule.loop.LoopRule;
import com.bstek.urule.model.rule.loop.LoopStart;
import com.bstek.urule.model.rule.loop.LoopTarget;
import com.bstek.urule.model.rule.loop.LoopTargetType;
import com.bstek.urule.model.scorecard.AssignTargetType;
import com.bstek.urule.model.scorecard.ScoringType;
import com.bstek.urule.model.scorecard.runtime.ScoreRule;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;

public abstract class AbstractJsonDeserializer<T> extends JsonDeserializer<T> {
    public AbstractJsonDeserializer() {
    }

    protected Rule parseRule(JsonParser var1, JsonNode var2) {
        SimpleDateFormat var3 = new SimpleDateFormat(Configure.getDateFormat());

        try {
            JsonNode var4 = var2.get("rule");
            if (var4 == null) {
                var4 = var2;
            }

            Object var5 = null;
            String var6 = JsonUtils.getJsonValue(var4, "scoringType");
            String var20;
            if (StringUtils.isNotBlank(var6)) {
                ScoringType var7 = ScoringType.valueOf(var6);
                ScoreRule var8 = new ScoreRule();
                var8.setScoringType(var7);
                this.buildScoreRule(var1, var4, var8);
                var5 = var8;
            } else {
                var20 = JsonUtils.getJsonValue(var4, "loopRule");
                if (var20 != null) {
                    boolean var21 = Boolean.valueOf(var20);
                    if (var21) {
                        LoopRule var9 = new LoopRule();
                        this.buildLoopRule(var4, var9);
                        var5 = var9;
                    } else {
                        var5 = new Rule();
                    }
                } else {
                    var5 = new Rule();
                }
            }

            var20 = JsonUtils.getJsonValue(var4, "withElse");
            if (StringUtils.isNotBlank(var20)) {
                ((Rule)var5).setWithElse(Boolean.valueOf(var20));
            }

            ((Rule)var5).setMutexGroup(JsonUtils.getJsonValue(var4, "mutexGroup"));
            ((Rule)var5).setPendedGroup(JsonUtils.getJsonValue(var4, "pendedGroup"));
            String var22 = JsonUtils.getJsonValue(var4, "autoFocus");
            if (var22 != null) {
                ((Rule)var5).setAutoFocus(Boolean.valueOf(var22));
            }

            String var23 = JsonUtils.getJsonValue(var4, "loop");
            if (var23 != null) {
                ((Rule)var5).setLoop(Boolean.valueOf(var23));
            }

            String var10 = JsonUtils.getJsonValue(var4, "effectiveDate");
            if (var10 != null) {
                ((Rule)var5).setEffectiveDate(var3.parse(var10));
            }

            String var11 = JsonUtils.getJsonValue(var4, "enabled");
            if (var11 != null) {
                ((Rule)var5).setEnabled(Boolean.valueOf(var11));
            }

            String var12 = JsonUtils.getJsonValue(var4, "debug");
            if (var12 != null) {
                ((Rule)var5).setDebug(Boolean.valueOf(var12));
            }

            String var13 = JsonUtils.getJsonValue(var4, "expiresDate");
            if (var13 != null) {
                ((Rule)var5).setExpiresDate(var3.parse(var13));
            }

            ((Rule)var5).setName(JsonUtils.getJsonValue(var4, "name"));
            ((Rule)var5).setFile(JsonUtils.getJsonValue(var4, "file"));
            String var14 = JsonUtils.getJsonValue(var4, "salience");
            if (var14 != null) {
                ((Rule)var5).setSalience(Integer.valueOf(var14));
            }

            Rhs var15 = new Rhs();
            ((Rule)var5).setRhs(var15);
            JsonNode var16 = var4.get("rhs");
            if (var16 != null) {
                var15.setActions(this.parseActions(var16));
            }

            JsonNode var17 = var4.get("other");
            if (var17 != null) {
                Other var18 = new Other();
                ((Rule)var5).setOther(var18);
                var18.setActions(this.parseActions(var17));
            }

            if (((Rule)var5).isWithElse()) {
                Utils.buildElseRule((Rule)var5);
            }

            return (Rule)var5;
        } catch (ParseException var19) {
            throw new RuleException(var19);
        }
    }

    private void buildScoreRule(JsonParser var1, JsonNode var2, ScoreRule var3) {
        var3.setScoringBean(JsonUtils.getJsonValue(var2, "scoringBean"));
        AssignTargetType var4 = AssignTargetType.valueOf(JsonUtils.getJsonValue(var2, "assignTargetType"));
        var3.setAssignTargetType(var4);
        var3.setVariableCategory(JsonUtils.getJsonValue(var2, "variableCategory"));
        var3.setVariableName(JsonUtils.getJsonValue(var2, "variableName"));
        var3.setVariableLabel(JsonUtils.getJsonValue(var2, "variableLabel"));
        var3.setKeyLabel(JsonUtils.getJsonValue(var2, "keyLabel"));
        var3.setKeyName(JsonUtils.getJsonValue(var2, "keyName"));
        String var5 = JsonUtils.getJsonValue(var2, "datatype");
        if (StringUtils.isNotBlank(var5)) {
            var3.setDatatype(Datatype.valueOf(var5));
        }

        try {
            JsonNode var6 = var2.get("knowledgePackageWrapper");
            ObjectMapper var7 = (ObjectMapper)var1.getCodec();
            KnowledgePackageWrapper var8 = (KnowledgePackageWrapper)var7.readValue(var6, KnowledgePackageWrapper.class);
            var8.buildDeserialize();
            var3.setKnowledgePackageWrapper(var8);
        } catch (Exception var9) {
            throw new RuleException(var9);
        }
    }

    private void buildLoopRule(JsonNode var1, LoopRule var2) {
        JsonNode var3 = var1.get("loopTarget");
        String var4 = JsonUtils.getJsonValue(var1, "loopTargetType");
        if (StringUtils.isNotBlank(var4)) {
            var2.setLoopTargetType(LoopTargetType.valueOf(var4));
        }

        if (var3 != null) {
            LoopTarget var5 = new LoopTarget();
            Value var6 = JsonUtils.parseValue(var3);
            var5.setValue(var6);
            var2.setLoopTarget(var5);
        }

        JsonNode var9 = var1.get("loopStart");
        if (var9 != null) {
            List var10 = this.parseActions(var9);
            LoopStart var7 = new LoopStart();
            var7.setActions(var10);
            var2.setLoopStart(var7);
        }

        JsonNode var11 = var1.get("loopEnd");
        if (var11 != null) {
            List var12 = this.parseActions(var11);
            LoopEnd var8 = new LoopEnd();
            var8.setActions(var12);
            var2.setLoopEnd(var8);
        }

        JsonNode var13 = var1.get("knowledgePackageWrapper");
        if (var13 != null) {
            KnowledgePackageWrapper var14 = JsonUtils.parseKnowledgePackageWrapper(var13.toString());
            var2.setKnowledgePackageWrapper(var14);
        }

    }

    private List<Action> parseActions(JsonNode var1) {
        ArrayList var2 = new ArrayList();
        JsonNode var3 = var1.get("actions");
        if (var3 == null) {
            return var2;
        } else {
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
                JsonNode var5 = (JsonNode)var4.next();
                ActionType var6 = ActionType.valueOf(JsonUtils.getJsonValue(var5, "actionType"));
                switch(var6) {
                    case ConsolePrint:
                        ConsolePrintAction var7 = new ConsolePrintAction();
                        var7.setValue(JsonUtils.parseValue(var5));
                        var7.setPriority(Integer.valueOf(JsonUtils.getJsonValue(var5, "priority")));
                        var2.add(var7);
                        break;
                    case ExecuteMethod:
                        ExecuteMethodAction var8 = new ExecuteMethodAction();
                        var8.setBeanId(JsonUtils.getJsonValue(var5, "beanId"));
                        var8.setBeanLabel(JsonUtils.getJsonValue(var5, "beanLabel"));
                        var8.setMethodLabel(JsonUtils.getJsonValue(var5, "methodLabel"));
                        var8.setPriority(Integer.valueOf(JsonUtils.getJsonValue(var5, "priority")));
                        var8.setMethodName(JsonUtils.getJsonValue(var5, "methodName"));
                        var8.setParameters(JsonUtils.parseParameters(var5));
                        var8.setKnowledgePackage(JsonUtils.getJsonValue(var5, "knowledgePackage"));
                        var2.add(var8);
                        break;
                    case VariableAssign:
                        VariableAssignAction var9 = new VariableAssignAction();
                        String var10 = JsonUtils.getJsonValue(var5, "type");
                        if (var10 != null) {
                            var9.setType(LeftType.valueOf(var10));
                        }

                        var9.setDatatype(Datatype.valueOf(JsonUtils.getJsonValue(var5, "datatype")));
                        var9.setVariableCategory(JsonUtils.getJsonValue(var5, "variableCategory"));
                        var9.setVariableLabel(JsonUtils.getJsonValue(var5, "variableLabel"));
                        var9.setVariableName(JsonUtils.getJsonValue(var5, "variableName"));
                        var9.setKeyLabel(JsonUtils.getJsonValue(var5, "keyLabel"));
                        var9.setKeyName(JsonUtils.getJsonValue(var5, "keyName"));
                        var9.setPriority(Integer.valueOf(JsonUtils.getJsonValue(var5, "priority")));
                        var9.setValue(JsonUtils.parseValue(var5));
                        var2.add(var9);
                        break;
                    case ExecuteCommonFunction:
                        ExecuteCommonFunctionAction var11 = new ExecuteCommonFunctionAction();
                        var11.setLabel(JsonUtils.getJsonValue(var5, "label"));
                        var11.setName(JsonUtils.getJsonValue(var5, "name"));
                        var11.setParameter(JsonUtils.parseCommonFunctionParameter(var5));
                        var11.setPriority(Integer.valueOf(JsonUtils.getJsonValue(var5, "priority")));
                        var2.add(var11);
                        break;
                    case Scoring:
                        int var12 = Integer.valueOf(JsonUtils.getJsonValue(var5, "rowNumber"));
                        String var13 = JsonUtils.getJsonValue(var5, "name");
                        String var14 = JsonUtils.getJsonValue(var5, "weight");
                        ScoringAction var15 = new ScoringAction(var12, var13, var14);
                        var15.setValue(JsonUtils.parseValue(var5));
                        var2.add(var15);
                        break;
                    case TemplateAction:
                        throw new RuleException("Unsupport action type:" + ActionType.TemplateAction);
                }
            }

            return var2;
        }
    }
}
