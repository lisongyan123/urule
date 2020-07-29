//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer.math;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.MathType;
import com.bstek.urule.model.rule.math.PowerMath;
import org.codehaus.jackson.JsonNode;

public class PowerMathDeserializer implements MathDeserializer {
    public PowerMathDeserializer() {
    }

    public MathSign deserialize(JsonNode var1) {
        PowerMath var2 = new PowerMath();
        var2.setBase(JsonUtils.parseValueNode(var1.get("base")));
        var2.setPower(JsonUtils.parseValueNode(var1.get("power")));
        return var2;
    }

    public MathType getType() {
        return MathType.power;
    }
}
