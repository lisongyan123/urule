//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.SimpleValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import org.codehaus.jackson.JsonNode;

public class InputValueDeserializer implements ValueDeserializer {
    public InputValueDeserializer() {
    }

    public Value deserialize(JsonNode var1) {
        SimpleValue var2 = new SimpleValue();
        var2.setContent(JsonUtils.getJsonValue(var1, "content"));
        var2.setArithmetic(JsonUtils.parseComplexArithmetic(var1));
        return var2;
    }

    public boolean support(ValueType var1) {
        return var1.equals(ValueType.Input);
    }
}
