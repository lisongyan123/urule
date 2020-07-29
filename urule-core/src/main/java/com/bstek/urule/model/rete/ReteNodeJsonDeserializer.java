//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.model.AbstractJsonDeserializer;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.lhs.AccumulateLeftPart;
import com.bstek.urule.model.rule.lhs.And;
import com.bstek.urule.model.rule.lhs.CalculateItem;
import com.bstek.urule.model.rule.lhs.CalculateType;
import com.bstek.urule.model.rule.lhs.CommonFunctionLeftPart;
import com.bstek.urule.model.rule.lhs.ConditionItem;
import com.bstek.urule.model.rule.lhs.Criteria;
import com.bstek.urule.model.rule.lhs.FunctionLeftPart;
import com.bstek.urule.model.rule.lhs.Junction;
import com.bstek.urule.model.rule.lhs.Left;
import com.bstek.urule.model.rule.lhs.LeftType;
import com.bstek.urule.model.rule.lhs.MethodLeftPart;
import com.bstek.urule.model.rule.lhs.Or;
import com.bstek.urule.model.rule.lhs.VariableLeftPart;
import com.bstek.urule.model.rule.loop.LoopTarget;
import com.bstek.urule.model.rule.loop.LoopTargetType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.node.ArrayNode;

public class ReteNodeJsonDeserializer extends AbstractJsonDeserializer<List<ReteNode>> {
    public ReteNodeJsonDeserializer() {
    }

    public List<ReteNode> deserialize(JsonParser var1, DeserializationContext var2) throws IOException, JsonProcessingException {
        ObjectCodec var3 = var1.getCodec();
        JsonNode var4 = var3.readTree(var1);
        ArrayList var5 = new ArrayList();
        Iterator var6 = var4.getElements();

        while(var6.hasNext()) {
            JsonNode var7 = (JsonNode)var6.next();
            int var8 = var7.get("id").getIntValue();
            JsonNode var9 = var7.get("nodeType");
            if (var9 != null) {
                String var10 = var9.getTextValue();
                NodeType var11 = NodeType.valueOf(var10);
                ReteNode var12 = NodeType.newReteNodeInstance(var11);
                if (var12 instanceof ObjectTypeNode) {
                    ObjectTypeNode var13 = (ObjectTypeNode)var12;
                    var13.setObjectTypeClass(var7.get("objectTypeClass").getTextValue());
                    var13.setId(var8);
                } else if (var12 instanceof AndNode) {
                    AndNode var16 = (AndNode)var12;
                    var16.setId(var8);
                    var16.setToLineCount(var7.get("toLineCount").getIntValue());
                    var16.setLines(this.parseLines(var7));
                } else if (var12 instanceof OrNode) {
                    OrNode var17 = (OrNode)var12;
                    var17.setId(var8);
                    var17.setLines(this.parseLines(var7));
                } else if (var12 instanceof CriteriaNode) {
                    CriteriaNode var18 = (CriteriaNode)var12;
                    var18.setId(var8);
                    JsonNode var14 = var7.get("debug");
                    if (var14 != null) {
                        var18.setDebug(var14.asBoolean());
                    }

                    JsonNode var15 = var7.get("criteria");
                    var18.setCriteria(this.parseCriteria(var15));
                    var18.setLines(this.parseLines(var7));
                } else if (var12 instanceof TerminalNode) {
                    TerminalNode var19 = (TerminalNode)var12;
                    var19.setId(var8);
                    var19.setRule(this.parseRule(var1, var7));
                }

                var5.add(var12);
            }
        }

        return var5;
    }

    private List<Line> parseLines(JsonNode var1) {
        JsonNode var2 = var1.get("lines");
        if (var2 == null) {
            return null;
        } else {
            ArrayList var3 = new ArrayList();
            Iterator var4 = var2.iterator();

            while(var4.hasNext()) {
                JsonNode var5 = (JsonNode)var4.next();
                Line var6 = new Line();
                var6.setFromNodeId(var5.get("fromNodeId").getIntValue());
                var6.setToNodeId(var5.get("toNodeId").getIntValue());
                var3.add(var6);
            }

            return var3;
        }
    }

    private Criteria parseCriteria(JsonNode var1) {
        Criteria var2 = new Criteria();
        JsonNode var3 = var1.get("op");
        String var4;
        if (var3 != null && var3.getTextValue() != null) {
            var4 = var3.getTextValue();
            Op var5 = Op.valueOf(var4);
            var2.setOp(var5);
        }

        if (var1.has("file") && var1.get("file") != null) {
            var4 = var1.get("file").getTextValue();
            var2.setFile(var4);
        }

        JsonNode var24 = var1.get("necessaryClassList");
        String var7;
        if (var24 != null && var24 instanceof ArrayNode) {
            ArrayNode var25 = (ArrayNode)var24;
            Iterator var6 = var25.getElements();

            while(var6.hasNext()) {
                var7 = ((JsonNode)var6.next()).asText();
                var2.addNecessaryClass(var7);
            }
        }

        JsonNode var26 = var1.get("left");
        Left var27 = new Left();
        var2.setLeft(var27);
        var7 = JsonUtils.getJsonValue(var26, "type");
        JsonNode var8 = var26.get("leftPart");
        var27.setType(LeftType.valueOf(var7));
        switch(var27.getType()) {
            case function:
                FunctionLeftPart var9 = new FunctionLeftPart();
                var9.setName(JsonUtils.getJsonValue(var8, "name"));
                var9.setParameters(JsonUtils.parseParameters(var8));
                var27.setLeftPart(var9);
                break;
            case method:
                MethodLeftPart var10 = new MethodLeftPart();
                var10.setBeanId(JsonUtils.getJsonValue(var8, "beanId"));
                var10.setBeanLabel(JsonUtils.getJsonValue(var8, "beanLabel"));
                var10.setMethodLabel(JsonUtils.getJsonValue(var8, "methodLabel"));
                var10.setMethodName(JsonUtils.getJsonValue(var8, "methodName"));
                var10.setParameters(JsonUtils.parseParameters(var8));
                var27.setLeftPart(var10);
                break;
            case commonfunction:
                CommonFunctionLeftPart var11 = new CommonFunctionLeftPart();
                var11.setLabel(JsonUtils.getJsonValue(var8, "label"));
                var11.setName(JsonUtils.getJsonValue(var8, "name"));
                var11.setParameter(JsonUtils.parseCommonFunctionParameter(var8));
                var27.setLeftPart(var11);
                break;
            case operatecollection:
                AccumulateLeftPart var12 = new AccumulateLeftPart();
                String var13 = JsonUtils.getJsonValue(var8, "loopTargetType");
                var12.setLoopTargetType(LoopTargetType.valueOf(var13));
                var12.setJunction(this.parseJunction(var8));
                JsonNode var14 = var8.get("loopTarget");
                LoopTarget var15 = new LoopTarget();
                var15.setValue(JsonUtils.parseValue(var14));
                var12.setLoopTarget(var15);
                JsonNode var16 = var8.get("calculateItems");
                if (var16 != null) {
                    ArrayList var17 = new ArrayList();

                    CalculateItem var20;
                    for(Iterator var18 = var16.iterator(); var18.hasNext(); var17.add(var20)) {
                        JsonNode var19 = (JsonNode)var18.next();
                        var20 = new CalculateItem();
                        var20.setType(CalculateType.valueOf(JsonUtils.getJsonValue(var19, "type")));
                        Value var21 = JsonUtils.parseValue(var19);
                        var20.setValue(var21);
                        boolean var22 = Boolean.valueOf(JsonUtils.getJsonValue(var19, "enableAssignment"));
                        var20.setEnableAssignment(var22);
                        if (var22) {
                            var20.setAssignDatatype(Datatype.valueOf(JsonUtils.getJsonValue(var19, "assignDatatype")));
                            var20.setAssignTargetType(JsonUtils.getJsonValue(var19, "assignTargetType"));
                            var20.setAssignVariable(JsonUtils.getJsonValue(var19, "assignVariable"));
                            var20.setAssignVariableCategory(JsonUtils.getJsonValue(var19, "assignVariableCategory"));
                            var20.setAssignVariableLabel(JsonUtils.getJsonValue(var19, "assignVariableLabel"));
                            var20.setKeyLabel(JsonUtils.getJsonValue(var19, "keyLabel"));
                            var20.setKeyName(JsonUtils.getJsonValue(var19, "keyName"));
                            var20.setType(CalculateType.valueOf(JsonUtils.getJsonValue(var19, "type")));
                        }
                    }

                    var12.setCalculateItems(var17);
                }

                JsonNode var29 = var8.get("conditionItems");
                if (var29 != null) {
                    ArrayList var30 = new ArrayList();
                    Iterator var32 = var29.iterator();

                    while(var32.hasNext()) {
                        JsonNode var34 = (JsonNode)var32.next();
                        ConditionItem var23 = new ConditionItem();
                        var23.setLeft(JsonUtils.getJsonValue(var34, "left"));
                        var23.setOp(Op.valueOf(JsonUtils.getJsonValue(var34, "op")));
                        var23.setValue(JsonUtils.parseValue(var34));
                        var30.add(var23);
                    }

                    var12.setConditionItems(var30);
                }

                var27.setLeftPart(var12);
                break;
            default:
                VariableLeftPart var31 = new VariableLeftPart();
                var31.setVariableCategory(JsonUtils.getJsonValue(var8, "variableCategory"));
                var31.setVariableLabel(JsonUtils.getJsonValue(var8, "variableLabel"));
                var31.setVariableName(JsonUtils.getJsonValue(var8, "variableName"));
                var31.setKeyLabel(JsonUtils.getJsonValue(var8, "keyLabel"));
                var31.setKeyName(JsonUtils.getJsonValue(var8, "keyName"));
                String var33 = JsonUtils.getJsonValue(var8, "datatype");
                if (StringUtils.isNotBlank(var33)) {
                    var31.setDatatype(Datatype.valueOf(var33));
                }

                var27.setLeftPart(var31);
        }

        var27.setArithmetic(JsonUtils.parseComplexArithmetic(var26));
        Value var28 = JsonUtils.parseValue(var1);
        if (var28 != null) {
            var2.setValue(var28);
        }

        return var2;
    }

    private Junction parseJunction(JsonNode var1) {
        JsonNode var2 = var1.get("junction");
        if (var2 == null) {
            return null;
        } else {
            String var3 = JsonUtils.getJsonValue(var2, "junctionType");
            Object var4 = null;
            if (var3.equals("and")) {
                var4 = new And();
            } else if (var3.equals("or")) {
                var4 = new Or();
            }

            JsonNode var5 = var2.get("criterions");
            if (var5 != null) {
                Iterator var6 = var5.iterator();
                ArrayList var7 = new ArrayList();

                while(var6.hasNext()) {
                    JsonNode var8 = (JsonNode)var6.next();
                    Criteria var9 = this.parseCriteria(var8);
                    var7.add(var9);
                }

                ((Junction)var4).setCriterions(var7);
            }

            return (Junction)var4;
        }
    }
}
