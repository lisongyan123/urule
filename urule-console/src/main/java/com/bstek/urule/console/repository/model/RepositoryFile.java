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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.codehaus.jackson.annotate.JsonIgnore;

public class RepositoryFile {
	private String id = UUID.randomUUID().toString();
	private String name;
	private String comment;
	private String fullPath;
	private Type type;
	private Type folderType;
	private boolean lock;
	private String lockInfo;
	@JsonIgnore
	private LibType libType;
	@JsonIgnore
	private RepositoryFile parentFile;
	private List<RepositoryFile> children;

	public RepositoryFile() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String var1) {
		this.id = var1;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String var1) {
		this.name = var1;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String var1) {
		this.comment = var1;
	}

	public RepositoryFile getParentFile() {
		return this.parentFile;
	}

	public void setParentFile(RepositoryFile var1) {
		this.parentFile = var1;
	}

	public LibType getLibType() {
		return this.libType;
	}

	public void setLibType(LibType var1) {
		this.libType = var1;
	}

	public List<RepositoryFile> getChildren() {
		return this.children;
	}

	public void addChild(RepositoryFile var1, boolean var2) {
		if (this.children == null) {
			this.children = new ArrayList();
		}

		var1.setParentFile(this);
		if (var2) {
			this.children.add(0, var1);
		} else {
			this.children.add(var1);
		}

	}

	public void setChildren(List<RepositoryFile> var1) {
		this.children = var1;
	}

	public String getFullPath() {
		if (this.fullPath == null) {
			if (this.parentFile != null) {
				this.fullPath = this.parentFile.getFullPath();
			} else {
				this.fullPath = "";
			}

			if (this.fullPath.equals("/")) {
				this.fullPath = "";
			}

			this.fullPath = this.fullPath + "/" + this.name;
		}

		return this.fullPath;
	}

	public void setFullPath(String var1) {
		this.fullPath = var1;
	}

	public Type getType() {
		return this.type;
	}

	public void setType(Type var1) {
		this.type = var1;
	}

	public Type getFolderType() {
		return this.folderType;
	}

	public void setFolderType(Type var1) {
		this.folderType = var1;
	}

	public boolean isLock() {
		return this.lock;
	}

	public void setLock(boolean var1) {
		this.lock = var1;
	}

	public String getLockInfo() {
		return this.lockInfo;
	}

	public void setLockInfo(String var1) {
		this.lockInfo = var1;
	}
}
