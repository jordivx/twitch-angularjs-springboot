package com.jordivx.twitchangularjsspringboot;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/top-games-page").setViewName("top-games-page");
        registry.addViewController("/streams-page").setViewName("streams-page");
        registry.addViewController("/channels-page").setViewName("channels-page");
    }

}