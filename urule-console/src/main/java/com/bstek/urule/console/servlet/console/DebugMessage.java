//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.console;

public class DebugMessage {
	private String a;
	private long b;

	public DebugMessage(String var1) {
		this.a = var1;
		this.b = System.currentTimeMillis();
	}

	public String getMessage() {
		return this.a;
	}

	public long getTimestamp() {
		return this.b;
	}
}
