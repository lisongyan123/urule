//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.repository;

import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NodeLockException;
import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.console.repository.model.LibType;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.repository.model.Type;
import com.bstek.urule.console.repository.refactor.Item;
import com.bstek.urule.console.repository.refactor.RefactorService;
import com.bstek.urule.console.repository.refactor.RefactorServiceImpl;
import com.bstek.urule.exception.RuleException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.lock.Lock;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.value.BinaryImpl;
import org.apache.jackrabbit.value.DateValue;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RepositoryServiceImpl extends BaseRepositoryService implements RepositoryService, ApplicationContextAware {
	private ClientProvider a;
	protected RefactorService refactorService;
	private ImportProjectProcessor b;

	public RepositoryServiceImpl() {
	}

	public List<ClientConfig> loadClientConfigs(String var1, boolean var2) throws Exception {
		ArrayList var3 = new ArrayList();
		if (this.a != null) {
			List var18 = this.a.loadClients(var1, var2);
			return (List)(var18 == null ? var3 : var18);
		} else {
			Node var4 = this.getRootNode();
			String var5 = this.processPath(var1) + "/" + "___client_config__file__";
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
				Iterator var13 = var12.elements().iterator();

				while(var13.hasNext()) {
					Object var14 = var13.next();
					if (var14 instanceof Element) {
						Element var15 = (Element)var14;
						if (var15.getName().equals("item")) {
							ClientConfig var16 = new ClientConfig();
							var16.setName(var15.attributeValue("name"));
							var16.setClient(var15.attributeValue("client"));
							String var17 = var15.attributeValue("enabled");
							if (var17 == null) {
								var16.setEnabled(true);
							} else {
								var16.setEnabled(Boolean.valueOf(var17));
							}

							var16.setProject(var1);
							if (var2) {
								var3.add(var16);
							} else if (var16.isEnabled()) {
								var3.add(var16);
							}
						}
					}
				}

				return var3;
			}
		}
	}

	public ClientProvider getClientProvider() {
		return this.a;
	}

	public List<RepositoryFile> getDirectories(String var1) throws Exception {
		Node var2 = this.getRootNode();
		NodeIterator var3 = var2.getNodes();
		Node var4 = null;

		while(var3.hasNext()) {
			Node var5 = var3.nextNode();
			if (var5.hasProperty("_file")) {
				String var6 = var5.getName();
				if (var1 == null || var1.equals(var6)) {
					var4 = var5;
					break;
				}
			}
		}

		if (var4 == null) {
			throw new RuleException("Project [" + var1 + "] not exist.");
		} else {
			ArrayList var11 = new ArrayList();
			RepositoryFile var12 = new RepositoryFile();
			var12.setName("根目录");
			String var7 = var4.getPath();
			var12.setFullPath(var7);
			var11.add(var12);
			NodeIterator var8 = var4.getNodes();

			while(var8.hasNext()) {
				Node var9 = var8.nextNode();
				if (var9.hasProperty("_dir")) {
					RepositoryFile var10 = new RepositoryFile();
					var10.setName(var9.getPath().substring(var7.length()));
					var10.setFullPath(var9.getPath());
					var11.add(var10);
					this.a((Node)var9, (List)var11, (String)var7);
				}
			}

			return var11;
		}
	}

	private void a(Node var1, List<RepositoryFile> var2, String var3) throws Exception {
		NodeIterator var4 = var1.getNodes();

		while(var4.hasNext()) {
			Node var5 = var4.nextNode();
			if (var5.hasProperty("_file") && var5.hasProperty("_dir")) {
				RepositoryFile var6 = new RepositoryFile();
				var6.setName(var5.getPath().substring(var3.length()));
				var6.setFullPath(var5.getPath());
				this.a(var5, var2, var3);
				var2.add(var6);
			}
		}

	}

	public Repository loadRepository(String var1, Principal var2, boolean var3, FileType[] var4, String var5) throws Exception {
		String var6 = null;
		if (var2 != null) {
			var6 = var2.getCompanyId();
			this.a(var2);
		}

		if (var1 != null && var1.startsWith("/")) {
			var1 = var1.substring(1, var1.length());
		}

		Repository var7 = new Repository();
		ArrayList var8 = new ArrayList();
		var7.setProjectNames(var8);
		RepositoryFile var9 = new RepositoryFile();
		var9.setFullPath("/");
		var9.setName("项目列表");
		var9.setType(Type.root);
		Node var10 = this.getRootNode();
		NodeIterator var11 = var10.getNodes();

		while(true) {
			Node var12;
			String var13;
			do {
				do {
					do {
						do {
							do {
								if (!var11.hasNext()) {
									var7.setRootFile(var9);
									return var7;
								}

								var12 = var11.nextNode();
							} while(!var12.hasProperty("_file"));

							if (!StringUtils.isNotEmpty(var6) || !var12.hasProperty("_company_id")) {
								break;
							}

							var13 = var12.getProperty("_company_id").getString();
						} while(!var6.equals(var13));

						var13 = var12.getName();
					} while(var13.indexOf("___resource_authority_config__file__") > -1);
				} while(var13.indexOf("___temp_mount_project_node_for_import__") > -1);
			} while(StringUtils.isNotBlank(var1) && !var1.equals(var13));

			if (StringUtils.isBlank(var1)) {
				var8.add(var13);
			}

			RepositoryFile var14 = this.buildProjectFile(var12, var4, var3, var5);
			var9.addChild(var14, false);
		}
	}

	public List<ProjectVariable> loadProjectLibraries(String var1, Principal var2) throws Exception {
		Repository var3 = this.loadRepository(var1, var2, false, new FileType[]{FileType.VariableLibrary, FileType.ParameterLibrary}, (String)null);
		List var4 = var3.getRootFile().getChildren();
		ArrayList var5 = new ArrayList();
		this.a((List)var4, (List)var5);
		return var5;
	}

	private void a(List<RepositoryFile> var1, List<ProjectVariable> var2) {
		if (var1 != null) {
			RepositoryFile var4;
			for(Iterator var3 = var1.iterator(); var3.hasNext(); this.a(var4.getChildren(), var2)) {
				var4 = (RepositoryFile)var3.next();
				String var5 = var4.getFullPath();
				if (var5.endsWith(FileType.VariableLibrary.toString()) || var5.endsWith(FileType.ParameterLibrary.toString())) {
					ProjectVariable var6 = new ProjectVariable();
					var6.setPath(var5);
					var2.add(var6);
				}
			}

		}
	}

	protected RepositoryFile buildProjectFile(Node var1, FileType[] var2, boolean var3, String var4) throws Exception {
		RepositoryFile var5 = new RepositoryFile();
		var5.setType(Type.project);
		var5.setName(var1.getName());
		var5.setFullPath("/" + var1.getName());
		RepositoryFile var6 = new RepositoryFile();
		var6.setFullPath(var5.getFullPath());
		var6.setName("资源");
		if (var2 == null || var2.length == 0) {
			RepositoryFile var7 = new RepositoryFile();
			var7.setName("知识包");
			var7.setType(Type.resourcePackage);
			var7.setFullPath(var5.getFullPath());
			var5.addChild(var7, false);
		}

		if (var3) {
			var6.setType(Type.resource);
			this.b(var1, var6, var2, var4);
		} else {
			var6.setType(Type.all);
			this.a(var1, var6, var2, var4);
		}

		var5.addChild(var6, false);
		return var5;
	}

	private void a(Node var1, RepositoryFile var2, FileType[] var3, String var4) throws Exception {
		FileType[] var5 = var3;
		if (var3 == null || var3.length == 0) {
			var5 = new FileType[]{FileType.VariableLibrary, FileType.ParameterLibrary, FileType.ConstantLibrary, FileType.ActionLibrary, FileType.Ruleset, FileType.RuleFlow, FileType.DecisionTable, FileType.Crosstab, FileType.DecisionTree, FileType.ScriptDecisionTable, FileType.UL, FileType.Scorecard, FileType.ComplexScorecard, FileType.ConditionTemplate, FileType.ActionTemplate};
		}

		var2.setLibType(LibType.all);
		this.a(var1.getNodes(), var2, var5, Type.all, var4);
	}

	private void b(Node var1, RepositoryFile var2, FileType[] var3, String var4) throws Exception {
		RepositoryFile var5 = this.a(var2, "库", LibType.res);
		var5.setType(Type.lib);
		var2.addChild(var5, false);
		FileType[] var6 = var3;
		if (var3 == null || var3.length == 0) {
			var6 = new FileType[]{FileType.VariableLibrary, FileType.ParameterLibrary, FileType.ConstantLibrary, FileType.ActionLibrary};
		}

		this.a(var1.getNodes(), var5, var6, Type.lib, var4);
		RepositoryFile var7 = this.a(var2, "决策集", LibType.ruleset);
		var7.setFullPath(var2.getFullPath());
		var7.setType(Type.ruleLib);
		RepositoryFile var8 = this.a(var2, "决策表", LibType.decisiontable);
		var8.setFullPath(var2.getFullPath());
		var8.setType(Type.decisionTableLib);
		RepositoryFile var9 = this.a(var2, "决策树", LibType.decisiontree);
		var9.setFullPath(var2.getFullPath());
		var9.setType(Type.decisionTreeLib);
		RepositoryFile var10 = this.a(var2, "评分卡", LibType.scorecard);
		var10.setFullPath(var2.getFullPath());
		var10.setType(Type.scorecardLib);
		RepositoryFile var11 = this.a(var2, "决策流", LibType.ruleflow);
		var11.setFullPath(var2.getFullPath());
		var11.setType(Type.flowLib);
		var2.addChild(var7, false);
		var2.addChild(var8, false);
		var2.addChild(var9, false);
		var2.addChild(var10, false);
		var2.addChild(var11, false);
		FileType[] var12 = var3;
		if (var3 == null || var3.length == 0) {
			var12 = new FileType[]{FileType.Ruleset, FileType.UL, FileType.ConditionTemplate, FileType.ActionTemplate};
		}

		FileType[] var13 = var3;
		if (var3 == null || var3.length == 0) {
			var13 = new FileType[]{FileType.DecisionTable, FileType.ScriptDecisionTable, FileType.Crosstab};
		}

		FileType[] var14 = var3;
		if (var3 == null || var3.length == 0) {
			var14 = new FileType[]{FileType.DecisionTree};
		}

		FileType[] var15 = var3;
		if (var3 == null || var3.length == 0) {
			var15 = new FileType[]{FileType.RuleFlow};
		}

		FileType[] var16 = var3;
		if (var3 == null || var3.length == 0) {
			var16 = new FileType[]{FileType.Scorecard, FileType.ComplexScorecard};
		}

		this.a(var1.getNodes(), var7, var12, Type.ruleLib, var4);
		this.a(var1.getNodes(), var8, var13, Type.decisionTableLib, var4);
		this.a(var1.getNodes(), var9, var14, Type.decisionTreeLib, var4);
		this.a(var1.getNodes(), var10, var16, Type.scorecardLib, var4);
		this.a(var1.getNodes(), var11, var15, Type.flowLib, var4);
	}

	private RepositoryFile a(RepositoryFile var1, String var2, LibType var3) {
		RepositoryFile var4 = new RepositoryFile();
		var4.setFullPath(var1.getFullPath());
		var4.setName(var2);
		var4.setLibType(var3);
		return var4;
	}

	private void a(NodeIterator var1, RepositoryFile var2, FileType[] var3, Type var4, String var5) throws Exception {
		LibType var6 = var2.getLibType();

		while(true) {
			Node var7;
			RepositoryFile var8;
			String var9;
			FileType var10;
			do {
				do {
					do {
						do {
							do {
								do {
									boolean var11;
									do {
										label133:
										do {
											while(true) {
												do {
													do {
														do {
															do {
																if (!var1.hasNext()) {
																	return;
																}

																var7 = var1.nextNode();
															} while(!var7.hasProperty("_file"));

															var8 = new RepositoryFile();
															var8.setLibType(var6);
															var9 = var7.getName();
														} while(var9.toLowerCase().indexOf("___res__package__file__") > -1);
													} while(var9.toLowerCase().indexOf("___client_config__file__") > -1);
												} while(var9.toLowerCase().indexOf("___resource_authority_config__file__") > -1);

												if (!var7.hasProperty("_dir")) {
													var10 = null;
													var11 = false;
													FileType[] var12 = var3;
													int var13 = var3.length;

													for(int var14 = 0; var14 < var13; ++var14) {
														FileType var15 = var12[var14];
														if (var9.toLowerCase().endsWith(var15.toString())) {
															var10 = var15;
															var11 = true;
															continue label133;
														}
													}
													break;
												}

												var8.setFullPath(var7.getPath());
												var8.setName(var9);
												var8.setType(Type.folder);
												this.a(var7, var8);
												var8.setFolderType(var4);
												var2.addChild(var8, true);
												this.a(var7.getNodes(), var8, var3, var4, var5);
											}
										} while(!var11);
									} while(var6.equals(LibType.res) && !var10.equals(FileType.ActionLibrary) && !var10.equals(FileType.ParameterLibrary) && !var10.equals(FileType.ConstantLibrary) && !var10.equals(FileType.VariableLibrary));
								} while(var6.equals(LibType.decisiontable) && !var10.equals(FileType.ScriptDecisionTable) && !var10.equals(FileType.DecisionTable) && !var10.equals(FileType.Crosstab));
							} while(var6.equals(LibType.decisiontree) && !var10.equals(FileType.DecisionTree));
						} while(var6.equals(LibType.ruleflow) && !var10.equals(FileType.RuleFlow));
					} while(var6.equals(LibType.scorecard) && !var10.equals(FileType.Scorecard) && !var10.equals(FileType.ComplexScorecard));
				} while(var6.equals(LibType.ruleset) && !var10.equals(FileType.Ruleset) && !var10.equals(FileType.UL) && !var10.equals(FileType.ConditionTemplate) && !var10.equals(FileType.ActionTemplate));
			} while(StringUtils.isNotBlank(var5) && var9.toLowerCase().indexOf(var5.toLowerCase()) == -1);

			if (var9.toLowerCase().endsWith(FileType.ActionLibrary.toString())) {
				var8.setType(Type.action);
			} else if (var9.toLowerCase().endsWith(FileType.VariableLibrary.toString())) {
				var8.setType(Type.variable);
			} else if (var9.toLowerCase().endsWith(FileType.ConstantLibrary.toString())) {
				var8.setType(Type.constant);
			} else if (var9.toLowerCase().endsWith(FileType.Ruleset.toString())) {
				var8.setType(Type.rule);
			} else if (var9.toLowerCase().endsWith(FileType.DecisionTable.toString())) {
				var8.setType(Type.decisionTable);
			} else if (var9.toLowerCase().endsWith(FileType.Crosstab.toString())) {
				var8.setType(Type.crosstab);
			} else if (var9.toLowerCase().endsWith(FileType.UL.toString())) {
				var8.setType(Type.ul);
			} else if (var9.toLowerCase().endsWith(FileType.ParameterLibrary.toString())) {
				var8.setType(Type.parameter);
			} else if (var9.toLowerCase().endsWith(FileType.RuleFlow.toString())) {
				var8.setType(Type.flow);
			} else if (var9.toLowerCase().endsWith(FileType.ScriptDecisionTable.toString())) {
				var8.setType(Type.scriptDecisionTable);
			} else if (var9.toLowerCase().endsWith(FileType.DecisionTree.toString())) {
				var8.setType(Type.decisionTree);
			} else if (var9.toLowerCase().endsWith(FileType.Scorecard.toString())) {
				var8.setType(Type.scorecard);
			} else if (var9.toLowerCase().endsWith(FileType.ComplexScorecard.toString())) {
				var8.setType(Type.complexscorecard);
			} else if (var9.toLowerCase().endsWith(FileType.ConditionTemplate.toString())) {
				var8.setType(Type.conditionTemplate);
			} else if (var9.toLowerCase().endsWith(FileType.ActionTemplate.toString())) {
				var8.setType(Type.actionTemplate);
			}

			var8.setFullPath(var7.getPath());
			var8.setName(var9);
			this.a(var7, var8);
			var2.addChild(var8, false);
			this.a(var7.getNodes(), var8, var3, var4, var5);
		}
	}

	private void a(Node var1, RepositoryFile var2) throws Exception {
		String var3 = var1.getPath();
		if (this.lockManager.isLocked(var3)) {
			String var4 = this.lockManager.getLock(var3).getLockOwner();
			var2.setLock(true);
			var2.setLockInfo("被" + var4 + "锁定");
		}
	}

	public void lockPath(String var1, Principal var2) throws Exception {
		var1 = this.processPath(var1);
		int var3 = var1.indexOf(":");
		if (var3 != -1) {
			var1 = var1.substring(0, var3);
		}

		Node var4 = this.getRootNode();
		if (!var4.hasNode(var1)) {
			throw new RuleException("File [" + var1 + "] not exist.");
		} else {
			Node var5 = var4.getNode(var1);
			String var6 = var5.getPath();
			if (this.lockManager.isLocked(var6)) {
				String var11 = this.lockManager.getLock(var6).getLockOwner();
				throw new NodeLockException("【" + var1 + "】已被" + var11 + "锁定，您不能进行再次锁定!");
			} else {
				ArrayList var7 = new ArrayList();
				this.a(var5, (Principal)var2, (List)var7, var1);
				Iterator var8 = var7.iterator();

				while(var8.hasNext()) {
					Node var9 = (Node)var8.next();
					if (this.lockManager.isLocked(var9.getPath())) {
						Lock var10 = this.lockManager.getLock(var9.getPath());
						this.lockManager.unlock(var10.getNode().getPath());
					}
				}

				if (!var5.isNodeType("{http://www.jcp.org/jcr/mix/1.0}lockable")) {
					if (!var5.isCheckedOut()) {
						this.versionManager.checkout(var5.getPath());
					}

					var5.addMixin("mix:lockable");
					this.session.save();
				}

				this.lockManager.lock(var6, true, true, 9223372036854775807L, var2.getName());
			}
		}
	}

	private void a(Node var1, Principal var2, List<Node> var3, String var4) throws Exception {
		NodeIterator var5 = var1.getNodes();

		while(var5.hasNext()) {
			Node var6 = var5.nextNode();
			String var7 = var6.getPath();
			if (this.lockManager.isLocked(var7)) {
				Lock var8 = this.lockManager.getLock(var7);
				String var9 = var8.getLockOwner();
				if (!var2.getName().equals(var9)) {
					throw new NodeLockException("当前目录下有子目录被其它人锁定，您不能执行锁定" + var4 + "目录");
				}

				var3.add(var6);
				this.a(var6, var2, var3, var4);
			}
		}

	}

	public void unlockPath(String var1, Principal var2) throws Exception {
		var1 = this.processPath(var1);
		int var3 = var1.indexOf(":");
		if (var3 != -1) {
			var1 = var1.substring(0, var3);
		}

		Node var4 = this.getRootNode();
		if (!var4.hasNode(var1)) {
			throw new RuleException("File [" + var1 + "] not exist.");
		} else {
			Node var5 = var4.getNode(var1);
			String var6 = var5.getPath();
			if (!this.lockManager.isLocked(var6)) {
				throw new NodeLockException("当前文件未锁定，不需要解锁!");
			} else {
				Lock var7 = this.lockManager.getLock(var6);
				String var8 = var7.getLockOwner();
				if (!var8.equals(var2.getName())) {
					throw new NodeLockException("当前文件由【" + var8 + "】锁定，您无权解锁!");
				} else {
					this.lockManager.unlock(var7.getNode().getPath());
				}
			}
		}
	}

	public void deleteFile(String var1, Principal var2) throws Exception {
		this.repositoryInteceptor.deleteFile(var1);
		var1 = this.processPath(var1);
		Node var3 = this.getRootNode();
		if (!var3.hasNode(var1)) {
			throw new RuleException("File [" + var1 + "] not exist.");
		} else {
			String[] var4 = var1.split("/");
			Node var5 = var3;
			String[] var6 = var4;
			int var7 = var4.length;

			for(int var8 = 0; var8 < var7; ++var8) {
				String var9 = var6[var8];
				if (!StringUtils.isEmpty(var9)) {
					String[] var10 = var9.split("\\.");
					String[] var11 = var10;
					int var12 = var10.length;

					for(int var13 = 0; var13 < var12; ++var13) {
						String var14 = var11[var13];
						if (!StringUtils.isEmpty(var14) && var5.hasNode(var14)) {
							var5 = var5.getNode(var14);
							this.lockCheck(var5, var2);
							if (!var5.isCheckedOut()) {
								this.versionManager.checkout(var5.getPath());
							}
						}
					}
				}
			}

			var5 = var3.getNode(var1);
			this.lockCheck(var5, var2);
			if (!var5.isCheckedOut()) {
				this.versionManager.checkout(var5.getPath());
			}

			var5.remove();
			this.session.save();
		}
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

	public boolean fileExistCheck(String var1) throws Exception {
		Node var2 = this.getRootNode();
		var1 = this.processPath(var1);
		if (!var1.contains(" ") && !var1.equals("")) {
			return var2.hasNode(var1);
		} else {
			return true;
		}
	}

	public RepositoryFile createProject(String var1, Principal var2, boolean var3) throws Exception {
		this.repositoryInteceptor.createProject(var1);
		Node var4 = this.getRootNode();
		if (var4.hasNode(var1)) {
			throw new RuleException("Project [" + var1 + "] already exist.");
		} else {
			Node var5 = var4.addNode(var1);
			var5.addMixin("mix:versionable");
			var5.setProperty("_file", true);
			var5.setProperty("_create_user", var2.getName());
			var5.setProperty("_company_id", var2.getCompanyId());
			Calendar var6 = Calendar.getInstance();
			var6.setTime(new Date());
			DateValue var7 = new DateValue(var6);
			var5.setProperty("_create_date", var7);
			this.session.save();
			this.a(var1, var2);
			this.b(var1, var2);
			RepositoryFile var8 = this.buildProjectFile(var5, (FileType[])null, var3, (String)null);
			return var8;
		}
	}

	public void createDir(String var1, Principal var2) throws Exception {
		this.repositoryInteceptor.createDir(var1);
		Node var3 = this.getRootNode();
		var1 = this.processPath(var1);
		if (var3.hasNode(var1)) {
			throw new RuleException("Dir [" + var1 + "] already exist.");
		} else {
			boolean var4 = false;
			String[] var5 = var1.split("/");
			Node var6 = var3;
			String[] var7 = var5;
			int var8 = var5.length;

			for(int var9 = 0; var9 < var8; ++var9) {
				String var10 = var7[var9];
				if (!StringUtils.isEmpty(var10)) {
					String[] var11 = var10.split("\\.");
					String[] var12 = var11;
					int var13 = var11.length;

					for(int var14 = 0; var14 < var13; ++var14) {
						String var15 = var12[var14];
						if (!StringUtils.isEmpty(var15)) {
							if (var6.hasNode(var15)) {
								var6 = var6.getNode(var15);
							} else {
								var6 = var6.addNode(var15);
								var6.addMixin("mix:versionable");
								var6.addMixin("mix:lockable");
								var6.setProperty("_dir", true);
								var6.setProperty("_file", true);
								var6.setProperty("_create_user", var2.getName());
								Calendar var16 = Calendar.getInstance();
								var16.setTime(new Date());
								DateValue var17 = new DateValue(var16);
								var6.setProperty("_create_date", var17);
								var4 = true;
							}
						}
					}
				}
			}

			if (var4) {
				this.session.save();
			}

		}
	}

	public void createFile(String var1, String var2, Principal var3) throws Exception {
		this.a(var1, var2, var3, true);
	}

	public void fileRename(String var1, String var2) throws Exception {
		this.repositoryInteceptor.renameFile(var1, var2);
		var1 = this.processPath(var1);
		var2 = this.processPath(var2);
		Node var3 = this.getRootNode();
		if (!var3.hasNode(var1)) {
			throw new RuleException("File [" + var1 + "] not exist.");
		} else {
			this.refactorService.refactorFile("/" + var1, "/" + var2);
			this.session.getWorkspace().move("/" + var1, "/" + var2);
			this.session.save();
		}
	}

	public void exportXml(String var1, OutputStream var2) throws Exception {
		this.session.exportSystemView(var1, var2, false, false);
	}

	public void exportXml(OutputStream var1) throws Exception {
		this.session.exportSystemView("/", var1, false, false);
	}

	public void importXml(InputStream var1, boolean var2) throws Exception {
		String var3 = this.getRootNode().getPath();
		if (var2) {
			this.session.importXML(var3, var1, 2);
		} else {
			Repository var4 = this.loadRepository((String)null, (Principal)null, false, (FileType[])null, (String)null);
			this.b.doImport(var1, var4);
		}

		this.session.save();
	}

	private void a(String var1, Principal var2) throws Exception {
		String var3 = this.processPath(var1) + "/" + "___res__package__file__";
		Node var4 = this.getRootNode();
		if (!var4.hasNode(var3)) {
			this.createFile(var3, "<?xml version=\"1.0\" encoding=\"utf-8\"?><res-packages></res-packages>", var2);
		}

	}

	private void b(String var1, Principal var2) throws Exception {
		Node var3 = this.getRootNode();
		String var4 = this.processPath(var1) + "/" + "___client_config__file__";
		if (!var3.hasNode(var4)) {
			this.createFile(var4, "<?xml version=\"1.0\" encoding=\"utf-8\"?><client-config></client-config>", var2);
		}

	}

	private void a(Principal var1) throws Exception {
		String var2 = var1.getCompanyId();
		String var3 = "___resource_authority_config__file__" + (var2 == null ? "" : var2);
		Node var4 = this.getRootNode();
		if (!var4.hasNode(var3)) {
			this.a(var3, "<?xml version=\"1.0\" encoding=\"utf-8\"?><user-permission></user-permission>", var1, false);
		}

	}

	private void a(String var1, String var2, Principal var3, boolean var4) throws Exception {
		String var5 = var3.getName();
		this.repositoryInteceptor.createFile(var1, var2);
		Node var6 = this.getRootNode();
		var1 = this.processPath(var1);
		if (var6.hasNode(var1)) {
			throw new RuleException("File [" + var1 + "] already exist.");
		} else {
			Node var7 = var6.addNode(var1);
			var7.addMixin("mix:versionable");
			var7.addMixin("mix:lockable");
			BinaryImpl var8 = new BinaryImpl(var2.getBytes("UTF-8"));
			var7.setProperty("_data", var8);
			if (var4) {
				var7.setProperty("_file", true);
			}

			var7.setProperty("_create_user", var5);
			Calendar var9 = Calendar.getInstance();
			var9.setTime(new Date());
			DateValue var10 = new DateValue(var9);
			var7.setProperty("_create_date", var10);
			this.session.save();
		}
	}

	public void refactorContent(String var1, Item var2) throws Exception {
		this.refactorService.refactorItem(var1, var2);
	}

	public boolean fileExist(String var1) throws Exception {
		Node var2 = this.getRootNode();
		return var2.hasNode(var1);
	}

	public RefactorService getRefactorService() {
		return this.refactorService;
	}

	public void setApplicationContext(ApplicationContext var1) throws BeansException {
		super.setApplicationContext(var1);
		this.refactorService = new RefactorServiceImpl(this, var1);
		this.b = new ImportProjectProcessor(this);
		Collection var2 = var1.getBeansOfType(ClientProvider.class).values();
		if (var2.size() > 0) {
			this.a = (ClientProvider)var2.iterator().next();
		}

	}
}
