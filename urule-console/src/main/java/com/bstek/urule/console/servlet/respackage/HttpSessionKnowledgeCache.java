//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.respackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class HttpSessionKnowledgeCache {
	private Map<String, SessionObject> a = new HashMap();

	public HttpSessionKnowledgeCache() {
	}

	public Object get(HttpServletRequest var1, String var2) {
		SessionObject var3 = this.a(var1);
		return var3.get(var2);
	}

	public void put(HttpServletRequest var1, String var2, Object var3) {
		SessionObject var4 = this.a(var1);
		var4.put(var2, var3);
	}

	public void remove(HttpServletRequest var1, String var2) {
		SessionObject var3 = this.a(var1);
		var3.remove(var2);
	}

	private SessionObject a(HttpServletRequest var1) {
		this.a();
		String var2 = var1.getSession().getId();
		SessionObject var3 = null;
		if (this.a.containsKey(var2)) {
			var3 = (SessionObject)this.a.get(var2);
		} else {
			var3 = new SessionObject();
			this.a.put(var2, var3);
		}

		return var3;
	}

	private void a() {
		ArrayList var1 = new ArrayList();
		Iterator var2 = this.a.keySet().iterator();

		String var3;
		while(var2.hasNext()) {
			var3 = (String)var2.next();
			SessionObject var4 = (SessionObject)this.a.get(var3);
			if (var4.isExpired()) {
				var1.add(var3);
			}
		}

		var2 = var1.iterator();

		while(var2.hasNext()) {
			var3 = (String)var2.next();
			this.a.remove(var3);
		}

	}
}
