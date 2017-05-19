package com.sinhadroid.trillbit.app.module.profile;

import android.os.Bundle;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitFragment;

/**
 * Created by deepanshu on 19/5/17.
 */

public class ProfileFragment extends TrillbitFragment {

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    @Override
    protected int getInflater() {
        return R.layout.fragment_profile;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

    }
}
