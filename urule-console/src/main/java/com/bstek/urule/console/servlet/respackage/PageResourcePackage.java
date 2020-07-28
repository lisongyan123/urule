//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.console.servlet.respackage;

import com.bstek.urule.console.repository.model.ResourcePackage;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;

public class PageResourcePackage {
    private int a = 1;
    private int b;
    private int c;
    private int d = 10;
    private List<ResourcePackage> e = new ArrayList();

    public PageResourcePackage(HttpServletRequest var1, List<ResourcePackage> var2) {
        String var3 = var1.getParameter("pageIndex");
        if (StringUtils.isNotBlank(var3)) {
            this.a = Integer.parseInt(var3);
            if (this.a < 1) {
                this.a = 1;
            }
        }

        this.c = var2.size();
        String var4 = var1.getParameter("pageSize");
        if (StringUtils.isNotBlank(var4)) {
            this.d = Integer.parseInt(var4);
            if (this.d < 10) {
                this.d = 10;
            }
        }

        this.a(var2);
    }

    private void a(List<ResourcePackage> var1) {
        this.b = this.c / this.d;
        if (this.c % this.d > 0) {
            ++this.b;
        }

        if (this.a > this.b) {
            this.a = this.b;
        }

        int var2 = (this.a - 1) * this.d;
        if (var2 < 0) {
            var2 = 0;
        }

        int var3 = this.a * this.d;

        for(int var4 = var2; var4 < this.c && var4 < var3; ++var4) {
            ResourcePackage var5 = (ResourcePackage)var1.get(var4);
            this.e.add(var5);
        }

    }

    public int getPageIndex() {
        return this.a;
    }

    public void setPageIndex(int var1) {
        this.a = var1;
    }

    public int getTotalPage() {
        return this.b;
    }

    public void setTotalPage(int var1) {
        this.b = var1;
    }

    public int getTotal() {
        return this.c;
    }

    public void setTotal(int var1) {
        this.c = var1;
    }

    public int getPageSize() {
        return this.d;
    }

    public void setPageSize(int var1) {
        this.d = var1;
    }

    public List<ResourcePackage> getResourcePackages() {
        return this.e;
    }

    public void setResourcePackages(List<ResourcePackage> var1) {
        this.e = var1;
    }
}
