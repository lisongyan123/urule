package com.bstek.urule.console;

import com.bstek.urule.console.servlet.RequestContext;

import java.util.List;

public class DefaultEnvironmentProvider implements EnvironmentProvider {
	public DefaultEnvironmentProvider() {
	}

	public Principal getLoginPrincipal(RequestContext var1) {
		DefaultPrincipal var2 = new DefaultPrincipal();
		var2.setCompanyId("bstek");
		var2.setName("admin");
		var2.setAdmin(true);
		return var2;
	}

	@Override
	public List<Principal> getPrincipals() {
		return null;
	}
}
