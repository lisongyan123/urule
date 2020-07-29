//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.MethodValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import org.codehaus.jackson.JsonNode;

public class MethodValueDeserializer implements ValueDeserializer {
    public MethodValueDeserializer() {
    }

    public Value deserialize(JsonNode var1) {
        MethodValue var2 = new MethodValue();
        var2.setArithmetic(JsonUtils.parseComplexArithmetic(var1));
        var2.setBeanId(JsonUtils.getJsonValue(var1, "beanId"));
        var2.setBeanLabel(JsonUtils.getJsonValue(var1, "beanLabel"));
        var2.setMethodLabel(JsonUtils.getJsonValue(var1, "methodLabel"));
        var2.setMethodName(JsonUtils.getJsonValue(var1, "methodName"));
        var2.setParameters(JsonUtils.parseParameters(var1));
        return var2;
    }

    public boolean support(ValueType var1) {
        return var1.equals(ValueType.Method);
    }
}
