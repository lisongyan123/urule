//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.constant;

import com.bstek.urule.Splash;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.exception.RuleException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class ConstantServletHandler extends RenderPageServletHandler {
	public ConstantServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = this.retriveMethod(var1);
		if (var3 != null) {
			this.invokeMethod(var3, var1, var2);
		} else {
			VelocityContext var4 = new VelocityContext();
			var4.put("contextPath", var1.getContextPath());
			var4.put("version", Splash.getVersion());
			var4.put("_date_", _DATE);
			var4.put("_lis_", Splash.getFetchVersion());
			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var5 = this.ve.getTemplate("html/constant-editor.html", "utf-8");
			PrintWriter var6 = var2.getWriter();
			var5.merge(var4, var6);
			var6.close();
		}

	}

	public void generateEnum(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
		String var3 = var1.getParameter("clazz");
		Class var4 = Class.forName(var3);
		if (!var4.isEnum()) {
			throw new RuleException("[" + var4 + "]不是一个枚举类！");
		} else {
			Enum[] var6 = (Enum[])var4.getEnumConstants();
			Method var7 = this.a(var4);
			ArrayList var8 = new ArrayList();
			Enum[] var9 = var6;
			int var10 = var6.length;

			for(int var11 = 0; var11 < var10; ++var11) {
				Enum var12 = var9[var11];
				String var13 = var12.name();
				EnumData var14 = new EnumData();
				var14.setName(var13);
				if (var7 != null) {
					Object var15 = var7.invoke(var12);
					if (var15 != null) {
						var14.setLabel(var15.toString());
					}
				}

				if (var14.getLabel() == null) {
					var14.setLabel(var14.getName());
				}

				var8.add(var14);
			}

			this.writeObjectToJson(var2, var8);
		}
	}

	private Method a(Class<?> var1) throws Exception {
		Method var2 = null;
		Method[] var3 = var1.getMethods();
		int var4 = var3.length;

		for(int var5 = 0; var5 < var4; ++var5) {
			Method var6 = var3[var5];
			if (var6.getName().equals("getLabel")) {
				var2 = var6;
				break;
			}
		}

		return var2;
	}

	public String url() {
		return "/constanteditor";
	}
}
