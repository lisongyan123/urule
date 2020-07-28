package com.bstek.urule.console;

import com.bstek.urule.builder.KnowledgeBase;
import com.bstek.urule.builder.KnowledgeBuilder;
import com.bstek.urule.builder.ResourceBase;
import com.bstek.urule.console.repository.KnowledgePackageRepositoryService;
import com.bstek.urule.console.repository.model.ResourceItem;
import com.bstek.urule.console.repository.model.ResourcePackage;
import com.bstek.urule.console.repository.model.VersionFile;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rete.RuleDueException;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgePackageImpl;
import com.bstek.urule.runtime.cache.CacheUtils;
import com.bstek.urule.runtime.service.KnowledgePackageService;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class DefaultKnowledgePackageService implements KnowledgePackageService {
	private KnowledgeBuilder a;
	private KnowledgePackageRepositoryService b;

	public DefaultKnowledgePackageService() {
	}

	public KnowledgePackage buildKnowledgePackage(String var1) throws IOException {
		try {
			String[] var2 = var1.split("/");
			if (var2.length != 2) {
				throw new RuleException("请求的知识包 [" + var1 + "] 格式不正确. 正确的应该类似于： \"projectName/packageId\".");
			} else {
				String var3 = var2[0];
				String var4 = var2[1];
				String var5 = null;
				int var6 = var4.indexOf(":");
				if (var6 > 0) {
					var5 = var4.substring(var6 + 1);
					var4 = var4.substring(0, var6);
				}

				if (var5 != null) {
					return this.b.getKnowledgePackge(var3 + "/" + var4, var5);
				} else {
					List var7 = this.b.loadProjectResourcePackages(var3);
					List var8 = null;
					ResourcePackage var9 = null;
					boolean var10 = true;
					Iterator var11 = var7.iterator();

					while(var11.hasNext()) {
						ResourcePackage var12 = (ResourcePackage)var11.next();
						if (var12.getId().equals(var4)) {
							var10 = var12.isCheck();
							var8 = var12.getResourceItems();
							var9 = var12;
							break;
						}
					}

					if (!var10) {
						throw new RuleException("知识包 【" + var4 + "】 已被停用!");
					} else if (var8 == null) {
						throw new RuleException("知识包 【" + var4 + "】 在项目 【" + var3 + "】 中不存在.");
					} else {
						ResourceBase var15 = this.a.newResourceBase();
						Iterator var16 = var8.iterator();

						while(var16.hasNext()) {
							ResourceItem var13 = (ResourceItem)var16.next();
							var15.addResource(var13.getPath(), var13.getVersion());
						}

						KnowledgeBase var17 = this.a.buildKnowledgeBase(var15);
						KnowledgePackageImpl var18 = (KnowledgePackageImpl)var17.getKnowledgePackage();
						var18.setVariableCategories(var17.getResourceLibrary().getVariableCategories());
						var18.setMonitor(var9.isMonitor());
						if (var18.isMonitor()) {
							var18.setShowFiredFlowNodeList(var9.isShowFiredFlowNodeList());
							var18.setShowLog(var9.isShowLog());
							var18.setShowMatchedRuleList(var9.isShowMatchedRuleList());
							var18.setShowNotMatchRuleList(var9.isShowNotMatchRuleList());
							var18.setInputData(var9.getInputData());
							var18.setOutputData(var9.getOutputData());
						}

						var18.resetTimestamp();
						var18.setPackageInfo(var1);
						return var18;
					}
				}
			}
		} catch (Exception var14) {
			if (var14 instanceof RuleDueException) {
				throw (RuleDueException)var14;
			} else {
				throw new RuleException(var14);
			}
		}
	}

	public KnowledgeBase buildKnowledgeBase(String var1) throws IOException {
		try {
			String[] var2 = var1.split("/");
			if (var2.length != 2) {
				throw new RuleException("请求的知识包 [" + var1 + "] 格式不正确. 正确的应该类似于： \"projectName/packageId\".");
			} else {
				String var3 = var2[0];
				String var4 = var2[1];
				int var5 = var4.indexOf(":");
				if (var5 > 0) {
					var4 = var4.substring(0, var5);
				}

				List var6 = this.b.loadProjectResourcePackages(var3);
				List var7 = null;
				boolean var8 = true;
				Iterator var9 = var6.iterator();

				while(var9.hasNext()) {
					ResourcePackage var10 = (ResourcePackage)var9.next();
					if (var10.getId().equals(var4)) {
						var8 = var10.isCheck();
						var7 = var10.getResourceItems();
						break;
					}
				}

				if (!var8) {
					throw new RuleException("知识包 【" + var4 + "】 已被停用!");
				} else if (var7 == null) {
					throw new RuleException("知识包 【" + var4 + "】 在项目 【" + var3 + "】 中不存在.");
				} else {
					ResourceBase var13 = this.a.newResourceBase();
					Iterator var14 = var7.iterator();

					while(var14.hasNext()) {
						ResourceItem var11 = (ResourceItem)var14.next();
						var13.addResource(var11.getPath(), var11.getVersion());
					}

					KnowledgeBase var15 = this.a.buildKnowledgeBase(var13);
					return var15;
				}
			}
		} catch (Exception var12) {
			if (var12 instanceof RuleDueException) {
				throw (RuleDueException)var12;
			} else {
				throw new RuleException(var12);
			}
		}
	}

	public KnowledgePackage verifyKnowledgePackage(String var1, long var2) throws IOException {
		String[] var4 = var1.split("/");
		if (var4.length != 2) {
			throw new RuleException("PackageInfo [" + var1 + "] is invalid. Correct such as \"projectName/packageId\".");
		} else {
			String var5 = var4[0];
			String var6 = var4[1];
			VersionFile var7 = this.b.getActivedKnowledgePackge(var5 + "/" + var6);
			if (var7 != null) {
				if (!var7.isState()) {
					throw new RuleException("知识包【" + var1 + "】已停用");
				} else if (var7.getTimestamp() == var2) {
					return null;
				} else {
					KnowledgePackage var8 = this.b.getKnowledgePackge(var5 + "/" + var6, var7.getName());
					CacheUtils.getKnowledgeCache().putKnowledge(var6, var8);
					return var8;
				}
			} else {
				return null;
			}
		}
	}

	public KnowledgePackage getDeployActiveKnowledgePackage(String var1) throws IOException {
		String[] var2 = var1.split("/");
		if (var2.length != 2) {
			throw new RuleException("PackageInfo [" + var1 + "] is invalid. Correct such as \"projectName/packageId\".");
		} else {
			String var3 = var2[0];
			String var4 = var2[1];
			VersionFile var5 = this.b.getActivedKnowledgePackge(var3 + "/" + var4);
			if (var5 != null) {
				KnowledgePackage var6 = this.b.getKnowledgePackge(var3 + "/" + var4, var5.getName());
				CacheUtils.getKnowledgeCache().putKnowledge(var4, var6);
				return var6;
			} else {
				return null;
			}
		}
	}

	public void setKnowledgePackageRepositoryService(KnowledgePackageRepositoryService var1) {
		this.b = var1;
	}

	public void setKnowledgeBuilder(KnowledgeBuilder var1) {
		this.a = var1;
	}
}
