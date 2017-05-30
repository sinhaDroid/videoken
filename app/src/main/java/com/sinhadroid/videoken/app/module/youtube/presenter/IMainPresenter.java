package com.sinhadroid.videoken.app.module.youtube.presenter;

/**
 * Created by deepanshu on 29/5/17.
 */

public interface IMainPresenter {
    void onCreate();

    void initializeYouTubeApi();

    void setYouTubeUrl(String searchString);

    void onResume();
}
