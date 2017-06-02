package com.ibingbo.rpc.service.impl;

import com.ibingbo.rpc.annotation.RpcService;
import com.ibingbo.rpc.service.IHelloService;

/**
 * Created by bing on 17/6/2.
 */
// 指定远程接口  使用 RpcService注解定义在服务接口的实现类上，需要对该实现类指定远程接口，因为实现类可能会实现多个接口，一定要告诉框架哪个才是远程接口
@RpcService(IHelloService.class)
public class HelloServiceImpl implements IHelloService {
    public String hello(String name) {
        return "hello, " + name;
    }
}
