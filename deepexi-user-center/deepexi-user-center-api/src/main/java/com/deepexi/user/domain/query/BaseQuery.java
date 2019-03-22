package com.deepexi.user.domain.query;

import com.deepexi.util.StringUtil;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

import java.io.Serializable;

/**
 * @author zou
 * @date 2019/3/22
 */
public class BaseQuery implements Serializable {
    private Integer page;
    private Integer size;
    private String orderColumn;
    private String sort;

    public BaseQuery() {
    }

    public BaseQuery(Integer page, Integer size, String orderColumn, String sort) {
        this.page = page;
        this.size = size;
        this.orderColumn = orderColumn;
        this.sort = sort;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrderString(){
        if (StringUtil.isEmpty(this.orderColumn)) {
            return "";
        }
        int len = this.orderColumn.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            char c = this.orderColumn.charAt(i);
            if (Character.isUpperCase(c) && i > 0) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(c));
        }
        sb.append(" ");
        sb.append(StringUtil.isNotEmpty(this.sort) ? this.sort : "");
        return sb.toString();
    }

}
