package com.payneteasy.reader.i18n.impl;

import com.payneteasy.android.sdk.processing.ProcessingStageEvent;
import com.payneteasy.android.sdk.reader.CardError;
import com.payneteasy.android.sdk.reader.CardReaderEvent;
import com.payneteasy.android.sdk.reader.CardReaderProblem;
import com.payneteasy.paynet.processing.response.StatusResponse;
import com.payneteasy.reader.i18n.IReaderI18nService;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class ReaderI18nServiceImpl implements IReaderI18nService {

    private final ReaderStateTranslator       readerStateTranslator;
    private final Map<Locale, ResourceBundle> bundles;

    public ReaderI18nServiceImpl(Map<Locale, ResourceBundle> aBundles) {
        bundles = aBundles;
        readerStateTranslator = new ReaderStateTranslator();
    }

    @Override
    public String translateReaderEvent(Locale aLocale, CardReaderEvent aEvent) {
        return readerStateTranslator.translateReaderEvent(createTranslator(aLocale), aEvent);
    }

    @Override
    public String translateCardError(Locale aLocale, CardError aCardError) {
        Translator translator = createTranslator(aLocale);
        if(aCardError == null || aCardError.getType() == null) {
            return translator.get("error.unknown");
        }
        switch(aCardError.getType()) {
            case PARSE_PACKET_ERROR:                        return translator.get("error.parse_packet_error");
            case PARSE_TRACK_ERROR:                         return translator.get("error.parse_track_error");
            case EMPTY_CARD_INFO:                           return translator.get("error.empty_card_info");
            case UNKNOWN_CARD_TYPE:                         return translator.get("error.unknown_card_type");
            case CARD_NOT_VALID:                            return translator.get("error.card_not_valid");
            case USER_CANCELLED:                            return translator.get("error.user_cancelled");
            case SKIP:                                      return translator.get("error.skip");
            case INVALID_DATA_IN_COMMAND_APDU:              return translator.get("error.invalid_data_in_command_apdu");
            case TERMINAL_NOR_READY:                        return translator.get("error.terminal_nor_ready");
            case NO_SMART_CARD_IN_SLOT:                     return translator.get("error.no_smart_card_in_slot");
            case INVALID_CARD:                              return translator.get("error.invalid_card");
            case INVALID_CARD_NO_MSR_FALLBACK_ALLOWED:      return translator.get("error.invalid_card_no_msr_fallback_allowed");
            case TRANSACTION_ALREADY_IN_PROGRESS:           return translator.get("error.transaction_already_in_progress");
            case DATA_MISSING_FROM_COMMAND_APDU:            return translator.get("error.data_missing_from_command_apdu");
            case UNSUPPORTED_CARD:                          return translator.get("error.unsupported_card");
            case MISSING_FILE:                              return translator.get("error.missing_file");
            case ICC_READ_ERROR:                            return translator.get("error.icc_read_error");
            case INVALID_ISSUE_PUBLIC_KEY:                  return translator.get("error.invalid_issue_public_key");
            case CONFIGURATION_ERROR:                       return translator.get("error.configuration_error");
            case ENCRYPTION_INITIALISE_ERROR:               return translator.get("error.encryption_initialise_error");
            case CONTACTLESS_TRANSACTION_TIMED_OUT:         return translator.get("error.contactless_transaction_timed_out");
            case CONTACTLESS_NO_CONDITIONS_USE_ICEE_OR_MSR: return translator.get("error.contactless_no_conditions_use_icee_or_msr");
            case CONTACTLESS_NOT_POSSIBLE_USE_ICEE_OR_MSR:  return translator.get("error.contactless_not_possible_use_icee_or_msr");
            case CONTACTLESS_ASK_CHIP_USE_ICC:              return translator.get("error.contactless_ask_chip_use_icc");
            case CONTACTLESS_HARDWARE_ERROR:                return translator.get("error.contactless_hardware_error");
            case CONTACTLESS_REQUIRED_EMV_FINAL_ADVICE:     return translator.get("error.contactless_required_emv_final_advice");
            case UNKNOWN:                                   return translator.get("error.unknown");
        }
        return aCardError + "";
    }

    private Translator createTranslator(Locale aLocale) {
        return new Translator(aLocale, bundles);
    }

    @Override
    public String translateCardReaderProblem(Locale aLocale, CardReaderProblem aCardReaderProblem) {
        if(aCardReaderProblem == null) {
            return "aCardReaderProblem is null";
        }
        Translator translator = createTranslator(aLocale);
        switch(aCardReaderProblem) {
            case BLUETOOTH_ERROR_CONNECTING:    return translator.get("problem.bluetooth_error_connecting");
            case BLUETOOTH_ADAPTER_NOT_FOUND:   return translator.get("problem.bluetooth_adapter_not_found");
            case BLUETOOTH_NO_PERMISSIONS:      return translator.get("problem.bluetooth_no_permissions");
            case BLUETOOTH_DISABLED:            return translator.get("problem.bluetooth_disabled");
            case BLUETOOTH_READER_NOT_PAIRED:   return translator.get("problem.bluetooth_reader_not_paired");
            case BLUETOOTH_PAIRING_REQUEST:     return translator.get("problem.bluetooth_pairing_request");
            case NETWORK_ERROR_CONNECTING:      return translator.get("problem.network_error_connecting");
            case AUDIO_AUDIORECORD_INIT_ERROR:  return translator.get("problem.audio_audiorecord_init_error");
            case AUDIO_AUDIORECORD_NOT_READY:   return translator.get("problem.audio_audiorecord_not_ready");
            case AUDIO_POWER_INIT_ERROR:        return translator.get("problem.audio_power_init_error");
            case AUDIO_READER_NOT_SUPPORTED:    return translator.get("problem.audio_reader_not_supported");
            case USB_CONNECTION_ERROR:          return translator.get("problem.usb_reader_connection_error");
            case USB_DEVICE_NOT_FOUND:          return translator.get("problem.usb_reader_device_not_found");
            case USB_ENDOINTS_NOT_FOUND:        return translator.get("problem.usb_reader_endpoints_not_found");
            case USB_DEVICE_PERMISSION_DENIED:  return translator.get("problem.usb_reader_permission_denied");

        }
        return aCardReaderProblem + "";
    }

    @Override
    public String translateProcessingEvent(Locale aLocale, ProcessingStageEvent aEvent) {
        if(aEvent == null || aEvent.type == null) {
            return null;
        }

        Translator translator = createTranslator(aLocale);
        switch (aEvent.type) {
            case RESULT:                  return translateProcessingResult(translator, aEvent.response);
            case EXCEPTION:               return translator.get("processing.event.exception", aEvent.exception);
            case SALE_SENDING:            return translator.get("processing.event.sale_sending");
            case ADVICE_SENDING:          return translator.get("processing.event.advice_sending");
            case ADVICE_REQUIRED:         return translator.get("processing.event.advice_required");
            case ERROR_3D_SECURE:         return translator.get("processing.event.error_3d_secure");
            case SALE_RESPONSE_WAITING:   return translator.get("processing.event.sale_response_waiting");
            case ADVICE_RESPONSE_WAITING: return translator.get("processing.event.advice_response_waiting");
            default:                      return translator.get("processing.event.default", aEvent.type);
        }

    }

    private String translateProcessingResult(Translator aTranslator, StatusResponse aResponse) {
        if(aResponse == null || aResponse.getStatus() == null) {
            return aTranslator.get("processing.result.null_response");
        }
        
        switch (aResponse.getStatus()) {
            case APPROVED:    return aTranslator.get("processing.result.approved");
            case ERROR:       return aTranslator.get("processing.result.error"  , aResponse.getErrorCode(), aResponse.getErrorMessage());
            case UNKNOWN:     return aTranslator.get("processing.result.unknown", aResponse.getErrorCode(), aResponse.getErrorMessage());
            case DECLINED:    return aTranslator.get("processing.result.declined", aResponse.getErrorCode(), aResponse.getErrorMessage());
            case FILTERED:    return aTranslator.get("processing.result.filtered", aResponse.getErrorCode(), aResponse.getErrorMessage());
            case PROCESSING:  return aTranslator.get("processing.result.processing");
            default:          return aTranslator.get("processing.result.default", aResponse.getStatus());
        }
    }
}
