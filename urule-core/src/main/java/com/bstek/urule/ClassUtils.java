//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule;

import com.bstek.urule.model.Label;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.variable.Act;
import com.bstek.urule.model.library.variable.Variable;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class ClassUtils {
    public ClassUtils() {
    }

    public static void classToXml(Class<?> var0, File var1) {
        if (!var1.exists()) {
            try {
                var1.createNewFile();
            } catch (IOException var12) {
                throw new RuntimeException(var12);
            }
        }

        FileOutputStream var2 = null;

        try {
            var2 = new FileOutputStream(var1);
            List var3 = classToVariables(var0);
            StringBuffer var4 = new StringBuffer();
            var4.append("<variables clazz=\"" + var0.getName() + "\">");
            Iterator var5 = var3.iterator();

            while(var5.hasNext()) {
                Variable var6 = (Variable)var5.next();
                var4.append("<variable ");
                var4.append("name=\"" + var6.getName() + "\" ");
                if (var6.getLabel() != null) {
                    var4.append("label=\"" + var6.getLabel() + "\" ");
                }

                if (var6.getDefaultValue() != null) {
                    var4.append("defaultValue=\"" + var6.getDefaultValue() + "\" ");
                }

                if (var6.getType() != null) {
                    var4.append("type=\"" + var6.getType() + "\" ");
                }

                if (var6.getAct() != null) {
                    var4.append("act=\"" + var6.getAct() + "\" ");
                }

                var4.append(">");
                var4.append("</variable>");
            }

            var4.append("</variables>");
            Document var15 = DocumentHelper.parseText(var4.toString());
            OutputFormat var16 = OutputFormat.createPrettyPrint();
            var16.setEncoding("utf-8");
            XMLWriter var7 = new XMLWriter(var2, var16);
            var7.write(var15);
            var7.close();
            var2.close();
        } catch (Exception var13) {
            throw new RuntimeException(var13);
        } finally {
            IOUtils.closeQuietly(var2);
        }

    }

    public static List<Variable> classToVariables(Class<?> var0) {
        try {
            List var1 = a("", var0, new ArrayList());
            return var1;
        } catch (Exception var2) {
            throw new RuntimeException(var2);
        }
    }

    private static List<Variable> a(String var0, Class<?> var1, Collection<Class<?>> var2) throws Exception {
        ArrayList var3 = new ArrayList();
        BeanInfo var4 = Introspector.getBeanInfo(var1, Object.class);
        PropertyDescriptor[] var5 = var4.getPropertyDescriptors();
        if (var5 != null && !var2.contains(var1)) {
            var2.add(var1);
            PropertyDescriptor[] var6 = var5;
            int var7 = var5.length;

            for(int var8 = 0; var8 < var7; ++var8) {
                PropertyDescriptor var9 = var6[var8];
                Variable var10 = new Variable();
                Class var11 = var9.getPropertyType();
                Datatype var12 = a(var11);
                String var13 = var9.getName();
                String var14 = a(var1, var13);
                String var15 = var0 + var9.getName();
                var10.setName(var15);
                var10.setLabel(var14 == null ? var15 : var14);
                var10.setType(var12);
                var10.setAct(Act.InOut);
                if (Datatype.Object.equals(var12) && !var11.equals(Object.class)) {
                    var3.add(var10);
                    var3.addAll(a(var0 + var9.getName() + ".", var11, var2));
                } else {
                    var3.add(var10);
                }
            }
        }

        return var3;
    }

    private static String a(Class<?> var0, String var1) throws Exception {
        Field var2 = null;

        while(var2 == null) {
            try {
                var2 = var0.getDeclaredField(var1);
            } catch (NoSuchFieldException var4) {
                if (var0 == Object.class) {
                    throw var4;
                }

                var0 = var0.getSuperclass();
            }
        }

        Label var3 = (Label)var2.getAnnotation(Label.class);
        return var3 != null ? var3.value() : null;
    }

    private static Datatype a(Class<?> var0) {
        if (String.class.isAssignableFrom(var0)) {
            return Datatype.String;
        } else if (!Boolean.class.isAssignableFrom(var0) && !Boolean.TYPE.isAssignableFrom(var0)) {
            if (!Integer.class.isAssignableFrom(var0) && !Integer.TYPE.isAssignableFrom(var0)) {
                if (!Float.class.isAssignableFrom(var0) && !Float.TYPE.isAssignableFrom(var0)) {
                    if (!Long.class.isAssignableFrom(var0) && !Long.TYPE.isAssignableFrom(var0)) {
                        if (BigDecimal.class.isAssignableFrom(var0)) {
                            return Datatype.BigDecimal;
                        } else if (!Double.class.isAssignableFrom(var0) && !Double.TYPE.isAssignableFrom(var0)) {
                            if (Date.class.isAssignableFrom(var0)) {
                                return Datatype.Date;
                            } else if (Date.class.isAssignableFrom(var0)) {
                                return Datatype.Date;
                            } else if (List.class.isAssignableFrom(var0)) {
                                return Datatype.List;
                            } else if (Map.class.isAssignableFrom(var0)) {
                                return Datatype.Map;
                            } else if (Set.class.isAssignableFrom(var0)) {
                                return Datatype.Set;
                            } else if (Enum.class.isAssignableFrom(var0)) {
                                return Datatype.Enum;
                            } else {
                                return !Character.class.isAssignableFrom(var0) && !Character.TYPE.isAssignableFrom(var0) ? Datatype.Object : Datatype.Char;
                            }
                        } else {
                            return Datatype.Double;
                        }
                    } else {
                        return Datatype.Long;
                    }
                } else {
                    return Datatype.Float;
                }
            } else {
                return Datatype.Integer;
            }
        } else {
            return Datatype.Boolean;
        }
    }
}
