package com.example.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageHelper {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, Locale.getDefault());
    }
}
