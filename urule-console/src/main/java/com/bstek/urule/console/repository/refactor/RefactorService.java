package com.bstek.urule.console.repository.refactor;

import com.bstek.urule.console.repository.Repository;

public interface RefactorService {
    void refactorFile(String var1, String var2) throws Exception;

    void refactorFile(String var1, String var2, Repository var3) throws Exception;

    void refactorItem(String var1, Item var2) throws Exception;
}