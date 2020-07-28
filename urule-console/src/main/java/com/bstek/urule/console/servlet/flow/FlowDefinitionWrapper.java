//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.flow;

import com.bstek.urule.model.flow.FlowNode;
import com.bstek.urule.model.flow.ProcessDefinition;
import com.bstek.urule.model.rule.Library;
import java.util.List;

public class FlowDefinitionWrapper {
	private String a;
	private boolean b;
	private List<Library> c;
	private List<FlowNode> d;

	public FlowDefinitionWrapper(ProcessDefinition var1) {
		this.a = var1.getId();
		this.b = var1.isDebug();
		this.c = var1.getLibraries();
		this.d = var1.getNodes();
	}

	public List<FlowNode> getNodes() {
		return this.d;
	}

	public void setNodes(List<FlowNode> var1) {
		this.d = var1;
	}

	public List<Library> getLibraries() {
		return this.c;
	}

	public void setLibraries(List<Library> var1) {
		this.c = var1;
	}

	public String getId() {
		return this.a;
	}

	public void setId(String var1) {
		this.a = var1;
	}

	public boolean isDebug() {
		return this.b;
	}

	public void setDebug(boolean var1) {
		this.b = var1;
	}
}
