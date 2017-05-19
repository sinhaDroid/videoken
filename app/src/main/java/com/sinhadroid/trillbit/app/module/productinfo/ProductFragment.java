package com.sinhadroid.trillbit.app.module.productinfo;

import android.os.Bundle;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitFragment;

public class ProductFragment extends TrillbitFragment {

    public static ProductFragment newInstance(int position) {
        return new ProductFragment();
    }
    @Override
    protected int getInflater() {
        return R.layout.fragment_product;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

    }
}
