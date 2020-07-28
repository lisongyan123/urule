//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet;

import com.bstek.urule.Utils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.exception.NoPermissionException;
import com.bstek.urule.console.repository.Repository;
import com.bstek.urule.console.repository.authority.Authority;
import com.bstek.urule.console.repository.authority.AuthorityService;
import com.bstek.urule.console.repository.model.RepositoryFile;
import com.bstek.urule.exception.RuleException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.log.NullLogChute;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class RenderPageServletHandler extends WriteJsonServletHandler implements ApplicationContextAware {
    protected VelocityEngine ve;
    protected ApplicationContext applicationContext;
    protected AuthorityService authorityService;
    private Logger a = Logger.getGlobal();

    public RenderPageServletHandler() {
    }

    protected boolean validateUserPwd(HttpServletRequest var1, String var2, String var3) {
        String var4 = var1.getParameter("_u");
        String var5 = var1.getParameter("_p");
        if (var4 != null && var5 != null) {
            try {
                var4 = URLDecoder.decode(var4, "utf-8");
                var5 = URLDecoder.decode(var5, "utf-8");
                if (var4.equals(var2) && var5.equals(var3)) {
                    return true;
                } else {
                    this.a.warning("User or password is invalid.");
                    return false;
                }
            } catch (UnsupportedEncodingException var7) {
                throw new RuleException(var7);
            }
        } else {
            this.a.warning("User and password can not be null.");
            return false;
        }
    }

    protected String buildErrorMsg(Exception var1) {
        Throwable var2 = this.a(var1);
        if (var2 instanceof NullPointerException) {
            return "空指针错误！";
        } else {
            String var3 = var2.getMessage();
            var3 = var3 == null ? "服务端错误!" : var3;
            return var3;
        }
    }

    private Throwable a(Throwable var1) {
        return var1.getCause() != null ? this.a(var1.getCause()) : var1;
    }

    protected String buildProjectNameFromFile(String var1) {
        String var2 = null;
        if (StringUtils.isNotBlank(var1)) {
            var1 = Utils.decodeURL(var1);
            if (var1.startsWith("/")) {
                var1 = var1.substring(1, var1.length());
                int var3 = var1.indexOf("/");
                var2 = var1.substring(0, var3);
            }
        }

        return var2;
    }

    protected Repository buildRepositoryAuthority(Principal var1, Repository var2) {
        Repository var3 = new Repository();
        ArrayList var4 = new ArrayList();
        var3.setProjectNames(var4);
        Iterator var5 = var2.getProjectNames().iterator();

        while(true) {
            String var6;
            Authority var8;
            do {
                if (!var5.hasNext()) {
                    RepositoryFile var9 = var2.getRootFile();
                    RepositoryFile var10 = this.a(var1, var9, var9);
                    var3.setRootFile(var10);
                    return var3;
                }

                var6 = (String)var5.next();
                String var7 = "/" + var6;
                var8 = this.authorityService.getAuthority(var1, var7);
            } while(var8 != null && !var8.isRead());

            var4.add(var6);
        }
    }

    private RepositoryFile a(Principal var1, RepositoryFile var2, RepositoryFile var3) {
        String var4 = var2.getFullPath();
        Authority var5 = this.authorityService.getAuthority(var1, var4);
        if (var5 != null && !var5.isRead()) {
            return null;
        } else {
            RepositoryFile var6 = new RepositoryFile();
            var6.setFolderType(var2.getFolderType());
            var6.setFullPath(var4);
            var6.setId(var2.getId());
            var6.setLibType(var2.getLibType());
            var6.setLock(var2.isLock());
            var6.setLockInfo(var2.getLockInfo());
            var6.setName(var2.getName());
            var6.setParentFile(var3);
            var6.setType(var2.getType());
            List var7 = var2.getChildren();
            if (var7 != null) {
                ArrayList var8 = new ArrayList();
                var6.setChildren(var8);
                Iterator var9 = var7.iterator();

                while(var9.hasNext()) {
                    RepositoryFile var10 = (RepositoryFile)var9.next();
                    RepositoryFile var11 = this.a(var1, var10, var6);
                    if (var11 != null) {
                        var8.add(var11);
                    }
                }
            }

            return var6;
        }
    }

    protected void checkFullPermission(Principal var1, String var2) {
        Authority var3 = this.authorityService.getAuthority(var1, var2);
        if (var3 != null && !var3.hasFullPermission()) {
            throw new NoPermissionException();
        }
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        this.applicationContext = var1;
        this.authorityService = (AuthorityService)var1.getBeansOfType(AuthorityService.class).values().iterator().next();
        this.ve = new VelocityEngine();
        this.ve.setProperty("resource.loader", "class");
        this.ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        this.ve.setProperty("runtime.log.logsystem", new NullLogChute());
        this.ve.init();
    }
}
