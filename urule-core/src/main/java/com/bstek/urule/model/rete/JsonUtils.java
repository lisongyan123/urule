//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import com.bstek.urule.Configure;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rete.jsondeserializer.CommonFunctionValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.ConstantValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.InputValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.MathValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.MethodValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.ParameterValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.ParenValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.SingIValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.ValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.VariableCategoryValueDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.VariableValueDeserializer;
import com.bstek.urule.model.rule.ArithmeticType;
import com.bstek.urule.model.rule.ComplexArithmetic;
import com.bstek.urule.model.rule.Parameter;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtils {
    private static List<ValueDeserializer> valueDeserializers = new ArrayList();

    public JsonUtils() {
    }

    public static String getJsonValue(JsonNode var0, String var1) {
        return var0.get(var1) != null ? var0.get(var1).asText() : null;
    }

    public static ComplexArithmetic parseComplexArithmetic(JsonNode var0) {
        JsonNode var1 = var0.get("arithmetic");
        if (var1 == null) {
            return null;
        } else {
            ComplexArithmetic var2 = new ComplexArithmetic();
            var2.setType(ArithmeticType.valueOf(getJsonValue(var1, "type")));
            var2.setValue(parseValue(var1));
            return var2;
        }
    }

    public static List<Parameter> parseParameters(JsonNode var0) {
        JsonNode var1 = var0.get("parameters");
        if (var1 == null) {
            return null;
        } else {
            Iterator var2 = var1.iterator();
            ArrayList var3 = new ArrayList();

            while(var2.hasNext()) {
                JsonNode var4 = (JsonNode)var2.next();
                Parameter var5 = new Parameter();
                var5.setName(getJsonValue(var4, "name"));
                String var6 = getJsonValue(var4, "type");
                if (var6 != null) {
                    var5.setType(Datatype.valueOf(var6));
                }

                String var7 = getJsonValue(var4, "valueType");
                if (var7 != null) {
                    var5.setValue(parseValue(var4));
                }

                var5.setValue(parseValue(var4));
                var3.add(var5);
            }

            return var3;
        }
    }

    public static Value parseValueNode(JsonNode var0) {
        Value var1 = null;
        ValueType var2 = ValueType.valueOf(getJsonValue(var0, "valueType"));
        Iterator var3 = valueDeserializers.iterator();

        while(var3.hasNext()) {
            ValueDeserializer var4 = (ValueDeserializer)var3.next();
            if (var4.support(var2)) {
                var1 = var4.deserialize(var0);
                break;
            }
        }

        return var1;
    }

    public static KnowledgePackageWrapper parseKnowledgePackageWrapper(String var0) {
        try {
            ObjectMapper var1 = new ObjectMapper();
            SimpleDateFormat var2 = new SimpleDateFormat(Configure.getDateFormat());
            var1.getDeserializationConfig().withDateFormat(var2);
            var1.setDateFormat(var2);
            KnowledgePackageWrapper var3 = (KnowledgePackageWrapper)var1.readValue(var0, KnowledgePackageWrapper.class);
            var3.buildDeserialize();
            return var3;
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }

    public static CommonFunctionParameter parseCommonFunctionParameter(JsonNode var0) {
        JsonNode var1 = var0.get("parameter");
        if (var1 == null) {
            return null;
        } else {
            CommonFunctionParameter var2 = new CommonFunctionParameter();
            var2.setName(getJsonValue(var1, "name"));
            var2.setProperty(getJsonValue(var1, "property"));
            var2.setPropertyLabel(getJsonValue(var1, "propertyLabel"));
            var2.setObjectParameter(parseValueNode(var1.get("objectParameter")));
            return var2;
        }
    }

    public static Value parseValue(JsonNode var0) {
        JsonNode var1 = var0.get("value");
        return var1 == null ? null : parseValueNode(var1);
    }

    public static List<ValueDeserializer> getValueDeserializers() {
        return valueDeserializers;
    }

    static {
        valueDeserializers.add(new ConstantValueDeserializer());
        valueDeserializers.add(new InputValueDeserializer());
        valueDeserializers.add(new ParameterValueDeserializer());
        valueDeserializers.add(new MethodValueDeserializer());
        valueDeserializers.add(new VariableCategoryValueDeserializer());
        valueDeserializers.add(new VariableValueDeserializer());
        valueDeserializers.add(new CommonFunctionValueDeserializer());
        valueDeserializers.add(new ParenValueDeserializer());
        valueDeserializers.add(new MathValueDeserializer());
        valueDeserializers.add(new SingIValueDeserializer());
    }
}
