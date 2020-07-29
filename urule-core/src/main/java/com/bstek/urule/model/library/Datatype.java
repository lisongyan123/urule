package com.bstek.urule.model.library;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.codehaus.jackson.map.ObjectMapper;

public enum Datatype {
    String,
    Integer,
    Char,
    Double,
    Long,
    Float,
    BigDecimal,
    Boolean,
    Date,
    List,
    Set,
    Map,
    Enum,
    Object;

    private Datatype() {
    }

    public static final Datatype parse(String var0) {
        if (var0.equals(String.toString())) {
            return String;
        } else if (var0.equals(Integer.toString())) {
            return Integer;
        } else if (var0.equals(Char.toString())) {
            return Char;
        } else if (var0.equals(Double.toString())) {
            return Double;
        } else if (var0.equals(Long.toString())) {
            return Long;
        } else if (var0.equals(Float.toString())) {
            return Float;
        } else if (var0.equals(BigDecimal.toString())) {
            return BigDecimal;
        } else if (var0.equals(Boolean.toString())) {
            return Boolean;
        } else if (var0.equals(Date.toString())) {
            return Date;
        } else if (var0.equals(List.toString())) {
            return List;
        } else if (var0.equals(Set.toString())) {
            return Set;
        } else if (var0.equals(Map.toString())) {
            return Map;
        } else {
            return var0.equals(Enum.toString()) ? Enum : Object;
        }
    }

    public String convertObjectToString(Object var1) {
        if (var1 == null) {
            return "";
        } else if (var1 instanceof String) {
            return var1.toString();
        } else {
            switch(this) {
                case Object:
                    return var1.toString();
                case Date:
                    SimpleDateFormat var2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    return var2.format((Date)var1);
                case List:
                    List var3 = (List)var1;
                    String var4 = "";

                    for(int var10 = 0; var10 < var3.size(); ++var10) {
                        Object var11 = var3.get(var10);
                        if (var10 > 0) {
                            var4 = var4 + ",";
                        }

                        var4 = var4 + var11;
                    }

                    return var4;
                case Set:
                    Set var5 = (Set)var1;
                    String var6 = "";
                    int var7 = 0;

                    for(Iterator var12 = var5.iterator(); var12.hasNext(); ++var7) {
                        Object var13 = var12.next();
                        if (var7 > 0) {
                            var6 = var6 + ",";
                        }

                        var6 = var6 + var13;
                    }

                    return var6;
                case BigDecimal:
                    BigDecimal var8 = Utils.toBigDecimal(var1);
                    return var8.floatValue() + "";
                case Double:
                    Double var9 = Utils.toBigDecimal(var1).doubleValue();
                    return var9.floatValue() + "";
                default:
                    return var1.toString();
            }
        }
    }

    public Object convert(Object var1) {
        switch(this) {
            case Object:
                return var1;
            case Date:
                if (var1 == null) {
                    return null;
                } else {
                    try {
                        if (var1 instanceof Date) {
                            return (Date)var1;
                        } else if (var1.toString().equals("")) {
                            return null;
                        } else {
                            try {
                                SimpleDateFormat var19 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                return var19.parse(var1.toString());
                            } catch (Exception var16) {
                                SimpleDateFormat var21 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                                try {
                                    return var21.parse(var1.toString());
                                } catch (Exception var12) {
                                    var21 = new SimpleDateFormat("yyyy-MM-dd");
                                    return var21.parse(var1.toString());
                                }
                            }
                        }
                    } catch (ParseException var17) {
                        throw new RuleException(var17);
                    }
                }
            case List:
                if (var1 == null) {
                    return null;
                } else if (var1 instanceof List) {
                    return (List)var1;
                } else {
                    String var18 = var1.toString();
                    if (var18.startsWith("[") && var18.endsWith("]")) {
                        ObjectMapper var20 = new ObjectMapper();

                        try {
                            return var20.readValue(var1.toString(), new ArrayListTypeReference());
                        } catch (Exception var15) {
                            throw new RuleException(var15);
                        }
                    }

                    ArrayList var4 = new ArrayList();
                    String[] var5 = var18.split(",");
                    String[] var22 = var5;
                    int var24 = var5.length;

                    for(int var26 = 0; var26 < var24; ++var26) {
                        String var28 = var22[var26];
                        var4.add(var28);
                    }

                    return var4;
                }
            case Set:
                if (var1 == null) {
                    return null;
                } else if (var1 instanceof Set) {
                    return (Set)var1;
                } else {
                    String var6 = var1.toString();
                    if (var6.startsWith("[") && var6.endsWith("]")) {
                        ObjectMapper var23 = new ObjectMapper();

                        try {
                            return var23.readValue(var1.toString(), new TreeSetTypeReference());
                        } catch (Exception var14) {
                            throw new RuleException(var14);
                        }
                    }

                    TreeSet var7 = new TreeSet();
                    String[] var25 = var6.split(",");
                    int var27 = var25.length;

                    for(int var10 = 0; var10 < var27; ++var10) {
                        String var11 = var25[var10];
                        var7.add(var11);
                    }

                    return var7;
                }
            case BigDecimal:
                if (var1 == null) {
                    var1 = "0";
                }

                return Utils.toBigDecimal(var1);
            case Double:
                if (var1 == null) {
                    var1 = "0";
                }

                return Utils.toBigDecimal(var1).doubleValue();
            case String:
                if (var1 == null) {
                    return var1;
                }

                return var1.toString();
            case Integer:
                if (var1 == null || var1.toString().equals("")) {
                    var1 = "0";
                }

                return Utils.toBigDecimal(var1).intValue();
            case Char:
                if (var1 == null) {
                    return '\u0000';
                } else if (var1 instanceof Character) {
                    return (Character)var1;
                } else {
                    String var2 = var1.toString();
                    if (var2.length() == 1) {
                        return var2.toCharArray()[0];
                    }

                    int var3 = Utils.toBigDecimal(var1).intValue();
                    return (char)var3;
                }
            case Long:
                if (var1 == null) {
                    var1 = "0";
                }

                return Utils.toBigDecimal(var1).longValue();
            case Float:
                if (var1 == null) {
                    var1 = "0";
                }

                return Utils.toBigDecimal(var1).floatValue();
            case Boolean:
                if (var1 == null) {
                    var1 = "false";
                }

                return java.lang.Boolean.valueOf(var1.toString());
            case Map:
                if (var1 == null) {
                    return null;
                } else if (var1 instanceof Map) {
                    return (Map)var1;
                } else {
                    ObjectMapper var8 = new ObjectMapper();

                    try {
                        Map var9 = (Map)var8.readValue(var1.toString(), new HashMapTypeReference());
                        return var9;
                    } catch (Exception var13) {
                        throw new RuleException(var13);
                    }
                }
            case Enum:
                return var1;
            default:
                return null;
        }
    }
}
