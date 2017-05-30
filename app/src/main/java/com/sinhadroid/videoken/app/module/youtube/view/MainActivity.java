package com.sinhadroid.videoken.app.module.youtube.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayerView;
import com.sinhadroid.videoken.app.R;
import com.sinhadroid.videoken.app.module.offline.VideoKenDataHandler;
import com.sinhadroid.videoken.app.module.playback.OnRecordListener;
import com.sinhadroid.videoken.app.module.recorder.view.RecordFragment;
import com.sinhadroid.videoken.app.module.youtube.presenter.IMainPresenter;
import com.sinhadroid.videoken.app.module.youtube.presenter.MainPresenterImpl;

public class MainActivity extends YouTubeBaseActivity implements IMainView, OnRecordListener {

    private static final int RECOVERY_REQUEST = 1;

    private YouTubePlayerView mYouTubePlayerView;
    private EditText search;

    private IMainPresenter mIMainPresenter;
    private RecordFragment mRecordFragment;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);

        mYouTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        search = (EditText) findViewById(R.id.search_option);

        this.mIMainPresenter = MainPresenterImpl.newInstance(this);
        mIMainPresenter.onCreate();
    }

    @Override
    public YouTubePlayerView getYouTubePlayerView() {
        return mYouTubePlayerView;
    }

    public void onClickSearch(View view) {
        String searchString = search.getText().toString();

        if (searchString.isEmpty()) {
            showToastMessage(getString(R.string.enter_url));
        } else {
            VideoKenDataHandler.getInstance().saveId(searchString);
            mIMainPresenter.setYouTubeUrl();
            updateAdapter();
        }
    }

    @Override
    public void loadFragment(RecordFragment recordFragment) {
        this.mRecordFragment = recordFragment;

        getFragmentManager().beginTransaction()
                .replace(R.id.content, recordFragment)
                .commit();
    }

    @Override
    public void getErrorDialog(YouTubeInitializationResult youTubeInitializationResult) {
        youTubeInitializationResult.getErrorDialog(this, RECOVERY_REQUEST).show();
    }

    @Override
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFileName(String fileName) {
        mRecordFragment.setFileName(fileName);
    }

    @Override
    public void showVoice() {
        mRecordFragment.showVoice();
    }

    @Override
    public void hideVoice() {
        mRecordFragment.hideVoice();
    }

    @Override
    public void updateAdapter() {
        mRecordFragment.updateAdapter();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            mIMainPresenter.initializeYouTubeApi();
        }
    }

    @Override
    public void onRecordListener() {
        mIMainPresenter.onResume();
    }
}
