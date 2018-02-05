# Translation for reader SDK

## How to use this library


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
```

See full example at [SimpleCardReaderPresenter.java](https://github.com/payneteasy/reader-example-gradle/blob/06f8a706438db254249311be759ea1b868b2e194/app/src/main/java/com/payneteasy/example/SimpleCardReaderPresenter.java#L33)

