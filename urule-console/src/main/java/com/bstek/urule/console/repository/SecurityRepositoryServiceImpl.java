package com.bstek.urule.console.repository;

import com.bstek.urule.console.DefaultPrincipal;
import com.bstek.urule.console.Principal;
import com.bstek.urule.exception.RuleException;
import java.util.ArrayList;
import java.util.List;
import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;

public class SecurityRepositoryServiceImpl extends BaseRepositoryService implements SecurityRepositoryService {
    private String a;

    public SecurityRepositoryServiceImpl() {
    }

    public String adminLogin(String var1) {
        String var2 = "__admin_user_data_" + this.a;

        try {
            Node var3 = this.getRootNode();
            if (var3.hasNode(var2)) {
                Node var4 = var3.getNode(var2);
                Property var5 = var4.getProperty("admin_password");
                String var6 = var5.getString();
                if (!var6.equals(var1)) {
                    return "管理员密码不正确";
                }
            } else {
                String var8 = "admin";
                Node var9 = var3.addNode(var2);
                var9.setProperty("admin_password", var8);
                this.session.save();
                if (!var1.equals(var8)) {
                    return "管理员密码不正确";
                }
            }

            return null;
        } catch (Exception var7) {
            throw new RuleException(var7);
        }
    }

    public String changeAdminPassword(String var1, String var2) {
        String var3 = "__admin_user_data_" + this.a;

        try {
            Node var4 = this.getRootNode();
            if (var4.hasNode(var3)) {
                Node var5 = var4.getNode(var3);
                Property var6 = var5.getProperty("admin_password");
                String var7 = var6.getString();
                if (!var7.equals(var1)) {
                    return "原管理员密码不正确";
                } else {
                    var6.setValue(var2);
                    this.session.save();
                    return null;
                }
            } else {
                return "管理员帐号信息不存在！";
            }
        } catch (Exception var8) {
            throw new RuleException(var8);
        }
    }

    public boolean userExist(String var1) {
        try {
            boolean var2 = false;
            Node var3 = this.a();
            PropertyIterator var4 = var3.getProperties();

            while(var4.hasNext()) {
                Property var5 = var4.nextProperty();
                String var6 = var5.getName();
                if (var6.equals(var1)) {
                    var2 = true;
                    break;
                }
            }

            return var2;
        } catch (Exception var7) {
            throw new RuleException(var7);
        }
    }

    public String login(String var1, String var2) {
        boolean var3 = false;

        try {
            Node var4 = this.a();
            PropertyIterator var5 = var4.getProperties();

            while(var5.hasNext()) {
                Property var6 = var5.nextProperty();
                String var7 = var6.getName();
                String var8 = var6.getString();
                if (var7.equals(var1) && var8.equals(var2)) {
                    var3 = true;
                    break;
                }
            }
        } catch (Exception var9) {
            throw new RuleException(var9);
        }

        return var3 ? null : "用户名或密码有误！";
    }

    public String addUser(String var1, String var2) {
        if (this.userExist(var1)) {
            return "用户名【" + var1 + "】已存在！";
        } else {
            try {
                Node var3 = this.a();
                var3.setProperty(var1, var2);
                this.session.save();
                return null;
            } catch (Exception var4) {
                throw new RuleException(var4);
            }
        }
    }

    public String deleteUser(String var1) {
        if (!this.userExist(var1)) {
            return "用户【" + var1 + "】不存在！";
        } else {
            try {
                Node var2 = this.a();
                PropertyIterator var3 = var2.getProperties();

                while(var3.hasNext()) {
                    Property var4 = var3.nextProperty();
                    String var5 = var4.getName();
                    if (var5.equals(var1)) {
                        var4.remove();
                        break;
                    }
                }

                this.session.save();
                return null;
            } catch (Exception var6) {
                throw new RuleException(var6);
            }
        }
    }

    public String resetPassword(String var1, String var2) {
        boolean var3 = false;

        try {
            Node var4 = this.a();
            PropertyIterator var5 = var4.getProperties();

            while(var5.hasNext()) {
                Property var6 = var5.nextProperty();
                String var7 = var6.getName();
                if (var7.equals(var1)) {
                    var6.setValue(var2);
                    var3 = true;
                    break;
                }
            }

            if (var3) {
                this.session.save();
                return null;
            } else {
                return "用户名有误！";
            }
        } catch (Exception var8) {
            throw new RuleException(var8);
        }
    }

    public String changePassword(String var1, String var2, String var3) {
        boolean var4 = false;

        try {
            Node var5 = this.a();
            PropertyIterator var6 = var5.getProperties();

            while(var6.hasNext()) {
                Property var7 = var6.nextProperty();
                String var8 = var7.getName();
                String var9 = var7.getString();
                if (var8.equals(var1) && var9.equals(var2)) {
                    var7.setValue(var3);
                    var4 = true;
                    break;
                }
            }

            if (var4) {
                this.session.save();
                return null;
            } else {
                return "原密码有误！";
            }
        } catch (Exception var10) {
            throw new RuleException(var10);
        }
    }

    public List<Principal> loadUsers() {
        ArrayList var1 = new ArrayList();

        try {
            Node var2 = this.a();
            PropertyIterator var3 = var2.getProperties();

            while(var3.hasNext()) {
                Property var4 = var3.nextProperty();
                String var5 = var4.getName();
                if (!var5.startsWith("jcr:")) {
                    DefaultPrincipal var6 = new DefaultPrincipal();
                    var6.setCompanyId(this.a);
                    var6.setName(var5);
                    var6.setDisplayName(var5);
                    var1.add(var6);
                }
            }

            return var1;
        } catch (Exception var7) {
            throw new RuleException(var7);
        }
    }

    private Node a() {
        String var1 = "__user_data_" + this.a;

        try {
            Node var2 = null;
            Node var3 = this.getRootNode();
            if (!var3.hasNode(var1)) {
                var2 = var3.addNode(var1);
                this.session.save();
            } else {
                var2 = var3.getNode(var1);
            }

            return var2;
        } catch (Exception var4) {
            throw new RuleException(var4);
        }
    }

    public void setCompanyId(String var1) {
        this.a = var1;
    }
}
