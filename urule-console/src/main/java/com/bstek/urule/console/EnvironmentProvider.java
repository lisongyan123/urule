package com.bstek.urule.console;

import com.bstek.urule.console.servlet.RequestContext;

import java.util.List;

public interface EnvironmentProvider {
	Principal getLoginPrincipal(RequestContext var1);

	List<Principal> getPrincipals();
}