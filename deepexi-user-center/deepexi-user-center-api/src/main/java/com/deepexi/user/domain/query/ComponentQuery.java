package com.deepexi.user.domain.query;

/**
 * @author zou
 * @date 2019/3/22
 */
public class ComponentQuery extends BaseQuery{
    private String name;
    private String categoryId;
    private Integer dr;

    public ComponentQuery(){}

    public ComponentQuery(Integer page, Integer size, String orderColumn, String sort, String name, String categoryId, Integer dr) {
        super(page, size, orderColumn, sort);
        this.name = name;
        this.categoryId = categoryId;
        this.dr = dr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }
}
