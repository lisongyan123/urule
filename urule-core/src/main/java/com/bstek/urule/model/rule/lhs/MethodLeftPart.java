//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.model.rule.Parameter;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

public class MethodLeftPart implements LeftPart {
    @JsonIgnore
    private String id;
    private String beanId;
    private String beanLabel;
    private String methodName;
    private String methodLabel;
    private List<Parameter> parameters;

    public MethodLeftPart() {
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

    public String getMethodName() {
        return this.methodName;
    }

    public void setMethodName(String var1) {
        this.methodName = var1;
    }

    public String getMethodLabel() {
        return this.methodLabel;
    }

    public void setMethodLabel(String var1) {
        this.methodLabel = var1;
    }

    public List<Parameter> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<Parameter> var1) {
        this.parameters = var1;
    }

    public String getId() {
        if (this.id == null) {
            if (this.parameters != null) {
                String var1 = "";
                int var2 = 0;

                for(Iterator var3 = this.parameters.iterator(); var3.hasNext(); ++var2) {
                    Parameter var4 = (Parameter)var3.next();
                    if (var2 > 0) {
                        var1 = var1 + ",";
                    }

                    var1 = var1 + var4.getId();
                }

                this.id = "[方法]" + this.beanLabel + "." + this.methodLabel + "(" + var1 + ")";
            } else {
                this.id = "[方法]" + this.beanLabel + "." + this.methodLabel;
            }
        }

        return this.id;
    }
}
