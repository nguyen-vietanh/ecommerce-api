package com.ecommerce.api.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class CorsConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    public CorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        for (CorsProperties.CorsMapping mapping : corsProperties.getMappings()) {
            registry.addMapping(mapping.getPath())
                    .allowedOrigins(mapping.getAllowedOrigins().toArray(String[]::new))
                    .allowedMethods(mapping.getAllowedMethods().toArray(String[]::new))
                    .allowedHeaders(mapping.getAllowedHeaders().toArray(String[]::new))
                    .allowCredentials(mapping.isAllowCredentials())
                    .maxAge(mapping.getMaxAge());
        }
    }
}
