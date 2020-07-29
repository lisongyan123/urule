//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.runtime.rete;

import java.util.List;
import java.util.Set;

public interface Activity extends Instance {
    List<Path> getPaths();

    boolean orNodeTokensExist(Set<Integer> var1);
}
