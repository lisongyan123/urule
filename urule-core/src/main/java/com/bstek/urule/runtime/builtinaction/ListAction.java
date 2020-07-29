//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.builtinaction;

import com.bstek.urule.Utils;
import com.bstek.urule.model.library.action.annotation.ActionBean;
import com.bstek.urule.model.library.action.annotation.ActionMethod;
import com.bstek.urule.model.library.action.annotation.ActionMethodParameter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@ActionBean(
        name = "List集合"
)
public class ListAction {
    public ListAction() {
    }

    @ActionMethod(
            name = "求List大小"
    )
    @ActionMethodParameter(
            names = {"集合对象"}
    )
    public int size(List<Object> var1) {
        return var1.size();
    }

    @ActionMethod(
            name = "求List中所有的数字最大值"
    )
    @ActionMethodParameter(
            names = {"包含所有数字的集合对象"}
    )
    public Number max(List<Object> var1) {
        if (var1.size() == 0) {
            return null;
        } else {
            double var2 = Utils.toBigDecimal(var1.get(0)).doubleValue();

            BigDecimal var6;
            for(Iterator var4 = var1.iterator(); var4.hasNext(); var2 = Math.max(var2, var6.doubleValue())) {
                Object var5 = var4.next();
                var6 = Utils.toBigDecimal(var5);
            }

            return var2;
        }
    }

    @ActionMethod(
            name = "求List中所有的数字最小值"
    )
    @ActionMethodParameter(
            names = {"包含所有数字的集合对象"}
    )
    public Number min(List<Object> var1) {
        if (var1.size() == 0) {
            return null;
        } else {
            double var2 = Utils.toBigDecimal(var1.get(0)).doubleValue();

            BigDecimal var6;
            for(Iterator var4 = var1.iterator(); var4.hasNext(); var2 = Math.min(var2, var6.doubleValue())) {
                Object var5 = var4.next();
                var6 = Utils.toBigDecimal(var5);
            }

            return var2;
        }
    }

    @ActionMethod(
            name = "向List中添加对象"
    )
    @ActionMethodParameter(
            names = {"集合对象", "要添加的对象"}
    )
    public void add(List<Object> var1, Object var2) {
        var1.add(var2);
    }

    @ActionMethod(
            name = "集合排序"
    )
    @ActionMethodParameter(
            names = {"集合对象", "属性名", "排序方式"}
    )
    public List<Object> sort(List<Object> var1, final String var2, String var3) {
        final boolean var4 = this.a(var3);
        Collections.sort(var1, new Comparator<Object>() {
            public int compare(Object var1, Object var2x) {
                int var3 = 0;
                String[] var4x = var2.split(",");
                String[] var5 = var4x;
                int var6 = var4x.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    String var8 = var5[var7];
                    var3 = this.a(var8, var4, var1, var2x);
                    if (var3 != 0) {
                        break;
                    }
                }

                return var3;
            }

            private int a(String var1, boolean var2x, Object var3, Object var4x) {
                Object var5 = Utils.getObjectProperty(var3, var1);
                Object var6 = Utils.getObjectProperty(var4x, var1);
                if (var5 == null) {
                    return var2x ? 0 : 1;
                } else if (var6 == null) {
                    return var2x ? 1 : 0;
                } else if (var5 instanceof String) {
                    return var2x ? ((String)var5).compareTo(var6.toString()) : ((String)var6).compareTo(var5.toString());
                } else if (var5 instanceof Date) {
                    return var2x ? ((Date)var5).compareTo((Date)var6) : ((Date)var6).compareTo((Date)var5);
                } else if (var5 instanceof Number) {
                    BigDecimal var7 = Utils.toBigDecimal(var5);
                    BigDecimal var8 = Utils.toBigDecimal(var6);
                    return var2x ? var7.compareTo(var8) : var8.compareTo(var7);
                } else {
                    return 0;
                }
            }
        });
        return var1;
    }

    private boolean a(String var1) {
        if (var1 == null) {
            return true;
        } else {
            return var1.equals("1") || var1.equals("true") || var1.equals("正序");
        }
    }

    @ActionMethod(
            name = "抽取集合属性"
    )
    @ActionMethodParameter(
            names = {"集合对象", "属性名"}
    )
    public List<Object> retrive(List<Object> var1, String var2) {
        ArrayList var3 = new ArrayList();
        if (var1 == null) {
            return var3;
        } else {
            Iterator var4 = var1.iterator();

            while(var4.hasNext()) {
                Object var5 = var4.next();
                Object var6 = Utils.getObjectProperty(var5, var2);
                var3.add(var6);
            }

            return var3;
        }
    }

    @ActionMethod(
            name = "从List中删除对象"
    )
    @ActionMethodParameter(
            names = {"集合对象", "要删除的对象"}
    )
    public void remove(List<Object> var1, Object var2) {
        var1.remove(var2);
    }

    @ActionMethod(
            name = "指定对象是否存在"
    )
    @ActionMethodParameter(
            names = {"集合对象", "要判断的对象"}
    )
    public boolean contains(List<Object> var1, Object var2) {
        return var1.contains(var2);
    }

    @ActionMethod(
            name = "List是否为空"
    )
    @ActionMethodParameter(
            names = {"集合对象"}
    )
    public boolean isEmpty(List<Object> var1) {
        return var1.isEmpty();
    }

    @ActionMethod(
            name = "实例化一个ArrayList"
    )
    @ActionMethodParameter(
            names = {}
    )
    public ArrayList<Object> newArrayListInstance() {
        return new ArrayList();
    }
}
