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
package com.bstek.urule.console.repository.model;

import com.bstek.urule.runtime.monitor.MonitorObject;
import java.util.Date;
import java.util.List;

public class ResourcePackage {
	private String a;
	private String b;
	private String c;
	private String d;
	private boolean e;
	private String f;
	private Date g;
	private boolean h;
	private boolean i;
	private boolean j;
	private boolean k;
	private boolean l;
	private ServiceConfig m;
	private List<MonitorObject> n;
	private List<MonitorObject> o;
	private List<ResourceItem> p;

	public ResourcePackage() {
	}

	public String getId() {
		return this.a;
	}

	public void setId(String var1) {
		this.a = var1;
	}

	public String getName() {
		return this.b;
	}

	public void setName(String var1) {
		this.b = var1;
	}

	public boolean isCheck() {
		return this.e;
	}

	public void setCheck(boolean var1) {
		this.e = var1;
	}

	public String getCheckState() {
		return this.f;
	}

	public void setCheckState(String var1) {
		this.f = var1;
	}

	public String getProject() {
		return this.c;
	}

	public void setProject(String var1) {
		this.c = var1;
	}

	public String getQuickTestData() {
		return this.d;
	}

	public void setQuickTestData(String var1) {
		this.d = var1;
	}

	public Date getCreateDate() {
		return this.g;
	}

	public void setCreateDate(Date var1) {
		this.g = var1;
	}

	public boolean isMonitor() {
		return this.h;
	}

	public void setMonitor(boolean var1) {
		this.h = var1;
	}

	public boolean isShowLog() {
		return this.i;
	}

	public void setShowLog(boolean var1) {
		this.i = var1;
	}

	public ServiceConfig getService() {
		return this.m;
	}

	public void setService(ServiceConfig var1) {
		this.m = var1;
	}

	public boolean isShowMatchedRuleList() {
		return this.j;
	}

	public void setShowMatchedRuleList(boolean var1) {
		this.j = var1;
	}

	public boolean isShowNotMatchRuleList() {
		return this.k;
	}

	public void setShowNotMatchRuleList(boolean var1) {
		this.k = var1;
	}

	public boolean isShowFiredFlowNodeList() {
		return this.l;
	}

	public void setShowFiredFlowNodeList(boolean var1) {
		this.l = var1;
	}

	public List<MonitorObject> getInputData() {
		return this.n;
	}

	public void setInputData(List<MonitorObject> var1) {
		this.n = var1;
	}

	public List<MonitorObject> getOutputData() {
		return this.o;
	}

	public void setOutputData(List<MonitorObject> var1) {
		this.o = var1;
	}

	public List<ResourceItem> getResourceItems() {
		return this.p;
	}

	public void setResourceItems(List<ResourceItem> var1) {
		this.p = var1;
	}
}
