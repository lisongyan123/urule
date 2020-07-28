package com.bstek.urule.console.repository;

import com.bstek.urule.builder.resource.Resource;
import com.bstek.urule.builder.resource.ResourceProvider;
import com.bstek.urule.exception.RuleException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

public class RepositoryResourceProvider implements ResourceProvider {
	public static final String JCR = "jcr:";
	private RepositoryService a;

	public RepositoryResourceProvider() {
	}

	public Resource provide(String var1, String var2) {
		String var3 = var1.substring(4, var1.length());
		InputStream var4 = null;

		try {
			if (!StringUtils.isEmpty(var2) && !var2.equals("LATEST")) {
				var4 = this.a.readFile(var3, var2);
			} else {
				var4 = this.a.readFile(var3, (String)null);
			}

			String var5 = IOUtils.toString(var4, "utf-8");
			IOUtils.closeQuietly(var4);
			return new Resource(var5, var1, var2);
		} catch (Exception var6) {
			throw new RuleException(var6);
		}
	}

	public boolean support(String var1) {
		return var1.startsWith("jcr:");
	}

	public void setRepositoryService(RepositoryService var1) {
		this.a = var1;
	}
}
