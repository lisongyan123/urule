//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.bstek.urule.model.table;

import com.bstek.urule.model.rule.lhs.And;
import com.bstek.urule.model.rule.lhs.Junction;
import com.bstek.urule.model.rule.lhs.Or;
import java.util.ArrayList;
import java.util.List;

public class Joint {
    private List<Condition> conditions;
    private List<Joint> joints;
    private JointType type;

    public Joint() {
    }

    public Junction getJunction() {
        return (Junction)(this.type.equals(JointType.and) ? new And() : new Or());
    }

    public List<Condition> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<Condition> var1) {
        this.conditions = var1;
    }

    public void addJoint(Joint var1) {
        if (this.joints == null) {
            this.joints = new ArrayList();
        }

        this.joints.add(var1);
    }

    public void addCondition(Condition var1) {
        if (this.conditions == null) {
            this.conditions = new ArrayList();
        }

        this.conditions.add(var1);
    }

    public List<Joint> getJoints() {
        return this.joints;
    }

    public void setJoints(List<Joint> var1) {
        this.joints = var1;
    }

    public JointType getType() {
        return this.type;
    }

    public void setType(JointType var1) {
        this.type = var1;
    }
}
