package com.sinhadroid.videoken.app.module.translation.utils;

/**
 * Created by deepanshu on 26/5/17.
 */

public interface ConversionCallaback {

    void onSuccess(String result);

    void onCompletion();

    void onErrorOccured(String errorMessage);
}
