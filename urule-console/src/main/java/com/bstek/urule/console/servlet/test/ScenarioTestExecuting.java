//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.test;

import com.bstek.urule.Utils;
import com.bstek.urule.console.repository.scenario.DataField;
import com.bstek.urule.console.repository.scenario.ScenarioRepositoryService;
import com.bstek.urule.console.repository.scenario.SimulateData;
import com.bstek.urule.console.repository.scenario.TestScenario;
import com.bstek.urule.console.servlet.JsonBuilder;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.GeneralEntity;
import com.bstek.urule.model.flow.FlowDefinition;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.assertor.AssertorEvaluator;
import com.bstek.urule.runtime.log.LogManager;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ScenarioTestExecuting {
    private ScenarioRepositoryService a;
    private AssertorEvaluator b;

    public ScenarioTestExecuting(ScenarioRepositoryService var1) {
        this.a = var1;
        this.b = (AssertorEvaluator)Utils.getApplicationContext().getBean("urule.assertorEvaluator");
    }

    public ResultWrapper doTest(TestScenario var1, KnowledgePackage var2, List<VariableCategory> var3, String var4) throws Exception {
        Long var5 = System.currentTimeMillis();
        Map var6 = this.a(var1, var4);
        KnowledgeSession var7 = KnowledgeSessionFactory.newKnowledgeSession(var2);
        Long var8 = System.currentTimeMillis();
        ResultWrapper var9 = new ResultWrapper();
        var9.setPrepareTime(var8 - var5);
        ArrayList var10 = new ArrayList();
        var9.setResultList(var10);
        long var11 = System.currentTimeMillis();
        Iterator var13 = var6.values().iterator();

        while(var13.hasNext()) {
            ScenarioData var14 = (ScenarioData)var13.next();
            List var15 = var14.getInput();
            List var16 = this.a(var15, var3);
            HashMap var17 = new HashMap();
            long var18 = System.currentTimeMillis();
            Iterator var20 = var16.iterator();

            while(true) {
                while(var20.hasNext()) {
                    GeneralEntity var21 = (GeneralEntity)var20.next();
                    if (!var21.getTargetClass().equals("参数") && !var21.getTargetClass().equals(HashMap.class.getName())) {
                        var7.insert(var21);
                    } else {
                        var17.putAll(var21);
                    }
                }

                Map var31 = var2.getFlowMap();
                if (var31 != null && var31.size() > 0) {
                    String var28 = ((FlowDefinition)var31.values().iterator().next()).getId();
                    if (var17.size() > 0) {
                        var7.startProcess(var28, var17);
                    } else {
                        var7.startProcess(var28);
                    }
                } else if (var17.size() > 0) {
                    var7.fireRules(var17);
                } else {
                    var7.fireRules();
                }

                long var29 = System.currentTimeMillis();
                long var23 = var29 - var18;
                TestResult var25 = new TestResult();
                var10.add(var25);
                var25.setScenarioId(var14.getScenarioId());
                var25.setScenarioDesc(var14.getScenarioDesc());
                var25.setConsumeTime(var23);
                var25.setInputData(var14.getInput().toString());
                var25.setOutputData(var14.getOutput() != null ? var14.getOutput().toString() : null);
                LogManager var26 = var7.getLogManager();
                if (var1.isShowLog()) {
                    var25.addLogs(var26.getLogger().getLogs());
                }

                if (var1.isShowFiredFlowNodeList()) {
                    var25.addFlowNodeList(var26.buildFlowNodeData());
                }

                if (var1.isShowNotMatchRuleList()) {
                    var25.addNotMatchedRuleList(var26.buildNotMatchRuleData());
                }

                if (var1.isShowMatchedRuleList()) {
                    var25.addMatchedRuleList(var26.buildMatchedRuleLog());
                }

                List var27 = this.a(var14.getOutput(), var16, var3, var7.getParameters());
                var25.setValueCompares(var27);
                break;
            }
        }

        long var30 = System.currentTimeMillis();
        var9.setTotalTime(var30 - var11);
        var9.setProject(var4);
        return var9;
    }

    private List<ValueCompare> a(List<DataObject> var1, List<GeneralEntity> var2, List<VariableCategory> var3, Map<String, Object> var4) {
        ArrayList var5 = new ArrayList();
        if (var1 == null) {
            return var5;
        } else {
            Iterator var6 = var1.iterator();

            while(var6.hasNext()) {
                DataObject var7 = (DataObject)var6.next();
                String var8 = var7.getName();
                Object var9 = null;
                if (var8.equals("参数")) {
                    var9 = var4;
                } else {
                    VariableCategory var10 = JsonBuilder.getInstance().findVariableCategory(var3, var8);
                    String var11 = var10.getClazz();
                    var9 = this.a(var2, var11);
                }

                Iterator var17 = var7.getFields().iterator();

                while(var17.hasNext()) {
                    ObjectField var18 = (ObjectField)var17.next();
                    String var12 = var18.getName();
                    Object var13 = Utils.getObjectProperty(var9, var12);
                    String var14 = var18.getValue();
                    boolean var15 = this.b.evaluate(var13, var14, var18.getDatatype(), var18.getOp());
                    ValueCompare var16 = new ValueCompare();
                    var16.setMatched(var15);
                    var16.setCategory(var8);
                    var16.setFieldName(var18.getLabel());
                    var16.setData(var13);
                    var16.setExpectedData(var14);
                    var16.setOp(var18.getOp());
                    var5.add(var16);
                }
            }

            return var5;
        }
    }

    private GeneralEntity a(List<GeneralEntity> var1, String var2) {
        Iterator var3 = var1.iterator();

        GeneralEntity var4;
        do {
            if (!var3.hasNext()) {
                throw new RuleException("对象类【" + var2 + "】不存在");
            }

            var4 = (GeneralEntity)var3.next();
        } while(!var4.getTargetClass().equals(var2));

        return var4;
    }

    private List<GeneralEntity> a(List<DataObject> var1, List<VariableCategory> var2) throws Exception {
        ArrayList var3 = new ArrayList();
        Iterator var4 = var1.iterator();

        GeneralEntity var7;
        label81:
        while(var4.hasNext()) {
            DataObject var5 = (DataObject)var4.next();
            VariableCategory var6 = JsonBuilder.getInstance().findVariableCategory(var2, var5.getName());
            var7 = new GeneralEntity(var6.getClazz());
            var3.add(var7);
            Iterator var8 = var5.getFields().iterator();

            while(true) {
                while(true) {
                    ObjectField var9;
                    String var10;
                    do {
                        if (!var8.hasNext()) {
                            continue label81;
                        }

                        var9 = (ObjectField)var8.next();
                        var10 = var9.getValue();
                    } while(var10 == null);

                    Variable var11 = JsonBuilder.getInstance().findVariable(var6, var9.getLabel());
                    Datatype var12 = var11.getType();
                    Object var13 = null;
                    if (!var12.equals(Datatype.Object) && !var12.equals(Datatype.List) && !var12.equals(Datatype.Set)) {
                        var13 = var12.convert(var10);
                    } else {
                        var13 = JsonBuilder.getInstance().buildComplexObject(var10, var2);
                    }

                    Object var14 = var7;
                    String[] var15 = var11.getName().split("\\.");

                    for(int var16 = 0; var16 < var15.length; ++var16) {
                        String var17 = var15[var16];
                        if (var16 == var15.length - 1) {
                            Utils.setObjectProperty(var14, var17, var13);
                            break;
                        }

                        Object var18 = Utils.getObjectProperty(var14, var17);
                        if (var18 == null) {
                            var18 = new HashMap();
                            Utils.setObjectProperty(var14, var17, var18);
                        }

                        var14 = var18;
                    }
                }
            }
        }

        var4 = var2.iterator();

        while(true) {
            VariableCategory var19;
            do {
                if (!var4.hasNext()) {
                    return var3;
                }

                var19 = (VariableCategory)var4.next();
            } while(var19.getName().equals("参数"));

            boolean var20 = false;
            Iterator var21 = var3.iterator();

            while(var21.hasNext()) {
                GeneralEntity var22 = (GeneralEntity)var21.next();
                if (var22.getTargetClass().equals(var19.getClazz())) {
                    var20 = true;
                    break;
                }
            }

            if (!var20) {
                var7 = new GeneralEntity(var19.getClazz());
                var3.add(var7);
            }
        }
    }

    private Map<String, ScenarioData> a(TestScenario var1, String var2) throws Exception {
        InputStream var3 = this.a.loadTestScenarioExcel(var1.getExcelId(), var2);
        XSSFWorkbook var4 = new XSSFWorkbook(var3);
        Map var5 = this.a(var1, var4.getSheetAt(0));
        this.a(var1, var5, var4.getSheetAt(1));
        var4.close();
        var3.close();
        return var5;
    }

    private Map<String, ScenarioData> a(TestScenario var1, XSSFSheet var2) {
        List var3 = var1.getInputData();
        XSSFRow var4 = var2.getRow(0);
        List var5 = var2.getMergedRegions();
        int var6 = 2;
        ArrayList var7 = new ArrayList();

        SimulateData var11;
        for(Cell var8 = var4.getCell(var6); var8 != null; var8 = var4.getCell(var6)) {
            String var9 = this.a(var8);
            if (var9 == null) {
                throw new RuleException("1行" + (var6 + 1) + "列单元格内容不能为空");
            }

            SimulateData var10 = this.b(var3, var9);
            if (var10 == null) {
                throw new RuleException("输入数据对象第1行第" + (var6 + 1) + "列值为\"" + var9 + "\"在配置中未定义!");
            }

            var11 = new SimulateData();
            var11.setName(var9);
            var11.setFields(var10.getFields());
            var7.add(var11);
            var6 += this.a(var5, 0, var6);
        }

        var6 = 2;
        XSSFRow var30 = var2.getRow(1);
        Iterator var31 = var7.iterator();

        while(var31.hasNext()) {
            var11 = (SimulateData)var31.next();
            List var12 = var11.getFields();
            ArrayList var13 = new ArrayList();

            for(int var14 = 0; var14 < var12.size(); ++var14) {
                Cell var15 = var30.getCell(var6);
                String var16 = this.a(var15);
                DataField var17 = this.a(var12, var16, false);
                if (var17 == null) {
                    throw new RuleException("输入数据对象第2行第" + (var6 + 1) + "列值为\"" + var16 + "\"在配置对象\"" + var11.getName() + "\"中不存在!");
                }

                var13.add(var17);
                ++var6;
            }

            var11.setFields(var13);
        }

        LinkedHashMap var32 = new LinkedHashMap();
        int var33 = var2.getLastRowNum();

        for(int var34 = 2; var34 <= var33; ++var34) {
            XSSFRow var35 = var2.getRow(var34);
            if (var35 != null) {
                Cell var36 = var35.getCell(0);
                String var37 = this.a(var36);
                if (!StringUtils.isBlank(var37)) {
                    Cell var38 = var35.getCell(1);
                    String var39 = this.a(var38);
                    if (var32.containsKey(var37)) {
                        throw new RuleException("场景数据定义中场景ID【" + var37 + "】存在重复值");
                    }

                    ScenarioData var18 = new ScenarioData();
                    var32.put(var37, var18);
                    var18.setScenarioId(var37);
                    var18.setScenarioDesc(var39);
                    ArrayList var19 = new ArrayList();
                    var18.setInput(var19);
                    var6 = 2;
                    Iterator var20 = var7.iterator();

                    while(var20.hasNext()) {
                        SimulateData var21 = (SimulateData)var20.next();
                        DataObject var22 = new DataObject();
                        var22.setName(var21.getName());
                        var19.add(var22);
                        ArrayList var23 = new ArrayList();
                        var22.setFields(var23);
                        List var24 = var21.getFields();

                        for(Iterator var25 = var24.iterator(); var25.hasNext(); ++var6) {
                            DataField var26 = (DataField)var25.next();
                            ObjectField var27 = new ObjectField();
                            var23.add(var27);
                            var27.setLabel(var26.getLabel());
                            var27.setName(var26.getName());
                            var27.setOp(var26.getOp());
                            var27.setDatatype(var26.getDatatype());
                            Cell var28 = var35.getCell(var6);
                            String var29 = this.a(var28);
                            var27.setValue(var29);
                        }
                    }
                }
            }
        }

        return var32;
    }

    private DataField a(List<DataField> var1, String var2, boolean var3) {
        Iterator var4 = var1.iterator();

        while(var4.hasNext()) {
            DataField var5 = (DataField)var4.next();
            if (var3) {
                String var6 = "\"" + var5.getLabel() + "\"" + var5.getOp();
                if (var6.equals(var2)) {
                    return var5;
                }
            } else if (var5.getLabel().equals(var2)) {
                return var5;
            }
        }

        return null;
    }

    private SimulateData b(List<SimulateData> var1, String var2) {
        Iterator var3 = var1.iterator();

        SimulateData var4;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            var4 = (SimulateData)var3.next();
        } while(!var2.equals(var4.getName()));

        return var4;
    }

    private String a(Cell var1) {
        CellType var2 = var1.getCellTypeEnum();
        switch(var2) {
            case _NONE:
                return null;
            case BLANK:
                return null;
            case BOOLEAN:
                return String.valueOf(var1.getBooleanCellValue());
            case ERROR:
                return null;
            case FORMULA:
                return String.valueOf(var1.getCellFormula());
            case NUMERIC:
                return this.b(var1);
            case STRING:
                return var1.getStringCellValue();
            default:
                return null;
        }
    }

    private String b(Cell var1) {
        if (DateUtil.isCellDateFormatted(var1)) {
            Date var2 = var1.getDateCellValue();
            if (var2 != null) {
                SimpleDateFormat var3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return var3.format(var2);
            } else {
                return null;
            }
        } else {
            var1.setCellType(CellType.STRING);
            return var1.getStringCellValue();
        }
    }

    private void a(TestScenario var1, Map<String, ScenarioData> var2, XSSFSheet var3) {
        List var4 = var1.getOutputData();
        XSSFRow var5 = var3.getRow(0);
        List var6 = var3.getMergedRegions();
        int var7 = 1;
        ArrayList var8 = new ArrayList();

        for(int var9 = 0; var9 < var4.size(); ++var9) {
            Cell var10 = var5.getCell(var7);
            if (var10 == null) {
                throw new RuleException("[预期结果]中第2行" + (var7 + 1) + "列单元格不存在!");
            }

            String var11 = this.a(var10);
            if (var11 == null) {
                throw new RuleException("1行" + (var7 + 1) + "列单元格内容不能为空");
            }

            SimulateData var12 = this.b(var4, var11);
            if (var12 == null) {
                throw new RuleException("[预期结果]中第1行" + (var7 + 1) + "列值为\"" + var11 + "\"与配置中未定义!");
            }

            SimulateData var13 = new SimulateData();
            var13.setName(var11);
            var13.setFields(var12.getFields());
            var8.add(var13);
            var7 += this.a(var6, 0, var7);
        }

        var7 = 1;
        XSSFRow var26 = var3.getRow(1);
        Iterator var27 = var8.iterator();

        while(var27.hasNext()) {
            SimulateData var29 = (SimulateData)var27.next();
            List var31 = var29.getFields();
            ArrayList var33 = new ArrayList();

            for(int var14 = 0; var14 < var31.size(); ++var14) {
                Cell var15 = var26.getCell(var7);
                if (var15 == null) {
                    throw new RuleException("[预期结果]中第2行" + (var7 + 1) + "列单元格不存在!");
                }

                String var16 = this.a(var15);
                DataField var17 = this.a(var31, var16, true);
                if (var17 == null) {
                    throw new RuleException("[预期结果]中第2行" + (var7 + 1) + "列值为\"" + var16 + "\"在配置对象\"" + var29.getName() + "\"中不存在!");
                }

                var33.add(var17);
                ++var7;
            }

            var29.setFields(var33);
        }

        int var28 = var3.getLastRowNum();

        for(int var30 = 2; var30 <= var28; ++var30) {
            XSSFRow var32 = var3.getRow(var30);
            Cell var34 = var32.getCell(0);
            if (var34 == null) {
                throw new RuleException("[预期结果]中第" + (var30 + 1) + "行1列单元格不存在，此单元格应该定义对应的场景ID，请检查您的Excel.");
            }

            String var35 = this.a(var34);
            if (!StringUtils.isBlank(var35)) {
                if (!var2.containsKey(var35)) {
                    throw new RuleException("[预期结果]中使用的场景ID【" + var35 + "】在场景数据中未定义，请检查您的Excel.");
                }

                ScenarioData var36 = (ScenarioData)var2.get(var35);
                ArrayList var37 = new ArrayList();
                var36.setOutput(var37);
                var7 = 1;
                Iterator var38 = var8.iterator();

                while(var38.hasNext()) {
                    SimulateData var18 = (SimulateData)var38.next();
                    DataObject var19 = new DataObject();
                    var37.add(var19);
                    var19.setName(var18.getName());
                    ArrayList var20 = new ArrayList();
                    var19.setFields(var20);

                    for(Iterator var21 = var18.getFields().iterator(); var21.hasNext(); ++var7) {
                        DataField var22 = (DataField)var21.next();
                        ObjectField var23 = new ObjectField();
                        var20.add(var23);
                        var23.setLabel(var22.getLabel());
                        var23.setName(var22.getName());
                        var23.setOp(var22.getOp());
                        var23.setDatatype(var22.getDatatype());
                        Cell var24 = var32.getCell(var7);
                        if (var24 == null) {
                            throw new RuleException("[预期结果]中第" + (var30 + 1) + "行" + (var7 + 1) + "列单元格不存在，请检查您的Excel.");
                        }

                        String var25 = this.a(var24);
                        var23.setValue(var25);
                    }
                }
            }
        }

    }

    private int a(List<CellRangeAddress> var1, int var2, int var3) {
        Iterator var4 = var1.iterator();

        CellRangeAddress var5;
        do {
            if (!var4.hasNext()) {
                return 1;
            }

            var5 = (CellRangeAddress)var4.next();
        } while(var5.getFirstRow() != var2 || var5.getFirstColumn() != var3);

        return var5.getLastColumn() - var5.getFirstColumn() + 1;
    }
}
