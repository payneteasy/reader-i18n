package com.payneteasy.reader.i18n;

import com.payneteasy.reader.i18n.impl.LocalePropertyResourceBundle;
import com.payneteasy.reader.i18n.impl.ReaderI18nServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class ReaderI18nServiceBuilder {

    private final List<ResourceBundle> bundles = new ArrayList<ResourceBundle>();

    public ReaderI18nServiceBuilder addBundle(ResourceBundle aResource) {
        bundles.add(aResource);
        return this;
    }

    public ReaderI18nServiceBuilder addPropertyFile(Locale aLocale, String aResourceLocation) throws IOException {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(aResourceLocation);
        if(in == null) {
            throw new IOException("No resource with path " + aResourceLocation);
        }
        InputStreamReader reader = new InputStreamReader(in, Charset.forName("utf-8"));
        PropertyResourceBundle bundle = new LocalePropertyResourceBundle(aLocale, reader);
        bundles.add(bundle);
        return this;
    }

    public IReaderI18nService build() {
        if(bundles.isEmpty()) {
            throw new IllegalStateException("There are no any bundles. Add bundles using addBundle() or addPropertyFile() methods.");
        }
        return new ReaderI18nServiceImpl();
    }

    public List<ResourceBundle> getBundles() {
        return bundles;
    }
}
