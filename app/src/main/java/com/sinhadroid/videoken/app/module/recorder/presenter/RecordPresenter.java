package com.sinhadroid.videoken.app.module.recorder.presenter;

public interface RecordPresenter {
    void onCreate();

    void sendItem(String mFileName, String filePath, long mElapsedMillis);

    void updateAdapter();

    void sendRecordedText(String result);
}
