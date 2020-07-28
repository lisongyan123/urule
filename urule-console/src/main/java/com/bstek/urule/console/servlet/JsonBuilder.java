//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;

import com.bstek.urule.console.servlet.rest.MultiData;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.GeneralEntity;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.library.variable.VariableCategory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonBuilder {
    private Logger a = Logger.getGlobal();
    private static JsonBuilder b = new JsonBuilder();

    public JsonBuilder() {
    }

    public static JsonBuilder getInstance() {
        return b;
    }

    public Object buildComplexObject(Object var1, List<VariableCategory> var2) throws Exception {
        if (var1 instanceof String) {
            String var14 = var1.toString().trim();
            ObjectMapper var15 = new ObjectMapper();
            if (var14.startsWith("{") && var14.endsWith("}")) {
                Map var18 = (Map)var15.readValue(var14, HashMap.class);
                Object var21 = var18.get("data");
                if (var21 != null && var21 instanceof List) {
                    List var23 = (List)var21;
                    return this.a(var23, var2);
                } else {
                    return this.a(var2, var18);
                }
            } else if (var14.startsWith("[") && var14.endsWith("]")) {
                ArrayList var17 = new ArrayList();
                List var20 = (List)var15.readValue(var14, ArrayList.class);
                Iterator var22 = var20.iterator();

                while(var22.hasNext()) {
                    Map var24 = (Map)var22.next();
                    var17.add(this.a(var2, var24));
                }

                return var17;
            } else if (!this.a(var14)) {
                return var14;
            } else {
                try {
                    Base64 var16 = new Base64();
                    ByteArrayOutputStream var19 = new ByteArrayOutputStream();
                    ByteArrayInputStream var7 = new ByteArrayInputStream(var16.decode(var14.getBytes("utf-8")));
                    GZIPInputStream var8 = new GZIPInputStream(var7);
                    byte[] var9 = new byte[1024];
                    boolean var10 = true;

                    int var25;
                    while((var25 = var8.read(var9)) != -1) {
                        var19.write(var9, 0, var25);
                    }

                    var7.close();
                    var8.close();
                    String var11 = var19.toString();
                    return this.buildComplexObject(var11, var2);
                } catch (Exception var12) {
                    this.a.warning(var12.getMessage());
                    this.a.warning("Fail to decode data :" + var14 + "");
                    return var14;
                }
            }
        } else if (!(var1 instanceof List)) {
            if (var1 instanceof Map) {
                Map var13 = (Map)var1;
                return this.a(var2, var13);
            } else {
                return var1;
            }
        } else {
            ArrayList var3 = new ArrayList();
            List var4 = (List)var1;
            Iterator var5 = var4.iterator();

            while(var5.hasNext()) {
                Map var6 = (Map)var5.next();
                var3.add(this.a(var2, var6));
            }

            return var3;
        }
    }

    private MultiData a(List<Object> var1, List<VariableCategory> var2) throws Exception {
        ArrayList var3 = new ArrayList();
        Iterator var4 = var1.iterator();

        while(true) {
            while(var4.hasNext()) {
                Object var5 = var4.next();
                if (var5 instanceof List) {
                    ArrayList var11 = new ArrayList();
                    var3.add(var11);
                    List var7 = (List)var5;
                    Iterator var8 = var7.iterator();

                    while(var8.hasNext()) {
                        Object var9 = var8.next();
                        if (!(var9 instanceof Map)) {
                            this.a.warning("[" + var9 + "] is not a map data!");
                        } else {
                            Map var10 = (Map)var9;
                            var11.add(this.a(var2, var10));
                        }
                    }
                } else {
                    Map var6 = (Map)var5;
                    var3.add(this.a(var2, var6));
                }
            }

            return new MultiData(var3);
        }
    }

    private boolean a(String var1) {
        String var2 = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
        return var1.matches(var2);
    }

    private Map<String, Object> a(List<VariableCategory> var1, Map<String, Object> var2) throws Exception {
        String var3 = (String)var2.get("name");
        if (StringUtils.isBlank(var3)) {
            throw new RuleException("复杂对象值【" + var2 + "】需要一个名为\"name\"的属性值来标明当前对象类型");
        } else {
            VariableCategory var4 = this.findVariableCategory(var1, var3);
            Object var5 = null;
            if (var3.equals("参数")) {
                var5 = new HashMap();
            } else {
                var5 = new GeneralEntity(var4.getClazz());
            }

            Object var6 = var2.get("fields");
            if (var6 == null) {
                throw new RuleException("复杂对象值【" + var2 + "】需要一个名为\"fields\"的属性值来标明当前对象有哪些属性.");
            } else {
                Iterator var8;
                if (var6 instanceof List) {
                    List var7 = (List)var6;
                    var8 = var7.iterator();

                    while(true) {
                        while(true) {
                            String var10;
                            Object var11;
                            do {
                                if (!var8.hasNext()) {
                                    return (Map)var5;
                                }

                                Map var9 = (Map)var8.next();
                                var10 = (String)var9.get("name");
                                if (StringUtils.isBlank(var10)) {
                                    throw new RuleException("子对象需要有一个名为\"" + var10 + "\"的属性来标明对象的具体属性名");
                                }

                                var11 = var9.get("value");
                            } while(var11 == null);

                            Variable var12 = this.findVariable(var4, var10);
                            Datatype var13 = var12.getType();
                            if (!var13.equals(Datatype.Object) && !var13.equals(Datatype.List)) {
                                ((Map)var5).put(var12.getName(), var13.convert(var11));
                            } else {
                                ((Map)var5).put(var12.getName(), this.buildComplexObject(var11, var1));
                            }
                        }
                    }
                } else if (!(var6 instanceof Map)) {
                    throw new RuleException("复杂对象值【" + var2 + "】的\"fields\"的属性值必须是一个对象类型或集合类型.");
                } else {
                    Map var15 = (Map)var6;
                    var8 = var15.keySet().iterator();

                    while(true) {
                        while(true) {
                            String var16;
                            Object var17;
                            do {
                                if (!var8.hasNext()) {
                                    return (Map)var5;
                                }

                                var16 = (String)var8.next();
                                var17 = var15.get(var16);
                            } while(var17 == null);

                            Variable var18 = this.findVariable(var4, var16);
                            Datatype var19 = var18.getType();
                            JsonBuilder.SubObject var20 = this.a((String)var18.getName(), (Map)var5);
                            Map var14 = var20.getMap();
                            if (!var19.equals(Datatype.Object) && !var19.equals(Datatype.List)) {
                                var14.put(var20.getName(), var19.convert(var17));
                            } else {
                                var14.put(var20.getName(), this.buildComplexObject(var17, var1));
                            }
                        }
                    }
                }
            }
        }
    }

    private JsonBuilder.SubObject a(String var1, Map<String, Object> var2) {
        Map var3 = var2;
        String[] var4 = var1.split("\\.");

        for(int var5 = 0; var5 < var4.length - 1; ++var5) {
            String var6 = var4[var5];
            if (var2.containsKey(var6)) {
                var3 = (Map)var2.get(var6);
            } else {
                var3.put(var6, new HashMap());
                var3 = (Map)var2.get(var6);
            }
        }

        var1 = var4[var4.length - 1];
        return new JsonBuilder.SubObject(var1, var3);
    }

    public VariableCategory findVariableCategory(List<VariableCategory> var1, String var2) {
        Iterator var3 = var1.iterator();

        VariableCategory var4;
        do {
            if (!var3.hasNext()) {
                throw new RuleException("变量对象【" + var2 + "】未定义!");
            }

            var4 = (VariableCategory)var3.next();
        } while(!var2.equals(var4.getName()) && !var2.equals(var4.getClazz()));

        return var4;
    }

    public Variable findVariable(VariableCategory var1, String var2) {
        Iterator var3 = var1.getVariables().iterator();

        Variable var4;
        do {
            if (!var3.hasNext()) {
                throw new RuleException("变量对象【" + var1.getName() + "】中未定义名为【" + var2 + "】字段！");
            }

            var4 = (Variable)var3.next();
        } while(!var4.getLabel().equals(var2) && !var4.getName().equals(var2));

        return var4;
    }

    class SubObject {
        private String b;
        private Map<String, Object> c;

        public SubObject(String var2, Map<String, Object> var3) {
            this.b = var2;
            this.c = var3;
        }

        public Map<String, Object> getMap() {
            return this.c;
        }

        public String getName() {
            return this.b;
        }
    }
}
