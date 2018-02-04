package com.payneteasy.reader.i18n;

import com.payneteasy.android.sdk.reader.CardReaderEvent;
import com.payneteasy.android.sdk.reader.CardReaderState;
import org.junit.Test;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static java.util.Locale.ENGLISH;
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

    @Test
    public void createEn() throws Exception {
        ReaderI18nServiceBuilder builder = new ReaderI18nServiceBuilder();
        IReaderI18nService service = builder
                .addPropertyFile(ENGLISH, "reader_en.properties")
                .build();

        assertEquals("Connecting to terminal ...", service.translateReaderEvent(ENGLISH, CardReaderEvent.of(CardReaderState.CONNECTING)));
    }

    @Test
    public void createRu() throws Exception {
        ReaderI18nServiceBuilder builder = new ReaderI18nServiceBuilder();
        Locale ru = new Locale("ru");
        IReaderI18nService service = builder
                .addPropertyFile(ENGLISH, "reader_en.properties")
                .addPropertyFile(ru, "reader_ru.properties")
                .build();

        assertEquals("Подключение к терминалу ...", service.translateReaderEvent(ru, CardReaderEvent.of(CardReaderState.CONNECTING)));
    }


}