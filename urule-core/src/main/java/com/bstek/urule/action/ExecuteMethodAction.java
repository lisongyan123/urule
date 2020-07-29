//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Parameter;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.rete.Context;
import com.bstek.urule.runtime.rete.ValueCompute;
import com.bstek.urule.runtime.service.KnowledgeService;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

public class ExecuteMethodAction extends AbstractAction {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private List<Parameter> f;
    private ActionType g;

    public ExecuteMethodAction() {
        this.g = ActionType.ExecuteMethod;
    }

    public ActionValue execute(Context var1, Map<String, Object> var2) {
        String var3 = (this.b == null ? this.a : this.b) + (this.c == null ? this.d : this.c);
        var3 = "$$$执行动作：" + var3;
        if (StringUtils.isNotBlank(this.e)) {
            if (this.debug) {
                var1.getLogger().logExecuteBeanMethod(var3, this.e);
            }

            this.a(var1);
            return null;
        } else {
            try {
                Object var4 = var1.getApplicationContext().getBean(this.a);
                Method var5 = null;
                if (this.f != null && this.f.size() > 0) {
                    ParametersWrap var20 = this.a(var1, var2);
                    Method[] var21 = var4.getClass().getMethods();
                    Datatype[] var22 = var20.getDatatypes();
                    boolean var9 = false;
                    Method[] var10 = var21;
                    int var11 = var21.length;

                    for(int var12 = 0; var12 < var11; ++var12) {
                        Method var13 = var10[var12];
                        var5 = var13;
                        String var14 = var13.getName();
                        if (var14.equals(this.d)) {
                            Class[] var15 = var13.getParameterTypes();
                            if (var15.length == this.f.size()) {
                                for(int var16 = 0; var16 < var15.length; ++var16) {
                                    Class var17 = var15[var16];
                                    Datatype var18 = var22[var16];
                                    var9 = this.a(var17, var18);
                                    if (!var9) {
                                        break;
                                    }
                                }

                                if (var9) {
                                    break;
                                }
                            }
                        }
                    }

                    if (!var9) {
                        throw new RuleException("Bean [" + this.a + "." + this.d + "] with " + this.f.size() + " parameters not exist");
                    } else {
                        String var23 = this.d;
                        ActionId var24 = (ActionId)var5.getAnnotation(ActionId.class);
                        if (var24 != null) {
                            var23 = var24.value();
                        }

                        if (this.debug) {
                            var1.getLogger().logExecuteBeanMethod(var3, var20.valuesToString());
                        }

                        Object var25 = var5.invoke(var4, var20.getValues());
                        if (var25 != null) {
                            if (var23.equals("_loop_rule_break_tag__")) {
                                var1.getWorkingMemory().getParameters().put(var23, var25);
                                return null;
                            } else {
                                return new ActionValueImpl(var23, var25);
                            }
                        } else {
                            return null;
                        }
                    }
                } else {
                    var5 = var4.getClass().getMethod(this.d);
                    String var6 = this.d;
                    ActionId var7 = (ActionId)var5.getAnnotation(ActionId.class);
                    if (var7 != null) {
                        var6 = var7.value();
                    }

                    if (this.debug) {
                        var1.getLogger().logExecuteBeanMethod(var3, "");
                    }

                    Object var8 = var5.invoke(var4);
                    if (var8 != null) {
                        if (var6.equals("_loop_rule_break_tag__")) {
                            var1.getWorkingMemory().getParameters().put(var6, var8);
                            return null;
                        } else {
                            return new ActionValueImpl(var6, var8);
                        }
                    } else {
                        return null;
                    }
                }
            } catch (Exception var19) {
                throw new RuleException(var19);
            }
        }
    }

    private void a(Context var1) {
        KnowledgeService var2 = (KnowledgeService)var1.getApplicationContext().getBean("urule.knowledgeService");

        try {
            KnowledgePackage var3 = var2.getKnowledge(this.e);
            KnowledgeSession var4 = (KnowledgeSession)var1.getWorkingMemory();
            KnowledgeSession var5 = KnowledgeSessionFactory.newKnowledgeSession(var3, var4);
            if (var3.getFlowMap() != null && var3.getFlowMap().size() != 0) {
                String var6 = ((FlowDefinition)var3.getFlowMap().values().iterator().next()).getId();
                var5.startProcess(var6, var4.getParameters());
            } else {
                var5.fireRules(var4.getParameters());
            }

            var1.addRuleData(var5.getLogManager().getRuleData());
            Map var11 = var5.getParameters();
            Map var7 = var4.getParameters();
            Iterator var8 = var11.keySet().iterator();

            while(var8.hasNext()) {
                String var9 = (String)var8.next();
                var7.put(var9, var11.get(var9));
            }

        } catch (Exception var10) {
            throw new RuleException(var10);
        }
    }

    private boolean a(Class<?> var1, Datatype var2) {
        boolean var3 = false;
        switch(var2) {
            case String:
                if (var1.equals(String.class)) {
                    var3 = true;
                } else {
                    var3 = false;
                }
                break;
            case BigDecimal:
                if (var1.equals(BigDecimal.class)) {
                    var3 = true;
                } else {
                    var3 = false;
                }
                break;
            case Boolean:
                if (!var1.equals(Boolean.class) && !var1.equals(Boolean.TYPE)) {
                    var3 = false;
                } else {
                    var3 = true;
                }
                break;
            case Date:
                if (var1.equals(Date.class)) {
                    var3 = true;
                } else {
                    var3 = false;
                }
                break;
            case Double:
                if (!var1.equals(Double.class) && !var1.equals(Double.TYPE)) {
                    var3 = false;
                } else {
                    var3 = true;
                }
                break;
            case Enum:
                if (Enum.class.isAssignableFrom(var1)) {
                    var3 = true;
                } else {
                    var3 = false;
                }
                break;
            case Float:
                if (!var1.equals(Float.class) && !var1.equals(Float.TYPE)) {
                    var3 = false;
                } else {
                    var3 = true;
                }
                break;
            case Integer:
                if (!var1.equals(Integer.class) && !var1.equals(Integer.TYPE)) {
                    var3 = false;
                } else {
                    var3 = true;
                }
                break;
            case Char:
                if (!var1.equals(Character.class) && !var1.equals(Character.TYPE)) {
                    var3 = false;
                } else {
                    var3 = true;
                }
                break;
            case List:
                if (List.class.isAssignableFrom(var1)) {
                    var3 = true;
                } else {
                    var3 = false;
                }
                break;
            case Long:
                if (!var1.equals(Long.class) && !var1.equals(Long.TYPE)) {
                    var3 = false;
                } else {
                    var3 = true;
                }
                break;
            case Map:
                if (Map.class.isAssignableFrom(var1)) {
                    var3 = true;
                } else {
                    var3 = false;
                }
                break;
            case Set:
                if (Set.class.isAssignableFrom(var1)) {
                    var3 = true;
                } else {
                    var3 = false;
                }
                break;
            case Object:
                var3 = true;
        }

        return var3;
    }

    private ParametersWrap a(Context var1, Map<String, Object> var2) {
        ArrayList var3 = new ArrayList();
        ArrayList var4 = new ArrayList();
        ValueCompute var5 = var1.getValueCompute();
        Iterator var6 = this.f.iterator();

        while(var6.hasNext()) {
            Parameter var7 = (Parameter)var6.next();
            Datatype var8 = var7.getType();
            var3.add(var8);
            Object var9 = var5.complexValueCompute(var7.getValue(), var1, var2);
            var4.add(var8.convert(var9));
        }

        Datatype[] var10 = new Datatype[var3.size()];
        var3.toArray(var10);
        Object[] var11 = new Object[var4.size()];
        var4.toArray(var11);
        ParametersWrap var12 = new ParametersWrap();
        var12.setDatatypes(var10);
        var12.setValues(var11);
        return var12;
    }

    public String getMethodLabel() {
        return this.c;
    }

    public void setMethodLabel(String var1) {
        this.c = var1;
    }

    public String getBeanId() {
        return this.a;
    }

    public void setBeanId(String var1) {
        this.a = var1;
    }

    public String getMethodName() {
        return this.d;
    }

    public void setMethodName(String var1) {
        this.d = var1;
    }

    public String getBeanLabel() {
        return this.b;
    }

    public void setBeanLabel(String var1) {
        this.b = var1;
    }

    public List<Parameter> getParameters() {
        return this.f;
    }

    public void setParameters(List<Parameter> var1) {
        this.f = var1;
    }

    public String getKnowledgePackage() {
        return this.e;
    }

    public void setKnowledgePackage(String var1) {
        this.e = var1;
    }

    public void addParameter(Parameter var1) {
        if (this.f == null) {
            this.f = new ArrayList();
        }

        this.f.add(var1);
    }

    public ActionType getActionType() {
        return this.g;
    }
}
