package com.payneteasy.reader.i18n;

import com.payneteasy.reader.i18n.impl.LocalePropertyResourceBundle;
import com.payneteasy.reader.i18n.impl.ReaderI18nServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class ReaderI18nServiceBuilder {

    private final Map<Locale, ResourceBundle> bundles = new HashMap<Locale, ResourceBundle>();

    public ReaderI18nServiceBuilder addBundle(ResourceBundle aResource) {
        bundles.put(aResource.getLocale(), aResource);
        return this;
    }

    public ReaderI18nServiceBuilder addPropertyFile(Locale aLocale, String aResourceLocation) throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(aResourceLocation);
        if(in == null) {
            throw new IOException("No resource with path " + aResourceLocation);
        }
        InputStreamReader reader = new InputStreamReader(in, Charset.forName("utf-8"));
        PropertyResourceBundle bundle = new LocalePropertyResourceBundle(aLocale, reader);
        bundles.put(aLocale, bundle);
        return this;
    }

    public IReaderI18nService build() {
        if(bundles.isEmpty()) {
            throw new IllegalStateException("There are no any bundles. Add bundles using addBundle() or addPropertyFile() methods.");
        }
        return new ReaderI18nServiceImpl(bundles);
    }

    public List<ResourceBundle> getBundles() {
        return new ArrayList<ResourceBundle>(bundles.values());
    }
}
