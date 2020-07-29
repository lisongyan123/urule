//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.assertor;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AssertorEvaluator implements ApplicationContextAware {
    public static final String BEAN_ID = "urule.assertorEvaluator";
    private Map<Op, Assertor> a = new HashMap();

    public AssertorEvaluator() {
    }

    public boolean evaluate(Object var1, Object var2, Datatype var3, Op var4) {
        Assertor var5 = (Assertor)this.a.get(var4);
        if (var5 == null) {
            throw new RuleException("Unsupport op:" + var4);
        } else {
            return var5.eval(var1, var2, var3);
        }
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        Collection var2 = var1.getBeansOfType(Assertor.class).values();
        Iterator var3 = var2.iterator();

        while(var3.hasNext()) {
            Assertor var4 = (Assertor)var3.next();
            this.a.put(var4.supportOp(), var4);
        }

    }
}
