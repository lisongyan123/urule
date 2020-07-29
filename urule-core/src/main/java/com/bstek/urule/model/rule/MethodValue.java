//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

public class MethodValue extends AbstractValue {
    private String beanId;
    private String beanLabel;
    private String methodLabel;
    private String methodName;
    private List<Parameter> parameters;
    private ValueType valueType;

    public MethodValue() {
        this.valueType = ValueType.Method;
    }

    public ValueType getValueType() {
        return this.valueType;
    }

    @JsonIgnore
    public String getId() {
        StringBuilder var1 = new StringBuilder();
        var1.append("[BEAN][" + this.beanId + "." + this.methodName + "]");
        if (this.parameters != null) {
            var1.append("(");

            for(int var2 = 0; var2 < this.parameters.size(); ++var2) {
                if (var2 > 0) {
                    var1.append(",");
                }

                Parameter var3 = (Parameter)this.parameters.get(var2);
                var1.append(var3.getId());
            }

            var1.append(")");
        }

        if (this.arithmetic != null) {
            var1.append(this.arithmetic.getId());
        }

        return var1.toString();
    }

    public String getBeanId() {
        return this.beanId;
    }

    public void setBeanId(String var1) {
        this.beanId = var1;
    }

    public String getBeanLabel() {
        return this.beanLabel;
    }

    public void setBeanLabel(String var1) {
        this.beanLabel = var1;
    }

    public String getMethodLabel() {
        return this.methodLabel;
    }

    public void setMethodLabel(String var1) {
        this.methodLabel = var1;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String var1) {
        this.methodName = var1;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<Parameter> var1) {
        this.parameters = var1;
    }
}
