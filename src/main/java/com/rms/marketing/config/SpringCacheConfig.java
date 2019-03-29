package com.rms.marketing.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jovi on 2018/10/11.
 * 缓存配置，根据配置文件中的 app.cache.enable 判定是否启用
 */
@Configuration
@ConditionalOnProperty(name = "app.cache.enable", havingValue = "true")
@EnableCaching
public class SpringCacheConfig {

    private static final Logger logger = LoggerFactory.getLogger(SpringCacheConfig.class);

}
