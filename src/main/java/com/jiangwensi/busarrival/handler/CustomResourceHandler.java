package com.jiangwensi.busarrival.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import java.util.Arrays;
import java.util.Properties;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
public class CustomResourceHandler extends ResourceHttpRequestHandler {

    @Autowired
    ApplicationContext applicationContext;

    @Bean
    public ResourceHttpRequestHandler resourceHttpRequestHandler() {
        ResourceHttpRequestHandler requestHandler = new CustomResourceHandler();
        requestHandler.setLocations(Arrays.<Resource>asList(applicationContext.getResource("/")));
        return requestHandler;
    }

    @Bean
    public SimpleUrlHandlerMapping sampleServletMapping(){
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MAX_VALUE - 2);
        Properties urlProperties = new Properties();
        urlProperties.put("/**", "resourceHttpRequestHandler");
        mapping.setMappings(urlProperties);
        return mapping;
    }
}
