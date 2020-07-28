package com.bstek.urule.console.repository;

public class ClientConfig {
	private String a;
	private String b;
	private String c;
	private boolean d;

	public ClientConfig() {
	}

	public String getName() {
		return this.a;
	}

	public void setName(String var1) {
		this.a = var1;
	}

	public String getClient() {
		return this.b;
	}

	public void setClient(String var1) {
		this.b = var1;
	}

	public String getProject() {
		return this.c;
	}

	public void setProject(String var1) {
		this.c = var1;
	}

	public boolean isEnabled() {
		return this.d;
	}

	public void setEnabled(boolean var1) {
		this.d = var1;
	}
}