package com.sinhadroid.trillbit.app.module.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitActivity;
import com.sinhadroid.trillbit.app.module.home.HomeFragment;
import com.sinhadroid.trillbit.app.module.profile.ProfileFragment;

import butterknife.BindView;

public class MainActivity extends TrillbitActivity {

    @BindView(R.id.navigation)
    BottomNavigationView mNavigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    loadFragment(HomeFragment.newInstance());
                    return true;
                case R.id.navigation_dashboard:
                    loadFragment(ProfileFragment.newInstance());
                    return true;
            }
            return false;
        }

    };

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {
        mNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(HomeFragment.newInstance());
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }

}
