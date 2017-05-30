package com.sinhadroid.videoken.app.module.youtube.presenter;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.sinhadroid.videoken.app.Config;
import com.sinhadroid.videoken.app.module.offline.VideoKenDataHandler;
import com.sinhadroid.videoken.app.module.recorder.view.RecordFragment;
import com.sinhadroid.videoken.app.module.youtube.view.IMainView;

import java.util.concurrent.TimeUnit;

/**
 * Created by deepanshu on 29/5/17.
 */

public class MainPresenterImpl implements IMainPresenter, YouTubePlayer.OnInitializedListener,
        YouTubePlayer.PlaybackEventListener, YouTubePlayer.PlayerStateChangeListener {

    private IMainView mIMainView;

    private YouTubePlayer mYouTubePlayer;

    private String playedId = "fhWaJi1Hsfo";

    private boolean b;
    private int itemDuration;

    private MainPresenterImpl(IMainView view) {
        this.mIMainView = view;
    }

    public static MainPresenterImpl newInstance(IMainView view) {
        return new MainPresenterImpl(view);
    }

    @Override
    public void onCreate() {

        initializeYouTubeApi();

        mIMainView.loadFragment(RecordFragment.newInstance());
    }

    @Override
    public void initializeYouTubeApi() {
        mIMainView.getYouTubePlayerView().initialize(Config.YOU_TUBE_API_KEY, this);
    }

    @Override
    public void setYouTubeUrl() {

        cueVideo();
    }

    @Override
    public void onResume() {
        mYouTubePlayer.seekToMillis(itemDuration);
        mYouTubePlayer.play();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        this.mYouTubePlayer = youTubePlayer;

        mYouTubePlayer.setPlaybackEventListener(this);

        mYouTubePlayer.setPlayerStateChangeListener(this);

        this.b = b;

        cueVideo();
    }

    private void cueVideo() {
        playedId = VideoKenDataHandler.getInstance().getId();

        if (!b) {
            mYouTubePlayer.cueVideo(playedId);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            mIMainView.getErrorDialog(youTubeInitializationResult);
        } else {
            mIMainView.showToastMessage("Error initializing YouTube player: " + youTubeInitializationResult.toString());
        }
    }

    @Override
    public void onPlaying() {
        mIMainView.hideVoice();
    }

    @Override
    public void onPaused() {
        itemDuration = mYouTubePlayer.getCurrentTimeMillis();

        long minutes = TimeUnit.MILLISECONDS.toMinutes(itemDuration);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(itemDuration)
                - TimeUnit.MINUTES.toSeconds(minutes);

        String time = String.format("%02d:%02d", minutes, seconds);

        mIMainView.setFileName(playedId + "@" + time);
        mIMainView.showVoice();
    }

    @Override
    public void onStopped() {
        mIMainView.hideVoice();
    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onLoaded(String s) {
        VideoKenDataHandler.getInstance().saveYouTubeVideoId(s);
        mIMainView.updateAdapter();
    }

    @Override
    public void onAdStarted() {

    }

    @Override
    public void onVideoStarted() {

    }

    @Override
    public void onVideoEnded() {

    }

    @Override
    public void onError(YouTubePlayer.ErrorReason errorReason) {
        mIMainView.showToastMessage(errorReason.name());
    }
}
