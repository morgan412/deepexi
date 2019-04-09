package com.deepexi.user.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deepexi.user.domain.eo.Component;
import com.deepexi.user.domain.query.ComponentQuery;
import com.deepexi.user.service.ComponentService;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import java.util.List;

@Service
@Path("/api/v1/components")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ComponentController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Reference(version = "${demo.service.version}")
    private ComponentService componentService;

    @Operation(summary = "查询组件",
        description = "条件分页查询组件信息",
        tags = {"component"})
    @GET
    @Path("/")
    public Payload getComponentList(@QueryParam("page") @DefaultValue("1")  Integer page,
                                    @QueryParam("size") @DefaultValue("10")  Integer size,
                                    @QueryParam("orderColumn") @DefaultValue("createdAt")  String orderColumn,
                                    @QueryParam("sort") @DefaultValue("desc")  String sort,
                                    @QueryParam("name") String name,
                                    @QueryParam("categoryId") String categoryId,
                                    @QueryParam("dr") Integer dr
                                    ) {
        return new Payload(componentService.getComponentList(new ComponentQuery(page, size, orderColumn, sort, name, categoryId, dr)));
    }

    @Operation(summary = "根据Id查询组件",
        description = "根据Id查询组件",
        tags = {"component"})
    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload getComponentById(@PathParam("id") String id) {
        return new Payload(componentService.getComponentById(id));
    }

    @Operation(summary = "新增组件",
        description = "新增组件",
        tags = {"component"})
    @POST
    @Path("/")
    public Payload createComponent(Component component) {
        return new Payload(componentService.createComponent(component));
    }

    @Operation(summary = "根据Id修改组件",
        description = "根据Id修改组件",
        tags = {"component"})
    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload updateComponentById(@PathParam("id") String id, Component component) {
        component.setId(id);
        return new Payload(componentService.updateComponentById(component));
    }

    @Operation(summary = "批量上下架",
        description = "批量上下架，修改状态",
        tags = {"component"},
        parameters = {@Parameter(name =  "ids", description = "要删除的Id数组")})
    @POST
    @Path("/{dr:[01]}")
    public Payload updateComponentsDr(String jsonParam,@PathParam("dr") Integer dr) {
        JSONObject obj = JSON.parseObject(jsonParam);
        List<String> ids = obj.getJSONArray("ids").toJavaList(String.class);
        return new Payload(componentService.updateComponentsDr(ids, dr));
    }

    @Operation(summary = "根据Id删除",
        description = "根据Id删除",
        tags = {"component"})
    @DELETE
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload deleteComponentById(@PathParam("id") String id) {
        return new Payload(componentService.deleteComponentById(id));
    }

    @Operation(summary = "批量删除",
        description = "批量删除",
        tags = {"component"},
        parameters = {@Parameter(name =  "ids", description = "要删除的Id数组")})
    @POST
    @Path("/batchdelete")
    public Payload deleteComponentByIds(String jsonParam) {
        JSONObject obj = JSON.parseObject(jsonParam);
        List<String> ids = obj.getJSONArray("ids").toJavaList(String.class);
        return new Payload(componentService.deleteComponentByIds(ids));
    }

}
