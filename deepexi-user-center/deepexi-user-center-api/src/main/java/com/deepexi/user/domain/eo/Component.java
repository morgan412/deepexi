package com.deepexi.user.domain.eo;

import com.deepexi.util.mapper.SuperEntity;

public class Component extends SuperEntity {
    /**
     * 组件名称
     */
    private String name;
    /**
     * 版本
     */
    private String version;
    /**
     * 分类Id
     */
    private String categoryId;

    public Component() {
    }

    public Component(String name, String version, String categoryId) {
        this.name = name;
        this.version = version;
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
}
