//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dbstore;

import com.bstek.urule.dbstore.service.DbService;
import com.bstek.urule.dbstore.service.MysqlDbService;
import java.sql.Connection;
import java.sql.Statement;
import javax.sql.DataSource;

public class MysqlDatabaseStore implements DatabaseStore {
    private static String a = "CREATE TABLE IF NOT EXISTS URULE_KP_STORE(ID_ VARCHAR(60) PRIMARY KEY,UPDATE_DATE_ BIGINT NOT NULL,CREATE_USER_ VARCHAR(60),DATA_ LONGBLOB)";
    private DataSource b;

    public MysqlDatabaseStore() {
    }

    public void init(DataSource var1) throws Exception {
        this.b = var1;
        Connection var2 = var1.getConnection();
        Statement var3 = var2.createStatement();
        var3.execute(a);
        var3.close();
        var2.close();
    }

    public DbService getDbService() {
        return new MysqlDbService(this.b);
    }

    public boolean support(String var1) {
        return var1.toLowerCase().indexOf("mysql") > -1;
    }
}
