package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("permit");
        registry.addViewController("/").setViewName("permit");
        registry.addViewController("/about").setViewName("permit");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/contact").setViewName("contact");
        registry.addViewController("/addItem").setViewName("permit");
        registry.addViewController("/panier").setViewName("permit");
        registry.addViewController("/tva").setViewName("/tva");
    }

}