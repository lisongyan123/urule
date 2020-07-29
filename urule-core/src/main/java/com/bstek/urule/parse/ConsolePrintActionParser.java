//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse;

import com.bstek.urule.action.Action;
import com.bstek.urule.action.ConsolePrintAction;
import java.util.Iterator;
import org.dom4j.Element;

public class ConsolePrintActionParser extends ActionParser {
    public ConsolePrintActionParser() {
    }

    public Action parse(Element var1) {
        ConsolePrintAction var2 = new ConsolePrintAction();
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 != null && var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (this.valueParser.support(var5.getName())) {
                    var2.setValue(this.valueParser.parse(var5));
                    break;
                }
            }
        }

        return var2;
    }

    public boolean support(String var1) {
        return var1.equals("console-print");
    }
}
