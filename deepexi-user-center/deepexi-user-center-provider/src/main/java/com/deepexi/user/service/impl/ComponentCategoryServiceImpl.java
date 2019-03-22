package com.deepexi.user.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;
import com.deepexi.user.domain.eo.ComponentCategory;
import com.deepexi.user.domain.vo.SelectVo;
import com.deepexi.user.extension.AppRuntimeEnv;
import com.deepexi.user.extension.ApplicationException;
import com.deepexi.user.mapper.ComponentCategoryMapper;
import com.deepexi.user.service.ComponentCategoryService;
import com.deepexi.util.pageHelper.PageBean;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Component
@Service(version = "${demo.service.version}")
public class ComponentCategoryServiceImpl implements ComponentCategoryService {

    private static final Logger logger = LoggerFactory.getLogger(ComponentCategoryServiceImpl.class);

    @Autowired
    private ComponentCategoryMapper componentCategoryMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public PageBean getComponentCategoryList(Integer page, Integer size, String name) {
        PageHelper.startPage(page, size);
        List<ComponentCategory> componentCategorys = componentCategoryMapper.selectPageVo(name);
        return new PageBean<>(componentCategorys);
    }

    @Override
    public List<SelectVo> getComponentCategorySelectVo() {
        List<ComponentCategory> componentCategorys = componentCategoryMapper.selectList(null);
        List<SelectVo> selectVos = new ArrayList<>();
        for (ComponentCategory componentCategory: componentCategorys) {
            selectVos.add(new SelectVo(componentCategory.getId(),componentCategory.getName()));
        }
        return selectVos;
    }

    @Override
    public Object createComponentCategory(ComponentCategory componentCategory) {
        componentCategoryMapper.insert(componentCategory);
        return componentCategory;
    }

    @Override
    public Boolean deleteComponentCategoryById(String id) {
        componentCategoryMapper.deleteById(id);
        return true;
    }

    @Override
    @SentinelResource(value = "testSentinel", fallback = "doFallback", blockHandler = "exceptionHandler")
    public ComponentCategory getComponentCategoryById(String id) {
        // dubbo生产者被消费者调用时，客户端隐式传入的参数
        String tenantId = RpcContext.getContext().getAttachment("tenantId");
        logger.info("获取客户端隐式参数，tenantId：{}", tenantId);
        return componentCategoryMapper.selectById(id);
    }

    public String doFallback(long i) {
        // Return fallback value.
        return "Oops, degraded";
    }

    /**
     * 熔断降级处理逻辑
     * @param s
     * @param ex
     * @return
     */
    public void exceptionHandler(long s, Exception ex) {
        // Do some log here.
        logger.info("-------------熔断降级处理逻辑---------\n");
        throw new ApplicationException(Response.Status.BAD_REQUEST, "100001", "熔断降级处理!");
    }

    public Object testError() {
        throw new ApplicationException(Response.Status.BAD_REQUEST, "100002", "这是返回的自定义错误信息!");
    }
}