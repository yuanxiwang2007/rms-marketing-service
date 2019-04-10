package com.hwt.ris.marketing;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@MapperScan("com.hwt.ris.marketing.mapper")
@SpringBootApplication(scanBasePackages = {"com.hwt.ris.marketing"})
public class Application{

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(Application.class, args);
        //new SpringContextUtil().setApplicationContext(app);
        System.out.println("started...");
    }

    @RequestMapping("/")
    public String hello(){
        logger.info("info");
        logger.debug("debug");
        logger.error("error");
        return "hello";
    }
}
