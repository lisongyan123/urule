//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rete;

public enum NodeType {
    and,
    or,
    criteria,
    objectType,
    terminal;

    private NodeType() {
    }

    public static ReteNode newReteNodeInstance(NodeType var0) {
        switch(var0) {
            case and:
                return new AndNode();
            case or:
                return new OrNode();
            case criteria:
                return new CriteriaNode();
            case objectType:
                return new ObjectTypeNode();
            case terminal:
                return new TerminalNode();
            default:
                return null;
        }
    }
}
