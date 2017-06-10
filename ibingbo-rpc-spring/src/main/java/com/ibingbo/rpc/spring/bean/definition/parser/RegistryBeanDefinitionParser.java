package com.ibingbo.rpc.spring.bean.definition.parser;

import com.ibingbo.rpc.spring.bean.RegistryBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * Created by bing on 17/6/10.
 */
public class RegistryBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    private static final String DEFAULT_REGISTRY_ID = "default";
    private static final String ADDRESS = "address";
    private static final String PATH = "path";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CONNECTION_TIMEOUT = "connectionTimeout";
    private static final String SESSION_TIMEOUT = "sessionTimeout";

    @Override
    protected Class<?> getBeanClass(Element element) {
        return RegistryBean.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        if (StringUtils.isEmpty(id)) {
            id = DEFAULT_REGISTRY_ID;
        }
        if (parserContext.getRegistry().containsBeanDefinition(id)) {
            throw new IllegalStateException("duplicate spring bean id " + id);
        }
        element.setAttribute("id", id);
        builder.addPropertyValue("id", id);

        NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Attr attr = (Attr) attrs.item(i);
            String name = attr.getName();
            String value = attr.getValue().trim();
            if (!StringUtils.isEmpty(value)) {
                if (ADDRESS.equals(name) || PATH.equals(name) || USERNAME.equals(name) || PASSWORD.equals(name) || CONNECTION_TIMEOUT.equals(name) || SESSION_TIMEOUT.equals(name)) {
                    builder.addPropertyValue(name, value);
                }
            }
        }


    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);
    }
}
