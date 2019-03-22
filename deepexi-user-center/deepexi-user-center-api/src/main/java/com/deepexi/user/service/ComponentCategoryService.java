package com.deepexi.user.service;

import com.deepexi.user.domain.eo.ComponentCategory;
import com.deepexi.user.domain.vo.SelectVo;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @author zou
 * @date 2019/3/21
 */
public interface ComponentCategoryService {

    PageBean getComponentCategoryList(Integer page, Integer size, String name);

    List<SelectVo> getComponentCategorySelectVo();

    Object createComponentCategory(ComponentCategory componentCategory);

    Boolean deleteComponentCategoryById(String id);

    ComponentCategory getComponentCategoryById(String id);
}
