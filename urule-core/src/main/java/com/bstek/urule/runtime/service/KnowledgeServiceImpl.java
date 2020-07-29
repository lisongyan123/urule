//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.service;

import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.cache.CacheUtils;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class KnowledgeServiceImpl implements KnowledgeService, ApplicationContextAware {
    private long a;
    private RemoteService b;
    private KnowledgePackageService c;
    private KnowledgePackageFileService d;
    private Logger e = Logger.getLogger(KnowledgeServiceImpl.class.getName());

    public KnowledgeServiceImpl() {
    }

    public KnowledgePackage[] getKnowledges(String[] var1) throws IOException {
        KnowledgePackage[] var2 = new KnowledgePackage[var1.length];

        for(int var3 = 0; var3 < var1.length; ++var3) {
            String var4 = var1[var3];
            var2[var3] = this.getKnowledge(var4);
        }

        return var2;
    }

    public KnowledgePackage getKnowledge(String var1) throws IOException {
        if (this.a == 0L) {
            return this.a(var1);
        } else {
            KnowledgePackage var2 = CacheUtils.getKnowledgeCache().getKnowledge(var1);
            long var3;
            KnowledgePackage var5;
            if (this.a == 1L) {
                if (var2 == null) {
                    var2 = this.a(var1);
                    CacheUtils.getKnowledgeCache().putKnowledge(var1, var2);
                } else {
                    var3 = var2.getTimestamp();
                    if (this.d.isEnable()) {
                        var5 = this.d.verifyKnowledgePackage(var1, var3);
                        if (var5 != null) {
                            var2 = var5;
                            CacheUtils.getKnowledgeCache().putKnowledge(var1, var5);
                        }
                    } else if (this.c != null) {
                        var5 = this.c.verifyKnowledgePackage(var1, var3);
                        if (var5 != null) {
                            return var5;
                        }
                    }
                }

                return var2;
            } else {
                if (var2 == null) {
                    var2 = this.a(var1);
                    CacheUtils.getKnowledgeCache().putKnowledge(var1, var2);
                } else {
                    var3 = var2.getTimestamp();
                    if (this.d.isEnable()) {
                        var5 = this.d.verifyKnowledgePackage(var1, var3);
                        if (var5 != null) {
                            CacheUtils.getKnowledgeCache().putKnowledge(var1, var5);
                            return var5;
                        }
                    } else if (this.c != null) {
                        var5 = this.c.verifyKnowledgePackage(var1, var3);
                        if (var5 != null) {
                            return var5;
                        }
                    }

                    long var10 = System.currentTimeMillis();
                    long var7 = var10 - var3;
                    if (var7 >= this.a) {
                        KnowledgePackage var9 = this.b.getKnowledge(var1, String.valueOf(var2.getTimestamp()));
                        if (var9 == null) {
                            var2.resetTimestamp();
                            CacheUtils.getKnowledgeCache().putKnowledge(var1, var2);
                        } else {
                            this.e.info("Update remote knowledgepackage.");
                            var9.resetTimestamp();
                            var2 = var9;
                            CacheUtils.getKnowledgeCache().putKnowledge(var1, var9);
                        }
                    }
                }

                return var2;
            }
        }
    }

    private KnowledgePackage a(String var1) throws IOException {
        KnowledgePackage var2 = this.d.loadKnowledgePackage(var1);
        if (var2 != null) {
            return var2;
        } else {
            var2 = this.b.getKnowledge(var1, (String)null);
            if (var2 != null) {
                return var2;
            } else if (this.c != null) {
                var2 = this.c.buildKnowledgePackage(var1);
                return var2;
            } else {
                throw new RuleException("Remote server/local repository/local data file all unavailable,can't load knowledgepackage[" + var1 + "]!");
            }
        }
    }

    public void setRemoteService(RemoteService var1) {
        this.b = var1;
    }

    public void setKnowledgeUpdateCycle(String var1) {
        if (!StringUtils.isEmpty(var1) && !var1.equals("${urule.knowledgeUpdateCycle}")) {
            this.a = Long.valueOf(var1);
        }
    }

    public void setKnowledgePackageFileService(KnowledgePackageFileService var1) {
        this.d = var1;
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        boolean var2 = var1.containsBean("urule.knowledgePackageService");
        if (var2) {
            this.c = (KnowledgePackageService)var1.getBean("urule.knowledgePackageService");
        }

        Collection var3 = var1.getBeansOfType(KnowledgePackageFileService.class).values();
        if (var3.size() == 1) {
            this.d = (KnowledgePackageFileService)var3.iterator().next();
        } else {
            Iterator var4 = var3.iterator();

            while(var4.hasNext()) {
                KnowledgePackageFileService var5 = (KnowledgePackageFileService)var4.next();
                if (var5.isEnable()) {
                    this.d = var5;
                    break;
                }
            }

            if (this.d == null) {
                this.d = (KnowledgePackageFileService)var3.iterator().next();
            }
        }

    }
}
