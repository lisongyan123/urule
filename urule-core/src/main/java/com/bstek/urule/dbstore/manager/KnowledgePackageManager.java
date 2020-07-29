//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dbstore.manager;

import com.bstek.urule.PropertyConfigurer;
import com.bstek.urule.dbstore.DatabaseStore;
import com.bstek.urule.dbstore.MssqlDatabaseStore;
import com.bstek.urule.dbstore.MysqlDatabaseStore;
import com.bstek.urule.dbstore.OracleDatabaseStore;
import com.bstek.urule.dbstore.service.DatabaseKnowledgePackageFileService;
import com.bstek.urule.dbstore.service.DbService;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.service.KnowledgeServiceImpl;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class KnowledgePackageManager implements ApplicationContextAware {
    public static final String BEAN_ID = "urule.dbstore.knowledgePackageManager";
    private static final String a = "urule.knowledgePackageDatabaseStore.dataSource";
    private DataSource b;
    private static List<DatabaseStore> c = new ArrayList();

    public KnowledgePackageManager() {
    }

    public int removeKnowledgePackage(String var1) throws Exception {
        Connection var2 = this.a();
        Statement var3 = null;
        String var4 = "DELETE FROM URULE_KP_STORE where ID_=?";

        int var5;
        try {
            var3 = var2.createStatement();
            var5 = var3.executeUpdate(var4);
        } finally {
            if (var3 != null) {
                var3.close();
            }

            var2.close();
        }

        return var5;
    }

    public boolean saveKnowledgePackage(InputStream var1, String var2, String var3) throws Exception {
        String var4 = "SELECT count(*) FROM URULE_KP_STORE WHERE ID_=?";
        Connection var5 = this.a();
        PreparedStatement var6 = null;

        boolean var9;
        try {
            var6 = var5.prepareStatement(var4);
            var6.setString(1, var2);
            ResultSet var7 = var6.executeQuery();
            int var8 = 0;
            if (var7.next()) {
                var8 = var7.getInt(1);
            }

            var7.close();
            var6.close();
            if (var8 > 0) {
                var4 = "UPDATE URULE_KP_STORE SET UPDATE_DATE_=?,CREATE_USER_=?,DATA_=? WHERE ID_=?";
                var6 = var5.prepareStatement(var4);
                var6.setLong(1, (new Date()).getTime());
                var6.setString(2, var3);
                var6.setBlob(3, var1);
                var6.setString(4, var2);
                var6.executeUpdate();
                var6.close();
                var9 = false;
                return var9;
            }

            var4 = "INSERT INTO URULE_KP_STORE(ID_,UPDATE_DATE_,CREATE_USER_,DATA_) VALUES(?,?,?,?)";
            var6 = var5.prepareStatement(var4);
            var6.setString(1, var2);
            var6.setLong(2, (new Date()).getTime());
            var6.setString(3, var3);
            var6.setBlob(4, var1);
            var6.executeUpdate();
            var6.close();
            var9 = true;
        } finally {
            if (!var6.isClosed()) {
                var6.close();
            }

            var5.close();
        }

        return var9;
    }

    private Connection a() throws SQLException {
        return this.b.getConnection();
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        String var2 = PropertyConfigurer.getProperty("urule.knowledgePackageDatabaseStore.dataSource");
        if (!StringUtils.isBlank(var2) && var1.containsBean(var2)) {
            this.b = (DataSource)var1.getBean(var2);
            String var3 = null;
            DbService var4 = null;
            Connection var5 = null;

            try {
                var5 = this.b.getConnection();
                DatabaseMetaData var6 = var5.getMetaData();
                var3 = var6.getDatabaseProductName();
                Iterator var7 = c.iterator();

                while(var7.hasNext()) {
                    DatabaseStore var8 = (DatabaseStore)var7.next();
                    if (var8.support(var3)) {
                        System.out.println(">>>初始化" + var3 + "数据库中知识包存储表,目标数据库中如不存在知识包存储表将会自动创建...");
                        var8.init(this.b);
                        var4 = var8.getDbService();
                        break;
                    }
                }
            } catch (Exception var16) {
                throw new RuleException(var16);
            } finally {
                try {
                    if (var5 != null) {
                        var5.close();
                    }
                } catch (SQLException var15) {
                    var15.printStackTrace();
                }

            }

            if (var4 != null) {
                DatabaseKnowledgePackageFileService var18 = new DatabaseKnowledgePackageFileService(var4);
                KnowledgeServiceImpl var19 = (KnowledgeServiceImpl)var1.getBean("urule.knowledgeService");
                var19.setKnowledgePackageFileService(var18);
            } else {
                System.out.println(">>>知识包数据存储不支持当前数据类型【" + var3 + "】...");
            }

        }
    }

    static {
        c.add(new MysqlDatabaseStore());
        c.add(new OracleDatabaseStore());
        c.add(new MssqlDatabaseStore());
    }
}
