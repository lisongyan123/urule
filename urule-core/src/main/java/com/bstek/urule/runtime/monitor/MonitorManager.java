//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.monitor;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.FactManager;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgeSession;
import com.bstek.urule.runtime.log.LogManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MonitorManager {
    private LogManager a;
    private long b;
    private FactManager c;
    private Collection<InvokeMonitor> d;
    private Map<String, MonitorDataImpl> e = new HashMap();
    private List<KnowledgePackage> f;

    public MonitorManager(KnowledgeSession var1) {
        this.a = var1.getLogManager();
        this.c = var1.getFactManager();
        this.f = var1.getKnowledgePackageList();
        this.d = Utils.getApplicationContext().getBeansOfType(InvokeMonitor.class).values();
    }

    public void doMonitorInputData(Map<String, Object> var1) {
        if (this.d.size() != 0) {
            Iterator var2 = this.f.iterator();

            while(var2.hasNext()) {
                KnowledgePackage var3 = (KnowledgePackage)var2.next();
                if (var3.isMonitor()) {
                    MonitorDataImpl var4 = new MonitorDataImpl();
                    var4.setPackageInfo(var3.getPackageInfo());
                    List var5 = var3.getInputData();
                    List var6 = this.a(var5, var3.getVariableCateogoryMap(), var1, true);
                    var4.setInputData(var6);
                    this.e.put(var3.getId(), var4);
                }
            }

        }
    }

    public void doMonitor(Map<String, Object> var1) {
        if (this.d.size() != 0) {
            Iterator var2 = this.f.iterator();

            while(true) {
                KnowledgePackage var3;
                do {
                    if (!var2.hasNext()) {
                        this.e.clear();
                        return;
                    }

                    var3 = (KnowledgePackage)var2.next();
                } while(!var3.isMonitor());

                MonitorDataImpl var4 = (MonitorDataImpl)this.e.get(var3.getId());
                if (var3.isShowFiredFlowNodeList()) {
                    var4.addFiredFlowNodeList(this.a.buildFlowNodeData());
                }

                var4.setShowFiredFlowNodeList(var3.isShowFiredFlowNodeList());
                if (var3.isShowMatchedRuleList()) {
                    var4.addMatchedRuleList(this.a.buildMatchedRuleLog());
                }

                var4.setShowMatchedRuleList(var3.isShowMatchedRuleList());
                if (var3.isShowNotMatchRuleList()) {
                    var4.addNotMatchRuleList(this.a.buildNotMatchRuleData());
                }

                var4.setShowNotMatchRuleList(var3.isShowNotMatchRuleList());
                if (var3.isShowLog()) {
                    var4.addLogs(this.a.getLogger().getLogs());
                }

                var4.setShowLog(var3.isShowLog());
                var4.setVersion(var3.getVersion());
                var4.setVersionComment(var3.getVersionComment());
                var4.setVersionCreateDate(var3.getVersionCreateDate());
                var4.setVersionCreateUser(var3.getVersionCreateUser());
                List var5 = var3.getOutputData();
                List var6 = this.a(var5, var3.getVariableCateogoryMap(), var1, false);
                var4.setOutputData(var6);
                var4.setTotalDuration(this.b);
                Iterator var7 = this.d.iterator();

                while(var7.hasNext()) {
                    InvokeMonitor var8 = (InvokeMonitor)var7.next();
                    var8.doMonitor(var4);
                }
            }
        }
    }

    private List<IOData> a(List<MonitorObject> var1, Map<String, String> var2, Map<String, Object> var3, boolean var4) {
        ArrayList var5 = new ArrayList();
        Map var6 = this.c.getFactMap();
        Iterator var7 = var1.iterator();

        while(var7.hasNext()) {
            MonitorObject var8 = (MonitorObject)var7.next();
            Object var9 = null;
            String var10 = null;
            if (var8.getName().equals("参数")) {
                var9 = var3;
                var10 = HashMap.class.getName();
            } else {
                var10 = (String)var2.get(var8.getName());
                if (var10 == null) {
                    if (var4) {
                        throw new RuleException("构建监控输入数据时，对象[" + var8.getName() + "]不存在！");
                    }

                    throw new RuleException("构建监控输出数据时，对象[" + var8.getName() + "]不存在！");
                }

                var9 = var6.get(var10);
                if (var9 == null) {
                    if (var4) {
                        throw new RuleException("构建监控输入数据时，对象[" + var10 + "]不存在！");
                    }

                    throw new RuleException("构建监控输出数据时，对象[" + var10 + "]不存在！");
                }
            }

            IOData var11 = new IOData();
            var5.add(var11);
            var11.setName(var8.getName());
            var11.setClazz(var10);
            ArrayList var12 = new ArrayList();
            var11.setFields(var12);
            Iterator var13 = var8.getFields().iterator();

            while(var13.hasNext()) {
                MonitorObjectField var14 = (MonitorObjectField)var13.next();
                IODataField var15 = new IODataField();
                String var16 = var14.getName();
                Object var17 = Utils.getObjectProperty(var9, var16);
                var15.setName(var16);
                var15.setLabel(var14.getLabel());
                var15.setValue(var17);
                var12.add(var15);
            }
        }

        return var5;
    }

    public void setTotalDuration(long var1) {
        this.b = var1;
    }
}
