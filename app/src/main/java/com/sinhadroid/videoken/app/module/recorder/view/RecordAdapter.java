package com.sinhadroid.videoken.app.module.recorder.view;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinhadroid.videoken.app.R;
import com.sinhadroid.videoken.app.module.common.Adapter;
import com.sinhadroid.videoken.app.module.playback.PlaybackFragment;
import com.sinhadroid.videoken.app.module.recorder.model.VideoKen;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecordAdapter extends Adapter<VideoKen, RecordAdapter.MyViewHolder> {

    private final Context context;

    public RecordAdapter(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(getLayoutInflater().inflate(R.layout.inflater_record_row, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_name)
        TextView mName;

        @BindView(R.id.user_text)
        TextView mText;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(VideoKen item) {
            mName.setText(item.getName());
            mText.setText(item.getRecordedText());
        }

        @OnClick(R.id.cardview)
        void onClickList() {
            try {
                PlaybackFragment playbackFragment =
                        new PlaybackFragment().newInstance(getItem(getAdapterPosition()));

                FragmentTransaction transaction = ((Activity) context)
                        .getFragmentManager()
                        .beginTransaction();


                playbackFragment.show(transaction, "dialog_playback");
            } catch (Exception e) {
                Log.e("Adap", "exception", e);
            }
        }
    }
}
