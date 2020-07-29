//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.dbstore.service;

import com.bstek.urule.Utils;
import com.bstek.urule.exception.RuleException;
import com.bstek.urule.runtime.KnowledgePackage;
import com.bstek.urule.runtime.KnowledgePackageImpl;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.io.IOUtils;

public abstract class DbService {
    private DataSource a;

    public DbService(DataSource var1) {
        this.a = var1;
    }

    public DataSource getDataSource() {
        return this.a;
    }

    public KnowledgePackage loadKnowledgePackage(String var1) {
        Connection var2 = null;

        KnowledgePackage var3;
        try {
            var2 = this.getConnection();
            var3 = this.queryKnowledgePackage(var1, var2);
        } catch (Exception var12) {
            throw new RuleException(var12);
        } finally {
            try {
                if (var2 != null) {
                    var2.close();
                }
            } catch (SQLException var11) {
            }

        }

        return var3;
    }

    public KnowledgePackage verifyKnowledgePackage(String var1, long var2) {
        String var4 = "SELECT count(*) FROM URULE_KP_STORE WHERE ID_=? AND UPDATE_DATE_=?";
        Connection var5 = null;
        ResultSet var6 = null;
        PreparedStatement var7 = null;

        KnowledgePackage var9;
        try {
            var5 = this.getConnection();
            var7 = var5.prepareStatement(var4);
            var7.setString(1, var1);
            var7.setLong(2, var2);
            var6 = var7.executeQuery();
            int var8 = 0;
            if (var6.next()) {
                var8 = var6.getInt(1);
            }

            if (var8 > 0) {
                var9 = null;
                return var9;
            }

            var9 = this.queryKnowledgePackage(var1, var5);
        } catch (Exception var19) {
            throw new RuleException(var19);
        } finally {
            try {
                if (var7 != null) {
                    var7.close();
                }

                if (var6 != null) {
                    var6.close();
                }

                if (var5 != null) {
                    var5.close();
                }
            } catch (SQLException var18) {
            }

        }

        return var9;
    }

    protected Connection getConnection() throws SQLException {
        return this.a.getConnection();
    }

    protected KnowledgePackage queryKnowledgePackage(String var1, Connection var2) throws SQLException, IOException {
        String var3 = "select DATA_,UPDATE_DATE_ from URULE_KP_STORE where ID_=?";
        ResultSet var4 = null;
        PreparedStatement var5 = null;
        InputStream var6 = null;

        KnowledgePackageImpl var13;
        try {
            var5 = var2.prepareStatement(var3);
            var5.setString(1, var1);
            var4 = var5.executeQuery();
            if (!var4.next()) {
                throw new RuleException("未在数据库中找到存储的知识包【" + var1 + "】");
            }

            Blob var7 = var4.getBlob(1);
            var6 = var7.getBinaryStream();
            String var8 = Utils.uncompress(IOUtils.toByteArray(var6));
            KnowledgePackage var9 = Utils.stringToKnowledgePackage(var8);
            KnowledgePackageImpl var10 = (KnowledgePackageImpl)var9;
            var10.setPackageInfo(var1);
            long var11 = var4.getLong(2);
            var10.setTimestamp(var11);
            var13 = var10;
        } finally {
            if (var5 != null) {
                var5.close();
            }

            if (var4 != null) {
                var4.close();
            }

            IOUtils.closeQuietly(var6);
        }

        return var13;
    }
}
