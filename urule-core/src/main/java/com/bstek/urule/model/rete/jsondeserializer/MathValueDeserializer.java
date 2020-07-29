//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete.jsondeserializer;

import com.bstek.urule.model.rete.JsonUtils;
import com.bstek.urule.model.rete.jsondeserializer.math.AbsoluteMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.DownRoundMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.ExtremumFunctionMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.FractionMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.LnMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.LogMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.MathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.NRadicalMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.PiMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.PowerMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.RadicalMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.SigmaMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.TriangleFunctionMathDeserializer;
import com.bstek.urule.model.rete.jsondeserializer.math.UpRoundMathDeserializer;
import com.bstek.urule.model.rule.MathValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.model.rule.math.MathType;
import java.util.HashMap;
import java.util.Map;
import org.codehaus.jackson.JsonNode;

public class MathValueDeserializer implements ValueDeserializer {
    private Map<MathType, MathDeserializer> map = new HashMap();

    public MathValueDeserializer() {
        AbsoluteMathDeserializer var1 = new AbsoluteMathDeserializer();
        this.map.put(var1.getType(), var1);
        FractionMathDeserializer var2 = new FractionMathDeserializer();
        this.map.put(var2.getType(), var2);
        LnMathDeserializer var3 = new LnMathDeserializer();
        this.map.put(var3.getType(), var3);
        LogMathDeserializer var4 = new LogMathDeserializer();
        this.map.put(var4.getType(), var4);
        NRadicalMathDeserializer var5 = new NRadicalMathDeserializer();
        this.map.put(var5.getType(), var5);
        RadicalMathDeserializer var6 = new RadicalMathDeserializer();
        this.map.put(var6.getType(), var6);
        PiMathDeserializer var7 = new PiMathDeserializer();
        this.map.put(var7.getType(), var7);
        PowerMathDeserializer var8 = new PowerMathDeserializer();
        this.map.put(var8.getType(), var8);
        SigmaMathDeserializer var9 = new SigmaMathDeserializer();
        this.map.put(var9.getType(), var9);
        TriangleFunctionMathDeserializer var10 = new TriangleFunctionMathDeserializer();
        this.map.put(var10.getType(), var10);
        ExtremumFunctionMathDeserializer var11 = new ExtremumFunctionMathDeserializer();
        this.map.put(var11.getType(), var11);
        UpRoundMathDeserializer var12 = new UpRoundMathDeserializer();
        this.map.put(var12.getType(), var12);
        DownRoundMathDeserializer var13 = new DownRoundMathDeserializer();
        this.map.put(var13.getType(), var13);
    }

    public Value deserialize(JsonNode var1) {
        MathValue var2 = new MathValue();
        JsonNode var3 = var1.get("mathSign");
        String var4 = JsonUtils.getJsonValue(var3, "type");
        MathType var5 = MathType.valueOf(var4);
        MathSign var6 = ((MathDeserializer)this.map.get(var5)).deserialize(var3);
        var2.setMathSign(var6);
        var2.setArithmetic(JsonUtils.parseComplexArithmetic(var1));
        return var2;
    }

    public boolean support(ValueType var1) {
        return var1.equals(ValueType.Math);
    }
}
