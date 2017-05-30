package com.sinhadroid.videoken.app.module.translation.utils;

import android.speech.SpeechRecognizer;

import java.util.ArrayList;

/**
 * Created by deepanshu on 26/5/17.
 */

public class TranslatorUtil {

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }


    /**
     * Check if input matches
     *
     * @param listOfPossibleMatches list of string  with possible match
     * @param stringToMatch         string you want to match list
     * @return true if matched else false
     */
    public static boolean findString(ArrayList<String> listOfPossibleMatches, String stringToMatch) {

        if (null != listOfPossibleMatches) {

            for (String transaltion : listOfPossibleMatches) {

                if (transaltion.contains(stringToMatch)) {

                    return true;
                }
            }
        }
        return false;
    }
}
