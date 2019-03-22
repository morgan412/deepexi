package com.deepexi.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.user.domain.eo.ComponentCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComponentCategoryMapper extends BaseMapper<ComponentCategory> {

    List<ComponentCategory> selectPageVo(@Param("name") String name);
}
