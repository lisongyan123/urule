package com.bstek.urule.console.repository;

import com.bstek.urule.console.repository.model.FileType;
import com.bstek.urule.exception.RuleException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import org.apache.tika.io.IOUtils;

public class RepositoryRefactor {
	private RepositoryService a;

	public RepositoryRefactor(RepositoryService var1) {
		this.a = var1;
	}

	public List<String> getReferenceFiles(Node var1, String var2, String var3) throws Exception {
		ArrayList var4 = new ArrayList();
		List var5 = this.getFiles(var1, var2);
		Iterator var6 = var5.iterator();

		while(var6.hasNext()) {
			String var7 = (String)var6.next();
			InputStream var8 = this.a.readFile(var7, (String)null);

			try {
				String var9 = IOUtils.toString(var8);
				var8.close();
				boolean var10 = var9.contains(var2);
				boolean var11 = var9.contains(var3);
				if (var10 && var11) {
					var4.add(var7);
				}
			} catch (IOException var12) {
				throw new RuleException(var12);
			}
		}

		return var4;
	}

	public List<String> getFiles(Node var1, String var2) {
		String var3 = this.a(var2);

		try {
			ArrayList var4 = new ArrayList();
			Node var5 = var1.getNode(var3);
			this.a(var4, var5);
			return var4;
		} catch (Exception var6) {
			throw new RuleException(var6);
		}
	}

	private void a(List<String> var1, Node var2) throws RepositoryException {
		Node var4;
		for(NodeIterator var3 = var2.getNodes(); var3.hasNext(); this.a(var1, var4)) {
			var4 = var3.nextNode();
			String var5 = var4.getPath();
			if (var5.endsWith(FileType.Ruleset.toString())) {
				var1.add(var5);
			} else if (var5.endsWith(FileType.UL.toString())) {
				var1.add(var5);
			} else if (var5.endsWith(FileType.DecisionTable.toString())) {
				var1.add(var5);
			} else if (var5.endsWith(FileType.ScriptDecisionTable.toString())) {
				var1.add(var5);
			} else if (var5.endsWith(FileType.DecisionTree.toString())) {
				var1.add(var5);
			} else if (var5.endsWith(FileType.RuleFlow.toString())) {
				var1.add(var5);
			}
		}

	}

	private String a(String var1) {
		if (var1.startsWith("/")) {
			var1 = var1.substring(1);
		}

		int var2 = var1.indexOf("/");
		return var1.substring(0, var2);
	}
}
