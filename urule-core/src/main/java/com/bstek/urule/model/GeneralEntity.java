package com.bstek.urule.model;

import com.bstek.urule.exception.RuleException;
import java.util.HashMap;
import java.util.UUID;
import org.apache.commons.lang.StringUtils;

public class GeneralEntity extends HashMap<String, Object> {
    private static final long serialVersionUID = 2778576006420277518L;
    private final String id = UUID.randomUUID().toString();
    private String targetClass;

    public GeneralEntity(String var1) {
        if (StringUtils.isBlank(var1)) {
            throw new RuleException("Target class cannot be null.");
        } else {
            this.targetClass = var1;
        }
    }

    public String getTargetClass() {
        return this.targetClass;
    }

    public String getId() {
        return this.id;
    }

    public boolean equals(Object var1) {
        if (var1 instanceof GeneralEntity) {
            GeneralEntity var2 = (GeneralEntity)var1;
            return var2.getId().contentEquals(this.id);
        } else {
            return false;
        }
    }
}
