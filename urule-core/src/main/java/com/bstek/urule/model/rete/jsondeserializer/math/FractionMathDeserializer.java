//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer.math;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.math.FractionMath;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.MathType;
import org.codehaus.jackson.JsonNode;

public class FractionMathDeserializer implements MathDeserializer {
    public FractionMathDeserializer() {
    }

    public MathSign deserialize(JsonNode var1) {
        FractionMath var2 = new FractionMath();
        JsonNode var3 = var1.get("numerator");
        var2.setNumerator(JsonUtils.parseValueNode(var3));
        JsonNode var4 = var1.get("denominator");
        var2.setDenominator(JsonUtils.parseValueNode(var4));
        return var2;
    }

    public MathType getType() {
        return MathType.fraction;
    }
}
