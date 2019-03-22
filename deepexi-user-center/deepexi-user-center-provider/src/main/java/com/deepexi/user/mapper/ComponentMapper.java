package com.deepexi.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.deepexi.user.domain.eo.Component;
import com.deepexi.user.domain.vo.ComponentVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ComponentMapper extends BaseMapper<Component> {

    List<ComponentVo> selectPageVo(@Param("name") String name, @Param("categoryId") String categoryId, @Param("dr") Integer dr);
}
