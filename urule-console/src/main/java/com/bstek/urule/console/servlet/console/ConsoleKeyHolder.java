//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.console;

public class ConsoleKeyHolder {
	private static final ThreadLocal<String> a = new ThreadLocal();

	public ConsoleKeyHolder() {
	}

	public static void setKey(String var0) {
		a.set(var0);
	}

	public static String getKey() {
		return (String)a.get();
	}

	public static void clean() {
		a.remove();
	}
}
