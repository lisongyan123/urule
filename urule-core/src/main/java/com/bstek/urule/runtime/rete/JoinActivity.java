//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class JoinActivity extends AbstractActivity {
    protected boolean passed;
    private List<Path> a = new ArrayList();

    public JoinActivity() {
    }

    protected boolean allPassed() {
        Iterator var1 = this.a.iterator();

        Path var2;
        do {
            if (!var1.hasNext()) {
                return true;
            }

            var2 = (Path)var1.next();
        } while(var2.isPassed());

        return false;
    }

    public void addFromPath(Path var1) {
        this.a.add(var1);
    }
}
