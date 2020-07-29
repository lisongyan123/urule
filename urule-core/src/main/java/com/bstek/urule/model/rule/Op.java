//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.rule;

import com.bstek.urule.exception.RuleException;

public enum Op {
    Equals {
        public String toString() {
            return "等于";
        }
    },
    EqualsIgnoreCase {
        public String toString() {
            return "等于(不分大小写)";
        }
    },
    NotEquals {
        public String toString() {
            return "不等于";
        }
    },
    NotEqualsIgnoreCase {
        public String toString() {
            return "不等于(不分大小写)";
        }
    },
    LessThen {
        public String toString() {
            return "小于";
        }
    },
    LessThenEquals {
        public String toString() {
            return "小于等于";
        }
    },
    GreaterThen {
        public String toString() {
            return "大于";
        }
    },
    GreaterThenEquals {
        public String toString() {
            return "大于等于";
        }
    },
    In {
        public String toString() {
            return "在集合中";
        }
    },
    NotIn {
        public String toString() {
            return "不在集合中";
        }
    },
    StartWith {
        public String toString() {
            return "开始于";
        }
    },
    NotStartWith {
        public String toString() {
            return "不开始于";
        }
    },
    EndWith {
        public String toString() {
            return "结束于";
        }
    },
    NotEndWith {
        public String toString() {
            return "不结束于";
        }
    },
    Null {
        public String toString() {
            return "为空";
        }
    },
    NotNull {
        public String toString() {
            return "不为空";
        }
    },
    Match {
        public String toString() {
            return "匹配";
        }
    },
    NotMatch {
        public String toString() {
            return "不匹配";
        }
    },
    Contain {
        public String toString() {
            return "包含";
        }
    },
    NotContain {
        public String toString() {
            return "不包含";
        }
    };

    private Op() {
    }

    public static Op parse(String var0) {
        if (var0.equals(">")) {
            return GreaterThen;
        } else if (var0.equals(">=")) {
            return GreaterThenEquals;
        } else if (var0.equals("==")) {
            return Equals;
        } else if (var0.equals("EqualsIgnoreCase")) {
            return EqualsIgnoreCase;
        } else if (var0.equals("!=")) {
            return NotEquals;
        } else if (var0.equals("NotEqualsIgnoreCase")) {
            return NotEqualsIgnoreCase;
        } else if (var0.equals("<")) {
            return LessThen;
        } else if (var0.equals("<=")) {
            return LessThenEquals;
        } else if (var0.equals("In")) {
            return In;
        } else if (var0.equals("NotIn")) {
            return NotIn;
        } else if (var0.equals("StartWith")) {
            return StartWith;
        } else if (var0.equals("NotStartWidth")) {
            return NotStartWith;
        } else if (var0.equals("EndWith")) {
            return EndWith;
        } else if (var0.equals("NotEndWith")) {
            return NotEndWith;
        } else if (var0.equals("Null")) {
            return Null;
        } else if (var0.equals("Notnull")) {
            return NotNull;
        } else if (var0.equals("Match")) {
            return Match;
        } else if (var0.equals("NotMatch")) {
            return NotMatch;
        } else if (var0.equals("Contain")) {
            return Contain;
        } else if (var0.equals("NotContain")) {
            return NotContain;
        } else {
            throw new RuleException("Unsupport op " + var0 + "");
        }
    }
}
