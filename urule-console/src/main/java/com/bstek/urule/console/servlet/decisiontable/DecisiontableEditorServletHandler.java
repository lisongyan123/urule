package com.bstek.urule.console.servlet.decisiontable;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.EnvironmentUtils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.ProjectVariable;
import com.bstek.urule.console.repository.RepositoryService;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.servlet.CellContent;
import com.bstek.urule.console.servlet.DesignerConfigure;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.RequestContext;
import com.bstek.urule.console.servlet.SessionStore;
import com.bstek.urule.console.servlet.common.CommonServletHandler;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.library.variable.CategoryType;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.table.DecisionTable;
import com.bstek.urule.parse.deserializer.DecisionTableDeserializer;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class DecisiontableEditorServletHandler extends RenderPageServletHandler {
    private RepositoryService a;
    private CommonServletHandler b;
    private DecisionTableDeserializer c;
    private DSLRuleSetBuilder d;

    public DecisiontableEditorServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = this.retriveMethod(var1);
        if (var3 != null) {
            this.invokeMethod(var3, var1, var2);
        } else {
            VelocityContext var4 = new VelocityContext();
            var4.put("contextPath", var1.getContextPath());
            var4.put("version", Splash.getVersion());
            var4.put("constantLink", DesignerConfigure.constantLink);
            var4.put("variableLink", DesignerConfigure.variableLink);
            var4.put("_date_", _DATE);
            var4.put("_lis_", Splash.getFetchVersion());
            String var5 = var1.getParameter("file");
            String var6 = this.buildProjectNameFromFile(var5);
            if (var6 != null) {
                var4.put("project", var6);
            }

            var4.put("showFileExtensionName", PropertyConfigurer.getProperty("urule.show.fileExtensionName"));
            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var7 = this.ve.getTemplate("html/decisiontable-editor.html", "utf-8");
            PrintWriter var8 = var2.getWriter();
            var7.merge(var4, var8);
            var8.close();
        }

    }

    public void importExcel(HttpServletRequest var1, HttpServletResponse var2) throws Exception {
        HashMap var3 = new HashMap();

        try {
            String var4 = var1.getParameter("project");
            var4 = Utils.decodeURL(var4);
            DiskFileItemFactory var20 = new DiskFileItemFactory();
            ServletFileUpload var6 = new ServletFileUpload(var20);
            List var7 = var6.parseRequest(var1);
            Iterator var8 = var7.iterator();
            TableData var9 = null;

            while(var8.hasNext()) {
                FileItem var10 = (FileItem)var8.next();
                String var11 = var10.getFieldName();
                if (var11.equals("excel_file")) {
                    InputStream var12 = var10.getInputStream();
                    var9 = this.a(var12);
                    var12.close();
                    break;
                }
            }

            if (var9 == null) {
                throw new RuleException("请上传一个Excel文件！");
            }

            Principal var21 = EnvironmentUtils.getLoginPrincipal(new RequestContext(var1, var2));
            List var22 = this.a.loadProjectLibraries(var4, var21);
            Iterator var23 = var22.iterator();

            while(var23.hasNext()) {
                ProjectVariable var13 = (ProjectVariable)var23.next();
                String var14 = var13.getPath();
                Object var15 = this.b.buildData(var14).get(0);
                if (var14.endsWith(FileType.ParameterLibrary.toString())) {
                    List var16 = (List)var15;
                    ArrayList var17 = new ArrayList();
                    VariableCategory var18 = new VariableCategory();
                    var18.setClazz(HashMap.class.getName());
                    var18.setName("参数");
                    var18.setType(CategoryType.Clazz);
                    var18.setVariables(var16);
                    var17.add(var18);
                    var13.setVariableCategories(var17);
                } else {
                    var13.setVariableCategories((List)var15);
                }
            }

            DecisionTableXmlBuilder var24 = new DecisionTableXmlBuilder(var9, this.c, var22, this.d);
            DecisionTable var25 = var24.buildTable();
            SessionStore.setAttribute("_import_data_", var25);
            var3.put("fail", false);
        } catch (Exception var19) {
            var19.printStackTrace();
            String var5 = this.buildErrorMsg(var19);
            var3.put("fail", true);
            var3.put("msg", var5);
        }

        this.writeObjectToJson(var2, var3);
    }

    private TableData a(InputStream var1) throws Exception {
        XSSFWorkbook var2 = new XSSFWorkbook(var1);
        if (var2.getNumberOfSheets() == 0) {
            var2.close();
            throw new RuleException("导入Excel不合法！");
        } else {
            ArrayList var3 = new ArrayList();
            XSSFSheet var4 = var2.getSheetAt(0);
            int var5 = var4.getLastRowNum();
            List var6 = this.a(var4);

            for(int var7 = 1; var7 <= var5; ++var7) {
                XSSFRow var8 = var4.getRow(var7);
                ContentRow var9 = new ContentRow();
                var3.add(var9);
                ArrayList var10 = new ArrayList();
                var9.setContents(var10);

                for(int var11 = 0; var11 < var6.size(); ++var11) {
                    XSSFCell var12 = var8.getCell(var11);
                    if (var12 != null) {
                        int var13 = this.a(var7, var11, var4);
                        if (var13 != 0) {
                            Header var14 = (Header)var6.get(var11);
                            CellContent var15 = new CellContent();
                            if (var13 > 0) {
                                var15.setSpan(var13);
                            }

                            var15.setHeader(var14);
                            CellType var16 = var12.getCellTypeEnum();
                            switch(var16) {
                                case STRING:
                                    var15.setContent(var12.getStringCellValue());
                                    break;
                                case BOOLEAN:
                                    var15.setContent(String.valueOf(var12.getBooleanCellValue()));
                                    break;
                                case NUMERIC:
                                    var12.setCellType(CellType.STRING);
                                    var15.setContent(var12.getStringCellValue());
                                case _NONE:
                                case BLANK:
                                case ERROR:
                                case FORMULA:
                            }

                            var15.setRow(var7 - 1);
                            var15.setCol(var11);
                            var10.add(var15);
                        }
                    }
                }
            }

            var2.close();
            return new TableData(var6, var3);
        }
    }

    private int a(int var1, int var2, XSSFSheet var3) {
        List var4 = var3.getMergedRegions();
        Iterator var5 = var4.iterator();

        CellRangeAddress var6;
        do {
            if (!var5.hasNext()) {
                return -1;
            }

            var6 = (CellRangeAddress)var5.next();
            if (var6.getFirstColumn() == var2 && var6.getFirstRow() == var1) {
                int var7 = var6.getLastRow() - var6.getFirstRow();
                ++var7;
                return var7;
            }
        } while(var2 < var6.getFirstColumn() || var2 > var6.getLastColumn() || var1 < var6.getFirstRow() || var1 > var6.getLastRow());

        return 0;
    }

    private List<Header> a(XSSFSheet var1) {
        XSSFRow var2 = var1.getRow(0);
        ArrayList var3 = new ArrayList();
        short var4 = var2.getLastCellNum();

        for(int var5 = 0; var5 < var4; ++var5) {
            XSSFCell var6 = var2.getCell(var5);
            String var7 = var6.getStringCellValue();
            if (!StringUtils.isBlank(var7)) {
                Header var8 = new Header();
                var3.add(var8);
                var8.setName(var7);
                XSSFComment var9 = var6.getCellComment();
                if (var9 != null) {
                    String var10 = var9.getString().toString().toLowerCase().trim();
                    if (!var10.equals("赋值") && !var10.equals("assign")) {
                        if (var10.equals("控制台输出") || var10.equals("out")) {
                            var8.setType(HeaderType.out);
                        }
                    } else {
                        var8.setType(HeaderType.assign);
                    }
                }
            }
        }

        return var3;
    }

    public void setRepositoryService(RepositoryService var1) {
        this.a = var1;
    }

    public void setDecisionTableDeserializer(DecisionTableDeserializer var1) {
        this.c = var1;
    }

    public void setCommonServletHandler(CommonServletHandler var1) {
        this.b = var1;
    }

    public void setDslRuleSetBuilder(DSLRuleSetBuilder var1) {
        this.d = var1;
    }

    public String url() {
        return "/decisiontableeditor";
    }
}
