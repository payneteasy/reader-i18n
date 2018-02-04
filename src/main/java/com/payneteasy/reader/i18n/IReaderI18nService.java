package com.payneteasy.reader.i18n;

import com.payneteasy.android.sdk.processing.ProcessingStageEvent;
import com.payneteasy.android.sdk.reader.CardError;
import com.payneteasy.android.sdk.reader.CardReaderEvent;
import com.payneteasy.android.sdk.reader.CardReaderProblem;

import java.util.Locale;

public interface IReaderI18nService {

     String translateReaderEvent(Locale aLocale, CardReaderEvent aEvent);

     String translateCardError(Locale aLocale, CardError aCardError);

     String translateCardReaderProblem(Locale aLocale, CardReaderProblem aProblem);

     String translateProcessingEvent(Locale aLocale, ProcessingStageEvent aEvent);

}
