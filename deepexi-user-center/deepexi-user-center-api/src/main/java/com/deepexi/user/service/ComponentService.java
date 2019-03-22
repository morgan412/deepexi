package com.deepexi.user.service;

import com.deepexi.user.domain.eo.Component;
import com.deepexi.user.domain.query.ComponentQuery;
import com.deepexi.util.pageHelper.PageBean;

import java.util.List;

/**
 * @author zou
 * @date 2019/3/21
 */
public interface ComponentService {
    PageBean getComponentList(ComponentQuery componentQuery);

    Object createComponent(Component component);

    Boolean deleteComponentById(String id);

    Boolean deleteComponentByIds(List<String> ids);

    Boolean updateComponentById(Component component);

    Component getComponentById(String id);

    Boolean updateComponentsDr(List<String> ids, Integer dr);
}
