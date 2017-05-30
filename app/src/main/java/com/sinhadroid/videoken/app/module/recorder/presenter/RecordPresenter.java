package com.sinhadroid.videoken.app.module.recorder.presenter;

public interface RecordPresenter {
    void onCreate();

    void sendItem(String mFileName, String filePath, long mElapsedMillis);

    void updateAdapter(String id);

    void sendRecordedText(String result);
}
