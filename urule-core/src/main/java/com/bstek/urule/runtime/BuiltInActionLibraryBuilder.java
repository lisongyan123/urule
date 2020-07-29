//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.action.Method;
import com.bstek.urule.model.library.action.Parameter;
import com.bstek.urule.model.library.action.SpringBean;
import com.bstek.urule.model.library.action.annotation.ActionBean;
import com.bstek.urule.model.library.action.annotation.ActionMethod;
import com.bstek.urule.model.library.action.annotation.ActionMethodParameter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class BuiltInActionLibraryBuilder implements ApplicationContextAware {
    private List<SpringBean> a = new ArrayList();

    public BuiltInActionLibraryBuilder() {
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.buildActions(var1);
    }

    public List<SpringBean> getBuiltInActions() {
        return this.a;
    }

    public void buildActions(ApplicationContext var1) {
        System.out.println("Load built in actions...");
        this.a.clear();
        Map var2 = var1.getBeansWithAnnotation(ActionBean.class);
        Iterator var3 = var2.keySet().iterator();

        while(var3.hasNext()) {
            String var4 = (String)var3.next();
            Object var5 = var2.get(var4);
            var5 = ProxyUtils.getTargetObject(var5);
            ActionBean var6 = (ActionBean)var5.getClass().getAnnotation(ActionBean.class);
            if (var6 != null) {
                SpringBean var7 = new SpringBean();
                var7.setId(var4);
                var7.setName(var6.name());
                var7.setMethods(this.a(var5.getClass().getMethods()));
                this.a.add(var7);
            }
        }

    }

    private List<Method> a(java.lang.reflect.Method[] var1) {
        ArrayList var2 = new ArrayList();
        java.lang.reflect.Method[] var3 = var1;
        int var4 = var1.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            java.lang.reflect.Method var6 = var3[var5];
            ActionMethod var7 = (ActionMethod)var6.getAnnotation(ActionMethod.class);
            if (var7 != null) {
                String var8 = var7.name();
                String var9 = var6.getName();
                Method var10 = new Method();
                var10.setMethodName(var9);
                var10.setName(var8);
                var2.add(var10);
                ActionMethodParameter var11 = (ActionMethodParameter)var6.getAnnotation(ActionMethodParameter.class);
                ArrayList var12 = new ArrayList();
                if (var11 != null) {
                    String[] var13 = var11.names();
                    String[] var14 = var13;
                    int var15 = var13.length;

                    for(int var16 = 0; var16 < var15; ++var16) {
                        String var17 = var14[var16];
                        var12.add(var17);
                    }
                }

                var10.setParameters(this.a(var6, var12));
            }
        }

        return var2;
    }

    private List<Parameter> a(java.lang.reflect.Method var1, List<String> var2) {
        ArrayList var3 = new ArrayList();
        Class[] var4 = var1.getParameterTypes();

        for(int var5 = 0; var5 < var4.length; ++var5) {
            Class var6 = var4[var5];
            String var7 = "";
            if (var2.size() > var5) {
                var7 = (String)var2.get(var5);
            }

            Parameter var8 = new Parameter();
            var8.setName(var7);
            var8.setType(this.a(var6));
            var3.add(var8);
        }

        return var3;
    }

    private Datatype a(Class<?> var1) {
        if (var1.getName().equals("java.lang.Object")) {
            return Datatype.Object;
        } else if (!var1.isAssignableFrom(Integer.class) && !var1.isAssignableFrom(Integer.TYPE)) {
            if (!var1.isAssignableFrom(Long.class) && !var1.isAssignableFrom(Long.TYPE)) {
                if (!var1.isAssignableFrom(Double.class) && !var1.isAssignableFrom(Double.TYPE)) {
                    if (!var1.isAssignableFrom(Float.class) && !var1.isAssignableFrom(Float.TYPE)) {
                        if (var1.isAssignableFrom(BigDecimal.class)) {
                            return Datatype.BigDecimal;
                        } else if (!var1.isAssignableFrom(Boolean.class) && !var1.isAssignableFrom(Boolean.TYPE)) {
                            if (var1.isAssignableFrom(Date.class)) {
                                return Datatype.Date;
                            } else if (var1.isAssignableFrom(List.class)) {
                                return Datatype.List;
                            } else if (var1.isAssignableFrom(Set.class)) {
                                return Datatype.Set;
                            } else if (var1.isAssignableFrom(Enum.class)) {
                                return Datatype.Enum;
                            } else if (var1.isAssignableFrom(Map.class)) {
                                return Datatype.Map;
                            } else if (var1.isAssignableFrom(String.class)) {
                                return Datatype.String;
                            } else {
                                return !var1.isAssignableFrom(Character.class) && !var1.isAssignableFrom(Character.TYPE) ? Datatype.Object : Datatype.Char;
                            }
                        } else {
                            return Datatype.Boolean;
                        }
                    } else {
                        return Datatype.Float;
                    }
                } else {
                    return Datatype.Double;
                }
            } else {
                return Datatype.Long;
            }
        } else {
            return Datatype.Integer;
        }
    }
}
