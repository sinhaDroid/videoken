package com.sinhadroid.trillbit.app.module.recorder.presenter;

import android.content.Context;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.offline.RecordDataHandler;
import com.sinhadroid.trillbit.app.module.recorder.model.RecordModel;
import com.sinhadroid.trillbit.app.module.recorder.model.RecordModelImpl;
import com.sinhadroid.trillbit.app.module.recorder.model.dto.Payload;
import com.sinhadroid.trillbit.app.module.recorder.view.RecordAdapter;
import com.sinhadroid.trillbit.app.module.recorder.view.RecordView;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class RecordPresenterImpl implements RecordPresenter, RecordModelImpl.OnRecordModelListener {

    private RecordView mRecordView;

    private RecordModel mRecordModel;

    private RecordAdapter mRecordAdapter;

    private RecordPresenterImpl(RecordView recordView) {
        this.mRecordView = recordView;
        this.mRecordModel = RecordModelImpl.newInstance(this);
    }

    public static RecordPresenterImpl newInstance(RecordView recordView) {
        return new RecordPresenterImpl(recordView);
    }

    @Override
    public void onCreate() {
        Context context = mRecordView.getContext();

        if (null != context) {
            mRecordView.setAdapter(mRecordAdapter = new RecordAdapter(context));

            // Check for existing record data in local
            List<Payload> recordList = RecordDataHandler.getInstance().getRecordList();
            if (!recordList.isEmpty()) {
                addAllInAdapter(recordList);
            }
        }
    }

    @Override
    public void sendAudio(String filePath) {
        File audioFile = new File(filePath);
        RequestBody audioBody = RequestBody.create(MediaType.parse("audio/*"), audioFile);
        MultipartBody.Part aFile = MultipartBody.Part.createFormData("audio_data", audioFile.getName(), audioBody);

        mRecordModel.callAudioApi(aFile);
    }

    private void addAllInAdapter(List<Payload> recordList) {
        mRecordAdapter.clear();
        mRecordAdapter.addAll(recordList);
        mRecordAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoInternet() {
        mRecordView.hideLoading();
        mRecordView.showMessage(R.string.no_network_connection);
    }

    @Override
    public void onGettingData() {
        mRecordView.showLoading();
    }

    @Override
    public void onFailed(String message) {
        mRecordView.hideLoading();
        mRecordView.showMessage(message);
    }

    @Override
    public void onSuccess(Payload payload) {
        mRecordView.hideLoading();

        mRecordAdapter.add(payload);
        mRecordAdapter.notifyDataSetChanged();
    }
}
