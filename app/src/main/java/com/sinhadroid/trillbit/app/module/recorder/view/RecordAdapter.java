package com.sinhadroid.trillbit.app.module.recorder.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.Adapter;
import com.sinhadroid.trillbit.app.module.recorder.model.dto.response.Payload;

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

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
