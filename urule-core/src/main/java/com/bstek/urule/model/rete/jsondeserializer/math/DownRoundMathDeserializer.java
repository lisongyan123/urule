//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer.math;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rule.math.DownRoundMath;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.MathType;
import org.codehaus.jackson.JsonNode;

public class DownRoundMathDeserializer implements MathDeserializer {
    public DownRoundMathDeserializer() {
    }

    public MathSign deserialize(JsonNode var1) {
        DownRoundMath var2 = new DownRoundMath();
        var2.setValue(JsonUtils.parseValueNode(var1.get("value")));
        return var2;
    }

    public MathType getType() {
        return MathType.downRound;
    }
}
