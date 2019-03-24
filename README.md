## 一、选题

选择使用 spring boot + dubbo 开发

源代码可见附件 `deepexi-code.zip`，或见 [https://github.com/Morgan412/deepexi](https://github.com/Morgan412/deepexi "https://github.com/Morgan412/deepexi")

前端页面展示地址 [http://106.13.11.180:8080](http://106.13.11.180:8080 "http://106.13.11.180:8080")



## 二、过程

**要求：**

* 在已提供的 spring boot + dubbo 微服务工程的基础上，一个微服务作为提供者，另一个作为消费者，实现远程调用的例子
* 实现一个组件管理的功能（分页查询、新增、修改、批量删除和上下架）
* `deepexi-user-center` 该微服务下实现要求的功能
* `deepexi-product-center` 该微服务作为消费者，调用deepexi-user-center，实现远程调用



**思路：**

&ensp;&ensp;&ensp;&ensp;由于是使用 dubbo RPC 框架，我只在 `deepexi-user-center`中实现了具体的功能，在 `deepexi-product-center` 中调用 service 接口，并封装成 API 接口

&ensp;&ensp;&ensp;&ensp;项目中的 RESTful 框架使用的是 resteasy，我结合了 swagger 生成了 API 文档

&ensp;&ensp;&ensp;&ensp;另外，我使用 Vue + Element 简单搭建了一个前端工程 `deepexi-ui` 用于展现结果




## 三、部署
&ensp;&ensp;&ensp;&ensp;之前是没有使用过 docker 的，后来简单了解了一下，刚好有个空闲的服务器，便尝试去部署两个微服务工程和前端工程



**1、安装 zookeeper**

先安装好并启动 docker 引擎

dubbo 服务注册中心是使用的 zookeeper，所有先拉取 zookeeper 镜像，并启动容器添加映射

```
$ docker pull zookeeper
$ docker run -d --name zookeeper --restart always --p 2181:2181  -d zookeeper:latest
```



**2、创建工程镜像并启动**

原始的工程中是有 `start-code.sh` shell脚本的，用于执行工程镜像的生成、和创建容器的命令，并且可以定义一些创建容器时的参数配置

由 Dockerfile 知道我们工程是继承于 openjdk8 基础镜像的，`start-code.sh` 启动脚本中也有使用 `docker maven` 对工程进行打包，所有我直接上传了源代码，稍微修改了下 start-code.sh，并执行该脚本，它会为我们拉取 openjdk 和 maven 的镜像，并创建我们的工程镜像，最后启动容器



**3、创建前端工程镜像**

创建前端工程镜像，前端使用的是 Vue，增加 Dockerfile 用于创建镜像

```
# 设置 nginx 基础镜像 
FROM nginx:1.15 
#  
MAINTAINER zouxq <zouxq412@foxmial.com> 
# 将 dist 文件中的内容复制到 /usr/share/nginx/html/ 这个目录下面 
COPY dist/ /usr/share/nginx/html/
```



**最后创建的镜像和容器如下：**

![](https://oscimg.oschina.net/oscnet/339c137949fdcfbb896e89cc70b61dc7cb4.jpg)





## 四、结果

**测试结果：**

1. postman 调用结果可以看下图，代码包见附件 `deepexi.postman_collection.json`
2. swagger openapi 文档地址 [http://106.13.11.180:8081/product/openapi/openapi.json](http://106.13.11.180:8081/product/openapi/openapi.json "http://106.13.11.180:8081/product/openapi/openapi.json")
3. 前端页面展示地址 [http://106.13.11.180:8080](http://106.13.11.180:8080 "http://106.13.11.180:8080")



#### 1、postman 调用截图

分页查询

![](https://oscimg.oschina.net/oscnet/19e613d31f533649f81c0fbef2a8e40352c.jpg)

新增

![](https://oscimg.oschina.net/oscnet/94a93236200f7ccde00a34875f11dda213d.jpg)

修改

![](https://oscimg.oschina.net/oscnet/3b01f01faa586432e9425d124fd55aae51d.jpg)

删除

![](https://oscimg.oschina.net/oscnet/2cc4f61fcb9dae684edb1165ea9d772079e.jpg)



#### 2、前端页面截图

功能页面

![](https://oscimg.oschina.net/oscnet/aa77b8c84f9690ae5358e72e8aa0d18ba67.jpg)

swagger-ui

![](https://oscimg.oschina.net/oscnet/230d4976182d79e8f9c5c771ea15d877aa3.jpg)
