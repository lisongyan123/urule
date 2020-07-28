//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.test;

import com.bstek.urule.Splash;
import com.bstek.urule.Utils;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.console.servlet.SessionStore;
import com.bstek.urule.model.rete.RuleData;
import com.bstek.urule.runtime.log.DataLog;
import com.bstek.urule.runtime.log.FlowNodeLog;
import com.bstek.urule.runtime.log.Log;
import com.bstek.urule.runtime.log.MatchedRuleLog;
import com.bstek.urule.runtime.log.UnitLog;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class TestResultServletHandler extends RenderPageServletHandler {
    public static final String TEST_RESULT = "scenario_test_result__";

    public TestResultServletHandler() {
    }

    public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        String var3 = this.retriveMethod(var1);
        if (var3 != null) {
            this.invokeMethod(var3, var1, var2);
        } else {
            ResultWrapper var4 = (ResultWrapper)SessionStore.getAttribute("scenario_test_result__");
            String var5 = this.a(var4);
            VelocityContext var6 = new VelocityContext();
            var6.put("contextPath", var1.getContextPath());
            var6.put("version", Splash.getVersion());
            var6.put("content", var5);
            String var7 = var4.getProject();
            if (var7.startsWith("/")) {
                var7 = var7.substring(1, var7.length());
            }

            SimpleDateFormat var8 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String var9 = "项目[" + var7 + "]中知识包[" + var4.getPackageName() + "]下的名为[" + var4.getScenarioName() + "]的仿真测试报告-" + var8.format(new Date());
            var6.put("title", var9);
            var6.put("_lis_", Splash.getFetchVersion());
            var2.setContentType("text/html");
            var2.setCharacterEncoding("utf-8");
            Template var10 = this.ve.getTemplate("html/test-result.html", "utf-8");
            PrintWriter var11 = var2.getWriter();
            var10.merge(var6, var11);
            var11.close();
        }

    }

    private String a(ResultWrapper var1) {
        StringBuilder var2 = new StringBuilder();
        if (var1 == null) {
            var2.append("<h1>方案测试报告不存在，或已过期，请重新测试具体方案!</h1>");
            return var2.toString();
        } else {
            String var3 = var1.getProject();
            if (var3.startsWith("/")) {
                var3 = var3.substring(1, var3.length());
            }

            var2.append("<div style='text-align:center'><h1>项目[" + var3 + "]中知识包[" + var1.getPackageName() + "]下的名为[" + var1.getScenarioName() + "]的测试报告</h1></div>");
            SimpleDateFormat var4 = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分ss秒");
            var2.append("<h5 style=\"margin:5px;\">报告生成时间：" + var4.format(new Date()) + "</h5>");
            var2.append("<h5 style=\"margin:5px;\">准备数据(解析方案中的Excel文件等)耗时：" + var1.getPrepareTime() + "ms，运行规则耗时：" + var1.getTotalTime() + "ms</h5>");
            StringBuilder var5 = new StringBuilder();
            var5.append("<table style='margin:5px;border-collapse: collapse;border:solid 1px #cacaca;font-size:12px;width:100%' border='1'>");
            int var6 = 0;
            int var7 = 0;
            Iterator var8 = var1.getResultList().iterator();

            while(var8.hasNext()) {
                TestResult var9 = (TestResult)var8.next();
                int var10 = 0;
                int var11 = 0;
                Iterator var12 = var9.getValueCompares().iterator();

                while(var12.hasNext()) {
                    ValueCompare var13 = (ValueCompare)var12.next();
                    if (var13.isMatched()) {
                        ++var10;
                    } else {
                        ++var11;
                    }
                }

                if (var11 > 0) {
                    var5.append("<tr name='fail_row'>");
                } else {
                    var5.append("<tr name='success_row'>");
                }

                var5.append("<td>");
                var5.append("<div style=\"margin:5px\">场景" + var9.getScenarioId() + "，" + var9.getScenarioDesc() + "</div>");
                var5.append("<div style=\"margin:5px\">耗时：" + var9.getConsumeTime() + "ms，预期结果匹配数量为：" + var10 + "条，不匹配为：" + var11 + "条，");
                if (var11 > 0) {
                    var5.append("<span style=\"color:red\"><label>失败&nbsp;X</label></span>");
                    ++var7;
                } else {
                    var5.append("<span style=\"color:green\">成功&radic;</span>");
                    ++var6;
                }

                var5.append("<span  name='detail' style='margin-left:10px;cursor:pointer;color:blue' c-data='" + var9.getScenarioId() + "'>明细</span>");
                var5.append("</div>");
                String var15 = this.a(var1, var9);
                var5.append(var15);
                var5.append("</td>");
                var5.append("</tr>");
            }

            BigDecimal var14 = Utils.toBigDecimal(var6).divide(Utils.toBigDecimal(var7 + var6), 2, 4).multiply(Utils.toBigDecimal(100));
            var2.append("<h5 style=\"margin:5px;\">成功：" + var6 + "条，失败：" + var7 + "条；成功率：" + var14.toPlainString() + "%；<span style='cursor:pointer;text-decoration:underline' id='see_success'>只看成功的</span>，<span style='cursor:pointer;text-decoration:underline' id='see_fail'>只看失败的</span>，<span style='cursor:pointer;text-decoration:underline' id='see_all'>查看全部</span></h5>");
            var5.append("</td>");
            var5.append("</tr>");
            var5.append("</table>");
            var2.append(var5);
            return var2.toString();
        }
    }

    private String a(ResultWrapper var1, TestResult var2) {
        StringBuilder var3 = new StringBuilder();
        var3.append("<div id='detailContent-" + var2.getScenarioId() + "' style='margin:5px;background:#f7f7f7;display:none'>");
        var3.append("<div style='margin-top:20px'><h3>详细内容</h3></div>");
        if (var1.isShowInputData()) {
            var3.append("<fieldset style='border:solid 1px #ddd;border-radius:5px;margin:5px'>");
            var3.append("<legend>");
            var3.append("输入数据");
            var3.append("</legend>");
            var3.append("<pre style='color:#9c27b0;white-space:pre-wrap'>");
            var3.append(var2.getInputData());
            var3.append("</pre>");
            var3.append("</fieldset>");
        }

        if (var1.isShowOutputData()) {
            var3.append("<fieldset style='border:solid 1px #ddd;border-radius:5px;margin:5px'>");
            var3.append("<legend>");
            var3.append("预期输出数据");
            var3.append("</legend>");
            var3.append("<pre style='color:#2196F3;white-space:pre-wrap'>");
            var3.append(var2.getOutputData());
            var3.append("</pre>");
            var3.append("</fieldset>");
        }

        int var4 = 1;
        var3.append("<fieldset style='border:solid 1px #ddd;border-radius:5px;margin:5px'>");
        var3.append("<legend>");
        var3.append("预期结果匹配情况");
        var3.append("</legend>");

        for(Iterator var5 = var2.getValueCompares().iterator(); var5.hasNext(); ++var4) {
            ValueCompare var6 = (ValueCompare)var5.next();
            var3.append("<div style=\"margin-top:5px\">");
            var3.append(var4 + ".");
            var3.append("对象\"" + var6.getCategory() + "\"的");
            var3.append("\"" + var6.getFieldName() + "\"" + var6.getOp().toString() + "预期值");
            if (var6.getExpectedData() == null) {
                var3.append("null");
            } else {
                var3.append("\"" + var6.getExpectedData() + "\"");
            }

            var3.append(",实际值为");
            if (var6.getData() == null) {
                var3.append("null");
            } else {
                var3.append("\"" + var6.getData() + "\"");
            }

            var3.append(",");
            if (var6.isMatched()) {
                var3.append("<span style=\"color:green\">成功&nbsp;&radic;</span>");
            } else {
                var3.append("<span style=\"color:red\"><label>失败&nbsp;X</label></span>");
            }

            var3.append("</div>");
        }

        var3.append("</fieldset>");
        if (var1.isShowMatchedRuleSize()) {
            var3.append("<div style=\"margin-top:5px\"><span style='color:#545454'>触发的规则数量：</span>" + var2.getMatchedRuleList().size() + "<div>");
        }

        if (var1.isShowNotMatchRuleSize()) {
            var3.append("<div style=\"margin-top:5px\"><span style='color:#545454'>未触发的规则数量：</span>" + var2.getNotMatchedRuleList().size() + "<div>");
        }

        if (var1.isShowFiredFlowNodeSize()) {
            var3.append("<div style='margin-top:5px'><span style=\"color:#545454;\">经过的规则流节点数量：</span>" + var2.getFlowNodeList().size() + "<div>");
        }

        boolean var8;
        Iterator var9;
        if (var1.isShowMatchedRuleList()) {
            var3.append("<div style=\"margin-top:5px\"><span style='color:#545454'>触发的规则列表：</span>");
            var3.append("<span style='margin:5px;color:#9c27b0'>");
            var8 = false;

            for(var9 = var2.getMatchedRuleList().iterator(); var9.hasNext(); var8 = true) {
                MatchedRuleLog var7 = (MatchedRuleLog)var9.next();
                if (var8) {
                    var3.append("、");
                }

                var3.append(var7.getRuleName() + "<span style='color:#795548'>(" + this.a(var7.getRuleFile()) + ")</span>");
            }

            var3.append("</span>");
            var3.append("<div>");
        }

        if (var1.isShowNotMatchRuleList()) {
            var3.append("<div style=\"margin-top:5px\"><span style='color:#545454'>未触发的规则列表：</span>");
            var3.append("<span style='margin:5px;color:#9c27b0'>");
            var8 = false;

            for(var9 = var2.getNotMatchedRuleList().iterator(); var9.hasNext(); var8 = true) {
                RuleData var10 = (RuleData)var9.next();
                if (var8) {
                    var3.append("、");
                }

                var3.append(var10.getName() + "<span style='color:#795548'>(" + this.a(var10.getFile()) + ")</span>");
            }

            var3.append("</span>");
            var3.append("<div>");
        }

        if (var1.isShowFiredFlowNodeList()) {
            var3.append("<div style='margin-top:5px'><span style=\"color:#545454;\">经过的规则流节点列表：</span>");
            var3.append("<span style='margin:5px;color:#9c27b0'>");
            var8 = false;

            for(var9 = var2.getFlowNodeList().iterator(); var9.hasNext(); var8 = true) {
                FlowNodeLog var12 = (FlowNodeLog)var9.next();
                if (var8) {
                    var3.append("、");
                }

                var3.append(var12.getNodeName() + "<span style='color:#795548'>(" + this.a(var12.getFile()) + ")</span>");
            }

            var3.append("</span>");
            var3.append("<div>");
        }

        if (var1.isShowLog()) {
            var3.append("<div style=\"color:#545454;margin-top:5px\">运行日志：</div>");
            List var11 = var2.getLogs();
            if (var11 != null && var11.size() != 0) {
                var3.append("<div style=\"font-size:11px;border:dotted 1px #a5a5a5;margin:5px;padding:5px;border-radius:5px\">");
                this.a(var3, var11);
                var3.append("</div>");
            } else {
                var3.append("<div style='color:#df3600'>当前未开启日志输出功能</div>");
            }
        }

        var3.append("</div>");
        return var3.toString();
    }

    private String a(String var1) {
        if (var1 == null) {
            return "无";
        } else {
            return var1.startsWith("jcr:") ? var1.substring(4, var1.length()) : var1;
        }
    }

    private void a(StringBuilder var1, List<Log> var2) {
        Iterator var3 = var2.iterator();

        while(var3.hasNext()) {
            Log var4 = (Log)var3.next();
            if (var4 instanceof UnitLog) {
                var1.append("<div style=\"margin:8px;border:dashed 1px #cccccc\">");
                UnitLog var5 = (UnitLog)var4;
                List var6 = var5.getLogs();
                this.a(var1, var6);
                var1.append("</div>");
            } else if (var4 instanceof DataLog) {
                DataLog var7 = (DataLog)var4;
                String var8 = var7.getHtmlMsg();
                var1.append(var8);
            }
        }

    }

    public String url() {
        return "/testresult";
    }
}
