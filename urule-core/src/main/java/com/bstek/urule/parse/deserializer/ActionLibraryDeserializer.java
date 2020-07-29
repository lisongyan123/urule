//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.parse.deserializer;

import com.bstek.urule.model.library.action.ActionLibrary;
import com.bstek.urule.parse.ActionLibraryParser;
import org.dom4j.Element;

public class ActionLibraryDeserializer implements Deserializer<ActionLibrary> {
    public static final String BEAN_ID = "urule.actionLibraryDeserializer";
    private ActionLibraryParser a;

    public ActionLibraryDeserializer() {
    }

    public ActionLibrary deserialize(Element var1) {
        return this.a.parse(var1);
    }

    public boolean support(Element var1) {
        return this.a.support(var1.getName());
    }

    public void setActionLibraryParser(ActionLibraryParser var1) {
        this.a = var1;
    }
}
