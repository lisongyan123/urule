//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.console;

import com.bstek.urule.runtime.log.DataLog;
import com.bstek.urule.runtime.log.Log;
import com.bstek.urule.runtime.log.LogWriter;
import com.bstek.urule.runtime.log.UnitLog;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ConsoleLogWriter implements LogWriter {
	private DebugMessageHolder a;

	public ConsoleLogWriter() {
	}

	public void write(List<Log> var1) throws IOException {
		StringBuilder var2 = new StringBuilder();
		this.a(var2, var1);
		String var3 = this.a.generateKey();
		ConsoleKeyHolder.setKey(var3);
		this.a.putDebugMessage(var3, var2.toString());
	}

	private void a(StringBuilder var1, List<Log> var2) {
		Iterator var3 = var2.iterator();

		while(var3.hasNext()) {
			Log var4 = (Log)var3.next();
			if (var4 instanceof UnitLog) {
				var1.append("<div style=\"margin:8px;border:solid 1px black;border-radius:5px;padding:5px\">");
				UnitLog var5 = (UnitLog)var4;
				List var6 = var5.getLogs();
				this.a(var1, var6);
				var1.append("</div>");
			} else if (var4 instanceof DataLog) {
				DataLog var7 = (DataLog)var4;
				String var8 = var7.getHtmlMsg();
				var1.append(var8);
			}
		}

	}

	public void setDebugMessageHolder(DebugMessageHolder var1) {
		this.a = var1;
	}
}
