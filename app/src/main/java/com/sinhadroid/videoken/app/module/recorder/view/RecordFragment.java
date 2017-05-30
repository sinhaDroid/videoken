package com.sinhadroid.videoken.app.module.recorder.view;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sinhadroid.videoken.app.R;
import com.sinhadroid.videoken.app.module.common.VideoKenFragment;
import com.sinhadroid.videoken.app.module.recorder.presenter.RecordPresenter;
import com.sinhadroid.videoken.app.module.recorder.presenter.RecordPresenterImpl;
import com.sinhadroid.videoken.app.module.translation.TranslatorFactory;
import com.sinhadroid.videoken.app.module.translation.utils.ConversionCallaback;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import static android.app.Activity.RESULT_OK;

public class RecordFragment extends VideoKenFragment implements RecordView, ConversionCallaback {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
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
    private String mFileName;
    private long mStartingTimeMillis = 0;

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
    public void onClickRecord() {

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
        Toast.makeText(getActivity(), getString(R.string.toast_recording_start), Toast.LENGTH_SHORT).show();
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
        startConverter();
        /*new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2000);*/
        mStartingTimeMillis = System.currentTimeMillis();
    }

    private void stopRecording() {
        Toast.makeText(getActivity(), getString(R.string.toast_recording_finish), Toast.LENGTH_SHORT).show();
        mRecorder.stop();
        mRecorder.release();
        long mElapsedMillis = (System.currentTimeMillis() - mStartingTimeMillis);
        mRecorder = null;

        mRecordPresenter.sendItem(mFileName, mFilePath, mElapsedMillis);
    }

    public void setFileNameAndPath() {
        File f;
        int count = 0;

        do {

//            String mFileName = "file2" + " #" + ".mp4";
            mFilePath = getActivity().getExternalCacheDir().getAbsolutePath();

            if (count > 0) {
                mFilePath += "/" + mFileName + count + ".mp4";
            } else {
                mFilePath += "/" + mFileName + ".mp4";
            }

            f = new File(mFilePath);
            count++;
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

    public void setFileName(String fileName) {
        this.mFileName = fileName;
    }

    public void showVoice() {
        fab.setVisibility(View.VISIBLE);
    }

    public void hideVoice() {
        fab.setVisibility(View.GONE);
    }

    public void updateAdapter(String id) {
        mRecordPresenter.updateAdapter(id);
    }

    @Override
    public void onSuccess(String result) {
        mRecordPresenter.sendRecordedText(result);
        Toast.makeText(getActivity(), result, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCompletion() {

    }

    @Override
    public void onErrorOccured(String errorMessage) {
        Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void startConverter() {
        /*Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException ignored) {

        }*/
        TranslatorFactory.getInstance()
                .getTranslator(TranslatorFactory.TRANSLATOR_TYPE.SPEECH_TO_TEXT, this)
                .initialize("Hello There", getActivity());
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    mRecordPresenter.sendRecordedText(result.get(0));
                }
                break;
            }

        }
    }*/
}
