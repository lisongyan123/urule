//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ResourceLoaderServletHandler implements ServletHandler, ApplicationContextAware {
	public static final String URL = "/res";
	private ApplicationContext a;

	public ResourceLoaderServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getContextPath() + "/urule" + "/res";
		String var4 = var1.getRequestURI();
		String var5 = var4.substring(var3.length() + 1);
		String var6 = "classpath:" + var5;
		if (var6.endsWith(".js")) {
			var2.setContentType("text/javascript");
		} else if (var6.endsWith(".css")) {
			var2.setContentType("text/css");
		} else if (var6.endsWith(".png")) {
			var2.setContentType("image/png");
		} else if (var6.endsWith(".jpg")) {
			var2.setContentType("image/jpeg");
		} else {
			var2.setContentType("application/octet-stream");
		}

		InputStream var7 = this.a.getResource(var6).getInputStream();
		ServletOutputStream var8 = var2.getOutputStream();

		try {
			IOUtils.copy(var7, var8);
		} finally {
			if (var7 != null) {
				var7.close();
			}

			if (var8 != null) {
				var8.flush();
				var8.close();
			}

		}

	}

	public boolean anonymousAccess() {
		return true;
	}

	public void setApplicationContext(ApplicationContext var1) throws BeansException {
		this.a = var1;
	}

	public String url() {
		return "/res";
	}
}
