//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer.math;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.MathType;
import com.bstek.urule.model.rule.math.RadicalMath;
import org.codehaus.jackson.JsonNode;

public class RadicalMathDeserializer implements MathDeserializer {
    public RadicalMathDeserializer() {
    }

    public MathSign deserialize(JsonNode var1) {
        RadicalMath var2 = new RadicalMath();
        var2.setValue(JsonUtils.parseValueNode(var1.get("value")));
        return var2;
    }

    public MathType getType() {
        return MathType.radical;
    }
}
