//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl.builder;

import com.bstek.urule.dsl.RuleParserParser.ResourceContext;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import org.antlr.v4.runtime.ParserRuleContext;

public class LibraryContextBuilder extends AbstractContextBuilder {
    public LibraryContextBuilder() {
    }

    public Library build(ParserRuleContext var1) {
        ResourceContext var2 = (ResourceContext)var1;
        String var3;
        if (var2.importActionLibrary() != null) {
            var3 = BuildUtils.getSTRINGContent(var2.importActionLibrary().STRING());
            return new Library(var3, (String)null, LibraryType.Action);
        } else if (var2.importConstantLibrary() != null) {
            var3 = BuildUtils.getSTRINGContent(var2.importConstantLibrary().STRING());
            return new Library(var3, (String)null, LibraryType.Constant);
        } else if (var2.importVariableLibrary() != null) {
            var3 = BuildUtils.getSTRINGContent(var2.importVariableLibrary().STRING());
            return new Library(var3, (String)null, LibraryType.Variable);
        } else if (var2.importParameterLibrary() != null) {
            var3 = BuildUtils.getSTRINGContent(var2.importParameterLibrary().STRING());
            return new Library(var3, (String)null, LibraryType.Parameter);
        } else {
            throw new RuleException("Unsupport context " + var2.getClass().getName() + "");
        }
    }

    public boolean support(ParserRuleContext var1) {
        return var1 instanceof ResourceContext;
    }
}
