package com.sinhadroid.trillbit.app.module.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.Trillbit;
import com.sinhadroid.trillbit.app.module.common.TrillbitActivity;
import com.sinhadroid.trillbit.app.module.home.HomeFragment;

import butterknife.OnClick;

public class MainActivity extends TrillbitActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        loadFragment(HomeFragment.newInstance());
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

    @OnClick(R.id.logout)
    public void onClickLogOut(View view) {
        Trillbit.getInstance().logout();
    }
}
