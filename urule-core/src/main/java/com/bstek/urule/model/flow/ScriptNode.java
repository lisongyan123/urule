//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.action.Action;
import com.bstek.urule.model.flow.ins.FlowContext;
import com.bstek.urule.model.flow.ins.FlowInstance;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.rule.Rhs;
import com.bstek.urule.model.rule.Rule;
import com.bstek.urule.model.rule.RuleSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ScriptNode extends BindingNode {
    private String script;
    private String actionXml;
    private ActionType actionType;
    private List<Action> actionsData;
    private FlowNodeType type;

    public ScriptNode() {
        this.actionType = ActionType.script;
        this.actionsData = new ArrayList();
        this.type = FlowNodeType.Script;
    }

    public void enterNode(FlowContext var1, FlowInstance var2) {
        var2.setCurrentNode(this);
        this.executeNodeEvent(EventType.enter, var1, var2);
        this.executeKnowledgePackage(var1, var2);
        this.executeNodeEvent(EventType.leave, var1, var2);
        this.leave((String)null, var1, var2);
    }

    public FlowNodeType getType() {
        return this.type;
    }

    public RuleSet buildRuleSet(List<Library> var1, FlowDefinition var2) {
        RuleSet var3 = new RuleSet();
        Rule var4 = new Rule();
        var4.setDebug(var2.isDebug());
        var4.setFile(var2.getFile());
        var4.setName("脚本节点[" + this.name + "]规则");
        Rhs var5 = new Rhs();
        var4.setRhs(var5);
        var5.setActions(this.actionsData);
        if (var1 != null) {
            Iterator var6 = var1.iterator();

            while(var6.hasNext()) {
                Library var7 = (Library)var6.next();
                var3.addLibrary(var7);
            }
        }

        ArrayList var8 = new ArrayList();
        var8.add(var4);
        var3.setRules(var8);
        return var3;
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

        var2.append("rule \"sr\" ");
        var2.append(" ");
        var2.append("if");
        var2.append(" ");
        var2.append("then");
        var2.append(" ");
        if (this.script != null) {
            var2.append(this.script);
        }

        var2.append(" ");
        var2.append("end");
        var2.append(" ");
        return var2.toString();
    }

    public String getScript() {
        return this.script;
    }

    public void setScript(String var1) {
        this.script = var1;
    }

    public ActionType getActionType() {
        return this.actionType;
    }

    public void setActionType(ActionType var1) {
        this.actionType = var1;
    }

    public String getActionXml() {
        return this.actionXml;
    }

    public void setActionXml(String var1) {
        this.actionXml = var1;
    }

    public List<Action> getActionsData() {
        return this.actionsData;
    }

    public void setActionsData(List<Action> var1) {
        this.actionsData = var1;
    }
}
