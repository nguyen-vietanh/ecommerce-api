package com.ecommerce.api.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MessageService {

    MessageSource messageSource;

    public String getMessage(String key) {
        return this.messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String key, Object... args) {
        return this.messageSource.getMessage(key, args, LocaleContextHolder.getLocale());
    }
}
