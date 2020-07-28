package com.bstek.urule.console.exception;

public class NoPermissionException extends RuntimeException {
	private static final long a = 441877650698078466L;

	public NoPermissionException() {
		super("无权限执行此操作");
	}
}