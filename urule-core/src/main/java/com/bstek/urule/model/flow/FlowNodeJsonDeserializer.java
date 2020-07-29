//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.runtime.KnowledgePackageWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;

public class FlowNodeJsonDeserializer extends JsonDeserializer<List<FlowNode>> {
    public FlowNodeJsonDeserializer() {
    }

    public List<FlowNode> deserialize(JsonParser var1, DeserializationContext var2) throws IOException, JsonProcessingException {
        ObjectMapper var3 = (ObjectMapper)var1.getCodec();
        JsonNode var4 = var3.readTree(var1);
        ArrayList var5 = new ArrayList();
        Iterator var6 = var4.getElements();

        while(true) {
            JsonNode var7;
            JsonNode var8;
            do {
                if (!var6.hasNext()) {
                    return var5;
                }

                var7 = (JsonNode)var6.next();
                var8 = var7.get("type");
            } while(var8 == null);

            Object var9 = null;
            FlowNodeType var10 = FlowNodeType.valueOf(var8.getTextValue());
            JsonNode var12;
            JsonNode var14;
            switch(var10) {
                case Action:
                    ActionNode var11 = new ActionNode();
                    var12 = var7.get("actionBean");
                    if (var12 != null) {
                        var11.setActionBean(var12.getTextValue());
                    }

                    var9 = var11;
                    break;
                case Script:
                    ScriptNode var13 = new ScriptNode();
                    var14 = var7.get("script");
                    if (var14 != null) {
                        var13.setScript(var14.getTextValue());
                    }

                    var9 = var13;
                    break;
                case Decision:
                    DecisionNode var15 = new DecisionNode();
                    DecisionType var16 = DecisionType.valueOf(var7.get("decisionType").getTextValue());
                    var15.setDecisionType(var16);
                    JsonNode var17 = var7.get("items");
                    Iterator var18 = var17.getElements();
                    ArrayList var19 = new ArrayList();

                    DecisionItem var24;
                    for(; var18.hasNext(); var19.add(var24)) {
                        JsonNode var34 = (JsonNode)var18.next();
                        var24 = new DecisionItem();
                        var24.setTo(var34.get("to").getTextValue());
                        if (!var16.equals(DecisionType.Criteria)) {
                            var24.setPercent(var34.get("percent").getIntValue());
                        }
                    }

                    var15.setItems(var19);
                    var9 = var15;
                    break;
                case End:
                    var9 = new EndNode();
                    break;
                case Fork:
                    ForkNode var20 = new ForkNode();
                    if (var7.get("multipleThread") != null) {
                        var20.setMultipleThread(var7.get("multipleThread").getTextValue());
                    }

                    var9 = var20;
                    break;
                case Join:
                    var9 = new JoinNode();
                    break;
                case Rule:
                    RuleNode var21 = new RuleNode();
                    var21.setFile(var7.get("file").getTextValue());
                    JsonNode var22 = var7.get("version");
                    if (var22 != null) {
                        var21.setVersion(var22.getTextValue());
                    }

                    var9 = var21;
                    break;
                case RulePackage:
                    RulePackageNode var23 = new RulePackageNode();
                    var23.setPackageId(var7.get("packageId").getTextValue());
                    if (var7.get("project") != null) {
                        var23.setPackageId(var7.get("project").getTextValue());
                    }

                    var9 = var23;
                    break;
                case Start:
                    var9 = new StartNode();
            }

            String var25 = var7.get("name").getTextValue();
            ((FlowNode)var9).setName(var25);
            var12 = var7.get("eventBean");
            if (var12 != null) {
                ((FlowNode)var9).setEventBean(var12.getTextValue());
            }

            JsonNode var26 = var7.get("connections");
            if (var26 != null) {
                ArrayList var27 = new ArrayList();
                Iterator var28 = var26.getElements();

                Connection var33;
                while(var28.hasNext()) {
                    JsonNode var30 = (JsonNode)var28.next();
                    var33 = (Connection)var3.readValue(var30, Connection.class);
                    var27.add(var33);
                }

                Iterator var31 = var27.iterator();

                while(var31.hasNext()) {
                    var33 = (Connection)var31.next();
                    var33.buildDeserialize();
                }

                ((FlowNode)var9).setConnections(var27);
            }

            var14 = var7.get("knowledgePackageWrapper");
            if (var14 != null) {
                KnowledgePackageWrapper var29 = (KnowledgePackageWrapper)var3.readValue(var14, KnowledgePackageWrapper.class);
                var29.buildDeserialize();
                BindingNode var32 = (BindingNode)var9;
                var32.setKnowledgePackageWrapper(var29);
            }

            var5.add(var9);
        }
    }
}
