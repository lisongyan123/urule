//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.builtinaction;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.GeneralEntity;
import com.bstek.urule.model.library.action.annotation.ActionBean;
import com.bstek.urule.model.library.action.annotation.ActionMethod;
import com.bstek.urule.model.library.action.annotation.ActionMethodParameter;

@ActionBean(
        name = "对象"
)
public class ObjectAction {
    public ObjectAction() {
    }

    @ActionMethod(
            name = "对象实例化"
    )
    @ActionMethodParameter(
            names = {"完整类路径"}
    )
    public Object newObjectInstance(String var1) {
        try {
            Class var2 = Class.forName(var1);
            return var2.newInstance();
        } catch (Exception var3) {
            return new GeneralEntity(var1);
        }
    }

    @ActionMethod(
            name = "根据对象创建新实例"
    )
    @ActionMethodParameter(
            names = {"目标对象"}
    )
    public Object newObjectInstanceByObject(Object var1) {
        try {
            if (var1 instanceof GeneralEntity) {
                GeneralEntity var2 = (GeneralEntity)var1;
                String var3 = var2.getTargetClass();
                return new GeneralEntity(var3);
            } else {
                return var1.getClass().newInstance();
            }
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }

    @ActionMethod(
            name = "对象取值"
    )
    @ActionMethodParameter(
            names = {"目标对象", "属性名"}
    )
    public Object newObjectInstance(Object var1, String var2) {
        try {
            return Utils.getObjectProperty(var1, var2);
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }
}
