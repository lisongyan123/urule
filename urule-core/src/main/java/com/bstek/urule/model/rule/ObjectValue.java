//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

public class ObjectValue extends AbstractValue {
    private Object object;

    public ObjectValue(Object var1) {
        this.object = var1;
    }

    public ValueType getValueType() {
        return ValueType.Object;
    }

    public Object getObject() {
        return this.object;
    }

    public String getId() {
        return "[对象]" + this.object.toString();
    }
}
