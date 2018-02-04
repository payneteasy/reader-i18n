package com.payneteasy.reader.i18n.impl;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Translator {

    private final ResourceBundle mainBundle;
    private final ResourceBundle defaultBundle;

    public Translator(Locale aLocale, Map<Locale, ResourceBundle> aBundles) {
        this.mainBundle = aBundles.get(aLocale);
        this.defaultBundle = aBundles.get(Locale.ENGLISH);
    }

    public String get(String aKey, Object ... args) {
        String pattern;
        if(mainBundle.containsKey(aKey)) {
            pattern = mainBundle.getString(aKey);
        } else if(defaultBundle.containsKey(aKey)) {
            pattern = defaultBundle.getString(aKey);
        } else {
            return "no translation for " + aKey;
        }

        MessageFormat format = new MessageFormat(pattern);
        return format.format(args);
    }
}
