//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer;

import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import org.codehaus.jackson.JsonNode;

public interface ValueDeserializer {
    Value deserialize(JsonNode var1);

    boolean support(ValueType var1);
}
