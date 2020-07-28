//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHolder {
    private static ThreadLocal<HttpServletRequest> a = new ThreadLocal();
    private static ThreadLocal<HttpServletResponse> b = new ThreadLocal();

    public RequestHolder() {
    }

    public static void set(HttpServletRequest var0, HttpServletResponse var1) {
        a.set(var0);
        b.set(var1);
    }

    public static RequestContext newRequestContext() {
        return new RequestContext((HttpServletRequest)a.get(), (HttpServletResponse)b.get());
    }

    public static void reset() {
        a.remove();
        b.remove();
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest)a.get();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse)b.get();
    }
}
