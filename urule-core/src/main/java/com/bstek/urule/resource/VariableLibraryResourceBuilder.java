//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.library.variable.VariableLibrary;
import com.bstek.urule.parse.deserializer.VariableLibraryDeserializer;
import org.dom4j.Element;

import java.util.Iterator;

public class VariableLibraryResourceBuilder implements ResourceBuilder<VariableLibrary> {
    private VariableLibraryDeserializer a;

    public VariableLibraryResourceBuilder() {
    }

    public VariableLibrary build(Element var1, String var2) {
        VariableLibrary var3 = new VariableLibrary();
        var3.setVariableCategories(this.a.deserialize(var1));
        Iterator var4 = var3.getVariableCategories().iterator();

        while(var4.hasNext()) {
            VariableCategory var5 = (VariableCategory)var4.next();
            var5.setFile(var2);
        }

        return var3;
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public ResourceType getType() {
        return ResourceType.VariableLibrary;
    }

    public void setVariableLibraryDeserializer(VariableLibraryDeserializer var1) {
        this.a = var1;
    }
}
