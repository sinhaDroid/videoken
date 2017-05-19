package com.sinhadroid.trillbit.app.module.recorder.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.Adapter;
import com.sinhadroid.trillbit.app.module.recorder.model.dto.Payload;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecordAdapter extends Adapter<Payload, RecordAdapter.MyViewHolder> {
    public RecordAdapter(Context context) {
        super(context);
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

        @BindView(R.id.user_id)
        TextView mId;

        @BindView(R.id.user_name)
        TextView mName;

        @BindView(R.id.file_name)
        TextView mFileName;

        @BindView(R.id.created)
        TextView mCreated;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @SuppressLint("SetTextI18n")
        void bind(Payload item) {
            mId.setText("Id : " + item.getId());
            mName.setText("Name : " +item.getUser());
            mFileName.setText("FileName : " + item.getFilename());
            mCreated.setText("Created At : " + item.getCreated());
        }
    }
}
