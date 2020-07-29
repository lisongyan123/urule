//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import com.bstek.urule.exception.RuleException;
import java.lang.reflect.Field;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

public class ProxyUtils {
    public ProxyUtils() {
    }

    public static Object getTargetObject(Object var0) {
        if (AopUtils.isAopProxy(var0)) {
            return AopUtils.isJdkDynamicProxy(var0) ? b(var0) : a(var0);
        } else {
            return var0;
        }
    }

    private static Object a(Object var0) {
        try {
            Field var1 = var0.getClass().getDeclaredField("CGLIB$CALLBACK_0");
            var1.setAccessible(true);
            Object var2 = var1.get(var0);
            Field var3 = var2.getClass().getDeclaredField("advised");
            var3.setAccessible(true);
            return ((AdvisedSupport)var3.get(var2)).getTargetSource().getTarget();
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }

    private static Object b(Object var0) {
        try {
            Field var1 = var0.getClass().getSuperclass().getDeclaredField("h");
            var1.setAccessible(true);
            AopProxy var2 = (AopProxy)var1.get(var0);
            Field var3 = var2.getClass().getDeclaredField("advised");
            var3.setAccessible(true);
            return ((AdvisedSupport)var3.get(var2)).getTargetSource().getTarget();
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }
}
