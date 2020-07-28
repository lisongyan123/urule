/*******************************************************************************
 * Copyright 2017 Bstek
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.bstek.urule.console;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.Utils;
import com.bstek.urule.console.repository.SecurityRepositoryService;

import java.util.Collection;

import com.bstek.urule.console.servlet.RequestContext;
import org.springframework.context.ApplicationContext;

public class EnvironmentUtils {
	private static EnvironmentProvider a;

	public EnvironmentUtils() {
	}

	public static Principal getLoginPrincipal(RequestContext var0) {
		if (a == null) {
			initEnvironmentProvider();
		}

		return a.getLoginPrincipal(var0);
	}

	public static void initEnvironmentProvider() {
		ApplicationContext var0 = Utils.getApplicationContext();
		String var1 = PropertyConfigurer.getProperty("urule.security.enable");
		boolean var2 = Boolean.valueOf(var1);
		if (var2) {
			SecurityRepositoryService var3 = (SecurityRepositoryService)var0.getBean("urule.securityRepositoryService");
			a = new DefaultSecurityEnvironmentProvider(var3);
		} else {
			Collection var4 = var0.getBeansOfType(EnvironmentProvider.class).values();
			if (var4.size() == 0) {
				a = new DefaultEnvironmentProvider();
			} else {
				a = (EnvironmentProvider)var4.iterator().next();
			}
		}

	}

	public static EnvironmentProvider getEnvironmentProvider() {
		if (a == null) {
			initEnvironmentProvider();
		}

		return a;
	}
}
