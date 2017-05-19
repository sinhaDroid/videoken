package com.sinhadroid.trillbit.app.module.recorder.model;

import com.sinhadroid.trillbit.app.Trillbit;
import com.sinhadroid.trillbit.app.module.offline.RecordDataHandler;
import com.sinhadroid.trillbit.app.module.recorder.model.dto.Payload;
import com.sinhadroid.trillbit.app.module.recorder.model.dto.RecordResponse;
import com.sinhadroid.trillbit.app.webservice.MyWebService;
import com.sinhadroid.trillbit.app.webservice.TrillbitCallBack;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Response;

public class RecordModelImpl implements RecordModel {

    private OnRecordModelListener mOnRecordModelListener;

    private RecordModelImpl(OnRecordModelListener listener) {
        this.mOnRecordModelListener = listener;
    }

    public static RecordModelImpl newInstance(OnRecordModelListener listener) {
        return new RecordModelImpl(listener);
    }

    @Override
    public void callAudioApi(MultipartBody.Part part) {

        mOnRecordModelListener.onGettingData();

        if (Trillbit.getInstance().hasNetworkConnection()) {
            MyWebService.getInstance().callAudioApi(part)
            .enqueue(new TrillbitCallBack<RecordResponse>() {
                @Override
                public void onResponse(Call<RecordResponse> call, Response<RecordResponse> response) {
                    super.onResponse(call, response);
                    if (response.isSuccessful()) {
                        handleAudioResponse(response.body());
                    } else {
                        mOnRecordModelListener.onFailed(response.message());
                    }
                }
            });
        } else {
            mOnRecordModelListener.onNoInternet();
        }
    }

    private void handleAudioResponse(RecordResponse response) {
        RecordDataHandler.getInstance().addRecordInList(response.getPayload());

        mOnRecordModelListener.onSuccess(response.getPayload());
    }

    public interface OnRecordModelListener {

        void onNoInternet();

        void onGettingData();

        void onFailed(String message);

        void onSuccess(Payload payload);
    }
}
