package com.sinhadroid.videoken.app.module.translation.translators;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;

import com.sinhadroid.videoken.app.module.translation.utils.ConversionCallaback;
import com.sinhadroid.videoken.app.module.translation.utils.TranslatorUtil;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by deepanshu on 26/5/17.
 */

public class SpeechToTextConvertor implements IConvertor {

    public SpeechToTextConvertor(ConversionCallaback conversioCallaBack) {
        this.conversionCallaback = conversioCallaBack;
    }

    private ConversionCallaback conversionCallaback;

    /**
     * Take speech input and convert it back as text
     */
    @Override
    public SpeechToTextConvertor initialize(String message, Context appContext) {

        //Prepeare Intent
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, message);
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "com.sinhadroid.videoken.app");

        //Add listeners
        CustomRecognitionListener listener = new CustomRecognitionListener();
        SpeechRecognizer sr = SpeechRecognizer.createSpeechRecognizer(appContext);
        sr.setRecognitionListener(listener);

        sr.startListening(intent);

        return this;
    }


    private class CustomRecognitionListener implements RecognitionListener {
        private static final String TAG = "RecognitionListener";

        public void onReadyForSpeech(Bundle params) {
            Log.d(TAG, "onReadyForSpeech");
        }

        public void onBeginningOfSpeech() {
            Log.d(TAG, "onBeginningOfSpeech");
        }

        public void onRmsChanged(float rmsdB) {
            Log.d(TAG, "onRmsChanged");
        }

        public void onBufferReceived(byte[] buffer) {
            Log.d(TAG, "onBufferReceived");
        }

        public void onEndOfSpeech() {
            Log.d(TAG, "onEndofSpeech");
        }

        public void onError(int error) {
            Log.e(TAG, "error " + error);

            conversionCallaback.onErrorOccured(TranslatorUtil.getErrorText(error));
        }

        public void onResults(Bundle results) {
            String str = "";
            Log.d(TAG, "onResults " + results);
            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);

            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    Log.d(TAG, "result " + data.get(i));
                    str += data.get(i) + "\n";
                }
            }

            conversionCallaback.onSuccess(str);
        }

        public void onPartialResults(Bundle partialResults) {
            Log.d(TAG, "onPartialResults");
        }

        public void onEvent(int eventType, Bundle params) {
            Log.d(TAG, "onEvent " + eventType);
        }
    }
}
