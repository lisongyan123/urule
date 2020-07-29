//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model;

import com.bstek.urule.model.rule.Rule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.ObjectCodec;
import org.codehaus.jackson.map.DeserializationContext;

public class RulesJsonDeserializer extends AbstractJsonDeserializer<List<Rule>> {
    public RulesJsonDeserializer() {
    }

    public List<Rule> deserialize(JsonParser var1, DeserializationContext var2) throws IOException, JsonProcessingException {
        ObjectCodec var3 = var1.getCodec();
        JsonNode var4 = var3.readTree(var1);
        Iterator var5 = var4.getElements();
        ArrayList var6 = new ArrayList();

        while(var5.hasNext()) {
            JsonNode var7 = (JsonNode)var5.next();
            var6.add(this.parseRule(var1, var7));
        }

        return var6;
    }
}
