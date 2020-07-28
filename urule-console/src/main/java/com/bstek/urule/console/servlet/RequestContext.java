//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestContext {
    private HttpServletRequest a;
    private HttpServletResponse b;

    public RequestContext(HttpServletRequest var1, HttpServletResponse var2) {
        this.a = var1;
        this.b = var2;
    }

    public HttpServletRequest getRequest() {
        return this.a;
    }

    public HttpServletResponse getResponse() {
        return this.b;
    }
}