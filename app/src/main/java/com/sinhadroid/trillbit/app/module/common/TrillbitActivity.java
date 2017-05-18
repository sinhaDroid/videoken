package com.sinhadroid.trillbit.app.module.common;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sinhadroid.trillbit.app.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by deepanshu on 18/5/17.
 */

public abstract class TrillbitActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());

        ButterKnife.bind(this);

        onViewCreated(savedInstanceState);
    }

    protected abstract int getContentView();

    protected abstract void onViewCreated(Bundle savedInstanceState);
}
