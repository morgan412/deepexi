package com.deepexi.user.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.dubbo.rpc.RpcContext;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.deepexi.user.domain.query.ComponentQuery;
import com.deepexi.user.domain.vo.ComponentVo;
import com.deepexi.user.extension.AppRuntimeEnv;
import com.deepexi.user.extension.ApplicationException;
import com.deepexi.user.mapper.ComponentMapper;
import com.deepexi.util.pageHelper.PageBean;
import com.deepexi.user.domain.eo.Component;
import com.deepexi.user.service.ComponentService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;

@org.springframework.stereotype.Component
@Service(version = "${demo.service.version}")
public class ComponentServiceImpl implements ComponentService {

    private static final Logger logger = LoggerFactory.getLogger(ComponentServiceImpl.class);

    @Autowired
    private ComponentMapper componentMapper;

    @Autowired
    private AppRuntimeEnv appRuntimeEnv;

    @Override
    public PageBean getComponentList(ComponentQuery componentQuery) {
        PageHelper.startPage(componentQuery.getPage(), componentQuery.getSize());
        PageHelper.orderBy(componentQuery.getOrderString());
        List<ComponentVo> ComponentVos = componentMapper.selectPageVo(componentQuery.getName(), componentQuery.getCategoryId(), componentQuery.getDr());
        return new PageBean<>(ComponentVos);
    }

    @Override
    public Object createComponent(Component component) {
        return componentMapper.insert(component);
    }

    @Override
    public Boolean deleteComponentById(String id) {
        componentMapper.deleteById(id);
        return true;
    }

    @Override
    public Boolean deleteComponentByIds(List<String> ids) {
        componentMapper.deleteBatchIds(ids);
        return true;
    }

    @Override
    public Boolean updateComponentById(Component component) {
        componentMapper.updateById(component);
        return true;
    }

    @Override
    public Boolean updateComponentsDr(List<String> ids, Integer dr) {
        Component component = new Component();
        component.setDr(dr);
        UpdateWrapper<Component> wrapper = new UpdateWrapper<>();
        wrapper.in("id",ids);
        componentMapper.update(component, wrapper);
        return true;
    }

    @Override
    @SentinelResource(value = "testSentinel", fallback = "doFallback", blockHandler = "exceptionHandler")
    public Component getComponentById(String id) {
        // dubbo生产者被消费者调用时，客户端隐式传入的参数
        String tenantId = RpcContext.getContext().getAttachment("tenantId");
        logger.info("获取客户端隐式参数，tenantId：{}", tenantId);
        return componentMapper.selectById(id);
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