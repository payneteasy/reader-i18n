package com.payneteasy.reader.i18n;

import org.junit.Test;

import java.util.List;
import java.util.ResourceBundle;

import static java.util.Locale.FRENCH;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReaderI18NServiceBuilderTest {

    @Test
    public void create() throws Exception {
        ReaderI18nServiceBuilder builder = new ReaderI18nServiceBuilder();
        IReaderI18nService service = builder
                .addPropertyFile(FRENCH, "test.properties")
                .build();

        assertNotNull(service);

        List<ResourceBundle> bundles = builder.getBundles();
        assertEquals(1, bundles.size());
        ResourceBundle bundle = bundles.get(0);
        assertEquals(FRENCH, bundle.getLocale());
        assertEquals("Hello Как дела", bundle.getString("test"));

    }

}