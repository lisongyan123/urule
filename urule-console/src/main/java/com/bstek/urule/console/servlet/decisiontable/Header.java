package com.bstek.urule.console.servlet.decisiontable;

public class Header {
    private String a;
    private HeaderType b;

    public Header() {
        this.b = HeaderType.condition;
    }

    public String getName() {
        return this.a;
    }

    public void setName(String var1) {
        this.a = var1;
    }

    public HeaderType getType() {
        return this.b;
    }

    public void setType(HeaderType var1) {
        this.b = var1;
    }
}
