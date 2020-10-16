package com.jiangwensi.busarrival.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.Thymeleaf;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafView;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Slf4j
@Configuration
@EnableScheduling
//@PropertySource(value = "file:${catalina.home}\\conf\\local.application.properties",ignoreResourceNotFound = false)
@PropertySource(value = "file:${catalina.home}//conf//local.application.properties",ignoreResourceNotFound = false)
public class AppConfiguration {
}
