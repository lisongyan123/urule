//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.model.rule.Other;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.lhs.Lhs;
import com.bstek.urule.model.rule.loop.LoopEnd;
import com.bstek.urule.model.rule.loop.LoopRule;
import com.bstek.urule.model.rule.loop.LoopRuleUnit;
import com.bstek.urule.model.rule.loop.LoopStart;
import com.bstek.urule.model.rule.loop.LoopTarget;
import com.bstek.urule.model.rule.loop.LoopTargetType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;

public class LoopRuleParser extends AbstractRuleParser<LoopRule> {
    private ValueParser a;

    public LoopRuleParser() {
    }

    public LoopRule parse(Element var1) {
        LoopRule var2 = new LoopRule();
        this.parseRule(var2, var1);
        LoopStart var3 = new LoopStart();
        var2.setLoopStart(var3);
        LoopEnd var4 = new LoopEnd();
        var2.setLoopEnd(var4);
        String var5 = var1.attributeValue("loop-target-type");
        if (StringUtils.isNotBlank(var5)) {
            var2.setLoopTargetType(LoopTargetType.valueOf(var5));
        }

        Iterator var6 = var1.elements().iterator();

        while(true) {
            while(true) {
                Object var7;
                do {
                    do {
                        if (!var6.hasNext()) {
                            this.a(var2);
                            return var2;
                        }

                        var7 = var6.next();
                    } while(var7 == null);
                } while(!(var7 instanceof Element));

                Element var8 = (Element)var7;
                String var9 = var8.getName();
                if (var9.equals("loop-start")) {
                    var3.setActions(this.rhsParser.parseActions(var8));
                } else if (var9.equals("loop-end")) {
                    var4.setActions(this.rhsParser.parseActions(var8));
                } else if (var9.equals("loop-target")) {
                    LoopTarget var10 = new LoopTarget();
                    var2.setLoopTarget(var10);
                    Iterator var11 = var8.elements().iterator();

                    while(var11.hasNext()) {
                        Object var12 = var11.next();
                        if (var12 != null && var12 instanceof Element) {
                            Element var13 = (Element)var12;
                            if (this.a.support(var13.getName())) {
                                var10.setValue(this.a.parse(var13));
                                break;
                            }
                        }
                    }
                } else if (var9.equals("units")) {
                    var2.setUnits(this.a(var8));
                }
            }
        }
    }

    private void a(LoopRule var1) {
        if (var1.getUnits() == null || var1.getUnits().size() <= 0) {
            LoopRuleUnit var2 = new LoopRuleUnit();
            var2.setLhs(var1.getLhs());
            var2.setOther(var1.getOther());
            var2.setRhs(var1.getRhs());
            var1.setLhs((Lhs)null);
            var1.setRhs((Rhs)null);
            var1.setOther((Other)null);
            ArrayList var3 = new ArrayList();
            var3.add(var2);
            var1.setUnits(var3);
        }
    }

    private List<LoopRuleUnit> a(Element var1) {
        ArrayList var2 = new ArrayList();
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (var5.getName().equals("unit")) {
                    LoopRuleUnit var6 = this.b(var5);
                    var2.add(var6);
                }
            }
        }

        return var2;
    }

    private LoopRuleUnit b(Element var1) {
        LoopRuleUnit var2 = new LoopRuleUnit();
        var2.setName(var1.attributeValue("name"));
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (this.lhsParser.support(var5.getName())) {
                    var2.setLhs(this.lhsParser.parse(var5));
                } else if (this.rhsParser.support(var5.getName())) {
                    var2.setRhs(this.rhsParser.parse(var5));
                } else if (this.otherParser.support(var5.getName())) {
                    var2.setOther(this.otherParser.parse(var5));
                }
            }
        }

        return var2;
    }

    public void setValueParser(ValueParser var1) {
        this.a = var1;
    }

    public boolean support(String var1) {
        return var1.equals("loop-rule");
    }
}
