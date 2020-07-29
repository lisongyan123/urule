//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer.math;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.math.ExtremumMath;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.MathType;
import org.codehaus.jackson.JsonNode;

public class ExtremumFunctionMathDeserializer implements MathDeserializer {
    public ExtremumFunctionMathDeserializer() {
    }

    public MathSign deserialize(JsonNode var1) {
        ExtremumMath var2 = new ExtremumMath();
        var2.setName(JsonUtils.getJsonValue(var1, "name"));
        var2.setValue1(JsonUtils.parseValueNode(var1.get("value1")));
        var2.setValue2(JsonUtils.parseValueNode(var1.get("value2")));
        return var2;
    }

    public MathType getType() {
        return MathType.extremum;
    }
}
