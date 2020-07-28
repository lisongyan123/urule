//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.scorecard;

public class TableHeader {
	private String a;

	public TableHeader() {
	}

	public boolean isScore() {
		return this.a.equals("分值");
	}

	public boolean isCondition() {
		return this.a.equals("条件");
	}

	public String getName() {
		return this.a;
	}

	public void setName(String var1) {
		this.a = var1;
	}
}
