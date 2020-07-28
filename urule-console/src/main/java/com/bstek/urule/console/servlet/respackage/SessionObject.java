//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.respackage;

import java.util.HashMap;
import java.util.Map;

public class SessionObject {
	private long a = System.currentTimeMillis();
	private static final long b = 1200000L;
	private Map<String, Object> c = new HashMap();

	public SessionObject() {
	}

	public void put(String var1, Object var2) {
		this.a = System.currentTimeMillis();
		if (this.c.containsKey(var1)) {
			this.c.remove(var1);
		}

		this.c.put(var1, var2);
	}

	public Object get(String var1) {
		this.a = System.currentTimeMillis();
		return this.c.get(var1);
	}

	public void remove(String var1) {
		this.a = System.currentTimeMillis();
		this.c.remove(var1);
	}

	public boolean isExpired() {
		long var1 = System.currentTimeMillis();
		long var3 = var1 - this.a;
		return var3 >= 1200000L;
	}
}

