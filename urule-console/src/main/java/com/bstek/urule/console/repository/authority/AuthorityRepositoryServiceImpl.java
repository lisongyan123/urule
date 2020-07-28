package com.bstek.urule.console.repository.authority;

import com.bstek.urule.Utils;
import com.bstek.urule.console.Principal;
import com.bstek.urule.console.repository.BaseRepositoryService;
import com.bstek.urule.console.repository.OperateType;
import com.bstek.urule.exception.RuleException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.Property;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class AuthorityRepositoryServiceImpl extends BaseRepositoryService implements AuthorityRepositoryService, ApplicationContextAware {
    private ApplicationContext a;
    private AuthorityService b;

    public AuthorityRepositoryServiceImpl() {
    }

    public void saveAuthority(Principal var1, String var2, Authority var3, OperateType var4) throws Exception {
        String var5 = var1.getCompanyId();
        String var6 = var3.getPath();
        List var7 = this.loadAuthorityUnits(var5);
        Object var8 = null;
        Iterator var9 = var7.iterator();

        while(var9.hasNext()) {
            AuthorityUnit var10 = (AuthorityUnit)var9.next();
            if (var2.equals(var10.getPrincipalName())) {
                var8 = var10.getAuthorities();
                break;
            }
        }

        if (var8 == null) {
            if (!var4.equals(OperateType.add)) {
                return;
            }

            AuthorityUnit var15 = new AuthorityUnit();
            var15.setPrincipalName(var2);
            var8 = new ArrayList();
            var15.setAuthorities((List)var8);
            var7.add(var15);
        }

        Authority var16 = null;
        Authority var11;
        Iterator var17;
        if (!var4.equals(OperateType.add)) {
            var17 = ((List)var8).iterator();

            while(var17.hasNext()) {
                var11 = (Authority)var17.next();
                String var12 = var11.getPath().toLowerCase();
                if (var12.equals(var6.toLowerCase())) {
                    var16 = var11;
                    break;
                }
            }

            if (var16 == null) {
                throw new RuleException("资源文件【" + var6 + "】不存在！");
            }
        }

        if (var4.equals(OperateType.delete)) {
            int var18 = ((List)var8).indexOf(var16);
            ((List)var8).remove(var18);
        } else if (var4.equals(OperateType.update)) {
            var16.setRead(var3.isRead());
            var16.setWrite(var3.isWrite());
            var16.setSave(var3.isSave());
            var16.setSaveas(var3.isSaveas());
        } else {
            var17 = ((List)var8).iterator();

            while(var17.hasNext()) {
                var11 = (Authority)var17.next();
                if (var11.getPath().equals(var6)) {
                    throw new RuleException("【" + var2 + "】对资源【" + var6 + "】的权限配置信息已存在！");
                }
            }

            Authority var19 = new Authority();
            var19.setPath(var6);
            var19.setRead(var3.isRead());
            var19.setWrite(var3.isWrite());
            var19.setSave(var3.isSave());
            var19.setSaveas(var3.isSaveas());
            ((List)var8).add(var19);
        }

        StringBuilder var20 = new StringBuilder();
        var20.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        var20.append("<authorities>");
        Iterator var21 = var7.iterator();

        while(var21.hasNext()) {
            AuthorityUnit var23 = (AuthorityUnit)var21.next();
            var20.append("<unit principal=\"" + var23.getPrincipalName() + "\">");
            Iterator var13 = var23.getAuthorities().iterator();

            while(var13.hasNext()) {
                Authority var14 = (Authority)var13.next();
                var20.append("<auth path=\"" + var14.getPath() + "\" read=\"" + var14.isRead() + "\" write=\"" + var14.isWrite() + "\" save=\"" + var14.isSave() + "\" saveas=\"" + var14.isSaveas() + "\"/>");
            }

            var20.append("</unit>");
        }

        var20.append("</authorities>");
        long var22 = this.saveAuthoritiesFile(var20.toString(), var1);
        if (this.b == null) {
            this.b = (AuthorityService)this.a.getBean("urule.authorityService");
        }

        this.b.refreshAuthority(var5);
        this.b.getAuthorityCache().resetTag(var22);
    }

    public long saveAuthoritiesFile(String var1, Principal var2) throws Exception {
        String var3 = var2.getCompanyId();
        String var4 = "___resource_authority_config__file__" + (var3 == null ? "" : var3);
        this.saveFile(var4, var1, false, (String)null, var2);
        return this.resetAuthorityTag(var3);
    }

    public List<AuthorityUnit> loadAuthorityUnits(String var1) throws Exception {
        ArrayList var2 = new ArrayList();
        String var3 = "___resource_authority_config__file__" + (var1 == null ? "" : var1);
        Node var4 = this.getRootNode();
        Node var5 = var4.getNode(var3);
        Property var6 = var5.getProperty("_data");
        Binary var7 = var6.getBinary();
        InputStream var8 = var7.getStream();
        String var9 = IOUtils.toString(var8, "utf-8");
        var8.close();
        Document var10 = DocumentHelper.parseText(var9);
        Element var11 = var10.getRootElement();
        Iterator var12 = var11.elements().iterator();

        while(var12.hasNext()) {
            Object var13 = var12.next();
            if (var13 instanceof Element) {
                Element var14 = (Element)var13;
                if (var14.getName().equals("unit")) {
                    AuthorityUnit var15 = new AuthorityUnit();
                    var15.setPrincipalName(var14.attributeValue("principal"));
                    var15.setAuthorities(this.a(var14));
                    var2.add(var15);
                }
            }
        }

        return var2;
    }

    private List<Authority> a(Element var1) {
        ArrayList var2 = new ArrayList();
        Iterator var3 = var1.elements().iterator();

        while(var3.hasNext()) {
            Object var4 = var3.next();
            if (var4 instanceof Element) {
                Element var5 = (Element)var4;
                if (var5.getName().equals("auth")) {
                    Authority var6 = new Authority();
                    var6.setPath(var5.attributeValue("path"));
                    String var7 = var5.attributeValue("read");
                    if (StringUtils.isNotBlank(var7)) {
                        var6.setRead(Boolean.valueOf(var7));
                    }

                    String var8 = var5.attributeValue("write");
                    if (StringUtils.isNotBlank(var8)) {
                        var6.setWrite(Boolean.valueOf(var8));
                    }

                    String var9 = var5.attributeValue("save");
                    if (StringUtils.isNotBlank(var9)) {
                        var6.setSave(Boolean.valueOf(var9));
                    }

                    String var10 = var5.attributeValue("saveas");
                    if (StringUtils.isNotBlank(var10)) {
                        var6.setSaveas(Boolean.valueOf(var10));
                    }

                    var2.add(var6);
                }
            }
        }

        return var2;
    }

    public long resetAuthorityTag(String var1) throws Exception {
        String var2 = "___resource_authority_config__file__" + (var1 == null ? "" : var1);
        var2 = Utils.decodeURL(var2);
        Node var3 = this.getRootNode();
        if (!var3.hasNode(var2)) {
            throw new RuleException("File [" + var2 + "] not exist.");
        } else {
            Node var4 = var3.getNode(var2);
            long var5 = (new Date()).getTime();
            var4.setProperty("___resource_authority_tag__", var5);
            this.session.save();
            return var5;
        }
    }

    public long check(String var1, long var2) throws Exception {
        String var4 = "___resource_authority_config__file__" + (var1 == null ? "" : var1);
        var4 = Utils.decodeURL(var4);
        Node var5 = this.getRootNode();
        if (!var5.hasNode(var4)) {
            return var2;
        } else {
            Node var6 = var5.getNode(var4);
            if (!var6.hasProperty("___resource_authority_tag__")) {
                return var2;
            } else {
                long var7 = var6.getProperty("___resource_authority_tag__").getLong();
                return var7 == var2 ? 0L : var7;
            }
        }
    }

    public void setApplicationContext(ApplicationContext var1) throws BeansException {
        super.setApplicationContext(var1);
        this.a = var1;
    }
}
