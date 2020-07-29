//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import com.bstek.urule.model.rule.VariableCategoryValue;
import org.codehaus.jackson.JsonNode;

public class VariableCategoryValueDeserializer implements ValueDeserializer {
    public VariableCategoryValueDeserializer() {
    }

    public Value deserialize(JsonNode var1) {
        VariableCategoryValue var2 = new VariableCategoryValue();
        var2.setArithmetic(JsonUtils.parseComplexArithmetic(var1));
        var2.setVariableCategory(JsonUtils.getJsonValue(var1, "variableCategory"));
        return var2;
    }

    public boolean support(ValueType var1) {
        return var1.equals(ValueType.VariableCategory);
    }
}
