package com.ibingbo.rpc.spring.bean.definition.parser;

import com.ibingbo.rpc.spring.bean.ReferenceBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by bing on 17/6/10.
 */
public class ReferenceBeanDefinitionParser extends AbstractSingleBeanDefinitionParser{
    @Override
    protected Class<?> getBeanClass(Element element) {
        return ReferenceBean.class;
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
