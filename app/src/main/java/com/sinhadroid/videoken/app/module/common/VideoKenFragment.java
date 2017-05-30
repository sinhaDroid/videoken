package com.sinhadroid.videoken.app.module.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinhadroid.videoken.app.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by deepanshu on 18/5/17.
 */

public abstract class VideoKenFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getInflater(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onViewCreated(savedInstanceState);
    }

    protected abstract int getInflater();

    protected abstract void onViewCreated(Bundle savedInstanceState);
}
