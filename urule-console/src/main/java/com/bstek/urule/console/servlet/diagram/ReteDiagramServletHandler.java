//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.diagram;

import com.bstek.urule.Utils;
import com.bstek.urule.builder.KnowledgeBase;
import com.bstek.urule.builder.KnowledgeBuilder;
import com.bstek.urule.builder.ResourceBase;
import com.bstek.urule.console.servlet.RenderPageServletHandler;
import com.bstek.urule.model.Node;
import com.bstek.urule.model.rete.AndNode;
import com.bstek.urule.model.rete.CriteriaNode;
import com.bstek.urule.model.rete.Line;
import com.bstek.urule.model.rete.ObjectTypeNode;
import com.bstek.urule.model.rete.OrNode;
import com.bstek.urule.model.rete.Rete;
import com.bstek.urule.model.rete.TerminalNode;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class ReteDiagramServletHandler extends RenderPageServletHandler {
	private KnowledgeBuilder a;
	private ReteNodeLayout b = new ReteNodeLayout();
	private final int c = 30;
	private final int d = 30;

	public ReteDiagramServletHandler() {
	}

	public void execute(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = this.retriveMethod(var1);
		if (var3 != null) {
			this.invokeMethod(var3, var1, var2);
		} else {
			var2.setDateHeader("Expires", -1L);
			var2.setHeader("Cache-Control", "no-cache");
			var2.setHeader("Pragma", "no-cache");
			VelocityContext var4 = new VelocityContext();
			var4.put("contextPath", var1.getContextPath());
			var4.put("files", var1.getParameter("files"));
			var2.setContentType("text/html");
			var2.setCharacterEncoding("utf-8");
			Template var5 = this.ve.getTemplate("html/rete-diagram.html", "utf-8");
			PrintWriter var6 = var2.getWriter();
			var5.merge(var4, var6);
			var6.close();
		}

	}

	public void loadReteDiagramData(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
		String var3 = var1.getParameter("files");
		var3 = Utils.decodeURL(var3);
		ResourceBase var4 = this.a.newResourceBase();
		String[] var5 = var3.split(";");
		String[] var6 = var5;
		int var7 = var5.length;

		for(int var8 = 0; var8 < var7; ++var8) {
			String var9 = var6[var8];
			String[] var10 = var9.split(",");
			var9 = var10[0];
			String var11 = var10[1];
			var9 = Utils.toUTF8(var9);
			var4.addResource(var9, var11);
		}

		KnowledgeBase var12 = this.a.buildKnowledgeBase(var4);
		Rete var13 = var12.getRete();
		Diagram var14 = this.a(var13);
		this.writeObjectToJson(var2, var14);
	}

	private Diagram a(Rete var1) {
		HashMap var2 = new HashMap();
		ArrayList var3 = new ArrayList();
		NodeInfo var4 = new NodeInfo();
		DiagramContext var5 = new DiagramContext(var3, var2);
		var4.setId(var5.nextId());
		var4.setLabel("入口");
		var4.setColor("#98AFC7");
		var4.setWidth(30);
		var4.setHeight(30);
		var4.setRoundCorner(10);
		List var6 = var1.getObjectTypeNodes();
		byte var7 = 1;
		Iterator var8 = var6.iterator();

		while(true) {
			NodeInfo var10;
			List var12;
			do {
				if (!var8.hasNext()) {
					Box var17 = this.b.layout(var4);
					Diagram var18 = new Diagram(var3, var4);
					if (var17 != null) {
						var18.setWidth(var17.getWidth() + 500);
						var18.setHeight(var17.getHeight() + 300);
					}

					return var18;
				}

				ObjectTypeNode var9 = (ObjectTypeNode)var8.next();
				var10 = new NodeInfo();
				var10.setId(var5.nextId());
				var10.setLabel("T");
				String var11 = var9.getObjectTypeClass();
				if (var11.equals("__*__")) {
					var10.setTitle("否则");
				} else {
					var10.setTitle(var11);
				}

				var10.setColor("#97CBFF");
				var10.setLevel(var7);
				var10.setWidth(30);
				var10.setHeight(30);
				var10.setRoundCorner(5);
				var4.addChild(var10);
				var12 = var9.getLines();
			} while(var12 == null);

			int var13 = var7 + 1;
			Iterator var14 = var12.iterator();

			while(var14.hasNext()) {
				Line var15 = (Line)var14.next();
				Edge var16 = new Edge(var4.getId(), var10.getId());
				var3.add(var16);
				this.a(var15, var5, var10, var13);
			}
		}
	}

	private void a(Line var1, DiagramContext var2, NodeInfo var3, int var4) {
		Node var5 = var1.getTo();
		if (var5 != null) {
			Map var6 = var2.getNodeMap();
			NodeInfo var7 = null;
			if (var6.containsKey(var5)) {
				var7 = (NodeInfo)var6.get(var5);
				var2.addEdge(new Edge(var3.getId(), var7.getId()));
			} else {
				List var8 = null;
				var7 = new NodeInfo();
				var7.setLevel(var4);
				var7.setId(var2.nextId());
				var7.setWidth(30);
				var7.setHeight(30);
				if (var5 instanceof CriteriaNode) {
					CriteriaNode var9 = (CriteriaNode)var5;
					var7.setColor("#B3D9D9");
					var7.setLabel("C");
					var7.setTitle(var9.getCriteriaInfo());
					var7.setRoundCorner(30);
					var8 = var9.getLines();
				} else if (var5 instanceof AndNode) {
					AndNode var12 = (AndNode)var5;
					var8 = var12.getLines();
					var7.setColor("#DAB1D5");
					var7.setLabel("AND");
					var7.setRoundCorner(15);
				} else if (var5 instanceof OrNode) {
					OrNode var13 = (OrNode)var5;
					var8 = var13.getLines();
					var7.setColor("#82D900");
					var7.setLabel("OR");
					var7.setRoundCorner(15);
				} else if (var5 instanceof TerminalNode) {
					TerminalNode var14 = (TerminalNode)var5;
					var7.setColor("orange");
					var7.setLabel(var14.getRule().getName());
					var7.setTitle(var14.getRule().getName());
					var7.setRoundCorner(0);
				}

				var6.put(var5, var7);
				var3.addChild(var7);
				var2.addEdge(new Edge(var3.getId(), var7.getId()));
				if (var8 != null) {
					int var15 = var4 + 1;
					Iterator var10 = var8.iterator();

					while(var10.hasNext()) {
						Line var11 = (Line)var10.next();
						this.a(var11, var2, var7, var15);
					}

				}
			}
		}
	}

	public void setKnowledgeBuilder(KnowledgeBuilder var1) {
		this.a = var1;
	}

	public String url() {
		return "/retediagram";
	}
}
