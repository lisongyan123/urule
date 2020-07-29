package com.bstek.urule;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.GeneralEntity;
import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.function.FunctionDescriptor;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.rule.Other;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgePackageImpl;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Utils implements ApplicationContextAware {
    private static boolean a;
    private static boolean b;
    private static boolean c;
    private static ApplicationContext d;
    private static Map<String, FunctionDescriptor> e = new HashMap();
    private static Map<String, FunctionDescriptor> f = new HashMap();

    public Utils() {
    }

    public static ApplicationContext getApplicationContext() {
        return d;
    }

    public static String decodeURL(String var0) {
        if (StringUtils.isBlank(var0)) {
            return var0;
        } else {
            try {
                var0 = URLDecoder.decode(var0, "utf-8");
                var0 = URLDecoder.decode(var0, "utf-8");
                return var0;
            } catch (Exception var2) {
                return var0;
            }
        }
    }

    public static String getClassName(Object var0) {
        String var1 = null;
        if (var0 instanceof GeneralEntity) {
            GeneralEntity var2 = (GeneralEntity)var0;
            var1 = var2.getTargetClass();
        } else {
            var1 = var0.getClass().getName();
        }

        return var1;
    }

    public static String decodeContent(String var0) {
        if (StringUtils.isBlank(var0)) {
            return var0;
        } else {
            try {
                var0 = URLDecoder.decode(var0, "utf-8");
                return var0;
            } catch (Exception var2) {
                return var0;
            }
        }
    }

    public static String encodeURL(String var0) {
        if (StringUtils.isBlank(var0)) {
            return var0;
        } else {
            try {
                return URLEncoder.encode(var0, "utf-8");
            } catch (UnsupportedEncodingException var2) {
                throw new RuleException(var2);
            }
        }
    }

    public static String toUTF8(String var0) {
        try {
            if (var0 == null) {
                return null;
            } else {
                byte[] var1 = var0.getBytes("iso8859-1");
                boolean var2 = var0.equals(new String(var1, "iso8859-1"));
                if (var2) {
                    var0 = new String(var1, "utf-8");
                }

                var2 = var0.equals(new String(var0.getBytes("iso8859-1"), "iso8859-1"));
                if (var2) {
                    var0 = new String(var1, "utf-8");
                }

                return var0;
            }
        } catch (Exception var3) {
            throw new RuleException(var3);
        }
    }

    public static Object getObjectProperty(Object var0, String var1) {
        try {
            return PropertyUtils.getProperty(var0, var1);
        } catch (NoSuchMethodException var3) {
            if (c) {
                return null;
            } else {
                throw new RuleException(var3);
            }
        } catch (NestedNullException var4) {
            if (c) {
                return null;
            } else {
                throw new RuleException(var4);
            }
        } catch (Exception var5) {
            throw new RuleException(var5);
        }
    }

    public static void setObjectProperty(Object var0, String var1, Object var2) {
        try {
            BeanUtils.setProperty(var0, var1, var2);
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }

    public static Datatype getDatatype(Object var0) {
        Datatype var1 = null;
        if (var0 == null) {
            var1 = Datatype.Object;
        } else if (var0 instanceof Integer) {
            var1 = Datatype.Integer;
        } else if (var0 instanceof Long) {
            var1 = Datatype.Long;
        } else if (var0 instanceof Double) {
            var1 = Datatype.Double;
        } else if (var0 instanceof Float) {
            var1 = Datatype.Float;
        } else if (var0 instanceof BigDecimal) {
            var1 = Datatype.BigDecimal;
        } else if (var0 instanceof Boolean) {
            var1 = Datatype.Boolean;
        } else if (var0 instanceof Date) {
            var1 = Datatype.Date;
        } else if (var0 instanceof List) {
            var1 = Datatype.List;
        } else if (var0 instanceof Set) {
            var1 = Datatype.Set;
        } else if (var0 instanceof Enum) {
            var1 = Datatype.Enum;
        } else if (var0 instanceof Map) {
            var1 = Datatype.Map;
        } else if (var0 instanceof String) {
            var1 = Datatype.String;
        } else if (var0 instanceof Character) {
            var1 = Datatype.Char;
        } else {
            var1 = Datatype.Object;
        }

        return var1;
    }

    public static BigDecimal toBigDecimal(Object var0) {
        try {
            if (var0 instanceof BigDecimal) {
                return (BigDecimal)var0;
            }

            if (var0 == null) {
                throw new IllegalArgumentException("Null can not to BigDecimal.");
            }

            if (var0 instanceof String) {
                String var3 = (String)var0;
                if (b && "".equals(var3.trim())) {
                    return BigDecimal.valueOf(0L);
                }

                var3 = var3.trim();
                return new BigDecimal(var3);
            }

            if (var0 instanceof Number) {
                return new BigDecimal(var0.toString());
            }

            if (var0 instanceof Character) {
                char var1 = (Character)var0;
                return new BigDecimal(var1);
            }
        } catch (Exception var2) {
            if (var0 != null && "".equals(var0.toString().trim())) {
                throw new NumberFormatException("Can not convert 空格 to number.");
            }

            throw new NumberFormatException("Can not convert " + var0 + " to number.");
        }

        throw new IllegalArgumentException(var0.getClass().getName() + " can not to BigDecimal.");
    }

    public static byte[] compress(String var0) {
        if (var0 == null) {
            return null;
        } else {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            GZIPOutputStream var2 = null;

            try {
                var2 = new GZIPOutputStream(var1);
                var2.write(var0.getBytes("UTF-8"));
                IOUtils.closeQuietly(var2);
                return var1.toByteArray();
            } catch (IOException var4) {
                throw new RuleException(var4);
            }
        }
    }

    public static String uncompress(byte[] var0) {
        if (var0.length < 1) {
            return null;
        } else {
            ByteArrayOutputStream var1 = new ByteArrayOutputStream();
            ByteArrayInputStream var2 = new ByteArrayInputStream(var0);
            GZIPInputStream var3 = null;

            String var5;
            try {
                var3 = new GZIPInputStream(var2);
                byte[] var4 = IOUtils.toByteArray(var3);
                var5 = new String(var4, "UTF-8");
            } catch (IOException var9) {
                throw new RuleException(var9);
            } finally {
                IOUtils.closeQuietly(var1);
                IOUtils.closeQuietly(var2);
                IOUtils.closeQuietly(var3);
            }

            return var5;
        }
    }

    public static Rule buildElseRule(Rule var0) {
        if (var0.getElseRule() != null) {
            return var0.getElseRule();
        } else {
            Other var1 = var0.getOther();
            if (var1 != null && var1.getActions().size() != 0) {
                Rule var2 = new Rule();
                var2.setFile(var0.getFile());
                var2.setName(var0.getName() + " - else");
                var2.setMutexGroup(var0.getMutexGroup());
                var2.setPendedGroup(var0.getPendedGroup());
                var2.setAutoFocus(var0.getAutoFocus());
                var2.setEffectiveDate(var0.getEffectiveDate());
                var2.setExpiresDate(var0.getExpiresDate());
                var2.setEnabled(var0.getEnabled());
                var2.setDebug(var0.getDebug());
                var2.setSalience(var0.getSalience());
                Rhs var3 = new Rhs();
                var2.setRhs(var3);
                var3.setActions(var1.getActions());
                var0.setElseRule(var2);
                return var2;
            } else {
                return null;
            }
        }
    }

    public static String knowledgePackageToString(KnowledgePackage var0) {
        ObjectMapper var1 = new ObjectMapper();
        var1.setSerializationInclusion(Inclusion.NON_NULL);
        var1.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        var1.setDateFormat(new SimpleDateFormat(Configure.getDateFormat()));
        KnowledgePackageWrapper var2 = new KnowledgePackageWrapper(var0);

        try {
            return var1.writeValueAsString(var2);
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }

    public static String knowledgePackageLibToString(KnowledgePackage var0) {
        KnowledgePackageImpl var1 = (KnowledgePackageImpl)var0;
        List var2 = var1.getVariableCategories();
        ObjectMapper var3 = new ObjectMapper();
        var3.setSerializationInclusion(Inclusion.NON_NULL);
        var3.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        var3.setDateFormat(new SimpleDateFormat(Configure.getDateFormat()));

        try {
            return var3.writeValueAsString(var2);
        } catch (Exception var5) {
            throw new RuleException(var5);
        }
    }

    public static List<VariableCategory> stringToKnowledgePackageLib(String var0) {
        try {
            ObjectMapper var1 = new ObjectMapper();
            SimpleDateFormat var2 = new SimpleDateFormat(Configure.getDateFormat());
            var1.getDeserializationConfig().withDateFormat(var2);
            var1.setDateFormat(var2);
            List var3 = (List)var1.readValue(var0, new TypeReference<List<VariableCategory>>() {
            });
            return var3;
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }

    public static KnowledgePackage stringToKnowledgePackage(String var0) {
        try {
            ObjectMapper var1 = new ObjectMapper();
            SimpleDateFormat var2 = new SimpleDateFormat(Configure.getDateFormat());
            var1.getDeserializationConfig().withDateFormat(var2);
            var1.setDateFormat(var2);
            KnowledgePackageWrapper var3 = (KnowledgePackageWrapper)var1.readValue(var0, KnowledgePackageWrapper.class);
            var3.buildDeserialize();
            KnowledgePackage var4 = var3.getKnowledgePackage();
            Map var5 = var4.getFlowMap();
            if (var5 != null && var5.size() > 0) {
                Iterator var6 = var5.values().iterator();

                while(var6.hasNext()) {
                    FlowDefinition var7 = (FlowDefinition)var6.next();
                    var7.buildConnectionToNode();
                }
            }

            return var4;
        } catch (Exception var8) {
            throw new RuleException(var8);
        }
    }

    public static FunctionDescriptor findFunctionDescriptor(String var0) {
        if (!e.containsKey(var0)) {
            throw new RuleException("Function[" + var0 + "] not exist.");
        } else {
            return (FunctionDescriptor)e.get(var0);
        }
    }

    public static Map<String, FunctionDescriptor> getFunctionDescriptorLabelMap() {
        return f;
    }

    public static Map<String, FunctionDescriptor> getFunctionDescriptorMap() {
        return e;
    }

    public void setDebug(boolean var1) {
        a = var1;
    }

    public static void setSpaceToZero(boolean var0) {
        b = var0;
    }

    public static void setSubPropertyNotExistToNull(boolean var0) {
        c = var0;
    }

    public static boolean isDebug() {
        return a;
    }

    public static boolean isSpaceToZero() {
        return b;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        e.clear();
        f.clear();
        Collection var2 = var1.getBeansOfType(FunctionDescriptor.class).values();
        Iterator var3 = var2.iterator();

        while(var3.hasNext()) {
            FunctionDescriptor var4 = (FunctionDescriptor)var3.next();
            if (!var4.isDisabled()) {
                if (e.containsKey(var4.getName())) {
                    throw new RuntimeException("Duplicate function [" + var4.getName() + "]");
                }

                e.put(var4.getName(), var4);
                f.put(var4.getLabel(), var4);
            }
        }

        d = var1;
    }
}
