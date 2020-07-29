//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dsl;

import com.bstek.urule.dsl.RuleParserParser.OpContext;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.model.rule.Op;

public class DSLUtils {
    public DSLUtils() {
    }

    public static Op parseOp(OpContext var0) {
        if (var0 == null) {
            return Op.Equals;
        } else if (var0.GreaterThen() != null) {
            return Op.GreaterThen;
        } else if (var0.GreaterThenOrEquals() != null) {
            return Op.GreaterThenEquals;
        } else if (var0.LessThen() != null) {
            return Op.LessThen;
        } else if (var0.LessThenOrEquals() != null) {
            return Op.LessThenEquals;
        } else if (var0.Equals() != null) {
            return Op.Equals;
        } else if (var0.NotEquals() != null) {
            return Op.NotEquals;
        } else if (var0.EndWith() != null) {
            return Op.EndWith;
        } else if (var0.NotEndWith() != null) {
            return Op.NotEndWith;
        } else if (var0.StartWith() != null) {
            return Op.StartWith;
        } else if (var0.NotStartWith() != null) {
            return Op.NotStartWith;
        } else if (var0.In() != null) {
            return Op.In;
        } else if (var0.NotIn() != null) {
            return Op.NotIn;
        } else if (var0.Match() != null) {
            return Op.Match;
        } else if (var0.NotMatch() != null) {
            return Op.NotMatch;
        } else if (var0.EqualsIgnoreCase() != null) {
            return Op.EqualsIgnoreCase;
        } else if (var0.NotEqualsIgnoreCase() != null) {
            return Op.NotEqualsIgnoreCase;
        } else if (var0.Contain() != null) {
            return Op.Contain;
        } else if (var0.NotContain() != null) {
            return Op.NotContain;
        } else {
            throw new RuleException("Operator [" + var0 + "] is invalid.");
        }
    }
}
