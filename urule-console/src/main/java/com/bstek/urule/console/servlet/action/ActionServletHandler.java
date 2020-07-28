//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.action;

import com.bstek.urule.Splash;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.model.ExposeAction;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.action.Parameter;
import com.bstek.urule.runtime.ProxyUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class ActionServletHandler extends RenderPageServletHandler {
	public ActionServletHandler() {
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
			Template var5 = this.ve.getTemplate("html/action-editor.html", "utf-8");
			PrintWriter var6 = var2.getWriter();
			var5.merge(var4, var6);
			var6.close();
		}

	}

	public void loadMethods(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("beanId");
		Object var4 = this.applicationContext.getBean(var3);
		Object var5 = ProxyUtils.getTargetObject(var4);
		ArrayList var6 = new ArrayList();
		Method[] var7 = var5.getClass().getMethods();
		Method[] var8 = var7;
		int var9 = var7.length;

		for(int var10 = 0; var10 < var9; ++var10) {
			Method var11 = var8[var10];
			ExposeAction var12 = (ExposeAction)var11.getAnnotation(ExposeAction.class);
			if (var12 != null) {
				String var13 = var11.getName();
				com.bstek.urule.model.library.action.Method var14 = new com.bstek.urule.model.library.action.Method();
				var14.setMethodName(var13);
				var14.setName(var12.value());
				var14.setParameters(this.a(var11));
				var6.add(var14);
			}
		}

		this.writeObjectToJson(var2, var6);
	}

	private List<Parameter> a(Method var1) {
		ArrayList var2 = new ArrayList();
		Class[] var3 = var1.getParameterTypes();

		for(int var4 = 0; var4 < var3.length; ++var4) {
			Class var5 = var3[var4];
			Parameter var6 = new Parameter();
			var6.setName("参数" + var4);
			var6.setType(this.a(var5));
			var2.add(var6);
		}

		return var2;
	}

	private Datatype a(Class<?> var1) {
		if (var1.equals(String.class)) {
			return Datatype.String;
		} else if (var1.equals(BigDecimal.class)) {
			return Datatype.BigDecimal;
		} else if (var1.equals(Boolean.class)) {
			return Datatype.Boolean;
		} else if (var1.equals(Boolean.class)) {
			return Datatype.Boolean;
		} else if (var1.equals(Boolean.TYPE)) {
			return Datatype.Boolean;
		} else if (var1.equals(Date.class)) {
			return Datatype.Date;
		} else if (var1.equals(Double.class)) {
			return Datatype.Double;
		} else if (var1.equals(Double.TYPE)) {
			return Datatype.Double;
		} else if (Enum.class.isAssignableFrom(var1)) {
			return Datatype.Enum;
		} else if (var1.equals(Float.class)) {
			return Datatype.Float;
		} else if (var1.equals(Float.TYPE)) {
			return Datatype.Float;
		} else if (var1.equals(Integer.class)) {
			return Datatype.Integer;
		} else if (var1.equals(Integer.TYPE)) {
			return Datatype.Integer;
		} else if (var1.equals(Character.class)) {
			return Datatype.Char;
		} else if (var1.equals(Character.TYPE)) {
			return Datatype.Char;
		} else if (List.class.isAssignableFrom(var1)) {
			return Datatype.List;
		} else if (var1.equals(Long.TYPE)) {
			return Datatype.Long;
		} else if (var1.equals(Long.class)) {
			return Datatype.Long;
		} else if (Map.class.isAssignableFrom(var1)) {
			return Datatype.Map;
		} else {
			return Set.class.isAssignableFrom(var1) ? Datatype.Set : Datatype.Object;
		}
	}

	public String url() {
		return "/actioneditor";
	}
}
