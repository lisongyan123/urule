package com.bstek.urule.console.servlet.crosstab;

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
import com.bstek.urule.model.crosstab.CrosstabDefinition;
import com.bstek.urule.model.library.variable.CategoryType;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.parse.deserializer.CrosstableDeserializer;
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
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class CrosstabEditorServletHandler extends RenderPageServletHandler {
    private RepositoryService a;
    private CommonServletHandler b;
    private CrosstableDeserializer c;
    private DSLRuleSetBuilder d;

    public CrosstabEditorServletHandler() {
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
            Template var7 = this.ve.getTemplate("html/crosstab-editor.html", "utf-8");
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
            CrossData var9 = null;

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

            CrossTableXmlBuilder var24 = new CrossTableXmlBuilder(var9, this.c, var22, this.d);
            CrosstabDefinition var25 = var24.doBuild();
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

    private CrossData a(InputStream var1) throws Exception {
        XSSFWorkbook var2 = new XSSFWorkbook(var1);
        if (var2.getNumberOfSheets() == 0) {
            var2.close();
            throw new RuleException("导入Excel不合法！");
        } else {
            ArrayList var3 = new ArrayList();
            ArrayList var4 = new ArrayList();
            XSSFSheet var5 = var2.getSheetAt(0);
            CrossHeader var6 = this.a(var5);
            XSSFRow var7 = var5.getRow(0);
            short var8 = var7.getLastCellNum();
            XSSFRow var9 = var5.getRow(var6.getRowSpan());

            int var10;
            for(var10 = 0; var10 < var8; ++var10) {
                CrossColumn var11 = new CrossColumn();
                var11.setNumber(var10 + 1);
                if (var10 < var6.getColSpan()) {
                    var11.setType(Type.left);
                    XSSFCell var12 = var9.getCell(var10);
                    XSSFComment var13 = var12.getCellComment();
                    if (var13 != null) {
                        String var14 = var13.getString().toString().toLowerCase().trim();
                        var11.setContent(var14);
                    }
                } else {
                    var11.setType(Type.top);
                }

                var4.add(var11);
            }

            var10 = var5.getLastRowNum();

            for(int var19 = 0; var19 <= var10; ++var19) {
                XSSFRow var21 = var5.getRow(var19);
                CrossRow var23 = new CrossRow();
                var23.setNumber(var19 + 1);
                if (var19 < var6.getRowSpan()) {
                    var23.setType(Type.top);
                    XSSFCell var26 = var21.getCell(var6.getColSpan());
                    XSSFComment var15 = var26.getCellComment();
                    if (var15 != null) {
                        String var16 = var15.getString().toString().toLowerCase().trim();
                        var23.setContent(var16);
                    }
                } else {
                    var23.setType(Type.left);
                }

                var3.add(var23);
            }

            ArrayList var20 = new ArrayList();

            for(int var22 = 0; var22 <= var10; ++var22) {
                XSSFRow var25 = var5.getRow(var22);

                for(int var27 = 0; var27 < var8; ++var27) {
                    if (var22 != 0 || var27 != 0) {
                        XSSFCell var28 = var25.getCell(var27);
                        if (var28 != null) {
                            Span var29 = this.a(var22, var27, var5);
                            if (var29 != null) {
                                String var17 = this.a(var28);
                                CellContent var18 = new CellContent();
                                var18.setCol(var27 + 1);
                                var18.setRow(var22 + 1);
                                var18.setContent(var17);
                                if (var22 < var6.getRowSpan()) {
                                    var18.setType("condition");
                                    var18.setSpan(var29.getCol());
                                }

                                if (var27 < var6.getColSpan()) {
                                    var18.setType("condition");
                                    var18.setSpan(var29.getRow());
                                }

                                var20.add(var18);
                            }
                        }
                    }
                }
            }

            var2.close();
            CrossData var24 = new CrossData();
            var24.setCells(var20);
            var24.setColumns(var4);
            var24.setRows(var3);
            var24.setHeader(var6);
            return var24;
        }
    }

    private CrossHeader a(XSSFSheet var1) {
        Span var2 = this.a(0, 0, var1);
        if (var2 == null) {
            throw new RuleException("导入的Excel不合法!");
        } else {
            CrossHeader var3 = new CrossHeader();
            var3.setRowSpan(var2.getRow());
            var3.setColSpan(var2.getCol());
            XSSFRow var4 = var1.getRow(0);
            XSSFCell var5 = var4.getCell(0);
            var3.setContent(this.a(var5));
            return var3;
        }
    }

    private Span a(int var1, int var2, XSSFSheet var3) {
        List var4 = var3.getMergedRegions();
        Iterator var5 = var4.iterator();

        CellRangeAddress var6;
        do {
            if (!var5.hasNext()) {
                Span var10 = new Span();
                var10.setRow(1);
                var10.setCol(1);
                return var10;
            }

            var6 = (CellRangeAddress)var5.next();
            if (var6.getFirstColumn() == var2 && var6.getFirstRow() == var1) {
                int var7 = var6.getLastRow() - var6.getFirstRow();
                if (var7 > 0) {
                    ++var7;
                }

                Span var8 = new Span();
                var8.setRow(var7);
                int var9 = var6.getLastColumn() - var6.getFirstColumn();
                if (var9 > 0) {
                    ++var9;
                }

                var8.setCol(var9);
                return var8;
            }
        } while(var2 < var6.getFirstColumn() || var2 > var6.getLastColumn() || var1 < var6.getFirstRow() || var1 > var6.getLastRow());

        return null;
    }

    private String a(XSSFCell var1) {
        String var2 = null;
        CellType var3 = var1.getCellTypeEnum();
        switch(var3) {
            case STRING:
                var2 = var1.getStringCellValue();
                break;
            case BOOLEAN:
                var2 = String.valueOf(var1.getBooleanCellValue());
                break;
            case NUMERIC:
                var1.setCellType(CellType.STRING);
                var2 = var1.getStringCellValue();
            case _NONE:
            case BLANK:
            case ERROR:
            case FORMULA:
        }

        return var2;
    }

    public void setCommonServletHandler(CommonServletHandler var1) {
        this.b = var1;
    }

    public void setRepositoryService(RepositoryService var1) {
        this.a = var1;
    }

    public void setCrosstableDeserializer(CrosstableDeserializer var1) {
        this.c = var1;
    }

    public void setDslRuleSetBuilder(DSLRuleSetBuilder var1) {
        this.d = var1;
    }

    public String url() {
        return "/crosstabeditor";
    }
}
