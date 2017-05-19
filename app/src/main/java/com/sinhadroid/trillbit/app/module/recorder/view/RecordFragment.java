package com.sinhadroid.trillbit.app.module.recorder.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.melnykov.fab.FloatingActionButton;
import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitFragment;
import com.sinhadroid.trillbit.app.module.offline.RecordDataHandler;
import com.sinhadroid.trillbit.app.module.recorder.presenter.RecordPresenter;
import com.sinhadroid.trillbit.app.module.recorder.presenter.RecordPresenterImpl;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;

public class RecordFragment extends TrillbitFragment implements RecordView {

    @BindView(R.id.record_rcv)
    RecyclerView mRecyclerView;

    @BindView(R.id.btnRecordFab)
    FloatingActionButton fab;

    private RecordPresenter mRecordPresenter;

    private static final String LOG_TAG = "AudioRecordTest";
    private static final int REQUEST_RECORD_AUDIO_PERMISSION = 200;
    private String mFilePath = null;

    private MediaRecorder mRecorder = null;

    private MediaPlayer mPlayer = null;

    // Requesting permission to RECORD_AUDIO
    private boolean permissionToRecordAccepted = false;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};

    boolean mStartRecording = true;

    public static RecordFragment newInstance() {
        return new RecordFragment();
    }

    @Override
    protected int getInflater() {
        return R.layout.fragment_record;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(llm);

        this.mRecordPresenter = RecordPresenterImpl.newInstance(this);
        mRecordPresenter.onCreate();
    }

    @Override
    public void setAdapter(RecordAdapter recordAdapter) {
        mRecyclerView.setAdapter(recordAdapter);
    }

    @OnClick(R.id.btnRecordFab)
    public void onClickRecord(View view) {

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION);
        } else {

            onRecord(mStartRecording);
            if (mStartRecording) {
                fab.setImageResource(R.drawable.ic_media_stop);
            } else {
                fab.setImageResource(R.drawable.ic_mic_white_36dp);
            }
            mStartRecording = !mStartRecording;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_RECORD_AUDIO_PERMISSION:
                permissionToRecordAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordAccepted) getActivity().finish();
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        showMessage(getString(R.string.toast_recording_start));
        setFileNameAndPath();

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setOutputFile(mFilePath);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        mRecorder.setAudioChannels(1);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        showMessage(getString(R.string.toast_recording_finish));
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;

        mRecordPresenter.sendAudio(mFilePath);
    }

    public void setFileNameAndPath() {
        int count = 0;
        File f;

        do {
            count++;

            String mFileName = getString(R.string.default_file_name)
                    + " #" + (RecordDataHandler.getInstance().getNoOfCount() + count) + ".mp4";
            mFilePath = getActivity().getExternalCacheDir().getAbsolutePath();
            mFilePath += "/" + mFileName;

            f = new File(mFilePath);
            RecordDataHandler.getInstance().saveNoOfCount(count);
        } while (f.exists() && !f.isDirectory());
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }

        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
        }
    }
}
