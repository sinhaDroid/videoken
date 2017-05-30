package com.sinhadroid.videoken.app.module.recorder.presenter;

import android.content.Context;

import com.sinhadroid.videoken.app.module.offline.VideoKenDataHandler;
import com.sinhadroid.videoken.app.module.recorder.model.VideoKen;
import com.sinhadroid.videoken.app.module.recorder.view.RecordAdapter;
import com.sinhadroid.videoken.app.module.recorder.view.RecordView;

import java.util.List;

public class RecordPresenterImpl implements RecordPresenter {

    private RecordView mRecordView;

    private RecordAdapter mRecordAdapter;
    private String result;

    private RecordPresenterImpl(RecordView recordView) {
        this.mRecordView = recordView;
    }

    public static RecordPresenterImpl newInstance(RecordView recordView) {
        return new RecordPresenterImpl(recordView);
    }

    @Override
    public void onCreate() {
        Context context = mRecordView.getContext();

        if (null != context) {
            mRecordView.setAdapter(mRecordAdapter = new RecordAdapter(context));

            updateAdapter(VideoKenDataHandler.getInstance().getYouTubeCommentMap().get(VideoKenDataHandler.getInstance().getId()));
        }
    }

    @Override
    public void sendItem(String mFileName, String filePath, long mElapsedMillis) {
        VideoKen videoKen = new VideoKen();
        videoKen.setName(mFileName);
        videoKen.setFilePath(filePath);
        videoKen.setLength(mElapsedMillis);
        if (null != result) {
            videoKen.setRecordedText(result);
        }

        VideoKenDataHandler.getInstance().saveYouTubeComment(VideoKenDataHandler.getInstance().getId(), videoKen);
        mRecordAdapter.add(videoKen, 0);
    }

    @Override
    public void updateAdapter() {
        updateAdapter(VideoKenDataHandler.getInstance().getYouTubeCommentMap().get(VideoKenDataHandler.getInstance().getId()));
    }

    @Override
    public void sendRecordedText(String result) {
        this.result = result;
    }

    private void updateAdapter(List<VideoKen> videoKens) {

        if (null != videoKens && videoKens.size() > 0) {
            mRecordAdapter.clear();
            mRecordAdapter.addAll(videoKens);
            mRecordAdapter.notifyDataSetChanged();
        }
    }
}
