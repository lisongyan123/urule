package com.bstek.urule.console.repository.refactor.file;

import com.bstek.urule.console.repository.refactor.Refactor;

public abstract class FileRefactor implements Refactor {
    public FileRefactor() {
    }

    public abstract String doRefactor(String var1, String var2, String var3);

    protected String perfectPath(String var1) {
        if (!var1.startsWith("/")) {
            var1 = "/" + var1;
        }

        return var1;
    }
}
