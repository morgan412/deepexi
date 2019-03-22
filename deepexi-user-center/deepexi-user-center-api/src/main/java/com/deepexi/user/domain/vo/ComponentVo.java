package com.deepexi.user.domain.vo;

import com.deepexi.user.domain.eo.Component;

/**
 * @author zou
 * @date 2019/3/19
 */
public class ComponentVo extends Component {
    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
