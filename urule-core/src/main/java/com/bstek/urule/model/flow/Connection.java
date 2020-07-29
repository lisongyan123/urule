//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.runtime.KnowledgePackageWrapper;
import com.bstek.urule.runtime.KnowledgeSession;

import java.util.Iterator;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;

public class Connection {
    public static final String RETURN_VALUE_KEY = "return_value__";
    private String name;
    private String toName;
    private String script;
    private String g;
    private KnowledgePackageWrapper knowledgePackageWrapper;
    @JsonIgnore
    private FlowNode to;

    public Connection() {
    }

    public boolean evaluate(FlowContext var1) {
        if (this.knowledgePackageWrapper == null) {
            return true;
        } else {
            KnowledgeSession var2 = (KnowledgeSession)var1.getWorkingMemory();
            KnowledgeSession var3 = KnowledgeSessionFactory.newKnowledgeSession(this.knowledgePackageWrapper, var1, var2);
            var3.fireRules(var1.getVariables());
            var1.addRuleData(var3.getLogManager().getRuleData());
            Object var4 = var3.getParameter("return_value__");
            return var4 == null ? false : Boolean.valueOf(var4.toString());
        }
    }

    public void buildDeserialize() {
        if (this.knowledgePackageWrapper != null) {
            this.knowledgePackageWrapper.buildDeserialize();
        }

    }

    public void execute(FlowContext var1, FlowInstance var2) {
        this.to.enter(var1, var2);
    }

    public String buildDSLScript(List<Library> var1) {
        StringBuffer var2 = new StringBuffer();
        if (var1 != null) {
            Iterator var3 = var1.iterator();

            while(var3.hasNext()) {
                Library var4 = (Library)var3.next();
                String var5 = var4.getPath();
                if (var4.getVersion() != null) {
                    var5 = var5 + ":" + var4.getVersion();
                }

                LibraryType var6 = var4.getType();
                switch(var6) {
                    case Action:
                        var2.append("importActionLibrary \"" + var5 + "\"");
                        var2.append("\r\n");
                        break;
                    case Constant:
                        var2.append("importConstantLibrary \"" + var5 + "\"");
                        var2.append("\r\n");
                        break;
                    case Parameter:
                        var2.append("importParameterLibrary \"" + var5 + "\"");
                        var2.append("\r\n");
                        break;
                    case Variable:
                        var2.append("importVariableLibrary \"" + var5 + "\"");
                        var2.append("\r\n");
                }
            }
        }

        var2.append("rule \"conn\"");
        var2.append("\r\n");
        var2.append("if");
        var2.append("\r\n");
        var2.append(this.script);
        var2.append("\r\n");
        var2.append("then");
        var2.append("\r\n");
        var2.append("parameter.return_value__=true");
        var2.append("\r\n");
        var2.append("end");
        return var2.toString();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public String getToName() {
        return this.toName;
    }

    public void setToName(String var1) {
        this.toName = var1;
    }

    public FlowNode getTo() {
        return this.to;
    }

    public void setTo(FlowNode var1) {
        this.to = var1;
    }

    public String getScript() {
        return this.script;
    }

    public void setScript(String var1) {
        this.script = var1;
    }

    public KnowledgePackageWrapper getKnowledgePackageWrapper() {
        return this.knowledgePackageWrapper;
    }

    public void setKnowledgePackageWrapper(KnowledgePackageWrapper var1) {
        this.knowledgePackageWrapper = var1;
    }

    public String getG() {
        return this.g;
    }

    public void setG(String var1) {
        this.g = var1;
    }
}
