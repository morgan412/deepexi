package com.deepexi.product.controller;

import com.deepexi.product.service.ProductService;
import com.deepexi.product.domain.eo.Product;
import com.deepexi.util.config.Payload;
import com.deepexi.util.constant.ContentType;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;

@Service
@Path("/api/v1/products")
@Consumes({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    @Operation(summary = "查询",
        description = "条件分页查询",
        tags = {"product"})
    @GET
    @Path("/")
    public Payload getProductList(@QueryParam("page") @DefaultValue("1")  Integer page,
                                  @QueryParam("size") @DefaultValue("10")  Integer size,
                                  @QueryParam("age") @DefaultValue("0")  Integer price) {
        return new Payload(productService.getProductList(page, size, price));
    }

    @Operation(summary = "根据Id查询",
        description = "根据Id查询",
        tags = {"product"})
    @GET
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload getProductById(@PathParam("id") String id) {
        return new Payload(productService.getProductById(id));
    }

    @Operation(summary = "新增组件",
        description = "新增组件",
        tags = {"product"})
    @POST
    @Path("/")
    public Payload createProduct(Product product) {
        return new Payload(productService.createProduct(product));
    }

    @Operation(summary = "根据Id修改",
        description = "根据Id修改",
        tags = {"product"})
    @PUT
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload updateProductById(@PathParam("id") String id, Product product) {
        return new Payload(null);
    }

    @Operation(summary = "根据Id删除",
        description = "根据Id删除",
        tags = {"product"})
    @DELETE
    @Path("/{id:[a-zA-Z0-9]+}")
    public Payload deleteProductById(@PathParam("id") String id) {
        return new Payload(productService.deleteProductById(id));
    }
}
