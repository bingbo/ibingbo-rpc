package com.ibingbo.rpc.spring.bean;

import com.ibingbo.rpc.server.RpcServer;
import com.ibingbo.rpc.server.ServiceRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

/**
 * Created by bing on 17/6/10.
 */
public class RegistryBean implements ApplicationContextAware, InitializingBean {

    private String id;
    private String address;
    private String path;
    private String username;
    private String password;
    private String connectionTimeout;
    private String sessionTimeout;

    private ServiceRegistry serviceRegistry;
    private RpcServer rpcServer;

    /**
     * 获取注册中心配置信息
     * @param beanId
     * @param beanFactory
     * @return
     */
    public static RegistryBean getRegistryBean(String beanId, BeanFactory beanFactory) {
        if (StringUtils.hasText(beanId) && beanFactory.containsBean(beanId)) {
            return (RegistryBean) beanFactory.getBean(beanId);
        } else {
            return beanFactory.getBean(RegistryBean.class);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(String sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.serviceRegistry = new ServiceRegistry(this.address);
        this.rpcServer = new RpcServer("127.0.0.1:8000", this.serviceRegistry);
        this.rpcServer.setApplicationContext(applicationContext);
    }

    public void afterPropertiesSet() throws Exception {
        this.rpcServer.afterPropertiesSet();
    }
}
