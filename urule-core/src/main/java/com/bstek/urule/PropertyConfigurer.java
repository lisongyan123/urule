//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Properties;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.support.PropertiesLoaderSupport;

public class PropertyConfigurer implements ApplicationContextAware {
    private static Properties a = new Properties();

    public PropertyConfigurer() {
    }

    public static String getProperty(String var0) {
        return a.getProperty(var0);
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        Collection var2 = var1.getBeansOfType(PropertiesLoaderSupport.class).values();
        Iterator var3 = var2.iterator();

        while(var3.hasNext()) {
            PropertiesLoaderSupport var4 = (PropertiesLoaderSupport)var3.next();
            this.a(var4);
        }

    }

    private void a(PropertiesLoaderSupport var1) {
        try {
            Method var2 = PropertiesLoaderSupport.class.getDeclaredMethod("mergeProperties");
            var2.setAccessible(true);
            Object var3 = var2.invoke(var1);
            a.putAll((Properties)var3);
        } catch (Exception var4) {
            throw new RuntimeException(var4);
        }
    }
}
