package com.bstek.urule.console.repository.model;

import java.util.Date;

public class VersionFile {
	private String a;
	private boolean b = true;
	private String c;
	private String d;
	private String e;
	private Date f;
	private boolean g;
	private long h;

	public VersionFile() {
	}

	public String getPath() {
		return this.a;
	}

	public void setPath(String var1) {
		this.a = var1;
	}

	public boolean isState() {
		return this.b;
	}

	public void setState(boolean var1) {
		this.b = var1;
	}

	public String getCreateUser() {
		return this.c;
	}

	public void setCreateUser(String var1) {
		this.c = var1;
	}

	public String getName() {
		return this.d;
	}

	public void setName(String var1) {
		this.d = var1;
	}

	public String getComment() {
		return this.e;
	}

	public void setComment(String var1) {
		this.e = var1;
	}

	public Date getCreateDate() {
		return this.f;
	}

	public void setCreateDate(Date var1) {
		this.f = var1;
	}

	public boolean isActived() {
		return this.g;
	}

	public void setActived(boolean var1) {
		this.g = var1;
	}

	public long getTimestamp() {
		return this.h;
	}

	public void setTimestamp(long var1) {
		this.h = var1;
	}
}
