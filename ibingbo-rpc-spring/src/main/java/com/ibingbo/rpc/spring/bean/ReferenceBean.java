package com.ibingbo.rpc.spring.bean;

import com.ibingbo.rpc.client.RpcProxy;
import com.ibingbo.rpc.client.ServiceDiscovery;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.core.Ordered;

/**
 * Created by bing on 17/6/10.
 */
public class ReferenceBean<T, S extends T> implements InitializingBean, DisposableBean, BeanFactoryAware ,FactoryBean<S>,Ordered{

    private String id;
    private String interfaceName;
    private String version;
    private String group;

    //默认最低优先级
    private int order = Ordered.LOWEST_PRECEDENCE;

    private Class<?> interfaceClass;
    private BeanFactory beanFactory;

    private RpcProxy rpcProxy;

    private S referenceService;

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    public void destroy() throws Exception {

    }

    public void afterPropertiesSet() throws Exception {
        this.interfaceClass = Class.forName(this.interfaceName, true, Thread.currentThread().getContextClassLoader());
        RegistryBean registryBean = RegistryBean.getRegistryBean(null, this.beanFactory);
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery(registryBean.getAddress());
        this.rpcProxy = new RpcProxy(serviceDiscovery);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getVersion() {
        return version;
    }

    public String getGroup() {
        return group;
    }

    public Class<?> getInterfaceClass() {
        return interfaceClass;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    public int getOrder() {
        return this.order;
    }

    public S getObject() throws Exception {
        if (this.referenceService == null) {
            this.referenceService = this.rpcProxy.create(this.interfaceClass);
        }
        return this.referenceService;
    }

    public Class<?> getObjectType() {
        return this.interfaceClass;
    }

    public boolean isSingleton() {
        return true;
    }
}
