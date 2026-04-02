package com.ecommerce.api.config;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class I18nConfig {

    // Reads the Accept-Language header on every request
    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
        resolver.setDefaultLocale(Locale.ENGLISH); // fallback if header is missing
        return resolver;
    }

    // Tells Spring where are message resource files located
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:i18n/messages");
        ms.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return ms;
    }
}
