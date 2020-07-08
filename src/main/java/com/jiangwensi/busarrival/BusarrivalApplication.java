package com.jiangwensi.busarrival;

import com.jiangwensi.busarrival.config.AppConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class BusarrivalApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BusarrivalApplication.class, args);
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//
//        AppConfiguration expressiveConfig = context.getBean(AppConfiguration.class);
//
//        expressiveConfig.outputResource();
//
//        context.close();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BusarrivalApplication.class);
    }
}
