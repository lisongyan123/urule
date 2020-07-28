package com.bstek.urule.console.servlet.common;

public class ErrorInfo {
	private int a;
	private int b;
	private String c;

	public ErrorInfo(int var1, int var2, String var3) {
		this.a = var1;
		this.b = var2;
		this.c = var3;
	}

	public int getLine() {
		return this.a;
	}

	public void setLine(int var1) {
		this.a = var1;
	}

	public String getMessage() {
		return this.c;
	}

	public void setMessage(String var1) {
		this.c = var1;
	}

	public int getCharPositionInLine() {
		return this.b;
	}

	public void setCharPositionInLine(int var1) {
		this.b = var1;
	}
}