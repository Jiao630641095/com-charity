package com.charity.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author：王思峰
 * @Description:
 * @Date: Created in 14:36 2017/9/21
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
////        //访问路径
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/");
//        super.addResourceHandlers(registry);
    }
}
