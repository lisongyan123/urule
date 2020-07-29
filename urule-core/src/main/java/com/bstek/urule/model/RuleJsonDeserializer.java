//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model;

import com.bstek.urule.model.rule.Rule;
import java.io.IOException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;

public class RuleJsonDeserializer extends AbstractJsonDeserializer<Rule> {
    public RuleJsonDeserializer() {
    }

    public Rule deserialize(JsonParser var1, DeserializationContext var2) throws IOException, JsonProcessingException {
        ObjectCodec var3 = var1.getCodec();
        JsonNode var4 = var3.readTree(var1);
        Rule var5 = this.parseRule(var1, var4);
        return var5;
    }
}
