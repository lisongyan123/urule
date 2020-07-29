//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import com.bstek.urule.exception.RuleAssertException;
import com.bstek.urule.model.GeneralEntity;
import java.util.Collection;

public class ObjectTypeActivity extends AbstractActivity {
    private Class<?> a;
    private String b;

    public ObjectTypeActivity(String var1) {
        this.b = var1;
    }

    public ObjectTypeActivity(Class<?> var1) {
        this.a = var1;
    }

    public Collection<FactTracker> enter(EvaluationContext var1, Object var2, FactTracker var3) {
        try {
            var3.setToken(var1.nextToken());
            return this.visitPahs(var1, var2, var3);
        } catch (Exception var6) {
            String var5 = var1.getTipMsg();
            throw new RuleAssertException(var5, var6);
        }
    }

    public boolean support(Object var1) {
        if (this.b != null && this.b.equals("__*__") && this.b.equals(var1)) {
            return true;
        } else if (this.a == null && this.b == null) {
            return true;
        } else {
            if (var1 instanceof GeneralEntity) {
                GeneralEntity var2 = (GeneralEntity)var1;
                String var3 = var2.getTargetClass();
                if (this.b != null) {
                    if (var3.equals(this.b)) {
                        return true;
                    }
                } else if (var3.equals(this.a.getName())) {
                    return true;
                }
            } else if (this.a != null) {
                Class var4 = var1.getClass();
                if (this.a.isAssignableFrom(var4) || this.a.getName().equals(var4.getName())) {
                    return true;
                }
            }

            return false;
        }
    }

    public void reset() {
    }
}

