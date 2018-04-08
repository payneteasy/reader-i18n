package com.payneteasy.reader.i18n.impl;

import com.payneteasy.android.sdk.message.MiuraCardStatusMessage;
import com.payneteasy.android.sdk.message.MiuraDeviceInfoMessage;
import com.payneteasy.android.sdk.message.MiuraDeviceStatusChangeMessage;
import com.payneteasy.android.sdk.reader.CardReaderEvent;
import com.payneteasy.reader.spire.commands.SpireRestart14Response;
import com.payneteasy.reader.spire.commands.SpireTransactionModeInitialisation12Response;
import com.payneteasy.reader.spire.events.*;
import com.payneteasy.reader.spire.supplementary.Spire46SupplementaryGoOnlineResponse;

public class ReaderStateTranslator {

    public String translateReaderEvent(Translator aTranslator, CardReaderEvent aEvent) {
        if(aEvent == null) {
            return "aEvent is null";
        }

        if(aEvent.getState() == null) {
            return "aEvent.getState() is null";
        }

        // do not edit. It's generated automatically by PropertyFileGenerator
        switch(aEvent.getState()) {
            case NOT_CONNECTED: return aTranslator.get("reader.not_connected");
            case CONNECTING: return aTranslator.get("reader.connecting");
            case CONNECTED: return aTranslator.get("reader.connected");
            case DECODING: return aTranslator.get("reader.decoding");
            case CHARGING: return aTranslator.get("reader.charging");
            case GETTING_SERIAL_NUMBER: return aTranslator.get("reader.getting_serial_number");
            case MIURA_DEVICE_INFO: return get_MIURA_DEVICE_INFO(aTranslator, (MiuraDeviceInfoMessage)aEvent.getMessage());
            case MIURA_CARD_STATUS: return get_MIURA_CARD_STATUS(aTranslator, (MiuraCardStatusMessage)aEvent.getMessage());
            case MIURA_DEVICE_STATUS_CHANGE: return get_MIURA_DEVICE_STATUS_CHANGE(aTranslator, (MiuraDeviceStatusChangeMessage)aEvent.getMessage());
            case INJECTING_KEYS: return aTranslator.get("reader.injecting_keys");
            case INITIALISING_ENCRYPTION: return aTranslator.get("reader.initialising_encryption");
            case CONFIGURING_READER: return aTranslator.get("reader.configuring_reader");
            case DOWNLOADING_FILE: return get_DOWNLOADING_FILE(aTranslator, (String)aEvent.getMessage());
            case SPIRE_14_RESTART_RESPONSE: {
                SpireRestart14Response message = (SpireRestart14Response) aEvent.getMessage();
                if(message == null) {
                    return aTranslator.get("reader.spire_14_restart_response.unknown");
                }
                switch(message.response) {
                    case UNKNOWN: return aTranslator.get("reader.spire_14_restart_response.unknown");
                    case Success: return aTranslator.get("reader.spire_14_restart_response.success");
                    case Failed: return aTranslator.get("reader.spire_14_restart_response.failed");
                    case RequireInformation: return aTranslator.get("reader.spire_14_restart_response.require_information");
                    case AbortOperation: return aTranslator.get("reader.spire_14_restart_response.abort_operation");
                    case RequireMacGeneration: return aTranslator.get("reader.spire_14_restart_response.require_mac_generation");
                    case RequireMacVerification: return aTranslator.get("reader.spire_14_restart_response.require_mac_verification");
                    case KeyUpdateVerificationFailed: return aTranslator.get("reader.spire_14_restart_response.key_update_verification_failed");
                }
            }
            case SPIRE_12_TRANSACTION_MODE_INITIALISATION_RESPONSE: {
                SpireTransactionModeInitialisation12Response message = (SpireTransactionModeInitialisation12Response) aEvent.getMessage();
                if(message == null) {
                    return aTranslator.get("reader.spire_12_transaction_mode_initialisation_response.unknown");
                }
                switch(message.response) {
                    case UNKNOWN: return aTranslator.get("reader.spire_12_transaction_mode_initialisation_response.unknown");
                    case Success: return aTranslator.get("reader.spire_12_transaction_mode_initialisation_response.success");
                    case Failed: return aTranslator.get("reader.spire_12_transaction_mode_initialisation_response.failed");
                    case RequireInformation: return aTranslator.get("reader.spire_12_transaction_mode_initialisation_response.require_information");
                    case AbortOperation: return aTranslator.get("reader.spire_12_transaction_mode_initialisation_response.abort_operation");
                    case RequireMacGeneration: return aTranslator.get("reader.spire_12_transaction_mode_initialisation_response.require_mac_generation");
                    case RequireMacVerification: return aTranslator.get("reader.spire_12_transaction_mode_initialisation_response.require_mac_verification");
                    case KeyUpdateVerificationFailed: return aTranslator.get("reader.spire_12_transaction_mode_initialisation_response.key_update_verification_failed");
                }
            }
            case SPIRE_42_GET_TRANSACTION_DATA_EVENT: return get_SPIRE_42_GET_TRANSACTION_DATA_EVENT(aTranslator, (SpireGetTransactionAndApplicationData42Event)aEvent.getMessage());
            case SPIRE_46_GO_ONLINE_EVENT: {
                SpireGoOnline46Event message = (SpireGoOnline46Event) aEvent.getMessage();
                if(message == null) {
                    return aTranslator.get("reader.spire_46_go_online_event.unknown");
                }
                switch(message.adviceRequired) {
                    case UNKNOWN: return aTranslator.get("reader.spire_46_go_online_event.unknown");
                    case NO_ADVICE_REQUIRED: return aTranslator.get("reader.spire_46_go_online_event.no_advice_required");
                    case ADVICE_REQUIRED: return aTranslator.get("reader.spire_46_go_online_event.advice_required");
                }
            }
            case SPIRE_46_GO_ONLINE_RESPONSE: return get_SPIRE_46_GO_ONLINE_RESPONSE(aTranslator, (Spire46SupplementaryGoOnlineResponse)aEvent.getMessage());
            case SPIRE_47_COMPLETE_TRANSACTION_EVENT: return get_SPIRE_47_COMPLETE_TRANSACTION_EVENT(aTranslator, (SpireCompleteTransaction47Event)aEvent.getMessage());
            case SPIRE_48_TERMINATE_TRANSACTION_EVENT: {
                SpireTerminateTransaction48Event message = (SpireTerminateTransaction48Event) aEvent.getMessage();
                if(message == null) {
                    return aTranslator.get("reader.spire_48_terminate_transaction_event.unknown");
                }
                switch(message.reason) {
                    case UNKNOWN: return aTranslator.get("reader.spire_48_terminate_transaction_event.unknown");
                    case _0_MPOS_GENERAL_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._0_mpos_general_failure");
                    case _1_CHIP_APPLICATION_SELECTION_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._1_chip_application_selection_failure");
                    case _2_CHIP_INITIATE_APPLICATION_PROCESSING_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._2_chip_initiate_application_processing_failure");
                    case _3_CHIP_READ_APPLICATION_DATA_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._3_chip_read_application_data_failure");
                    case _4_CHIP_OFFLINE_DATA_AUTHENTICATION_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._4_chip_offline_data_authentication_failure");
                    case _5_CHIP_PROCESS_RESTRICTIONS_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._5_chip_process_restrictions_failure");
                    case _6_CHIP_TERMINAL_RISK_MANAGEMENT_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._6_chip_terminal_risk_management_failure");
                    case _7_CHIP_CARDHOLDER_VERIFICATION_METHOD_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._7_chip_cardholder_verification_method_failure");
                    case _8_CHIP_TERMINAL_ACTION_ANALYSIS_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._8_chip_terminal_action_analysis_failure");
                    case _9_CHIP_CARD_ACTION_ANALYSIS_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._9_chip_card_action_analysis_failure");
                    case _10_CHIP_COMPLETION_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._10_chip_completion_failure");
                    case _11_EPOS_TRANSACTION_TERMINATED: return aTranslator.get("reader.spire_48_terminate_transaction_event._11_epos_transaction_terminated");
                    case _12_CHIP_NO_ANSWER_TO_RESET: return aTranslator.get("reader.spire_48_terminate_transaction_event._12_chip_no_answer_to_reset");
                    case _13_SWIPE_READ_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._13_swipe_read_failure");
                    case _14_CHIP_CARD_REMOVED: return aTranslator.get("reader.spire_48_terminate_transaction_event._14_chip_card_removed");
                    case _15_MPOS_USER_CANCELLED: return aTranslator.get("reader.spire_48_terminate_transaction_event._15_mpos_user_cancelled");
                    case _16_CHIP_NO_SUPPORTED_APPLICATIONS: return aTranslator.get("reader.spire_48_terminate_transaction_event._16_chip_no_supported_applications");
                    case _17_CHIP_CARD_BLOCKED: return aTranslator.get("reader.spire_48_terminate_transaction_event._17_chip_card_blocked");
                    case _18_CHIP_READ_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._18_chip_read_failure");
                    case _19_MPOS_USER_TIME_OUT: return aTranslator.get("reader.spire_48_terminate_transaction_event._19_mpos_user_time_out");
                    case _20_MPOS_DUKPT_KEY_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._20_mpos_dukpt_key_failure");
                    case _21_MPOS_MKSK_KEY_FAILURE: return aTranslator.get("reader.spire_48_terminate_transaction_event._21_mpos_mksk_key_failure");
                    case _22_CONTACTLESS_NOT_ALLOWED: return aTranslator.get("reader.spire_48_terminate_transaction_event._22_contactless_not_allowed");
                    case _23_CONTACTLESS_ABORTED: return aTranslator.get("reader.spire_48_terminate_transaction_event._23_contactless_aborted");
                    case _24_BAD_CURRENCRY: return aTranslator.get("reader.spire_48_terminate_transaction_event._24_bad_currencry");
                }
            }
            case SPIRE_49_STATUS_REPORT: {
                SpireStatusReport49Event message = (SpireStatusReport49Event) aEvent.getMessage();
                if(message == null || message.status == null) {
                    return aTranslator.get("reader.spire_49_status_report.unknown");
                }
                switch(message.status) {
                    case UNKNOWN: return aTranslator.get("reader.spire_49_status_report.unknown");
                    case CardEntryPrompted: return aTranslator.get("reader.spire_49_status_report.card_entry_prompted");
                    case SmartcardInserted: return aTranslator.get("reader.spire_49_status_report.smartcard_inserted");
                    case SmartcardRemovePrompted: return aTranslator.get("reader.spire_49_status_report.smartcard_remove_prompted");
                    case SmartcardRemoved: return aTranslator.get("reader.spire_49_status_report.smartcard_removed");
                    case CardEntryBypassed: return aTranslator.get("reader.spire_49_status_report.card_entry_bypassed");
                    case CardEntryTimedOut: return aTranslator.get("reader.spire_49_status_report.card_entry_timed_out");
                    case CardEntryAborted: return aTranslator.get("reader.spire_49_status_report.card_entry_aborted");
                    case CardSwiped: return aTranslator.get("reader.spire_49_status_report.card_swiped");
                    case CardSwipeError: return aTranslator.get("reader.spire_49_status_report.card_swipe_error");
                    case ContactlessCardTapped: return aTranslator.get("reader.spire_49_status_report.contactless_card_tapped");
                    case ContactlessCardTapError: return aTranslator.get("reader.spire_49_status_report.contactless_card_tap_error");
                    case ApplicationSelectionStarted: return aTranslator.get("reader.spire_49_status_report.application_selection_started");
                    case ApplicationSelectionCompleted: return aTranslator.get("reader.spire_49_status_report.application_selection_completed");
                    case PinEntryStarted: return aTranslator.get("reader.spire_49_status_report.pin_entry_started");
                    case PinEntryCompleted: return aTranslator.get("reader.spire_49_status_report.pin_entry_completed");
                    case PinEntryAborted: return aTranslator.get("reader.spire_49_status_report.pin_entry_aborted");
                    case PinEntryBypassed: return aTranslator.get("reader.spire_49_status_report.pin_entry_bypassed");
                    case PinEntryTimedOut: return aTranslator.get("reader.spire_49_status_report.pin_entry_timed_out");
                    case LastPinEntry: return aTranslator.get("reader.spire_49_status_report.last_pin_entry");
                    case AmountConfirmationStarted: return aTranslator.get("reader.spire_49_status_report.amount_confirmation_started");
                    case AmountConfirmationCompleted: return aTranslator.get("reader.spire_49_status_report.amount_confirmation_completed");
                    case AmountConfirmationAborted: return aTranslator.get("reader.spire_49_status_report.amount_confirmation_aborted");
                    case AmountConfirmationBypassed: return aTranslator.get("reader.spire_49_status_report.amount_confirmation_bypassed");
                    case AmountConfirmationTimedOut: return aTranslator.get("reader.spire_49_status_report.amount_confirmation_timed_out");
                    case DCCSelectionStarted: return aTranslator.get("reader.spire_49_status_report.d_c_c_selection_started");
                    case DCCCardholderCurrencySelected: return aTranslator.get("reader.spire_49_status_report.d_c_c_cardholder_currency_selected");
                    case DCCCardholderCurrencyNotSelected: return aTranslator.get("reader.spire_49_status_report.d_c_c_cardholder_currency_not_selected");
                    case DCCSelectionTimedOut: return aTranslator.get("reader.spire_49_status_report.d_c_c_selection_timed_out");
                    case GratuityEntryStarted: return aTranslator.get("reader.spire_49_status_report.gratuity_entry_started");
                    case GratuityEntered: return aTranslator.get("reader.spire_49_status_report.gratuity_entered");
                    case GratuityNotEntered: return aTranslator.get("reader.spire_49_status_report.gratuity_not_entered");
                    case GratuityEntryTimedOut: return aTranslator.get("reader.spire_49_status_report.gratuity_entry_timed_out");
                }
            }
            case SPIRE_53_SWIPED_CARD_EVENT: return get_SPIRE_53_SWIPED_CARD_EVENT(aTranslator, (SpireProcessSwipedCard_52Event)aEvent.getMessage());
            case SPIRE_53_SWIPED_TRANSACTION_EVENT: return get_SPIRE_53_SWIPED_TRANSACTION_EVENT(aTranslator, (SpireGetSwipedTransactionData53Event)aEvent.getMessage());
            case SPIRE_RESTARTING: return aTranslator.get("reader.spire_restarting");
        }

        return "unknown state " + aEvent.getState();
    }

    private String get_SPIRE_47_COMPLETE_TRANSACTION_EVENT(Translator aTranslator, SpireCompleteTransaction47Event message) {
        return aTranslator.get("reader.complete_transaction_event");
    }

    private String get_SPIRE_46_GO_ONLINE_RESPONSE(Translator aTranslator, Spire46SupplementaryGoOnlineResponse message) {
        return aTranslator.get("reader.46_go_online_response");
    }

    private String get_SPIRE_53_SWIPED_TRANSACTION_EVENT(Translator aTranslator, SpireGetSwipedTransactionData53Event message) {
        return aTranslator.get("reader.spire_53_swiped_transaction_event");
    }

    private String get_SPIRE_53_SWIPED_CARD_EVENT(Translator aTranslator, SpireProcessSwipedCard_52Event message) {
        return aTranslator.get("reader.spire_53_swiped_card_event");
    }

    private String get_SPIRE_42_GET_TRANSACTION_DATA_EVENT(Translator aTranslator, SpireGetTransactionAndApplicationData42Event message) {
        return aTranslator.get("reader.spire_42_get_transaction_data_event");
    }

    private String get_DOWNLOADING_FILE(Translator aTranslator, String aFilename) {
        return aTranslator.get("reader.downloading_file", aFilename);
    }

    private String get_MIURA_DEVICE_STATUS_CHANGE(Translator aTranslator, MiuraDeviceStatusChangeMessage message) {
        if(message == null || message.status == null) {
            return aTranslator.get("reader.miura_device_status_change.unknown");
        }
        switch (message.status) {
            case APPLICATION_SELECTION: return aTranslator.get("reader.miura_device_status_change.application_selection");
            case DEVICE_POWERED_ON: return aTranslator.get("reader.miura_device_status_change.device_powered_on");
            case DEVICE_POWERING_OFF: return aTranslator.get("reader.miura_device_status_change.device_powering_off");
            case MPI_RESTARTING: return aTranslator.get("reader.miura_device_status_change.mpi_restarting");
            case DEVICE_REBOOTING: return aTranslator.get("reader.miura_device_status_change.device_rebooting");
            case UNKNOWN: return aTranslator.get("reader.miura_device_status_change.unknown");
            case PIN_ENTRY_EVENT: {

                if(message.pinEntryStatus != null) {
                    switch (message.pinEntryStatus) {
                        case INCORRECT_PIN: return aTranslator.get("reader.miura_device_status_change.pin_entry_event.incorrect_pin");
                        case LAST_POSSIBLE_ATTEMPT: return aTranslator.get("reader.miura_device_status_change.pin_entry_event.last_possible_attempt");
                        case PIN_ENTRY_COMPLETED: return aTranslator.get("reader.miura_device_status_change.pin_entry_event.pin_entry_completed");
                        case PIN_ENTRY_ERROR: return aTranslator.get("reader.miura_device_status_change.pin_entry_event.pin_entry_error");
                        case PIN_OK: return aTranslator.get("reader.miura_device_status_change.pin_entry_event.pin_ok");
                    }
                } else {
                    return aTranslator.get("reader.miura_device_status_change.pin_entry_event.enter_pin");
                }
            }
        }
        return null;
    }

    private String get_MIURA_CARD_STATUS(Translator aTranslator, MiuraCardStatusMessage message) {
        if(message == null) {
            return null;
        }
        if(message.isCardPresent) {
            if(message.isEmvCompatable) {
                return aTranslator.get("reader.miura_device_info.chip_card_inserted");
            } else if(message.isMsrDataAvailable) {
                return aTranslator.get("reader.miura_device_info.msr_card_swiped");
            }
        }
        return null;
    }

    private String get_MIURA_DEVICE_INFO(Translator aTranslator, MiuraDeviceInfoMessage aMessage) {
        if(aMessage == null) {
            return null;
        }
        return aTranslator.get("reader.miura_device_info", aMessage.deviceId, aMessage.osVersion, aMessage.mpiVersion);
    }


}
