package com.deepexi.product.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.deepexi.user.domain.eo.ComponentCategory;
import com.deepexi.user.service.ComponentCategoryService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/components/category")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ComponentCategoryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "${demo.service.version}")
    private ComponentCategoryService componentCategoryService;

    @Operation(summary = "查询组件",
        description = "条件分页查询组件信息",
        tags = {"category"})
    @GET
    @Path("/")
    public Payload getComponentCategoryList(@QueryParam("page") @DefaultValue("1")  Integer page,
                                  @QueryParam("size") @DefaultValue("10")  Integer size,
                                  @QueryParam("name") String name) {
        return new Payload(componentCategoryService.getComponentCategoryList(page, size, name));
    }

    @Operation(summary = "查询组件分类下拉列表",
        description = "查询组件分类下拉列表",
        tags = {"category"})
    @GET
    @Path("/select")
    public Payload getComponentCategorySelectVo() {
        return new Payload(componentCategoryService.getComponentCategorySelectVo());
    }

    @Operation(summary = "根据Id查询组件分类",
        description = "根据Id查询组件分类",
        tags = {"category"})
    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload getComponentCategoryById(@PathParam("id") String id) {
        return new Payload(componentCategoryService.getComponentCategoryById(id));
    }

    @Operation(summary = "新增组件分类",
        description = "新增组件分类",
        tags = {"category"})
    @POST
    @Path("/")
    public Payload createComponentCategory(ComponentCategory componentCategory) {
        return new Payload(componentCategoryService.createComponentCategory(componentCategory));
    }

    @Operation(summary = "根据Id修改分类",
        description = "根据Id修改分类",
        tags = {"category"})
    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload updateComponentCategoryById(@PathParam("id") String id, ComponentCategory componentCategory) {
        return new Payload(null);
    }

    @Operation(summary = "根据Id删除",
        description = "根据Id删除",
        tags = {"category"})
    @DELETE
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload deleteComponentCategoryById(@PathParam("id") String id) {
        return new Payload(componentCategoryService.deleteComponentCategoryById(id));
    }
}
