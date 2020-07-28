package com.bstek.urule.console.repository.reference;

import com.bstek.urule.console.repository.BaseRepositoryService;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.exception.RuleException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import org.apache.commons.io.IOUtils;

public class ReferenceServiceImpl extends BaseRepositoryService implements ReferenceService {
    public ReferenceServiceImpl() {
    }

    public List<RefFile> loadReferenceFiles(String var1, SearchItem var2) throws Exception {
        String var3 = this.a(var1, var2, false);
        List var4 = this.a(var1, var3);
        var3 = this.a(var1, var2, true);
        List var5 = this.a(var1, var3);
        var4.addAll(var5);
        return this.a(var4);
    }

    public List<RefFile> loadReferenceFiles(String var1) throws Exception {
        List var2 = this.a((String)var1, (String)null);
        return this.a(var2);
    }

    private List<RefFile> a(List<String> var1) {
        ArrayList var2 = new ArrayList();

        RefFile var5;
        String var7;
        for(Iterator var3 = var1.iterator(); var3.hasNext(); var5.setName(var7)) {
            String var4 = (String)var3.next();
            var5 = new RefFile();
            var2.add(var5);
            var5.setPath(var4);
            if (var4.endsWith(FileType.Ruleset.toString())) {
                var5.setEditor("/ruleseteditor");
                var5.setType("决策集");
            } else if (var4.endsWith(FileType.ConditionTemplate.toString())) {
                var5.setEditor("/conditiontemplate");
                var5.setType("条件模版");
            } else if (var4.endsWith(FileType.ActionTemplate.toString())) {
                var5.setEditor("/actiontemplate");
                var5.setType("动作模版");
            } else if (var4.endsWith(FileType.UL.toString())) {
                var5.setEditor("/uleditor");
                var5.setType("脚本决策集");
            } else if (var4.endsWith(FileType.DecisionTable.toString())) {
                var5.setEditor("/decisiontableeditor");
                var5.setType("决策表");
            } else if (var4.endsWith(FileType.ScriptDecisionTable.toString())) {
                var5.setEditor("/scriptdecisiontableeditor");
                var5.setType("脚本决策表");
            } else if (var4.endsWith(FileType.Crosstab.toString())) {
                var5.setEditor("/crosstabeditor");
                var5.setType("交叉决策表");
            } else if (var4.endsWith(FileType.DecisionTree.toString())) {
                var5.setEditor("/decisiontreeeditor");
                var5.setType("决策树");
            } else if (var4.endsWith(FileType.RuleFlow.toString())) {
                var5.setEditor("/ruleflowdesigner");
                var5.setType("决策流");
            } else if (var4.endsWith(FileType.Scorecard.toString())) {
                var5.setEditor("/scorecardeditor");
                var5.setType("评分卡");
            } else if (var4.endsWith(FileType.ComplexScorecard.toString())) {
                var5.setEditor("/complexscorecardeditor");
                var5.setType("复杂评分卡");
            }

            int var6 = var4.lastIndexOf("/");
            var7 = var4;
            if (var6 > -1) {
                var7 = var4.substring(var6 + 1, var4.length());
            }
        }

        return var2;
    }

    private List<String> a(String var1, String var2) throws Exception {
        Node var3 = this.getRootNode();
        ArrayList var4 = new ArrayList();
        List var5 = this.a(var3, var1);
        Iterator var6 = var5.iterator();

        while(var6.hasNext()) {
            String var7 = (String)var6.next();
            InputStream var8 = this.readFile(var7, (String)null);
            String var9 = IOUtils.toString(var8, "UTF-8");
            var8.close();
            boolean var10 = var9.contains(var1);
            boolean var11 = true;
            if (var2 != null) {
                var11 = var9.contains(var2);
            }

            if (var10 && var11) {
                var4.add(var7);
            }
        }

        return var4;
    }

    private List<String> a(Node var1, String var2) throws Exception {
        String var3 = this.getProject(var2);
        ArrayList var4 = new ArrayList();
        Node var5 = var1.getNode(var3);
        this.a((List)var4, (Node)var5);
        return var4;
    }

    public String getProject(String var1) {
        if (var1.startsWith("/")) {
            var1 = var1.substring(1);
        }

        int var2 = var1.indexOf("/");
        if (var2 == -1) {
            var2 = var1.length();
        }

        return var1.substring(0, var2);
    }

    private void a(List<String> var1, Node var2) throws RepositoryException {
        Node var4;
        for(NodeIterator var3 = var2.getNodes(); var3.hasNext(); this.a(var1, var4)) {
            var4 = var3.nextNode();
            String var5 = var4.getPath();
            if (var5.endsWith(FileType.Ruleset.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.UL.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.DecisionTable.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.ScriptDecisionTable.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.Crosstab.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.DecisionTree.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.RuleFlow.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.Scorecard.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.ComplexScorecard.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.ConditionTemplate.toString())) {
                var1.add(var5);
            } else if (var5.endsWith(FileType.ActionTemplate.toString())) {
                var1.add(var5);
            }
        }

    }

    private String a(String var1, SearchItem var2, boolean var3) {
        StringBuilder var4 = new StringBuilder();
        if (var1.endsWith(FileType.ActionLibrary.toString())) {
            ActionSearchItem var8 = (ActionSearchItem)var2;
            if (var3) {
                var4.append(var8.getBeanLabel());
                var4.append(".");
                var4.append(var8.getMethodLabel());
            } else {
                var4.append("bean=\"" + var8.getBeanName() + "\"");
                var4.append(" bean-label=\"" + var8.getBeanLabel() + "\"");
                var4.append(" method-label=\"" + var8.getMethodLabel() + "\"");
                var4.append(" method-name=\"" + var8.getMethodName() + "\"");
            }

            return var4.toString();
        } else if (var1.endsWith(FileType.ConstantLibrary.toString())) {
            ConstantSearchItem var7 = (ConstantSearchItem)var2;
            if (var3) {
                var4.append(var7.getConstCategoryLabel());
                var4.append(".");
                var4.append(var7.getConstLabel());
            } else {
                var4.append("const-category=\"" + var7.getConstCategoryLabel() + "\"");
                var4.append(" const=\"" + var7.getConstName() + "\"");
            }

            return var4.toString();
        } else if (var1.endsWith(FileType.ParameterLibrary.toString())) {
            ParameterSearchItem var6 = (ParameterSearchItem)var2;
            if (var3) {
                var4.append("参数.");
                var4.append(var6.getVarLabel());
            } else {
                var4.append("var-category=\"参数\"");
                var4.append(" var=\"" + var6.getVarName() + "\"");
            }

            return var4.toString();
        } else if (var1.endsWith(FileType.VariableLibrary.toString())) {
            VariableSearchItem var5 = (VariableSearchItem)var2;
            if (var3) {
                var4.append(var5.getVarCategory());
                var4.append(".");
                var4.append(var5.getVarLabel());
            } else {
                var4.append("var-category=\"" + var5.getVarCategory() + "\"");
                var4.append(" var=\"" + var5.getVarName() + "\"");
            }

            return var4.toString();
        } else {
            throw new RuleException("Unknow file : " + var1);
        }
    }
}
