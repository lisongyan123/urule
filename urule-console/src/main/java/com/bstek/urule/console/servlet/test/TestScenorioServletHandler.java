//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.test;

import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.builder.KnowledgeBase;
import com.bstek.urule.builder.KnowledgeBuilder;
import com.bstek.urule.builder.ResourceBase;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.scenario.DataField;
import com.bstek.urule.console.repository.scenario.ScenarioRepositoryService;
import com.bstek.urule.console.repository.scenario.SimulateData;
import com.bstek.urule.console.repository.scenario.TestScenario;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.console.servlet.SessionStore;
import com.bstek.urule.console.servlet.respackage.HttpSessionKnowledgeCache;
import com.bstek.urule.model.library.Datatype;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.rule.Op;
import com.bstek.urule.runtime.KnowledgePackage;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class TestScenorioServletHandler extends RenderPageServletHandler {
    private ScenarioRepositoryService a;
    private KnowledgeBuilder b;
    private HttpSessionKnowledgeCache c;
    private ScenarioTestExecuting d;
    private Logger e = Logger.getGlobal();

    public TestScenorioServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = this.retriveMethod(var1);
        if (var3 != null) {
            this.invokeMethod(var3, var1, var2);
        } else {
            VelocityContext var4 = new VelocityContext();
            var4.put("contextPath", var1.getContextPath());
            var4.put("version", Splash.getVersion());
            var4.put("_date_", _DATE);
            var4.put("_lis_", Splash.getFetchVersion());
            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var5 = this.ve.getTemplate("html/test-editor.html", "utf-8");
            PrintWriter var6 = var2.getWriter();
            var5.merge(var4, var6);
            var6.close();
        }

    }

    public void doTest(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        TestScenario var3 = this.a(var1);
        String var4 = var1.getParameter("project");
        var4 = Utils.decodeURL(var4);
        String var5 = var1.getParameter("packageName");
        var5 = Utils.decodeURL(var5);
        KnowledgeBase var6 = this.c(var1);
        KnowledgePackage var7 = var6.getKnowledgePackage();
        List var8 = var6.getResourceLibrary().getVariableCategories();
        ResultWrapper var9 = this.d.doTest(var3, var7, var8, var4);
        var9.setPackageName(var5);
        var9.setScenarioName(var3.getName());
        var9.setShowFiredFlowNodeList(var3.isShowFiredFlowNodeList());
        var9.setShowFiredFlowNodeSize(var3.isShowFiredFlowNodeSize());
        var9.setShowInputData(var3.isShowInputData());
        var9.setShowOutputData(var3.isShowOutputData());
        var9.setShowLog(var3.isShowLog());
        var9.setShowMatchedRuleList(var3.isShowMatchedRuleList());
        var9.setShowMatchedRuleSize(var3.isShowMatchedRuleSize());
        var9.setShowNotMatchRuleList(var3.isShowNotMatchRuleList());
        var9.setShowNotMatchRuleSize(var3.isShowNotMatchRuleSize());
        SessionStore.setAttribute("scenario_test_result__", var9);
    }

    private TestScenario a(HttpServletRequest var1) throws IOException, JsonParseException, JsonMappingException {
        String var2 = var1.getParameter("item");
        ObjectMapper var3 = new ObjectMapper();
        Map var4 = (Map)var3.readValue(var2, HashMap.class);
        TestScenario var5 = new TestScenario();
        var5.setName((String)var4.get("name"));
        var5.setDesc((String)var4.get("desc"));
        var5.setCreateUser((String)var4.get("createUser"));
        var5.setExcelId((String)var4.get("excelId"));
        if (var4.get("showInputData") != null) {
            var5.setShowInputData((Boolean)var4.get("showInputData"));
        }

        if (var4.get("showOutputData") != null) {
            var5.setShowOutputData((Boolean)var4.get("showOutputData"));
        }

        if (var4.get("showLog") != null) {
            var5.setShowLog((Boolean)var4.get("showLog"));
        }

        if (var4.get("showMatchedRuleList") != null) {
            var5.setShowMatchedRuleList((Boolean)var4.get("showMatchedRuleList"));
        }

        if (var4.get("showMatchedRuleSize") != null) {
            var5.setShowMatchedRuleSize((Boolean)var4.get("showMatchedRuleSize"));
        }

        if (var4.get("showNotMatchRuleList") != null) {
            var5.setShowNotMatchRuleList((Boolean)var4.get("showNotMatchRuleList"));
        }

        if (var4.get("showNotMatchRuleSize") != null) {
            var5.setShowNotMatchRuleSize((Boolean)var4.get("showNotMatchRuleSize"));
        }

        if (var4.get("showFiredFlowNodeList") != null) {
            var5.setShowFiredFlowNodeList((Boolean)var4.get("showFiredFlowNodeList"));
        }

        if (var4.get("showFiredFlowNodeSize") != null) {
            var5.setShowFiredFlowNodeSize((Boolean)var4.get("showFiredFlowNodeSize"));
        }

        ArrayList var6 = new ArrayList();
        ArrayList var7 = new ArrayList();
        var5.setInputData(var6);
        var5.setOutputData(var7);
        Iterator var8 = ((List)var4.get("inputData")).iterator();

        Map var9;
        SimulateData var10;
        ArrayList var11;
        Iterator var12;
        Map var13;
        DataField var14;
        while(var8.hasNext()) {
            var9 = (Map)var8.next();
            var10 = new SimulateData();
            var10.setName((String)var9.get("name"));
            var11 = new ArrayList();
            var10.setFields(var11);
            var6.add(var10);
            var12 = ((List)var9.get("fields")).iterator();

            while(var12.hasNext()) {
                var13 = (Map)var12.next();
                var14 = new DataField();
                var14.setName((String)var13.get("name"));
                var14.setLabel((String)var13.get("label"));
                var14.setDatatype(Datatype.valueOf((String)var13.get("datatype")));
                var11.add(var14);
            }
        }

        var8 = ((List)var4.get("outputData")).iterator();

        while(var8.hasNext()) {
            var9 = (Map)var8.next();
            var10 = new SimulateData();
            var10.setName((String)var9.get("name"));
            var11 = new ArrayList();
            var10.setFields(var11);
            var7.add(var10);
            var12 = ((List)var9.get("fields")).iterator();

            while(var12.hasNext()) {
                var13 = (Map)var12.next();
                var14 = new DataField();
                var14.setName((String)var13.get("name"));
                var14.setLabel((String)var13.get("label"));
                var14.setDatatype(Datatype.valueOf((String)var13.get("datatype")));
                var14.setOp(Op.valueOf((String)var13.get("op")));
                var11.add(var14);
            }
        }

        return var5;
    }

    public void loadTestScenarios(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("project");
        var3 = Utils.decodeURL(var3);
        String var4 = var1.getParameter("packageId");
        var4 = Utils.decodeURL(var4);
        List var5 = this.a.loadTestScenarios(var3, var4);
        this.writeObjectToJson(var2, var5);
    }

    public void saveTestScenarios(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("project");
        String var4 = var1.getParameter("packageId");
        var3 = Utils.decodeURL(var3);
        var4 = Utils.decodeURL(var4);
        String var5 = var1.getParameter("content");
        var5 = Utils.decodeContent(var5);
        this.a.saveTestScenarios(var3, var4, var5);
    }

    public void uploadScenarioExcel(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("excelId");
        if (StringUtils.isBlank(var3)) {
            var3 = UUID.randomUUID().toString();
        }

        String var4 = var1.getParameter("project");
        var4 = Utils.decodeURL(var4);

        try {
            if (this.a.deleteTestScenarioExcel(var3, var4)) {
                this.e.info("Removed existing excel file.");
            }

            DiskFileItemFactory var5 = new DiskFileItemFactory();
            ServletFileUpload var15 = new ServletFileUpload(var5);
            List var7 = var15.parseRequest(var1);
            Iterator var8 = var7.iterator();

            while(var8.hasNext()) {
                FileItem var9 = (FileItem)var8.next();
                String var10 = var9.getFieldName();
                if (var10.equals("file")) {
                    InputStream var11 = var9.getInputStream();
                    this.a.saveTestScenarioExcel(var3, var4, var11);
                    var11.close();
                    break;
                }
            }

            Principal var16 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
            HashMap var13 = new HashMap();
            var13.put("excelId", var3);
            var13.put("createUser", var16.getName());
            SimpleDateFormat var14 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            var13.put("createDate", var14.format(new Date()));
            this.writeObjectToJson(var2, var13);
        } catch (Exception var12) {
            HashMap var6 = new HashMap();
            var6.put("error", true);
            var6.put("exception", var12.getMessage() == null ? var12.getClass().getName() : var12.getMessage());
            this.writeObjectToJson(var2, var6);
        }

    }

    public void downloadExcel(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        String var3 = var1.getParameter("project");
        var3 = Utils.decodeURL(var3);
        String var4 = var1.getParameter("excelId");
        InputStream var5 = this.a.loadTestScenarioExcel(var4, var3);
        var2.setContentType("application/x-xls");
        var2.setHeader("Content-Disposition", "attachment; filename=urule-scenario-test-data.xlsx");
        ServletOutputStream var6 = var2.getOutputStream();
        IOUtils.copy(var5, var6);
        var6.flush();
        var6.close();
        var5.close();
    }

    public void generateExcel(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = var1.getParameter("input");
        String var4 = var1.getParameter("output");
        var3 = Utils.decodeURL(var3);
        var4 = Utils.decodeURL(var4);
        ObjectMapper var5 = new ObjectMapper();
        List var6 = (List)var5.readValue(var3, ArrayList.class);
        List var7 = (List)var5.readValue(var4, ArrayList.class);
        SXSSFWorkbook var8 = new SXSSFWorkbook();
        this.a(var6, var8);
        this.b(var7, var8);
        var2.setContentType("application/x-xls");
        var2.setHeader("Content-Disposition", "attachment; filename=urule-scenario-test-template.xlsx");
        ServletOutputStream var9 = var2.getOutputStream();
        var8.write(var9);
        var9.flush();
        var9.close();
        var8.close();
    }

    private void a(List<Map<String, Object>> var1, SXSSFWorkbook var2) {
        XSSFCellStyle var3 = (XSSFCellStyle)var2.createCellStyle();
        var3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        var3.setFillForegroundColor(new XSSFColor(new Color(147, 228, 15)));
        this.a(var3);
        XSSFCellStyle var4 = (XSSFCellStyle)var2.createCellStyle();
        var4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        var4.setFillForegroundColor(new XSSFColor(new Color(111, 208, 215)));
        this.a(var4);
        SXSSFSheet var5 = var2.createSheet("场景");
        Row var6 = var5.createRow(0);
        var6.createCell(0);
        var6.createCell(1);
        int var7 = 2;
        int var8 = 0;

        Cell var12;
        List var14;
        for(Iterator var9 = var1.iterator(); var9.hasNext(); var7 += var14.size()) {
            Map var10 = (Map)var9.next();
            XSSFCellStyle var11 = var3;
            if (var8 % 2 == 0) {
                var11 = var4;
            }

            ++var8;
            var12 = var6.createCell(var7);
            var12.setCellStyle(var11);
            String var13 = (String)var10.get("name");
            var12.setCellValue(var13);
            var14 = (List)var10.get("fields");
            if (var14.size() > 1) {
                for(int var15 = 0; var15 < var14.size() - 1; ++var15) {
                    int var16 = var15 + var7 + 1;
                    var6.createCell(var16).setCellStyle(var11);
                }

                CellRangeAddress var29 = new CellRangeAddress(0, 0, var7, var7 + var14.size() - 1);
                var5.addMergedRegion(var29);
            }
        }

        XSSFCellStyle var23 = (XSSFCellStyle)var2.createCellStyle();
        XSSFColor var24 = new XSSFColor(new Color(197, 218, 115));
        var23.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        var23.setFillForegroundColor(var24);
        this.a(var23);
        Row var25 = var5.createRow(1);
        var12 = var25.createCell(0);
        var12.setCellStyle(var23);
        var12.setCellValue("方案标识");
        Cell var26 = var25.createCell(1);
        var26.setCellStyle(var23);
        var26.setCellValue("描述");
        var5.setColumnWidth(1, 3000);
        var7 = 2;
        var8 = 0;
        Iterator var27 = var1.iterator();

        Iterator var18;
        Map var19;
        while(var27.hasNext()) {
            Map var30 = (Map)var27.next();
            XSSFCellStyle var32 = var3;
            if (var8 % 2 == 0) {
                var32 = var4;
            }

            ++var8;
            List var17 = (List)var30.get("fields");

            for(var18 = var17.iterator(); var18.hasNext(); ++var7) {
                var19 = (Map)var18.next();
                Cell var20 = var25.createCell(var7);
                var20.setCellStyle(var32);
                var5.setColumnWidth(var7, 3300);
                String var21 = (String)var19.get("label");
                var20.setCellValue(var21);
            }
        }

        Row var28 = var5.createRow(2);
        XSSFCellStyle var31 = (XSSFCellStyle)var2.createCellStyle();
        this.a(var31);
        Cell var33 = var28.createCell(0);
        var33.setCellStyle(var31);
        this.a(var31);
        var33.setCellValue("1");
        Cell var34 = var28.createCell(1);
        var34.setCellStyle(var31);
        var34.setCellValue("描述内容");
        var7 = 2;
        var18 = var1.iterator();

        while(var18.hasNext()) {
            var19 = (Map)var18.next();
            List var35 = (List)var19.get("fields");

            for(int var36 = 0; var36 < var35.size(); ++var36) {
                Cell var22 = var28.createCell(var7);
                var22.setCellStyle(var31);
                ++var7;
            }
        }

    }

    private void b(List<Map<String, Object>> var1, SXSSFWorkbook var2) {
        XSSFCellStyle var3 = (XSSFCellStyle)var2.createCellStyle();
        var3.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        var3.setFillForegroundColor(new XSSFColor(new Color(255, 235, 69)));
        this.a(var3);
        XSSFCellStyle var4 = (XSSFCellStyle)var2.createCellStyle();
        var4.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        var4.setFillForegroundColor(new XSSFColor(new Color(248, 255, 217)));
        this.a(var4);
        SXSSFSheet var5 = var2.createSheet("预期结果");
        Row var6 = var5.createRow(0);
        var6.createCell(0);
        int var7 = 1;
        int var8 = 0;

        Cell var12;
        List var14;
        for(Iterator var9 = var1.iterator(); var9.hasNext(); var7 += var14.size()) {
            Map var10 = (Map)var9.next();
            XSSFCellStyle var11 = var3;
            if (var8 % 2 == 0) {
                var11 = var4;
            }

            ++var8;
            var12 = var6.createCell(var7);
            var12.setCellStyle(var11);
            String var13 = (String)var10.get("name");
            var12.setCellValue(var13);
            var14 = (List)var10.get("fields");
            if (var14.size() > 1) {
                for(int var15 = 0; var15 < var14.size() - 1; ++var15) {
                    int var16 = var15 + var7 + 1;
                    var6.createCell(var16).setCellStyle(var11);
                }

                CellRangeAddress var31 = new CellRangeAddress(0, 0, var7, var7 + var14.size() - 1);
                var5.addMergedRegion(var31);
            }
        }

        XSSFCellStyle var24 = (XSSFCellStyle)var2.createCellStyle();
        XSSFColor var25 = new XSSFColor(new Color(197, 218, 115));
        var24.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        var24.setFillForegroundColor(var25);
        this.a(var24);
        Row var26 = var5.createRow(1);
        var12 = var26.createCell(0);
        var12.setCellStyle(var24);
        var12.setCellValue("方案标识");
        var7 = 1;
        var8 = 0;
        Iterator var27 = var1.iterator();

        while(var27.hasNext()) {
            Map var29 = (Map)var27.next();
            XSSFCellStyle var32 = var3;
            if (var8 % 2 == 0) {
                var32 = var4;
            }

            ++var8;
            List var33 = (List)var29.get("fields");

            for(Iterator var17 = var33.iterator(); var17.hasNext(); ++var7) {
                Map var18 = (Map)var17.next();
                Cell var19 = var26.createCell(var7);
                var19.setCellStyle(var32);
                var5.setColumnWidth(var7, 4000);
                String var20 = (String)var18.get("label");
                String var21 = (String)var18.get("op");
                Op var22 = Op.valueOf(var21);
                String var23 = "\"" + var20 + "\"" + var22.toString();
                var19.setCellValue(var23);
            }
        }

        Row var28 = var5.createRow(2);
        XSSFCellStyle var30 = (XSSFCellStyle)var2.createCellStyle();
        this.a(var30);
        Cell var35 = var28.createCell(0);
        var35.setCellStyle(var30);
        var35.setCellValue("1");
        var7 = 1;
        Iterator var34 = var1.iterator();

        while(var34.hasNext()) {
            Map var36 = (Map)var34.next();
            List var37 = (List)var36.get("fields");

            for(int var38 = 0; var38 < var37.size(); ++var38) {
                Cell var39 = var28.createCell(var7);
                var39.setCellStyle(var30);
                ++var7;
            }
        }

    }

    private void a(XSSFCellStyle var1) {
        var1.setBorderLeft(BorderStyle.THIN);
        var1.setBorderRight(BorderStyle.THIN);
        var1.setBorderTop(BorderStyle.THIN);
        var1.setBorderBottom(BorderStyle.THIN);
        var1.setWrapText(true);
    }

    public void loadLibs(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        List var3 = this.b(var1);
        this.writeObjectToJson(var2, var3);
    }

    private List<VariableCategory> b(HttpServletRequest var1) throws IOException {
        KnowledgeBase var2 = this.c(var1);
        List var3 = var2.getResourceLibrary().getVariableCategories();
        return var3;
    }

    private KnowledgeBase c(HttpServletRequest var1) throws IOException {
        String var2 = var1.getParameter("files");
        var2 = Utils.decodeURL(var2);
        ResourceBase var3 = this.b.newResourceBase();
        String[] var4 = var2.split(";");
        String[] var5 = var4;
        int var6 = var4.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            String var8 = var5[var7];
            String[] var9 = var8.split(",");
            var8 = var9[0];
            String var10 = null;
            if (var9.length > 1) {
                var10 = var9[1];
            }

            var3.addResource(var8, var10);
        }

        KnowledgeBase var11 = this.b.buildKnowledgeBase(var3);
        this.c.remove(var1, "_k_b");
        this.c.put(var1, "_k_b", var11);
        return var11;
    }

    public void setScenarioRepositoryService(ScenarioRepositoryService var1) {
        this.a = var1;
        this.d = new ScenarioTestExecuting(var1);
    }

    public void setKnowledgeBuilder(KnowledgeBuilder var1) {
        this.b = var1;
    }

    public void setHttpSessionKnowledgeCache(HttpSessionKnowledgeCache var1) {
        this.c = var1;
    }

    public String url() {
        return "/test";
    }
}
