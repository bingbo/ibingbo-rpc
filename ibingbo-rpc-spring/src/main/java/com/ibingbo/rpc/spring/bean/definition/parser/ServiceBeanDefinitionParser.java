package com.ibingbo.rpc.spring.bean.definition.parser;

import com.ibingbo.rpc.spring.bean.ServiceBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by bing on 17/6/10.
 */
public class ServiceBeanDefinitionParser extends AbstractSingleBeanDefinitionParser{
    @Override
    protected Class<?> getBeanClass(Element element) {
        return ServiceBean.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);
    }
}
