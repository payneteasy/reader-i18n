package com.payneteasy.reader.i18n.impl;

import java.io.IOException;
import java.io.Reader;
import java.util.Locale;
import java.util.PropertyResourceBundle;

public class LocalePropertyResourceBundle extends PropertyResourceBundle {

    private final Locale locale;

    public LocalePropertyResourceBundle(Locale aLocale, Reader reader) throws IOException {
        super(reader);
        locale = aLocale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
