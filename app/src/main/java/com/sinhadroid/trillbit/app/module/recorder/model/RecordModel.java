package com.sinhadroid.trillbit.app.module.recorder.model;

import okhttp3.MultipartBody;

/**
 * Created by deepanshu on 19/5/17.
 */

public interface RecordModel {
    void callAudioApi(MultipartBody.Part part);
}
