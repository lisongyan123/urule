package com.bstek.urule.console;

public class DefaultPrincipal implements Principal {
	private String a;
	private String b;
	private String c;
	private boolean d;

	public DefaultPrincipal() {
	}

	public String getName() {
		return this.a;
	}

	public void setName(String var1) {
		this.a = var1;
	}

	public String getDisplayName() {
		return this.b;
	}

	public void setDisplayName(String var1) {
		this.b = var1;
	}

	public String getCompanyId() {
		return this.c;
	}

	public void setCompanyId(String var1) {
		this.c = var1;
	}

	public boolean isAdmin() {
		return this.d;
	}

	public void setAdmin(boolean var1) {
		this.d = var1;
	}
}
