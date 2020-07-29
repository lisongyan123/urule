//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer.math;

import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.MathType;
import com.bstek.urule.model.rule.math.PiMath;
import org.codehaus.jackson.JsonNode;

public class PiMathDeserializer implements MathDeserializer {
    public PiMathDeserializer() {
    }

    public MathSign deserialize(JsonNode var1) {
        return new PiMath();
    }

    public MathType getType() {
        return MathType.pi;
    }
}
