//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dbstore;

import com.bstek.urule.dbstore.service.DbService;
import javax.sql.DataSource;

public interface DatabaseStore {
    String TABLE_NAME = "URULE_KP_STORE";

    void init(DataSource var1) throws Exception;

    boolean support(String var1);

    DbService getDbService();
}
