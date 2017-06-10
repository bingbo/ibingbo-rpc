package com.ibingbo.rpc.spring.bean.definition.parser;

import com.ibingbo.rpc.spring.bean.ReferenceBean;
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
public class ReferenceBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
    private static final String INTERFACE = "interface";
    private static final String VERSION = "version";
    private static final String GROUP = "group";

    private static final String PROPERTY_KEY_INTERFACE_NAME = "interfaceName";
    private static final String DEFAULT_VERSION = "1.0.0";
    private static final String DEFAULT_GROUP = "normal";

    @Override
    protected Class<?> getBeanClass(Element element) {
        return ReferenceBean.class;
    }

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        String id = element.getAttribute("id");
        if (id == null || id.length() == 0) {
            String name = element.getAttribute("name");
            if (name == null || name.length() == 0) {
                String interfaceName = element.getAttribute(INTERFACE);
                interfaceName = StringUtils.hasText(interfaceName) ? interfaceName : null;

                String version = element.getAttribute(VERSION);
                version = StringUtils.hasText(version) ? version : DEFAULT_VERSION;

                String group = element.getAttribute(GROUP);
                group = StringUtils.hasText(group) ? group : DEFAULT_GROUP;

                name = interfaceName + "." + version + "." + group;

                if (name == null || name.length() == 0) {
                    name = this.getClass().getSimpleName();
                }
                id = name;
                int count = 2;
                while (parserContext.getRegistry().containsBeanDefinition(id)) {
                    id = name + (count++);
                }


            }
        }
        if (id != null && id.length() > 0) {
            if (parserContext.getRegistry().containsBeanDefinition(id)) {
                throw new IllegalStateException("Duplicate spring bean id " + id);
            }
            element.setAttribute("id", id);
            builder.addPropertyValue("id", id);
        }

        NamedNodeMap attrs = element.getAttributes();
        for (int i = 0; i < attrs.getLength(); i++) {
            Attr attr = (Attr) attrs.item(i);
            String name = attr.getName();
            String value = attr.getValue();
            if (value != null && value.trim().length() > 0) {
                if (INTERFACE.equals(name)) {
                    builder.addPropertyValue(PROPERTY_KEY_INTERFACE_NAME, value.trim());
                } else if (VERSION.equals(name) || GROUP.equals(name)) {
                    builder.addPropertyValue(name, value.trim());
                }
            }
        }
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        super.doParse(element, builder);
    }
}
