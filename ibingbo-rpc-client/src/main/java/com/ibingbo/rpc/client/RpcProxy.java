package com.ibingbo.rpc.client;

import com.ibingbo.rpc.common.RpcRequest;
import com.ibingbo.rpc.common.RpcResponse;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by bing on 17/6/2.
 */
public class RpcProxy {
    private String serverAddress;
    private RpcServiceDiscovery serviceDiscovery;

    public RpcProxy(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcProxy(RpcServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    public RpcProxy(String serverAddress, RpcServiceDiscovery serviceDiscovery) {
        this.serverAddress = serverAddress;
        this.serviceDiscovery = serviceDiscovery;
    }


    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                RpcRequest request = new RpcRequest();//创建并初始化rpc请求
                request.setRequestId(UUID.randomUUID().toString());
                request.setClassName(method.getDeclaringClass().getName());
                request.setMethodName(method.getName());
                request.setParameterTypes(method.getParameterTypes());
                request.setParameters(args);

                if (serviceDiscovery != null) {
                    serverAddress = serviceDiscovery.discover();//发现服务
                }

                String[] array = serverAddress.split(":");
                String host = array[0];
                int port = Integer.parseInt(array[1]);

                RpcClient client =new RpcClient(host,port);
                RpcResponse response = client.send(request);

                if (response.getError() != null) {
                    throw response.getError();
                } else {
                    return response.getResult();
                }
            }
        });
    }

    public String getServiceAddress() {
        return serverAddress;
    }

    public void setServiceAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RpcServiceDiscovery getServiceDiscovery() {
        return serviceDiscovery;
    }

    public void setServiceDiscovery(RpcServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }
}
