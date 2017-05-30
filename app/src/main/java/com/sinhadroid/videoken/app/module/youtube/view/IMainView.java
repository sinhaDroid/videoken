package com.sinhadroid.videoken.app.module.youtube.view;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerView;
import com.sinhadroid.videoken.app.module.recorder.view.RecordFragment;

/**
 * Created by deepanshu on 29/5/17.
 */

public interface IMainView {
    YouTubePlayerView getYouTubePlayerView();

    void loadFragment(RecordFragment recordFragment);

    void getErrorDialog(YouTubeInitializationResult youTubeInitializationResult);

    void showToastMessage(String message);

    void setFileName(String time);

    void showVoice();

    void hideVoice();

    void updateAdapter(String id);
}
