//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder.resource;

import com.bstek.urule.exception.RuleException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FileResourceProvider implements ResourceProvider, ApplicationContextAware {
    private ApplicationContext a;

    public FileResourceProvider() {
    }

    public Resource provide(String var1, String var2) {
        try {
            InputStream var3 = this.a.getResource(var1).getInputStream();
            String var4 = IOUtils.toString(var3, "utf-8");
            IOUtils.closeQuietly(var3);
            return new Resource(var4, var1, var2);
        } catch (IOException var5) {
            throw new RuleException(var5);
        }
    }

    public boolean support(String var1) {
        return var1.startsWith("classpath:") || var1.startsWith("file:") || var1.startsWith("WEB-INF/");
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.a = var1;
    }
}
