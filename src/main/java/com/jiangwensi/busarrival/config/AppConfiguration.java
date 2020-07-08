package com.jiangwensi.busarrival.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Jiang Wensi on 8/7/2020
 */
@Slf4j
@Configuration
@PropertySource(value = "file:${catalina.home}\\conf\\local.application.properties",ignoreResourceNotFound = false)
//@PropertySource(value = "file:D:\\MyProjects\\busarrival\\local.application.properties",ignoreResourceNotFound = false)
public class AppConfiguration {
}
