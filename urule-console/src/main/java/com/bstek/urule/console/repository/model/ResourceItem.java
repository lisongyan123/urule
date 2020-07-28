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

public class ResourceItem {
	private String a;
	private String b;
	private String c;
	private String d;

	public ResourceItem() {
	}

	public String getName() {
		return this.a;
	}

	public void setName(String var1) {
		this.a = var1;
	}

	public String getPath() {
		return this.b;
	}

	public void setPath(String var1) {
		this.b = var1;
	}

	public String getPackageId() {
		return this.c;
	}

	public void setPackageId(String var1) {
		this.c = var1;
	}

	public String getVersion() {
		return this.d;
	}

	public void setVersion(String var1) {
		this.d = var1;
	}
}
