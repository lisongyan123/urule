//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.ParameterValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import org.codehaus.jackson.JsonNode;

public class ParameterValueDeserializer implements ValueDeserializer {
    public ParameterValueDeserializer() {
    }

    public Value deserialize(JsonNode var1) {
        ParameterValue var2 = new ParameterValue();
        var2.setArithmetic(JsonUtils.parseComplexArithmetic(var1));
        var2.setVariableLabel(JsonUtils.getJsonValue(var1, "variableLabel"));
        var2.setVariableName(JsonUtils.getJsonValue(var1, "variableName"));
        var2.setKeyLabel(JsonUtils.getJsonValue(var1, "keyLabel"));
        var2.setKeyName(JsonUtils.getJsonValue(var1, "keyName"));
        return var2;
    }

    public boolean support(ValueType var1) {
        return var1.equals(ValueType.Parameter);
    }
}
