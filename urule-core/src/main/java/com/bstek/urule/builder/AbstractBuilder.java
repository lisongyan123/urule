//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder;

import com.bstek.urule.builder.resource.ResourceBuilder;
import com.bstek.urule.builder.resource.ResourceProvider;
import com.bstek.urule.exception.RuleException;
import java.util.Collection;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class AbstractBuilder implements ApplicationContextAware {
    protected Collection<ResourceProvider> providers;
    protected ApplicationContext applicationContext;
    protected Collection<ResourceBuilder> resourceBuilders;

    public AbstractBuilder() {
    }

    public ResourceBase newResourceBase() {
        return new ResourceBase(this.providers);
    }

    protected Element parseResource(String var1) {
        try {
            Document var2 = DocumentHelper.parseText(var1);
            Element var3 = var2.getRootElement();
            return var3;
        } catch (DocumentException var4) {
            throw new RuleException(var4);
        }
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.resourceBuilders = var1.getBeansOfType(ResourceBuilder.class).values();
        this.providers = var1.getBeansOfType(ResourceProvider.class).values();
        this.applicationContext = var1;
        var1.getBeansWithAnnotation(SuppressWarnings.class);
    }
}
