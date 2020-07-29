//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.exception.RuleException;

public enum ArithmeticType {
    Add {
        public String toString() {
            return "+";
        }
    },
    Sub {
        public String toString() {
            return "-";
        }
    },
    Mul {
        public String toString() {
            return "*";
        }
    },
    Div {
        public String toString() {
            return "/";
        }
    },
    Mod {
        public String toString() {
            return "%";
        }
    },
    Gt {
        public String toString() {
            return ">";
        }
    },
    Lt {
        public String toString() {
            return "<";
        }
    },
    Gte {
        public String toString() {
            return "≥";
        }
    },
    Lte {
        public String toString() {
            return "≤";
        }
    },
    Eq {
        public String toString() {
            return "==";
        }
    },
    NotEq {
        public String toString() {
            return "≠";
        }
    };

    private ArithmeticType() {
    }

    public static ArithmeticType parse(String var0) {
        if (var0.equals("+")) {
            return Add;
        } else if (var0.equals("-")) {
            return Sub;
        } else if (var0.equals("*")) {
            return Mul;
        } else if (var0.equals("/")) {
            return Div;
        } else if (var0.equals("%")) {
            return Mod;
        } else if (var0.equals(">")) {
            return Gt;
        } else if (var0.equals("<")) {
            return Lt;
        } else if (var0.equals("≥")) {
            return Gte;
        } else if (var0.equals("≤")) {
            return Lte;
        } else if (var0.equals("==")) {
            return Eq;
        } else if (var0.equals("≠")) {
            return NotEq;
        } else {
            throw new RuleException("Unsupport arithmetic type [" + var0 + "]");
        }
    }
}
