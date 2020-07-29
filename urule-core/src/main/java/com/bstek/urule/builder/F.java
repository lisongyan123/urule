//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.builder;

import com.bstek.urule.builder.resource.Resource;
import com.bstek.urule.builder.resource.ResourceBuilder;
import com.bstek.urule.builder.resource.ResourceType;
import com.bstek.urule.model.library.ResourceLibrary;
import com.bstek.urule.model.library.action.ActionLibrary;
import com.bstek.urule.model.library.constant.ConstantLibrary;
import com.bstek.urule.model.library.variable.Variable;
import com.bstek.urule.model.library.variable.VariableCategory;
import com.bstek.urule.model.library.variable.VariableLibrary;
import com.bstek.urule.model.rule.Library;
import com.bstek.urule.model.rule.LibraryType;
import com.bstek.urule.model.template.ActionTemplate;
import com.bstek.urule.model.template.ConditionTemplate;
import com.bstek.urule.runtime.BuiltInActionLibraryBuilder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Element;

public class ResourceLibraryBuilder extends AbstractBuilder {
    private BuiltInActionLibraryBuilder a;

    public ResourceLibraryBuilder() {
    }

    public ResourceLibrary buildResourceLibrary(Collection<Library> var1) {
        if (var1 == null) {
            var1 = Collections.EMPTY_LIST;
        }

        this.a((Collection)var1);
        ArrayList var2 = new ArrayList();
        ArrayList var3 = new ArrayList();
        ArrayList var4 = new ArrayList();
        ArrayList var5 = new ArrayList();
        ArrayList var6 = new ArrayList();
        ArrayList var7 = new ArrayList();
        ResourceBase var8 = this.newResourceBase();
        Iterator var9 = ((Collection)var1).iterator();

        while(var9.hasNext()) {
            Library var10 = (Library)var9.next();
            var8.addResource(var10.getPath(), var10.getVersion());
        }

        var9 = var8.getResources().iterator();

        while(true) {
            while(var9.hasNext()) {
                Resource var21 = (Resource)var9.next();
                String var11 = var21.getContent();
                Element var12 = this.parseResource(var11);
                Iterator var13 = this.resourceBuilders.iterator();

                while(var13.hasNext()) {
                    ResourceBuilder var14 = (ResourceBuilder)var13.next();
                    if (var14.support(var12)) {
                        String var15 = var21.getPath();
                        if (var21.getVersion() != null) {
                            var15 = var15 + ":" + var21.getVersion();
                        }

                        Object var16 = var14.build(var12, var15);
                        ResourceType var17 = var14.getType();
                        if (var17.equals(ResourceType.ActionLibrary)) {
                            ActionLibrary var18 = (ActionLibrary)var16;
                            var5.add(var18);
                        } else if (var17.equals(ResourceType.VariableLibrary)) {
                            VariableLibrary var28 = (VariableLibrary)var16;
                            var6.add(var28);
                        } else if (var17.equals(ResourceType.ConstantLibrary)) {
                            ConstantLibrary var29 = (ConstantLibrary)var16;
                            var4.add(var29);
                        } else if (var17.equals(ResourceType.ParameterLibrary)) {
                            VariableCategory var30 = (VariableCategory)var16;
                            var7.add(var30);
                        } else if (var17.equals(ResourceType.ConditionTemplate)) {
                            ConditionTemplate var31 = (ConditionTemplate)var16;
                            var2.add(var31);
                        } else if (var17.equals(ResourceType.ActionTemplate)) {
                            ActionTemplate var32 = (ActionTemplate)var16;
                            var3.add(var32);
                        }
                        break;
                    }
                }
            }

            if (var7.size() > 0) {
                VariableCategory var19 = (VariableCategory)var7.get(0);
                Iterator var22 = var7.iterator();

                label73:
                while(true) {
                    VariableCategory var25;
                    do {
                        do {
                            if (!var22.hasNext()) {
                                VariableLibrary var23 = new VariableLibrary();
                                var23.addVariableCategory(var19);
                                var6.add(var23);
                                break label73;
                            }

                            var25 = (VariableCategory)var22.next();
                        } while(var25.equals(var19));
                    } while(var25.getVariables() == null);

                    Iterator var26 = var25.getVariables().iterator();

                    while(var26.hasNext()) {
                        Variable var27 = (Variable)var26.next();
                        var19.addVariable(var27);
                    }
                }
            }

            List var20 = this.a.getBuiltInActions();
            if (var20.size() > 0) {
                ActionLibrary var24 = new ActionLibrary();
                var24.setSpringBeans(var20);
                var5.add(var24);
            }

            return new ResourceLibrary(var6, var5, var4, var2, var3);
        }
    }

    private void a(Collection<Library> var1) {
        ResourceBase var2 = this.newResourceBase();
        Iterator var3 = var1.iterator();

        while(var3.hasNext()) {
            Library var4 = (Library)var3.next();
            if (var4.getType().equals(LibraryType.ConditionTemplate)) {
                var2.addResource(var4.getPath(), var4.getVersion());
            }
        }

        ArrayList var13 = new ArrayList();
        Iterator var14 = var2.getResources().iterator();

        while(true) {
            while(var14.hasNext()) {
                Resource var5 = (Resource)var14.next();
                String var6 = var5.getContent();
                Element var7 = this.parseResource(var6);
                Iterator var8 = this.resourceBuilders.iterator();

                while(var8.hasNext()) {
                    ResourceBuilder var9 = (ResourceBuilder)var8.next();
                    if (var9.support(var7)) {
                        Object var10 = var9.build(var7, var5.getPath());
                        ResourceType var11 = var9.getType();
                        if (var11.equals(ResourceType.ConditionTemplate)) {
                            ConditionTemplate var12 = (ConditionTemplate)var10;
                            var13.addAll(var12.getLibraries());
                        }
                        break;
                    }
                }
            }

            var14 = var13.iterator();

            while(var14.hasNext()) {
                Library var15 = (Library)var14.next();
                boolean var16 = false;
                Iterator var17 = var1.iterator();

                while(var17.hasNext()) {
                    Library var18 = (Library)var17.next();
                    if (var15.getPath().equals(var18.getPath())) {
                        var16 = true;
                        break;
                    }
                }

                if (!var16) {
                    var1.add(var15);
                }
            }

            return;
        }
    }

    public void setBuiltInActionLibraryBuilder(BuiltInActionLibraryBuilder var1) {
        this.a = var1;
    }
}
