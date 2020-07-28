//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.diagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReteNodeLayout {
    private final int a = 50;
    private final int b = 50;
    private final int c = 30;
    private final int d = 30;

    public ReteNodeLayout() {
    }

    public Box layout(NodeInfo var1) {
        List var2 = var1.getChildren();
        if (var2 == null) {
            return null;
        } else {
            HashMap var3 = new HashMap();
            HashMap var4 = new HashMap();
            this.a(var2, var4);
            int var5 = this.a(var4) - 1;
            int var6 = var5 * 30 + var5 * 50;
            int var7 = var6 / 2 + 50 + 30;
            var1.setX(var7);
            var1.setY(5);
            this.a(var2, var1, var4, var3);
            Box var8 = new Box();
            var8.setWidth(var6 + 100 + 30);
            int var9 = var4.size() * 30 * 3 + 100;
            var8.setHeight(var9);
            return var8;
        }
    }

    private void a(List<NodeInfo> var1, NodeInfo var2, Map<Integer, List<NodeInfo>> var3, Map<Integer, Integer> var4) {
        for(int var5 = 0; var5 < var1.size(); ++var5) {
            NodeInfo var6 = (NodeInfo)var1.get(var5);
            int var7 = var6.getLevel();
            var6.setY(var7 * 50 + var7 * 30);
            List var8 = var6.getChildren();
            int var9 = 0;
            if (var4.containsKey(var7)) {
                var9 = (Integer)var4.get(var7);
            }

            int var10 = var2.getX();
            int var11 = ((List)var3.get(var7)).size();
            int var12;
            if (var9 == 0) {
                if (var11 > 1) {
                    var12 = var11 * 30 + var11 * 50;
                    var9 = var10 - var12 / 2 - 50;
                } else {
                    var9 = var10;
                }
            }

            var12 = 80 + var9;
            if (var11 == 1) {
                var12 = var9;
            }

            var6.setX(var12);
            var4.put(var7, var12);
            if (var8 != null) {
                this.a(var8, var2, var3, var4);
            }
        }

    }

    private int a(Map<Integer, List<NodeInfo>> var1) {
        int var2 = 1;
        Iterator var3 = var1.values().iterator();

        while(var3.hasNext()) {
            List var4 = (List)var3.next();
            if (var4.size() > var2) {
                var2 = var4.size();
            }
        }

        return var2;
    }

    private void a(List<NodeInfo> var1, Map<Integer, List<NodeInfo>> var2) {
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            NodeInfo var4 = (NodeInfo)var3.next();
            int var5 = var4.getLevel();
            List var6;
            if (var2.containsKey(var5)) {
                var6 = (List)var2.get(var5);
                var6.add(var4);
            } else {
                ArrayList var7 = new ArrayList();
                var7.add(var4);
                var2.put(var5, var7);
            }

            var6 = var4.getChildren();
            if (var6 != null) {
                this.a(var6, var2);
            }
        }

    }
}
