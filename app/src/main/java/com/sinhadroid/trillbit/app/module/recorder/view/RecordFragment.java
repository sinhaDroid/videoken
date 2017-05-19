package com.sinhadroid.trillbit.app.module.recorder.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;
import com.sinhadroid.trillbit.app.Constants;
import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitFragment;
import com.sinhadroid.trillbit.app.module.recorder.presenter.RecordPresenter;
import com.sinhadroid.trillbit.app.module.recorder.presenter.RecordPresenterImpl;

import butterknife.BindView;

public class RecordFragment extends TrillbitFragment implements RecordView {

    //Recording controls
    private FloatingActionButton mRecordButton = null;
    private Button mPauseButton = null;

    private TextView mRecordingPrompt;
    private int mRecordPromptCount = 0;

    private boolean mStartRecording = true;
    private boolean mPauseRecording = true;

    private Chronometer mChronometer = null;
    long timeWhenPaused = 0; //stores time when user clicks pause button

    @BindView(R.id.record_rcv)
    RecyclerView mRecyclerView;

    private RecordPresenter mRecordPresenter;

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
}
