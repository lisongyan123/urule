package com.bstek.urule.model.function;

import com.bstek.urule.runtime.WorkingMemory;

public interface FunctionDescriptor {
    Argument getArgument();

    Object doFunction(Object var1, String var2, WorkingMemory var3);

    String getName();

    String getLabel();

    boolean isDisabled();
}