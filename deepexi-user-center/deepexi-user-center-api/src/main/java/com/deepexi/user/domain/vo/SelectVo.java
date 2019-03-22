package com.deepexi.user.domain.vo;

import java.io.Serializable;

/**
 * 下拉框展示对象
 * @author zou
 * @date 2019/3/20
 */
public class SelectVo implements Serializable {

    private String value;
    private String label;

    public SelectVo() {
    }

    public SelectVo(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
