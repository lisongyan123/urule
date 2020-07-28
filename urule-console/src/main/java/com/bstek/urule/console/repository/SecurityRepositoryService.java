package com.bstek.urule.console.repository;

import com.bstek.urule.console.Principal;
import java.util.List;

public interface SecurityRepositoryService {
    String BEAN_ID = "urule.securityRepositoryService";
    String ADMIN_USER_DATA = "__admin_user_data_";
    String USER_DATA = "__user_data_";
    String ADMIN_PASSWORD = "admin_password";
    String DEFAULT_LOGIN_USER = "default_urule_security_login_user";

    String adminLogin(String var1);

    String login(String var1, String var2);

    String addUser(String var1, String var2);

    String deleteUser(String var1);

    boolean userExist(String var1);

    String changePassword(String var1, String var2, String var3);

    String resetPassword(String var1, String var2);

    String changeAdminPassword(String var1, String var2);

    List<Principal> loadUsers();
}