package com.ibingbo.rpc.spring.namespace.handler;

import com.ibingbo.rpc.spring.bean.definition.parser.ReferenceBeanDefinitionParser;
import com.ibingbo.rpc.spring.bean.definition.parser.RegistryBeanDefinitionParser;
import com.ibingbo.rpc.spring.bean.definition.parser.ServiceBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by bing on 17/6/10.
 */
public class IbingboNamespaceHandler extends NamespaceHandlerSupport{
    public void init() {
        registerBeanDefinitionParser("registry", new RegistryBeanDefinitionParser());
        registerBeanDefinitionParser("service", new ServiceBeanDefinitionParser());
        registerBeanDefinitionParser("reference", new ReferenceBeanDefinitionParser());
    }
}
