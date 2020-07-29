//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.AbstractValue;
import com.bstek.urule.model.rule.CommonFunctionValue;
import com.bstek.urule.model.rule.ConstantValue;
import com.bstek.urule.model.rule.MathValue;
import com.bstek.urule.model.rule.MethodValue;
import com.bstek.urule.model.rule.ParameterValue;
import com.bstek.urule.model.rule.SignIValue;
import com.bstek.urule.model.rule.SimpleValue;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.ValueType;
import com.bstek.urule.model.rule.VariableCategoryValue;
import com.bstek.urule.model.rule.VariableValue;
import com.bstek.urule.model.rule.lhs.CommonFunctionParameter;
import com.bstek.urule.model.rule.math.MathSign;
import com.bstek.urule.parse.math.AbsoluteMathParser;
import com.bstek.urule.parse.math.DownRoundMathParser;
import com.bstek.urule.parse.math.ExtremumFunctionMathParser;
import com.bstek.urule.parse.math.FractionMathParser;
import com.bstek.urule.parse.math.LnMathParser;
import com.bstek.urule.parse.math.LogMathParser;
import com.bstek.urule.parse.math.MathParser;
import com.bstek.urule.parse.math.NRadicalMathParser;
import com.bstek.urule.parse.math.PiMathParser;
import com.bstek.urule.parse.math.PowerMathParser;
import com.bstek.urule.parse.math.RadicalMathParser;
import com.bstek.urule.parse.math.SigmaMathParser;
import com.bstek.urule.parse.math.TriangleFunctionMathParser;
import com.bstek.urule.parse.math.UpRoundMathParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class ValueParser extends AbstractParser<Value> {
    private List<MathParser> a = new ArrayList();
    private ComplexArithmeticParser b;

    public ValueParser() {
        this.a.add(new AbsoluteMathParser(this));
        this.a.add(new PowerMathParser(this));
        this.a.add(new RadicalMathParser(this));
        this.a.add(new NRadicalMathParser(this));
        this.a.add(new SigmaMathParser(this));
        this.a.add(new FractionMathParser(this));
        this.a.add(new PiMathParser(this));
        this.a.add(new LnMathParser(this));
        this.a.add(new LogMathParser(this));
        this.a.add(new TriangleFunctionMathParser(this));
        this.a.add(new ExtremumFunctionMathParser(this));
        this.a.add(new UpRoundMathParser(this));
        this.a.add(new DownRoundMathParser(this));
    }

    public Value parse(Element var1) {
        Object var2 = null;
        ValueType var3 = ValueType.valueOf(var1.attributeValue("type"));
        String var5;
        String var7;
        if (var3.equals(ValueType.Input)) {
            SimpleValue var4 = new SimpleValue();
            var5 = var1.attributeValue("content");
            var4.setContent(StringEscapeUtils.unescapeXml(var5));
            var2 = var4;
        } else if (var3.equals(ValueType.Parameter)) {
            ParameterValue var12 = new ParameterValue();
            var12.setVariableName(var1.attributeValue("var"));
            var12.setVariableLabel(var1.attributeValue("var-label"));
            var12.setKeyLabel(var1.attributeValue("key-label"));
            var12.setKeyName(var1.attributeValue("key-name"));
            var5 = var1.attributeValue("datatype");
            if (StringUtils.isNotEmpty(var5) && !var5.equals("undefined")) {
                var12.setDatatype(Datatype.valueOf(var5));
            }

            var2 = var12;
        } else {
            String var6;
            String var8;
            if (var3.equals(ValueType.Variable)) {
                VariableValue var13 = new VariableValue();
                var5 = var1.attributeValue("var");
                if (StringUtils.isNotEmpty(var5)) {
                    var13.setVariableName(var5);
                }

                var6 = var1.attributeValue("var-label");
                if (StringUtils.isNotEmpty(var6)) {
                    var13.setVariableLabel(var6);
                }

                var7 = var1.attributeValue("datatype");
                if (StringUtils.isNotEmpty(var7)) {
                    var13.setDatatype(Datatype.valueOf(var7));
                }

                var8 = var1.attributeValue("var-category");
                if (StringUtils.isNotEmpty(var8)) {
                    var13.setVariableCategory(var8);
                }

                var2 = var13;
            } else if (var3.equals(ValueType.VariableCategory)) {
                String var14 = var1.attributeValue("var-category");
                var2 = new VariableCategoryValue(var14);
            } else if (var3.equals(ValueType.Method)) {
                MethodValue var15 = new MethodValue();
                var5 = var1.attributeValue("bean-name");
                var15.setBeanId(var5);
                var6 = var1.attributeValue("bean-label");
                var15.setBeanLabel(var6);
                var7 = var1.attributeValue("method-name");
                var15.setMethodName(var7);
                var8 = var1.attributeValue("method-label");
                var15.setMethodLabel(var8);
                List var9 = this.parseParameters(var1, this);
                var15.setParameters(var9);
                var2 = var15;
            } else if (var3.equals(ValueType.CommonFunction)) {
                CommonFunctionValue var16 = new CommonFunctionValue();
                var16.setName(var1.attributeValue("function-name"));
                var16.setLabel(var1.attributeValue("function-label"));
                Iterator var18 = var1.elements().iterator();

                label111:
                while(true) {
                    Element var25;
                    do {
                        Object var20;
                        do {
                            if (!var18.hasNext()) {
                                var2 = var16;
                                break label111;
                            }

                            var20 = var18.next();
                        } while(!(var20 instanceof Element));

                        var25 = (Element)var20;
                    } while(!var25.getName().equals("function-parameter"));

                    CommonFunctionParameter var26 = new CommonFunctionParameter();
                    var26.setName(var25.attributeValue("name"));
                    var26.setProperty(var25.attributeValue("property-name"));
                    var26.setPropertyLabel(var25.attributeValue("property-name"));
                    Iterator var27 = var25.elements().iterator();

                    while(var27.hasNext()) {
                        Object var10 = var27.next();
                        if (var10 instanceof Element) {
                            Element var11 = (Element)var10;
                            if (var11.getName().equals("value")) {
                                var26.setObjectParameter(this.parse(var11));
                            }
                        }
                    }

                    var16.setParameter(var26);
                }
            } else if (var3.equals(ValueType.Math)) {
                MathValue var17 = new MathValue();
                var17.setMathSign(this.a(var1));
                var2 = var17;
            } else if (var3.equals(ValueType.SignI)) {
                SignIValue var19 = new SignIValue();
                var2 = var19;
            } else {
                ConstantValue var21 = new ConstantValue();
                var5 = var1.attributeValue("const");
                var21.setConstantName(var5);
                var6 = var1.attributeValue("const-label");
                if (StringUtils.isNotEmpty(var6)) {
                    var21.setConstantLabel(var6);
                }

                var7 = var1.attributeValue("const-category");
                if (StringUtils.isNotEmpty(var7)) {
                    var21.setConstantCategory(var7);
                }

                var8 = var1.attributeValue("data-type");
                if (StringUtils.isNotBlank(var8)) {
                    var21.setDatatype(Datatype.valueOf(var8));
                }

                var2 = var21;
            }
        }

        Iterator var22 = var1.elements().iterator();

        while(var22.hasNext()) {
            Object var23 = var22.next();
            if (var23 != null && var23 instanceof Element) {
                Element var24 = (Element)var23;
                var7 = var24.getName();
                if (this.b.support(var7)) {
                    ((AbstractValue)var2).setArithmetic(this.b.parse(var24));
                    break;
                }
            }
        }

        return (Value)var2;
    }

    private MathSign a(Element var1) {
        Iterator var2 = var1.elements().iterator();

        while(true) {
            Object var3;
            do {
                if (!var2.hasNext()) {
                    throw new RuleException("Unknow element [" + var1.asXML() + "]");
                }

                var3 = var2.next();
            } while(!(var3 instanceof Element));

            Element var4 = (Element)var3;
            Iterator var5 = this.a.iterator();

            while(var5.hasNext()) {
                MathParser var6 = (MathParser)var5.next();
                if (var6.support(var4.getName())) {
                    return (MathSign)var6.parse(var4);
                }
            }
        }
    }

    public boolean support(String var1) {
        return var1.equals("value");
    }

    public void setArithmeticParser(ComplexArithmeticParser var1) {
        this.b = var1;
    }
}
