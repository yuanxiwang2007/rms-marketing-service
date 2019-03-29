package com.rms.marketing;

import com.rms.marketing.common.util.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yxw on 2019-03-06.
 */
@Controller
@EnableWebMvc
@EnableAsync
@EnableScheduling
@MapperScan("com.rms.marketing.mapper")
@SpringBootApplication(scanBasePackages = {"com.rms.marketing"})
public class Application{

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(Application.class, args);
        new SpringContextUtil().setApplicationContext(app);
        System.out.println("started...");
    }

}
