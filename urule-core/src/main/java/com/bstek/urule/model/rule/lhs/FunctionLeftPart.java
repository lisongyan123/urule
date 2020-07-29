//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule.lhs;

import com.bstek.urule.model.rule.Parameter;
import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

public class FunctionLeftPart implements LeftPart {
    @JsonIgnore
    private String id;
    private String name;
    private List<Parameter> parameters;

    public FunctionLeftPart() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
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

                this.id = "[函数]." + this.name + "(" + var1 + ")";
            } else {
                this.id = "[函数]." + this.name;
            }
        }

        return this.id;
    }
}
