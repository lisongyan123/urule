package com.bstek.urule.console.repository;

import com.bstek.urule.Utils;
import com.bstek.urule.console.DefaultRepositoryInteceptor;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.RepositoryInteceptor;
import com.bstek.urule.console.exception.NodeLockException;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.console.repository.model.Type;
import com.bstek.urule.console.repository.model.VersionFile;
import com.bstek.urule.exception.RuleException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.lock.LockManager;
import javax.jcr.version.Version;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.VersionManager;
import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.core.RepositoryImpl;
import org.apache.jackrabbit.value.BinaryImpl;
import org.apache.jackrabbit.value.DateValue;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class BaseRepositoryService implements RepositoryReader, ApplicationContextAware {
	protected RepositoryBuilder repositoryBuilder;
	protected RepositoryImpl repository;
	protected Session session;
	protected VersionManager versionManager;
	protected LockManager lockManager;
	protected RepositoryInteceptor repositoryInteceptor;

	public BaseRepositoryService() {
	}

	public void saveFile(String var1, String var2, boolean var3, String var4, Principal var5) throws Exception {
		var1 = Utils.decodeURL(var1);
		this.repositoryInteceptor.saveFile(var1, var2);
		var1 = this.processPath(var1);
		int var6 = var1.indexOf(":");
		if (var6 != -1) {
			var1 = var1.substring(0, var6);
		}

		Node var7 = this.getRootNode();
		if (!var7.hasNode(var1)) {
			throw new RuleException("File [" + var1 + "] not exist.");
		} else {
			Node var8 = var7.getNode(var1);
			this.lockCheck(var8, var5);
			boolean var9 = this.versionManager.isCheckedOut(var8.getPath());
			if (!var9) {
				this.versionManager.checkout(var8.getPath());
			}

			BinaryImpl var10 = new BinaryImpl(var2.getBytes("utf-8"));
			var8.setProperty("_data", var10);
			var8.setProperty("_file", true);
			var8.setProperty("_create_user", var5.getName());
			Calendar var11 = Calendar.getInstance();
			var11.setTime(new Date());
			DateValue var12 = new DateValue(var11);
			var8.setProperty("_create_date", var12);
			if (var3 && StringUtils.isNotBlank(var4)) {
				var8.setProperty("_version_comment", var4);
			}

			this.session.save();
			if (var3) {
				this.versionManager.checkin(var8.getPath());
			}

		}
	}

	protected void lockCheck(Node var1, Principal var2) throws Exception {
		if (this.lockManager.isLocked(var1.getPath())) {
			String var3 = this.lockManager.getLock(var1.getPath()).getLockOwner();
			if (!var3.equals(var2.getName())) {
				throw new NodeLockException("【" + var1.getName() + "】已被" + var3 + "锁定!");
			}
		}
	}

	public List<RepositoryFile> loadProjects(String var1) throws Exception {
		ArrayList var2 = new ArrayList();
		Node var3 = this.getRootNode();
		NodeIterator var4 = var3.getNodes();

		while(true) {
			Node var5;
			String var6;
			do {
				do {
					if (!var4.hasNext()) {
						return var2;
					}

					var5 = var4.nextNode();
				} while(!var5.hasProperty("_file"));

				if (!StringUtils.isNotEmpty(var1) || !var5.hasProperty("_company_id")) {
					break;
				}

				var6 = var5.getProperty("_company_id").getString();
			} while(!var1.equals(var6));

			if (var5.getName().indexOf("___resource_authority_config__file__") <= -1) {
				RepositoryFile var7 = new RepositoryFile();
				var7.setType(Type.project);
				var7.setName(var5.getName());
				var7.setFullPath("/" + var5.getName());
				var2.add(var7);
			}
		}
	}

	public List<VersionFile> getVersionFiles(String var1) throws Exception {
		var1 = this.processPath(var1);
		Node var2 = this.getRootNode();
		if (!var2.hasNode(var1)) {
			throw new RuleException("File [" + var1 + "] not exist.");
		} else {
			ArrayList var3 = new ArrayList();
			Node var4 = var2.getNode(var1);
			VersionHistory var5 = this.versionManager.getVersionHistory(var4.getPath());
			VersionIterator var6 = var5.getAllVersions();

			while(var6.hasNext()) {
				Version var7 = var6.nextVersion();
				String var8 = var7.getName();
				if (!var8.startsWith("jcr:")) {
					Node var9 = var7.getFrozenNode();
					VersionFile var10 = new VersionFile();
					var10.setName(var7.getName());
					var10.setPath(var4.getPath());
					Property var11 = var9.getProperty("_create_user");
					var10.setCreateUser(var11.getString());
					var11 = var9.getProperty("_create_date");
					var10.setCreateDate(var11.getDate().getTime());
					if (var9.hasProperty("_version_comment")) {
						var11 = var9.getProperty("_version_comment");
						var10.setComment(var11.getString());
					}

					if (var9.hasProperty("_actived")) {
						var11 = var9.getProperty("_actived");
						var10.setActived(var11.getBoolean());
					}

					if (var9.hasProperty("_timestamp")) {
						var11 = var9.getProperty("_timestamp");
						var10.setTimestamp(var11.getLong());
					}

					var3.add(0, var10);
				}
			}

			return var3;
		}
	}

	public InputStream readFile(String var1) throws Exception {
		return this.readFile(var1, (String)null);
	}

	public InputStream readFile(String var1, String var2) throws Exception {
		if (StringUtils.isNotBlank(var2)) {
			this.repositoryInteceptor.readFile(var1 + ":" + var2);
			return this.a(var1, var2);
		} else {
			this.repositoryInteceptor.readFile(var1);
			Node var3 = this.getRootNode();
			int var4 = var1.lastIndexOf(":");
			if (var4 > -1) {
				var2 = var1.substring(var4 + 1, var1.length());
				var1 = var1.substring(0, var4);
				return this.readFile(var1, var2);
			} else {
				var1 = this.processPath(var1);
				if (!var3.hasNode(var1)) {
					throw new RuleException("File [" + var1 + "] not exist.");
				} else {
					Node var5 = var3.getNode(var1);
					Property var6 = var5.getProperty("_data");
					Binary var7 = var6.getBinary();
					return var7.getStream();
				}
			}
		}
	}

	private InputStream a(String var1, String var2) throws Exception {
		var1 = this.processPath(var1);
		Node var3 = this.getRootNode();
		if (!var3.hasNode(var1)) {
			throw new RuleException("File [" + var1 + "] not exist.");
		} else {
			Node var4 = var3.getNode(var1);
			VersionHistory var5 = this.versionManager.getVersionHistory(var4.getPath());
			Version var6 = var5.getVersion(var2);
			Node var7 = var6.getFrozenNode();
			Property var8 = var7.getProperty("_data");
			Binary var9 = var8.getBinary();
			return var9.getStream();
		}
	}

	protected String processPath(String var1) {
		return var1.startsWith("/") ? var1.substring(1, var1.length()) : var1;
	}

	public Node getRootNode() throws Exception {
		return this.session.getRootNode();
	}

	public Session getSession() {
		return this.session;
	}

	public void setRepositoryBuilder(RepositoryBuilder var1) {
		this.repositoryBuilder = var1;
	}

	public void setApplicationContext(ApplicationContext var1) throws BeansException {
		try {
			this.repository = this.repositoryBuilder.getRepository();
			SimpleCredentials var2 = new SimpleCredentials("admin", "admin".toCharArray());
			var2.setAttribute("AutoRefresh", true);
			this.session = this.repository.login(var2, (String)null);
			this.versionManager = this.session.getWorkspace().getVersionManager();
			this.lockManager = this.session.getWorkspace().getLockManager();
			Collection var3 = var1.getBeansOfType(RepositoryInteceptor.class).values();
			if (var3.size() == 0) {
				this.repositoryInteceptor = new DefaultRepositoryInteceptor();
			} else {
				this.repositoryInteceptor = (RepositoryInteceptor)var3.iterator().next();
			}

		} catch (Exception var4) {
			throw new RuleException(var4);
		}
	}
}
