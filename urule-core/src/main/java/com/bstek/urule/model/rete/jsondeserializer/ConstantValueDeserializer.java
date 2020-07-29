//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.ConstantValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonNode;

public class ConstantValueDeserializer implements ValueDeserializer {
    public ConstantValueDeserializer() {
    }

    public Value deserialize(JsonNode var1) {
        ConstantValue var2 = new ConstantValue();
        var2.setConstantCategory(JsonUtils.getJsonValue(var1, "constantCategory"));
        var2.setConstantLabel(JsonUtils.getJsonValue(var1, "constantLabel"));
        var2.setConstantName(JsonUtils.getJsonValue(var1, "constantName"));
        String var3 = JsonUtils.getJsonValue(var1, "datatype");
        if (StringUtils.isNotBlank(var3)) {
            var2.setDatatype(Datatype.valueOf(var3));
        }

        var2.setArithmetic(JsonUtils.parseComplexArithmetic(var1));
        return var2;
    }

    public boolean support(ValueType var1) {
        return var1.equals(ValueType.Constant);
    }
}
