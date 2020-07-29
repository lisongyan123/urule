//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dbstore;

import com.bstek.urule.dbstore.service.DbService;
import com.bstek.urule.dbstore.service.OracleDbService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

public class OracleDatabaseStore implements DatabaseStore {
    private static final String a = "CREATE TABLE URULE_KP_STORE(ID_ VARCHAR2(60) PRIMARY KEY,UPDATE_DATE_ NUMBER NOT NULL,CREATE_USER_ VARCHAR2(60),DATA_ BLOB)";
    private DataSource b;

    public OracleDatabaseStore() {
    }

    public void init(DataSource var1) throws Exception {
        this.b = var1;
        Connection var2 = var1.getConnection();
        String var3 = "SELECT COUNT(*) FROM User_Tables WHERE table_name = 'URULE_KP_STORE'";
        Statement var4 = var2.createStatement();
        ResultSet var5 = var4.executeQuery(var3);
        int var6 = 0;
        if (var5.next()) {
            var6 = var5.getInt(1);
        }

        var5.close();
        if (var6 == 0) {
            var4.execute("CREATE TABLE URULE_KP_STORE(ID_ VARCHAR2(60) PRIMARY KEY,UPDATE_DATE_ NUMBER NOT NULL,CREATE_USER_ VARCHAR2(60),DATA_ BLOB)");
        }

        var4.close();
        var2.close();
    }

    public DbService getDbService() {
        return new OracleDbService(this.b);
    }

    public boolean support(String var1) {
        return var1.toLowerCase().indexOf("oracle") > -1;
    }
}
