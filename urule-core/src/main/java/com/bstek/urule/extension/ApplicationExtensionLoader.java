//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.extension;

import com.bstek.urule.exception.RuleException;
import java.util.Iterator;
import java.util.ServiceLoader;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationExtensionLoader implements ApplicationContextAware {
    public ApplicationExtensionLoader() {
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        ServiceLoader var2 = ServiceLoader.load(ApplicationExtension.class);
        Iterator var3 = var2.iterator();

        try {
            while(var3.hasNext()) {
                ApplicationExtension var4 = (ApplicationExtension)var3.next();
                System.out.println(">>>加载扩展[" + var4.name() + "]......");
                var4.loadExtension(var1);
            }

        } catch (Exception var5) {
            throw new RuleException(var5);
        }
    }
}
