package com.bstek.urule.console.repository.reference;

import java.util.List;

public interface ReferenceService {
    String BEAN_ID = "urule.referenceService";

    List<RefFile> loadReferenceFiles(String var1, SearchItem var2) throws Exception;

    List<RefFile> loadReferenceFiles(String var1) throws Exception;
}
