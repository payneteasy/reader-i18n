package com.payneteasy.reader.i18n.impl;

import com.payneteasy.android.sdk.processing.ProcessingStageEvent;
import com.payneteasy.android.sdk.reader.CardError;
import com.payneteasy.android.sdk.reader.CardReaderEvent;
import com.payneteasy.android.sdk.reader.CardReaderProblem;
import com.payneteasy.reader.i18n.IReaderI18nService;

import java.util.Locale;

public class ReaderI18nServiceImpl implements IReaderI18nService {

    private final ReaderStateTranslator readerStateTranslator;

    public ReaderI18nServiceImpl() {
        readerStateTranslator = new ReaderStateTranslator();
    }

    @Override
    public String translateReaderEvent(Locale aLocale, CardReaderEvent aEvent) {
        return readerStateTranslator.translateReaderEvent(new Translator(), aEvent);
    }

    @Override
    public String translateCardError(Locale aLocale, CardError aCardError) {
        Translator aTranslator = new Translator();
        switch(aCardError.getType()) {
            case PARSE_PACKET_ERROR: return aTranslator.get("error.parse_packet_error");
            case PARSE_TRACK_ERROR: return aTranslator.get("error.parse_track_error");
            case EMPTY_CARD_INFO: return aTranslator.get("error.empty_card_info");
            case UNKNOWN_CARD_TYPE: return aTranslator.get("error.unknown_card_type");
            case CARD_NOT_VALID: return aTranslator.get("error.card_not_valid");
            case USER_CANCELLED: return aTranslator.get("error.user_cancelled");
            case SKIP: return aTranslator.get("error.skip");
            case INVALID_DATA_IN_COMMAND_APDU: return aTranslator.get("error.invalid_data_in_command_apdu");
            case TERMINAL_NOR_READY: return aTranslator.get("error.terminal_nor_ready");
            case NO_SMART_CARD_IN_SLOT: return aTranslator.get("error.no_smart_card_in_slot");
            case INVALID_CARD: return aTranslator.get("error.invalid_card");
            case INVALID_CARD_NO_MSR_FALLBACK_ALLOWED: return aTranslator.get("error.invalid_card_no_msr_fallback_allowed");
            case TRANSACTION_ALREADY_IN_PROGRESS: return aTranslator.get("error.transaction_already_in_progress");
            case DATA_MISSING_FROM_COMMAND_APDU: return aTranslator.get("error.data_missing_from_command_apdu");
            case UNSUPPORTED_CARD: return aTranslator.get("error.unsupported_card");
            case MISSING_FILE: return aTranslator.get("error.missing_file");
            case ICC_READ_ERROR: return aTranslator.get("error.icc_read_error");
            case INVALID_ISSUE_PUBLIC_KEY: return aTranslator.get("error.invalid_issue_public_key");
            case CONFIGURATION_ERROR: return aTranslator.get("error.configuration_error");
            case ENCRYPTION_INITIALISE_ERROR: return aTranslator.get("error.encryption_initialise_error");
            case CONTACTLESS_TRANSACTION_TIMED_OUT: return aTranslator.get("error.contactless_transaction_timed_out");
            case CONTACTLESS_NO_CONDITIONS_USE_ICEE_OR_MSR: return aTranslator.get("error.contactless_no_conditions_use_icee_or_msr");
            case CONTACTLESS_NOT_POSSIBLE_USE_ICEE_OR_MSR: return aTranslator.get("error.contactless_not_possible_use_icee_or_msr");
            case CONTACTLESS_ASK_CHIP_USE_ICC: return aTranslator.get("error.contactless_ask_chip_use_icc");
            case CONTACTLESS_HARDWARE_ERROR: return aTranslator.get("error.contactless_hardware_error");
            case CONTACTLESS_REQUIRED_EMV_FINAL_ADVICE: return aTranslator.get("error.contactless_required_emv_final_advice");
            case UNKNOWN: return aTranslator.get("error.unknown");
        }
        return aCardError + "";
    }

    @Override
    public String translateCardReaderProblem(Locale aLocale, CardReaderProblem aCardReaderProblem) {
        Translator translator = new Translator();
        switch(aCardReaderProblem) {
            case BLUETOOTH_ERROR_CONNECTING: return translator.get("error.bluetooth_error_connecting");
            case BLUETOOTH_ADAPTER_NOT_FOUND: return translator.get("error.bluetooth_adapter_not_found");
            case BLUETOOTH_NO_PERMISSIONS: return translator.get("error.bluetooth_no_permissions");
            case BLUETOOTH_DISABLED: return translator.get("error.bluetooth_disabled");
            case BLUETOOTH_READER_NOT_PAIRED: return translator.get("error.bluetooth_reader_not_paired");
            case BLUETOOTH_PAIRING_REQUEST: return translator.get("error.bluetooth_pairing_request");
            case NETWORK_ERROR_CONNECTING: return translator.get("error.network_error_connecting");
            case AUDIO_AUDIORECORD_INIT_ERROR: return translator.get("error.audio_audiorecord_init_error");
            case AUDIO_AUDIORECORD_NOT_READY: return translator.get("error.audio_audiorecord_not_ready");
            case AUDIO_POWER_INIT_ERROR: return translator.get("error.audio_power_init_error");
            case AUDIO_READER_NOT_SUPPORTED: return translator.get("error.audio_reader_not_supported");
        }
        return aCardReaderProblem + "";
    }

    @Override
    public String translateProcessingEvent(Locale aLocale, ProcessingStageEvent aEvent) {
        return null;
    }
}
