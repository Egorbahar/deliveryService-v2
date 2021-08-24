package com.exposit.core.component;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * A Source for messages supporting for internationalization
 */
@Component
public class LocalMessageSource {
    private MessageSource messageSource;

    public LocalMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Get message in the language according to the locale
     *
     * @param messageCode - property
     * @param arguments   arguments
     * @return String
     */
    public String getMessage(String messageCode, Object[] arguments) {
        Locale locale = new Locale("En");
        return messageSource.getMessage(messageCode, arguments,locale);
    }
}
