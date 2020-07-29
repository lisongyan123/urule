//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dbstore;

import com.bstek.urule.dbstore.service.DbService;
import com.bstek.urule.dbstore.service.MssqlDbService;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;

public class MssqlDatabaseStore implements DatabaseStore {
    private static final String a = "CREATE TABLE URULE_KP_STORE(ID_ varchar(60) primary key,UPDATE_DATE_ bigint not null,CREATE_USER_ varchar(60),DATA_ varbinary(max))";
    private DataSource b;

    public MssqlDatabaseStore() {
    }

    public void init(DataSource var1) throws Exception {
        this.b = var1;
        Connection var2 = var1.getConnection();
        String var3 = "SELECT count(*) FROM sysobjects WHERE name='URULE_KP_STORE'";
        Statement var4 = var1.getConnection().createStatement();
        ResultSet var5 = var4.executeQuery(var3);
        int var6 = 0;
        if (var5.next()) {
            var6 = var5.getInt(1);
        }

        var5.close();
        if (var6 == 0) {
            var4.execute("CREATE TABLE URULE_KP_STORE(ID_ varchar(60) primary key,UPDATE_DATE_ bigint not null,CREATE_USER_ varchar(60),DATA_ varbinary(max))");
        }

        var4.close();
        var2.close();
    }

    public DbService getDbService() {
        return new MssqlDbService(this.b);
    }

    public boolean support(String var1) {
        return var1.toLowerCase().indexOf("sql server") > -1;
    }
}
