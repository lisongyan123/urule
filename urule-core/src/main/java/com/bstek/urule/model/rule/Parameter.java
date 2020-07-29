//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Parameter {
    @JsonIgnore
    private String id;
    private String name;
    private Datatype type;
    private Value value;

    public Parameter() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public Datatype getType() {
        return this.type;
    }

    public void setType(Datatype var1) {
        this.type = var1;
    }

    public Value getValue() {
        return this.value;
    }

    public void setValue(Value var1) {
        this.value = var1;
    }

    public String getId() {
        if (this.id == null) {
            if (this.value == null) {
                throw new RuleException("Parameter [" + this.name + "] not assignment value.");
            }

            this.id = this.value.getId();
        }

        return this.id;
    }
}
