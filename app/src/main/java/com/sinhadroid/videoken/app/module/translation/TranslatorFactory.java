package com.sinhadroid.videoken.app.module.translation;

import com.sinhadroid.videoken.app.module.translation.translators.IConvertor;
import com.sinhadroid.videoken.app.module.translation.translators.SpeechToTextConvertor;
import com.sinhadroid.videoken.app.module.translation.utils.ConversionCallaback;

/**
 * Created by deepanshu on 26/5/17.
 */

public class TranslatorFactory {


    private static TranslatorFactory ourInstance = new TranslatorFactory();

    public enum TRANSLATOR_TYPE {SPEECH_TO_TEXT}

    private TranslatorFactory() {
    }

    public static TranslatorFactory getInstance() {
        return ourInstance;
    }

    /**
     * Factory method to return object of Speech To Text
     *
     * @param translator_type
     * @param conversionCallaback
     * @return
     */
    public IConvertor getTranslator(TRANSLATOR_TYPE translator_type, ConversionCallaback conversionCallaback) {
        switch (translator_type) {

            case SPEECH_TO_TEXT:

                //Get speech to text translator
                return new SpeechToTextConvertor(conversionCallaback);
        }

        return null;
    }
}
