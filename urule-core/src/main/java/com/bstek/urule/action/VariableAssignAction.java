//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.action;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Value;
import com.bstek.urule.model.rule.lhs.LeftType;
import com.bstek.urule.runtime.rete.Context;
import com.bstek.urule.runtime.rete.ValueCompute;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;

public class VariableAssignAction extends AbstractAction {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Datatype f;
    private Value g;
    private LeftType h;
    private ActionType i;

    public VariableAssignAction() {
        this.i = ActionType.VariableAssign;
    }

    public ActionValue execute(Context var1, Map<String, Object> var2) {
        Object var3 = null;
        ValueCompute var4 = var1.getValueCompute();
        Object var5 = null;
        if (this.g == null) {
            return null;
        } else {
            var5 = var4.complexValueCompute(this.g, var1, var2);
            String var6 = var1.getVariableCategoryClass(this.e);
            if (var6.equals(HashMap.class.getName())) {
                var3 = var1.getWorkingMemory().getParameters();
            } else {
                var3 = var4.findObject(var6, var2, var1);
            }

            if (var3 == null) {
                throw new RuleException("Class [" + var6 + "] not exist.");
            } else {
                if (this.f.equals(Datatype.Enum) && var5 != null && StringUtils.isNotBlank(var5.toString())) {
                    PropertyDescriptor var7 = BeanUtils.getPropertyDescriptor(var3.getClass(), this.c);
                    if (var7 == null) {
                        throw new RuleException("赋值操作无法获取当前枚举类型！");
                    }

                    Class var8 = var7.getPropertyType();
                    var5 = Enum.valueOf(var8, var5.toString());
                } else if (var5 != null) {
                    var5 = this.f.convert(var5);
                }

                String var9 = this.e;
                if (this.b == null && this.a == null) {
                    var9 = var9 + "." + (this.d == null ? this.c : this.d);
                } else {
                    var9 = var9 + "." + (this.b == null ? this.a : this.b) + "." + (this.d == null ? this.c : this.d);
                }

                if (this.debug) {
                    var1.getLogger().logValueAssign(var9, var5);
                }

                if (this.a != null) {
                    var3 = Utils.getObjectProperty(var3, this.a);
                    if (var3 == null) {
                        throw new RuleException("要赋值的对象【" + this.b + "】在参数中不存在");
                    }
                }

                if (var5 instanceof Map) {
                    Map var11 = (Map)var5;
                    this.a(var3, var11, var9);
                    return null;
                } else {
                    if (var5 instanceof String && this.f.equals(Datatype.Object)) {
                        String var10 = (String)var5;
                        this.a(var3, var10, var9);
                    } else {
                        this.a(var3, this.c, var5);
                    }

                    return null;
                }
            }
        }
    }

    private void a(Object var1, String var2, String var3) {
        if (var2.startsWith("{") && var2.endsWith("}")) {
            ObjectMapper var4 = new ObjectMapper();

            try {
                Map var5 = (Map)var4.readValue(var2, HashMap.class);
                this.a(var1, var5, var3);
            } catch (Exception var6) {
                throw new RuleException("赋值操作值为Map类型:" + var2 + ",但无法将其转换为Map，请检查输入字符格式.");
            }
        } else {
            this.a(var1, (String)this.c, (Object)var2);
        }

    }

    private void a(Object var1, Map<String, Object> var2, String var3) {
        String var4 = this.c;
        PropertyDescriptor var5 = BeanUtils.getPropertyDescriptor(var1.getClass(), this.c);
        if (var5 == null) {
            this.a(var1, (String)var4, (Object)var2);
        } else {
            Class var6 = var5.getPropertyType();
            if (Map.class.isAssignableFrom(var6)) {
                this.a(var1, (String)var4, (Object)var2);
            } else {
                Object var7 = Utils.getObjectProperty(var1, var4);
                if (var7 == null) {
                    try {
                        Object var8 = var6.newInstance();
                        Iterator var9 = var2.keySet().iterator();

                        while(var9.hasNext()) {
                            String var10 = (String)var9.next();
                            this.a(var8, var10, var2.get(var10));
                        }

                        this.a(var1, var4, var8);
                    } catch (IllegalAccessException | InstantiationException var12) {
                        throw new RuleException("赋值操作值为Map类型，赋值对象[" + var3 + "]类型为" + var6.getName() + "，无法对" + var6.getName() + "进行实例化");
                    } catch (Exception var13) {
                        throw new RuleException("赋值操作值为Map类型，赋值对象[" + var3 + "]类型为" + var6.getName() + "，Map中key与[" + var6.getName() + "]类型对象属性存在不匹配情况");
                    }
                } else {
                    try {
                        Iterator var14 = var2.keySet().iterator();

                        while(var14.hasNext()) {
                            String var15 = (String)var14.next();
                            this.a(var7, var15, var2.get(var15));
                        }
                    } catch (Exception var11) {
                        throw new RuleException("赋值操作值为Map类型，赋值对象[" + var3 + "]类型为" + var6.getName() + "，Map中key与[" + var6.getName() + "]类型对象属性存在不匹配情况");
                    }
                }

            }
        }
    }

    private void a(Object var1, String var2, Object var3) {
        String[] var4 = var2.split("\\.");
        Object var5 = var1;

        for(int var6 = 0; var6 < var4.length; ++var6) {
            String var7 = var4[var6];
            if (var6 == var4.length - 1) {
                Utils.setObjectProperty(var5, var7, var3);
                break;
            }

            Object var8 = Utils.getObjectProperty(var5, var7);
            if (var8 == null) {
                PropertyDescriptor var9 = BeanUtils.getPropertyDescriptor(var5.getClass(), var7);
                if (var9 == null) {
                    var8 = new HashMap();
                } else {
                    Class var10 = var9.getPropertyType();
                    if (Map.class.isAssignableFrom(var10)) {
                        var8 = new HashMap();
                    } else {
                        try {
                            var8 = var10.newInstance();
                        } catch (IllegalAccessException | InstantiationException var12) {
                            var12.printStackTrace();
                            throw new RuleException("尝试对[" + var1.getClass().getName() + "]对象实例的[" + var2 + "]里的子对象[" + var10.getName() + "]实例化失败，请确认子对象[" + var10.getName() + "]有空的构造函数");
                        }
                    }
                }

                Utils.setObjectProperty(var5, var7, var8);
            }

            var5 = var8;
        }

    }

    public LeftType getType() {
        return this.h;
    }

    public void setType(LeftType var1) {
        this.h = var1;
    }

    public String getVariableName() {
        return this.c;
    }

    public void setVariableName(String var1) {
        this.c = var1;
    }

    public String getVariableLabel() {
        return this.d;
    }

    public void setVariableLabel(String var1) {
        this.d = var1;
    }

    public String getVariableCategory() {
        return this.e;
    }

    public void setVariableCategory(String var1) {
        this.e = var1;
    }

    public String getKeyName() {
        return this.a;
    }

    public void setKeyName(String var1) {
        this.a = var1;
    }

    public String getKeyLabel() {
        return this.b;
    }

    public void setKeyLabel(String var1) {
        this.b = var1;
    }

    public Value getValue() {
        return this.g;
    }

    public void setValue(Value var1) {
        this.g = var1;
    }

    public Datatype getDatatype() {
        return this.f;
    }

    public void setDatatype(Datatype var1) {
        this.f = var1;
    }

    public ActionType getActionType() {
        return this.i;
    }
}
