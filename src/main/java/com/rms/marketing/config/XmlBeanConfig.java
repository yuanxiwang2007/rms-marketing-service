package com.rms.marketing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by jovi on 2018/10/10.
 */
@Configuration
@ImportResource(locations={"classpath:spring/*.xml"})
public class XmlBeanConfig {

    private static final Logger logger = LoggerFactory.getLogger(XmlBeanConfig.class);
}
