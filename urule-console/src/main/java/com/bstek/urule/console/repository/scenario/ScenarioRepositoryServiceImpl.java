package com.bstek.urule.console.repository.scenario;

import com.bstek.urule.console.repository.BaseRepositoryService;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.rule.Op;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Property;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.value.BinaryImpl;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ScenarioRepositoryServiceImpl extends BaseRepositoryService implements ScenarioRepositoryService {
    public ScenarioRepositoryServiceImpl() {
    }

    public void deleteTestScenarios(String var1, String var2) throws Exception {
        Node var3 = this.getRootNode();
        String var4 = var1 + "/" + "___test__scenario__file__" + var2;
        var4 = this.processPath(var4);
        if (var3.hasNode(var4)) {
            List var5 = this.loadTestScenarios(var1, var2);
            Iterator var6 = var5.iterator();

            while(var6.hasNext()) {
                TestScenario var7 = (TestScenario)var6.next();
                String var8 = var7.getExcelId();
                this.deleteTestScenarioExcel(var8, var1);
            }

            var3.getNode(var4).remove();
            this.session.save();
        }
    }

    public void saveTestScenarios(String var1, String var2, String var3) throws Exception {
        Node var4 = this.getRootNode();
        String var5 = var1 + "/" + "___test__scenario__file__" + var2;
        var5 = this.processPath(var5);
        Node var6 = null;
        if (var4.hasNode(var5)) {
            var6 = var4.getNode(var5);
        } else {
            var6 = var4.addNode(var5);
        }

        BinaryImpl var7 = new BinaryImpl(var3.getBytes("utf-8"));
        var6.setProperty("_data", var7);
        this.session.save();
    }

    public void saveTestScenarioExcel(String var1, String var2, InputStream var3) throws Exception {
        Node var4 = this.getRootNode();
        String var5 = var2 + "/" + "___test__scenario__excel__file__" + "_" + var1;
        var5 = this.processPath(var5);
        if (var4.hasNode(var5)) {
            throw new RuleException("File [" + var5 + "] already exist.");
        } else {
            Node var6 = var4.addNode(var5);
            BinaryImpl var7 = new BinaryImpl(var3);
            var6.setProperty("_data", var7);
            this.session.save();
        }
    }

    public InputStream loadTestScenarioExcel(String var1, String var2) throws Exception {
        Node var3 = this.getRootNode();
        String var4 = var2 + "/" + "___test__scenario__excel__file__" + "_" + var1;
        var4 = this.processPath(var4);
        if (!var3.hasNode(var4)) {
            throw new RuleException("File [" + var4 + "] not exist.");
        } else {
            Node var5 = var3.getNode(var4);
            return var5.getProperty("_data").getBinary().getStream();
        }
    }

    public boolean deleteTestScenarioExcel(String var1, String var2) throws Exception {
        Node var3 = this.getRootNode();
        String var4 = var2 + "/" + "___test__scenario__excel__file__" + "_" + var1;
        var4 = this.processPath(var4);
        if (var3.hasNode(var4)) {
            Node var5 = var3.getNode(var4);
            if (!var5.isCheckedOut()) {
                this.versionManager.checkout(var5.getPath());
            }

            var5.remove();
            this.session.save();
            return true;
        } else {
            return false;
        }
    }

    public List<TestScenario> loadTestScenarios(String var1, String var2) throws Exception {
        ArrayList var3 = new ArrayList();
        Node var4 = this.getRootNode();
        String var5 = var1 + "/" + "___test__scenario__file__" + var2;
        var5 = this.processPath(var5);
        if (!var4.hasNode(var5)) {
            return var3;
        } else {
            Node var6 = var4.getNode(var5);
            Property var7 = var6.getProperty("_data");
            Binary var8 = var7.getBinary();
            InputStream var9 = var8.getStream();
            String var10 = IOUtils.toString(var9, "utf-8");
            var9.close();
            Document var11 = DocumentHelper.parseText(var10);
            Element var12 = var11.getRootElement();
            SimpleDateFormat var13 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Iterator var14 = var12.elements().iterator();

            while(true) {
                Element var16;
                do {
                    Object var15;
                    do {
                        if (!var14.hasNext()) {
                            return var3;
                        }

                        var15 = var14.next();
                    } while(!(var15 instanceof Element));

                    var16 = (Element)var15;
                } while(!var16.getName().equals("scenario"));

                TestScenario var17 = new TestScenario();
                var17.setName(var16.attributeValue("name"));
                var17.setDesc(var16.attributeValue("desc"));
                var17.setExcelId(var16.attributeValue("excel-id"));
                var17.setCreateDate(var13.parse(var16.attributeValue("create-date")));
                var17.setCreateUser(var16.attributeValue("create-user"));
                var17.setShowInputData(Boolean.valueOf(var16.attributeValue("show-input-data")));
                var17.setShowOutputData(Boolean.valueOf(var16.attributeValue("show-output-data")));
                var17.setShowLog(Boolean.valueOf(var16.attributeValue("show-log")));
                var17.setShowFiredFlowNodeList(Boolean.valueOf(var16.attributeValue("show-fired-flow-node-list")));
                var17.setShowFiredFlowNodeSize(Boolean.valueOf(var16.attributeValue("show-fired-flow-node-size")));
                var17.setShowMatchedRuleList(Boolean.valueOf(var16.attributeValue("show-matched-rule-list")));
                var17.setShowMatchedRuleSize(Boolean.valueOf(var16.attributeValue("show-matched-rule-size")));
                var17.setShowNotMatchRuleSize(Boolean.valueOf(var16.attributeValue("show-not-match-rule-size")));
                var17.setShowNotMatchRuleList(Boolean.valueOf(var16.attributeValue("show-not-match-rule-list")));
                var3.add(var17);
                ArrayList var18 = new ArrayList();
                ArrayList var19 = new ArrayList();
                var17.setInputData(var18);
                var17.setOutputData(var19);
                Iterator var20 = var16.elements().iterator();

                while(var20.hasNext()) {
                    Object var21 = var20.next();
                    if (var21 instanceof Element) {
                        Element var22 = (Element)var21;
                        if (var22.getName().equals("input")) {
                            var18.add(this.a(var22));
                        }

                        if (var22.getName().equals("output")) {
                            var19.add(this.a(var22));
                        }
                    }
                }
            }
        }
    }

    private SimulateData a(Element var1) {
        SimulateData var2 = new SimulateData();
        var2.setName(var1.attributeValue("name"));
        ArrayList var3 = new ArrayList();
        var2.setFields(var3);
        Iterator var4 = var1.elements().iterator();

        while(var4.hasNext()) {
            Object var5 = var4.next();
            if (var5 instanceof Element) {
                Element var6 = (Element)var5;
                if (var6.getName().equals("field")) {
                    DataField var7 = new DataField();
                    var7.setName(var6.attributeValue("name"));
                    var7.setLabel(var6.attributeValue("label"));
                    var7.setDatatype(Datatype.valueOf(var6.attributeValue("datatype")));
                    String var8 = var6.attributeValue("op");
                    if (StringUtils.isNotBlank(var8)) {
                        var7.setOp(Op.valueOf(var8));
                    }

                    var3.add(var7);
                }
            }
        }

        return var2;
    }
}
