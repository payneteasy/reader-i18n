# Translation for reader SDK

## How to use this library

### Add dependency

Add repository

```
repositories {
    maven { url "http://paynet-qa.clubber.me/reader/maven" }
}
```

Add artifact
```
compile 'com.payneteasy:reader-i18n:1.0-13'
```

### Add code

Creates IReaderI18nService by using ReaderI18nServiceBuilder
```java

IReaderI18nService translationService = new ReaderI18nServiceBuilder()
    .addPropertyFile(Locale.ENGLISH, "reader_en.properties")
    .addPropertyFile(new Locale("ru"), "reader_ru.properties")
    .build();
```

Then you can use translation service for events:

```java
@Override
public void cardReaderStateChanged(CardReaderEvent cardReaderEvent) {
    setStatus("cardReaderStateChanged: %s", translationService.translateReaderEvent(defaultLocale, cardReaderEvent));
}

@Override
public void onCardError(CardError cardError) {
    setStatus("onCardError: %s", translationService.translateCardError(defaultLocale, cardError));
}

@Override
public void onReaderNotSupported(CardReaderProblem aProblem) {
    setStatus("onReaderNotSupported: %s", translationService.translateCardReaderProblem(defaultLocale, aProblem));
}

@Override
public ProcessingContinuation onCard(BankCard bankCard) {

    setStatus("onCard: %s", bankCard);

    return ProcessingContinuation.Builder
            .startSaleOnline()
            .processingBaseUrl  ( Config.SERVER_BASE_URL)
            .merchantLogin      ( Config.MERCHANT_LOGIN )
            .merchantControlKey ( Config.MERCHANT_KEY   )
            .merchantEndPointId ( Config.END_POINT_ID   )
            .orderDescription   ( "test description"    )
            .orderInvoiceNumber ( "invoice-"+System.currentTimeMillis())
            .orderMerchantData  ( "custom merchant data for internal use")
            .customerPhone      ( "+7 (499) 918-64-41"  )
            .customerEmail      ( "info@payneteasy.com" )
            .customerCountry    ( "RUS"                 )
            .listener(new IProcessingStageListener() {
                @Override
                public void onStageChanged(ProcessingStageEvent aEvent) {
                    String message = translationService.translateProcessingEvent(defaultLocale, aEvent);
                    setStatus(message);
                }
            })
            .build();

}

```

See full example at [SimpleCardReaderPresenter.java](https://github.com/payneteasy/reader-example-gradle/blob/06f8a706438db254249311be759ea1b868b2e194/app/src/main/java/com/payneteasy/example/SimpleCardReaderPresenter.java#L33)

