//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.resource;

import com.bstek.urule.model.library.variable.CategoryType;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.parse.deserializer.ParameterLibraryDeserializer;
import org.dom4j.Element;

import java.util.HashMap;

public class ParameterLibraryResourceBuilder implements ResourceBuilder<VariableCategory> {
    private ParameterLibraryDeserializer a;

    public ParameterLibraryResourceBuilder() {
    }

    public VariableCategory build(Element var1, String var2) {
        VariableCategory var3 = new VariableCategory();
        var3.setName("参数");
        var3.setClazz(HashMap.class.getName());
        var3.setType(CategoryType.Clazz);
        var3.setVariables(this.a.deserialize(var1));
        var3.setFile(var2);
        return var3;
    }

    public ResourceType getType() {
        return ResourceType.ParameterLibrary;
    }

    public boolean support(Element var1) {
        return this.a.support(var1);
    }

    public void setParameterLibraryDeserializer(ParameterLibraryDeserializer var1) {
        this.a = var1;
    }
}
