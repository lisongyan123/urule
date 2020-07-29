//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

import java.util.Date;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;
import org.codehaus.jackson.annotate.JsonSubTypes.Type;
import org.codehaus.jackson.annotate.JsonTypeInfo.As;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;

@JsonTypeInfo(
        use = Id.NAME,
        include = As.PROPERTY,
        property = "type"
)
@JsonSubTypes({@Type(
        value = ReteUnit.class,
        name = "nomal"
), @Type(
        value = MutexReteUnit.class,
        name = "mutex"
)})
@JsonTypeName("nomal")
public class ReteUnit {
    private String ruleName;
    private Date effectiveDate;
    private Date expiresDate;
    private Rete rete;

    public ReteUnit() {
    }

    public ReteUnit(Rete var1, String var2) {
        this.rete = var1;
        this.ruleName = var2;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public void setRuleName(String var1) {
        this.ruleName = var1;
    }

    public Date getEffectiveDate() {
        return this.effectiveDate;
    }

    public void setEffectiveDate(Date var1) {
        this.effectiveDate = var1;
    }

    public Date getExpiresDate() {
        return this.expiresDate;
    }

    public void setExpiresDate(Date var1) {
        this.expiresDate = var1;
    }

    public Rete getRete() {
        return this.rete;
    }

    public void setRete(Rete var1) {
        this.rete = var1;
    }
}
