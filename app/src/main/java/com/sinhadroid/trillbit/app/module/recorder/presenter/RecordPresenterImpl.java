package com.sinhadroid.trillbit.app.module.recorder.presenter;

import android.content.Context;

import com.sinhadroid.trillbit.app.module.offline.RecordDataHandler;
import com.sinhadroid.trillbit.app.module.recorder.model.RecordModel;
import com.sinhadroid.trillbit.app.module.recorder.model.RecordModelImpl;
import com.sinhadroid.trillbit.app.module.recorder.model.dto.response.Payload;
import com.sinhadroid.trillbit.app.module.recorder.view.RecordAdapter;
import com.sinhadroid.trillbit.app.module.recorder.view.RecordView;

import java.util.List;

public class RecordPresenterImpl implements RecordPresenter {

    private RecordView mRecordView;

    private RecordModel mRecordModel;

    private RecordAdapter mRecordAdapter;

    private RecordPresenterImpl(RecordView recordView) {
        this.mRecordView = recordView;
        this.mRecordModel = RecordModelImpl.newInstance();
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

    private void addAllInAdapter(List<Payload> recordList) {
        mRecordAdapter.clear();
        mRecordAdapter.addAll(recordList);
        mRecordAdapter.notifyDataSetChanged();
    }
}
