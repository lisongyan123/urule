//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer;

import com.bstek.urule.model.rule.SignIValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import org.codehaus.jackson.JsonNode;

public class SingIValueDeserializer implements ValueDeserializer {
    public SingIValueDeserializer() {
    }

    public Value deserialize(JsonNode var1) {
        SignIValue var2 = new SignIValue();
        return var2;
    }

    public boolean support(ValueType var1) {
        return var1.equals(ValueType.SignI);
    }
}
