package com.hom.juc.threadPool;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan(basePackages = "com.hom")
@ImportResource(value = {"applicationContext.xml"})
@EnableScheduling
public class ThreadConf {
}
