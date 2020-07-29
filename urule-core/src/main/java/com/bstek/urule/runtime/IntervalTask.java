//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime;

import java.util.TimerTask;

public class IntervalTask extends TimerTask {
    private DynamicSpringConfigLoader a;
    private RemoteDynamicJarsBuilder b;

    public IntervalTask(DynamicSpringConfigLoader var1, RemoteDynamicJarsBuilder var2) {
        this.a = var1;
        this.b = var2;
    }

    public void run() {
        String var1 = this.a.buildDynamicJarsStoreDirectPath();

        try {
            boolean var2 = this.b.requestRemoteJars(var1);
            if (!var2) {
                return;
            }

            this.a.loadDynamicJars(var1);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }
}
