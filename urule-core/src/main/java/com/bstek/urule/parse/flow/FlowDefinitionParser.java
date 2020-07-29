//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.flow;

import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.flow.FlowNode;
import com.bstek.urule.model.flow.StartNode;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.parse.Parser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FlowDefinitionParser implements Parser<FlowDefinition>, ApplicationContextAware {
    private Collection<FlowNodeParser> a;

    public FlowDefinitionParser() {
    }

    public FlowDefinition parse(Element var1) {
        FlowDefinition var2 = new FlowDefinition();
        var2.setId(var1.attributeValue("id"));
        String var3 = var1.attributeValue("debug");
        if (StringUtils.isNotBlank(var3)) {
            var2.setDebug(Boolean.valueOf(var3));
        }

        ArrayList var4 = new ArrayList();
        Iterator var5 = var1.elements().iterator();

        while(true) {
            while(true) {
                Object var6;
                do {
                    do {
                        if (!var5.hasNext()) {
                            var2.setNodes(var4);
                            var2.buildConnectionToNode();
                            return var2;
                        }

                        var6 = var5.next();
                    } while(var6 == null);
                } while(!(var6 instanceof Element));

                Element var7 = (Element)var6;
                String var8 = var7.getName();
                if (var8.equals("import-variable-library")) {
                    var2.addLibrary(this.a(var7, LibraryType.Variable));
                } else if (var8.equals("quick-test-data")) {
                    String var12 = var7.getTextTrim();
                    var2.setQuickTestData(var12);
                } else if (var8.equals("import-constant-library")) {
                    var2.addLibrary(this.a(var7, LibraryType.Constant));
                } else if (var8.equals("import-action-library")) {
                    var2.addLibrary(this.a(var7, LibraryType.Action));
                } else if (var8.equals("import-parameter-library")) {
                    var2.addLibrary(this.a(var7, LibraryType.Parameter));
                } else {
                    Iterator var9 = this.a.iterator();

                    while(var9.hasNext()) {
                        FlowNodeParser var10 = (FlowNodeParser)var9.next();
                        if (var10.support(var7.getName())) {
                            FlowNode var11 = (FlowNode)var10.parse(var7);
                            var4.add(var11);
                            if (var11 instanceof StartNode) {
                                var2.setStartNode((StartNode)var11);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    private Library a(Element var1, LibraryType var2) {
        String var3 = var1.attributeValue("path");
        if (var3.endsWith(".xml")) {
            Library var7 = new Library(var3, (String)null, var2);
            return var7;
        } else {
            int var4 = var3.lastIndexOf(":");
            String var5 = var3.substring(var4 + 1, var3.length());
            if (var5.equals("LATEST")) {
                var5 = null;
            }

            var3 = var3.substring(0, var4);
            Library var6 = new Library(var3, var5, var2);
            return var6;
        }
    }

    public boolean support(String var1) {
        return var1.equals("rule-flow");
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.a = var1.getBeansOfType(FlowNodeParser.class).values();
    }
}
