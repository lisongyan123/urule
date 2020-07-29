//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.debug;

import com.bstek.urule.SpringBootHome;
import com.bstek.urule.runtime.log.DataLog;
import com.bstek.urule.runtime.log.Log;
import com.bstek.urule.runtime.log.LogWriter;
import com.bstek.urule.runtime.log.UnitLog;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class DefaultHtmlFileLogWriter implements LogWriter {
    private String a;

    public DefaultHtmlFileLogWriter() {
    }

    public void write(List<Log> var1) throws IOException {
        if (!StringUtils.isBlank(this.a)) {
            StringBuilder var2 = new StringBuilder();
            this.a(var2, var1);
            SimpleDateFormat var3 = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
            String var4 = this.a + "/urule-debug-" + var3.format(new Date()) + ".html";
            StringBuilder var5 = new StringBuilder();
            var5.append("<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>URule Pro调试日志信息</title><body style='font-size:12px'>");
            var5.append(var2.toString());
            var5.append("</body></html>");
            FileOutputStream var6 = new FileOutputStream(new File(var4));
            IOUtils.write(var5.toString(), var6, "utf-8");
            var6.flush();
            var6.close();
        }
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

    public void setPath(String var1) {
        if (!StringUtils.isBlank(var1)) {
            File var2 = new File(var1);
            if (!var2.exists()) {
                SpringBootHome var3 = new SpringBootHome();
                var2 = var3.findSpringbootJarHomeDir(this.getClass());

                try {
                    String var4 = var2.getAbsolutePath();
                    if (var1.startsWith("/")) {
                        var1 = var4 + var1;
                    } else {
                        var1 = var4 + "/" + var1;
                    }

                    var2 = new File(var1);
                    if (!var2.exists()) {
                        var2.mkdirs();
                    }

                    var1 = var2.getCanonicalPath();
                } catch (IOException var5) {
                }
            }

            this.a = var1;
        }
    }
}
