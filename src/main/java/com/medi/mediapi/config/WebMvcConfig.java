package com.medi.mediapi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@ComponentScan(basePackages = {"com.medi.mediapi"})
// @EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @PostConstruct
    public void init() {
        log.info("!!!!!!!!!!!!!!!!SCAN!!!!!!!!!!!!!");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        log.info("!!!!!!!!!!CORS CHECK!!!!!!!!!!!");

        // 모든 경로에 대해
        registry.addMapping("/**")
                // Origin이 http:localhost:3000에 대해
                .allowedOrigins("http://localhost:3000", "http://183.111.252.163")
                // GET, POST, PUT, PATCH, DELETE, OPTIONS 메서드를 허용합니다.
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }
}