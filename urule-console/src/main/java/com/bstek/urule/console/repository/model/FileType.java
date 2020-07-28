package com.bstek.urule.console.repository.model;

import com.bstek.urule.exception.RuleException;

public enum FileType {
	Ruleset {
		public String toString() {
			return "rs.xml";
		}
	},
	DecisionTable {
		public String toString() {
			return "dt.xml";
		}
	},
	Crosstab {
		public String toString() {
			return "ct.xml";
		}
	},
	ScriptDecisionTable {
		public String toString() {
			return "dts.xml";
		}
	},
	ActionLibrary {
		public String toString() {
			return "al.xml";
		}
	},
	VariableLibrary {
		public String toString() {
			return "vl.xml";
		}
	},
	ParameterLibrary {
		public String toString() {
			return "pl.xml";
		}
	},
	ConstantLibrary {
		public String toString() {
			return "cl.xml";
		}
	},
	RuleFlow {
		public String toString() {
			return "rl.xml";
		}
	},
	UL {
		public String toString() {
			return ".ul";
		}
	},
	DecisionTree {
		public String toString() {
			return "dtree.xml";
		}
	},
	ComplexScorecard {
		public String toString() {
			return "scc";
		}
	},
	Scorecard {
		public String toString() {
			return "sc";
		}
	},
	ConditionTemplate {
		public String toString() {
			return "ctp";
		}
	},
	ActionTemplate {
		public String toString() {
			return "atp";
		}
	},
	DIR {
		public String toString() {
			return "DIR";
		}
	};

	private FileType() {
	}

	public static FileType parse(String var0) {
		if (var0.equals("rs.xml")) {
			return Ruleset;
		} else if (var0.equals("dt.xml")) {
			return DecisionTable;
		} else if (var0.equals("dts.xml")) {
			return ScriptDecisionTable;
		} else if (var0.equals("al.xml")) {
			return ActionLibrary;
		} else if (var0.equals("vl.xml")) {
			return VariableLibrary;
		} else if (var0.equals("pl.xml")) {
			return ParameterLibrary;
		} else if (var0.equals("cl.xml")) {
			return ConstantLibrary;
		} else if (var0.equals("rl.xml")) {
			return RuleFlow;
		} else if (var0.equals("ul")) {
			return UL;
		} else if (var0.equals("dtree.xml")) {
			return DecisionTree;
		} else if (var0.equals("sc")) {
			return Scorecard;
		} else if (var0.equals("scc")) {
			return ComplexScorecard;
		} else if (var0.equals("DIR")) {
			return DIR;
		} else if (var0.equals("ct.xml")) {
			return Crosstab;
		} else if (var0.equals("ctp")) {
			return ConditionTemplate;
		} else if (var0.equals("atp")) {
			return ActionTemplate;
		} else {
			throw new RuleException("Unknow type:" + var0);
		}
	}
}
