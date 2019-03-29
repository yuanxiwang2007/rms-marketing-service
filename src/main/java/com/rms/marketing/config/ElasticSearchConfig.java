package com.rms.marketing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class ElasticSearchConfig {

    static {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    // 开始的时候就创建索引

}
