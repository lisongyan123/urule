//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.CommonFunctionValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import org.codehaus.jackson.JsonNode;

public class CommonFunctionValueDeserializer implements ValueDeserializer {
    public CommonFunctionValueDeserializer() {
    }

    public Value deserialize(JsonNode var1) {
        CommonFunctionValue var2 = new CommonFunctionValue();
        var2.setArithmetic(JsonUtils.parseComplexArithmetic(var1));
        var2.setLabel(JsonUtils.getJsonValue(var1, "label"));
        var2.setName(JsonUtils.getJsonValue(var1, "name"));
        var2.setParameter(JsonUtils.parseCommonFunctionParameter(var1));
        var2.setValueType(ValueType.CommonFunction);
        return var2;
    }

    public boolean support(ValueType var1) {
        return var1.equals(ValueType.CommonFunction);
    }
}
