//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.table;

import com.bstek.urule.action.Action;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.table.Cell;
import com.bstek.urule.parse.ActionParser;
import com.bstek.urule.parse.Parser;
import com.bstek.urule.parse.ValueParser;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CellParser implements Parser<Cell>, ApplicationContextAware {
    private JointParser a;
    private ValueParser b;
    private Collection<ActionParser> c;

    public CellParser() {
    }

    public Cell parse(Element var1) {
        Cell var2 = new Cell();
        var2.setRow(Integer.valueOf(var1.attributeValue("row")));
        var2.setCol(Integer.valueOf(var1.attributeValue("col")));
        var2.setRowspan(Integer.valueOf(var1.attributeValue("rowspan")));
        var2.setVariableLabel(var1.attributeValue("var-label"));
        var2.setVariableName(var1.attributeValue("var"));
        var2.setKeyLabel(var1.attributeValue("key-label"));
        var2.setKeyName(var1.attributeValue("key-name"));
        String var3 = var1.attributeValue("datatype");
        if (StringUtils.isNotBlank(var3)) {
            var2.setDatatype(Datatype.valueOf(var3));
        }

        Iterator var4 = var1.elements().iterator();

        while(true) {
            while(true) {
                Object var5;
                do {
                    do {
                        if (!var4.hasNext()) {
                            return var2;
                        }

                        var5 = var4.next();
                    } while(var5 == null);
                } while(!(var5 instanceof Element));

                Element var6 = (Element)var5;
                String var7 = var6.getName();
                if (this.a.support(var7)) {
                    var2.setJoint(this.a.parse(var6));
                } else if (this.b.support(var7)) {
                    var2.setValue(this.b.parse(var6));
                } else {
                    Iterator var8 = this.c.iterator();

                    while(var8.hasNext()) {
                        ActionParser var9 = (ActionParser)var8.next();
                        if (var9.support(var7)) {
                            var2.setAction((Action)var9.parse(var6));
                            break;
                        }
                    }
                }
            }
        }
    }

    public boolean support(String var1) {
        return var1.equals("cell");
    }

    public void setJointParser(JointParser var1) {
        this.a = var1;
    }

    public void setValueParser(ValueParser var1) {
        this.b = var1;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.c = var1.getBeansOfType(ActionParser.class).values();
    }
}
