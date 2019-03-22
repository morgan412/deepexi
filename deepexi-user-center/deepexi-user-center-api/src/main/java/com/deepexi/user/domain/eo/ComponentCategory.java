package com.deepexi.user.domain.eo;

import com.deepexi.util.mapper.SuperEntity;

public class ComponentCategory extends SuperEntity {
    /**
     * 组件名称
     */
    private String name;

    public ComponentCategory() {
    }

    public ComponentCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
