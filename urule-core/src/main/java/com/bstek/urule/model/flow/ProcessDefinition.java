//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.flow;

import com.bstek.urule.model.rule.Library;
import java.util.List;

public interface ProcessDefinition {
    List<Library> getLibraries();

    String getId();

    boolean isDebug();

    List<FlowNode> getNodes();

    String getFile();
}
