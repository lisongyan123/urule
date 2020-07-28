package com.bstek.urule.console.repository;

import com.bstek.urule.SpringBootHome;
import com.bstek.urule.exception.RuleException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;
import javax.jcr.RepositoryException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.jackrabbit.core.RepositoryImpl;
import org.apache.jackrabbit.core.config.AccessManagerConfig;
import org.apache.jackrabbit.core.config.BeanConfig;
import org.apache.jackrabbit.core.config.ClusterConfig;
import org.apache.jackrabbit.core.config.DataSourceConfig;
import org.apache.jackrabbit.core.config.LoginModuleConfig;
import org.apache.jackrabbit.core.config.PersistenceManagerConfig;
import org.apache.jackrabbit.core.config.RepositoryConfig;
import org.apache.jackrabbit.core.config.RepositoryConfigurationParser;
import org.apache.jackrabbit.core.config.SecurityConfig;
import org.apache.jackrabbit.core.config.SecurityManagerConfig;
import org.apache.jackrabbit.core.config.VersioningConfig;
import org.apache.jackrabbit.core.data.DataStore;
import org.apache.jackrabbit.core.data.DataStoreFactory;
import org.apache.jackrabbit.core.data.FileDataStore;
import org.apache.jackrabbit.core.fs.FileSystem;
import org.apache.jackrabbit.core.fs.FileSystemException;
import org.apache.jackrabbit.core.fs.FileSystemFactory;
import org.apache.jackrabbit.core.fs.local.LocalFileSystem;
import org.apache.jackrabbit.core.query.QueryHandlerFactory;
import org.apache.jackrabbit.core.state.DefaultISMLocking;
import org.apache.jackrabbit.core.state.ISMLocking;
import org.apache.jackrabbit.core.state.ISMLockingFactory;
import org.apache.jackrabbit.core.util.CooperativeFileLock;
import org.apache.jackrabbit.core.util.RepositoryLockMechanism;
import org.apache.jackrabbit.core.util.RepositoryLockMechanismFactory;
import org.apache.jackrabbit.core.util.db.ConnectionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RepositoryBuilder implements InitializingBean, ApplicationContextAware {
	private String a;
	private Element b;
	private RepositoryImpl c;
	private String d;
	private ApplicationContext e;
	private String f;
	private boolean g;
	public static String databaseType;
	public static DataSource datasource;
	private Logger h = Logger.getLogger(RepositoryBuilder.class.getName());

	public RepositoryBuilder() {
	}

	public RepositoryImpl getRepository() {
		return this.c;
	}

	private SecurityConfig a() {
		SecurityConfig var1 = new SecurityConfig("uruleRepoSecurity", this.g(), this.h(), this.i());
		return var1;
	}

	private RepositoryLockMechanismFactory b() {
		return new RepositoryLockMechanismFactory() {
			public RepositoryLockMechanism getRepositoryLockMechanism() throws RepositoryException {
				return new CooperativeFileLock();
			}
		};
	}

	private FileSystemFactory a(final String var1) {
		return new FileSystemFactory() {
			public FileSystem getFileSystem() throws RepositoryException {
				try {
					LocalFileSystem var1x = new LocalFileSystem();
					var1x.setPath("" + RepositoryBuilder.this.a + "/" + var1);
					var1x.init();
					return var1x;
				} catch (FileSystemException var2) {
					throw new RepositoryException("File system initialization failure.", var2);
				}
			}
		};
	}

	private DataStoreFactory c() {
		return new DataStoreFactory() {
			public DataStore getDataStore() throws RepositoryException {
				FileDataStore var1 = new FileDataStore();
				var1.setPath("" + RepositoryBuilder.this.a + "/repository/datastore");
				var1.setMinRecordLength(100);
				return null;
			}
		};
	}

	private VersioningConfig d() {
		String var1 = "" + this.a + "/version";
		FileSystemFactory var2 = this.a("version");
		PersistenceManagerConfig var3 = this.f();
		ISMLockingFactory var4 = this.e();
		VersioningConfig var5 = new VersioningConfig(var1, var2, var3, var4);
		return var5;
	}

	private ISMLockingFactory e() {
		return new ISMLockingFactory() {
			public ISMLocking getISMLocking() throws RepositoryException {
				return new DefaultISMLocking();
			}
		};
	}

	private PersistenceManagerConfig f() {
		Properties var1 = new Properties();
		BeanConfig var2 = new BeanConfig("org.apache.jackrabbit.core.persistence.bundle.BundleFsPersistenceManager", var1);
		PersistenceManagerConfig var3 = new PersistenceManagerConfig(var2);
		return var3;
	}

	private SecurityManagerConfig g() {
		Properties var1 = new Properties();
		BeanConfig var2 = new BeanConfig("org.apache.jackrabbit.core.security.simple.SimpleSecurityManager", var1);
		SecurityManagerConfig var3 = new SecurityManagerConfig(var2, "default", (BeanConfig)null);
		return var3;
	}

	private AccessManagerConfig h() {
		Properties var1 = new Properties();
		BeanConfig var2 = new BeanConfig("org.apache.jackrabbit.core.security.simple.SimpleAccessManager", var1);
		AccessManagerConfig var3 = new AccessManagerConfig(var2);
		return var3;
	}

	private LoginModuleConfig i() {
		Properties var1 = new Properties();
		var1.put("anonymousId", "anonymous");
		var1.put("adminId", "admin");
		BeanConfig var2 = new BeanConfig("org.apache.jackrabbit.core.security.simple.SimpleLoginModule", var1);
		LoginModuleConfig var3 = new LoginModuleConfig(var2);
		return var3;
	}

	private void b(String var1) throws Exception {
		this.h.info("Build repository from user custom xml file...");
		InputStream var2 = null;

		try {
			var2 = this.e.getResource(var1).getInputStream();
			String var3 = System.getProperty("java.io.tmpdir");
			if (StringUtils.isNotBlank(var3) && var3.length() > 1) {
				if (!var3.endsWith("/") && !var3.endsWith("\\")) {
					var3 = var3 + "/urule-temp-repo-home/";
				} else {
					var3 = var3 + "urule-temp-repo-home/";
				}

				File var4 = new File(var3);
				this.a(var4);
			} else {
				var3 = "";
			}

			RepositoryConfig var8 = RepositoryConfig.create(var2, var3);
			this.c = RepositoryImpl.create(var8);
		} finally {
			if (var2 != null) {
				var2.close();
			}

		}

	}

	private void a(File var1) {
		if (var1.isDirectory()) {
			File[] var2 = var1.listFiles();
			int var3 = var2.length;

			for(int var4 = 0; var4 < var3; ++var4) {
				File var5 = var2[var4];
				this.a(var5);
			}
		}

		var1.delete();
	}

	private void j() throws Exception {
		SecurityConfig var1 = this.a();
		FileSystemFactory var2 = this.a("repository");
		String var3 = "" + this.a + "/workspaces";
		Object var4 = null;
		String var5 = "default";
		byte var6 = 0;
		VersioningConfig var7 = this.d();
		Object var8 = null;
		Object var9 = null;
		DataStoreFactory var10 = this.c();
		RepositoryLockMechanismFactory var11 = this.b();
		DataSourceConfig var12 = new DataSourceConfig();
		ConnectionFactory var13 = new ConnectionFactory();
		RepositoryConfigurationParser var14 = new RepositoryConfigurationParser(new Properties());
		this.k();
		RepositoryConfig var15 = new RepositoryConfig(this.a, var1, var2, var3, (String)var4, var5, var6, this.b, var7, (QueryHandlerFactory)var8, (ClusterConfig)var9, var10, var11, var12, var13, var14);
		var15.init();
		this.c = RepositoryImpl.create(var15);
		File var16 = new File(this.a);
		System.out.println("Use \"" + var16.getCanonicalPath() + "\" as urule repository home directory");
	}

	private void k() {
		InputStream var1 = null;
		DocumentBuilderFactory var2 = DocumentBuilderFactory.newInstance();

		try {
			DocumentBuilder var3 = var2.newDocumentBuilder();
			var1 = this.e.getResource("classpath:com/bstek/urule/console/repository/workspace_template.xml").getInputStream();
			Document var4 = var3.parse(var1);
			this.b = var4.getDocumentElement();
		} catch (Exception var8) {
			throw new RuleException(var8);
		} finally {
			IOUtils.closeQuietly(var1);
		}

	}

	public void setApplicationContext(ApplicationContext var1) throws BeansException {
		this.e = var1;
	}

	private void a(ApplicationContext var1) {
		if (var1 instanceof WebApplicationContext) {
			WebApplicationContext var2 = (WebApplicationContext)var1;
			File var3 = new File(this.a);
			if (!var3.exists()) {
				ServletContext var4 = var2.getServletContext();
				if (var4 != null) {
					String var5 = var4.getRealPath(this.a);
					if (!StringUtils.isBlank(var5) && (new File(var5)).exists()) {
						this.a = var5;
					} else {
						this.a = this.l();
					}
				} else {
					this.a = this.l();
				}
			}

			var3 = new File(this.a);
			if (!var3.exists()) {
				throw new RuleException("Repository root dir " + this.a + " is not exist.");
			}
		} else {
			this.h.info("Current is not a standard web container,so can't resolve real path for repo home dir.");
		}

		System.out.println("Use \"" + this.a + "\" as urule repository home directory.");
	}

	private String l() {
		SpringBootHome var1 = new SpringBootHome();
		File var2 = var1.findSpringbootJarHomeDir(this.getClass());
		String var3 = var2.getAbsolutePath();
		if (this.a.startsWith("/")) {
			this.a = var3 + this.a;
		} else {
			this.a = var3 + "/" + this.a;
		}

		File var4 = new File(this.a);
		if (!var4.exists()) {
			var4.mkdirs();
		}

		try {
			this.a = var4.getCanonicalPath();
		} catch (IOException var6) {
		}

		return this.a;
	}

	public void afterPropertiesSet() throws Exception {
		if (StringUtils.isNotBlank(this.f)) {
			datasource = (DataSource)this.e.getBean(this.f);
		}

		if (this.c != null) {
			this.c.shutdown();
		}

		if (StringUtils.isNotBlank(this.a) && !this.a.equals("${urule.repository.dir}")) {
			this.a(this.e);
		}

		if (StringUtils.isNotBlank(this.d)) {
			this.b(this.d);
		} else if (datasource != null) {
			if (databaseType == null) {
				throw new RuleException("You need config \"urule.repository.databasetype\" property when use spring datasource!");
			}

			this.m();
		} else {
			if (StringUtils.isBlank(this.a)) {
				throw new RuleException("You need config \"urule.repository.dir\" property for set repository home dir.");
			}

			this.j();
		}

	}

	private void m() throws Exception {
		System.out.println("Init repository from spring datasource [" + this.f + "] with database type [" + databaseType + "]...");
		String var1 = databaseType;
		if (!this.g) {
			var1 = var1 + "-without-cluster";
		}

		String var2 = "classpath:com/bstek/urule/console/repository/database/configs/" + var1 + ".xml";
		this.b(var2);
	}

	public void setRepoHomeDir(String var1) {
		this.a = var1;
	}

	public void setRepositoryXml(String var1) {
		this.d = var1;
	}

	public void setDatabaseType(String var1) {
		databaseType = var1;
	}

	public void setDatabaseCluster(boolean var1) {
		this.g = var1;
	}

	public void setRepositoryDatasourceName(String var1) {
		this.f = var1;
	}

	public void destroy() {
		System.out.println("Shutdown repository...");
		this.c.shutdown();
		System.out.println("Shutdown repository completed...");
	}
}
