package com.sinhadroid.trillbit.app.module.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.sinhadroid.trillbit.app.R;
import com.sinhadroid.trillbit.app.module.common.TrillbitFragment;
import com.sinhadroid.trillbit.app.module.productinfo.view.ProductFragment;
import com.sinhadroid.trillbit.app.module.recorder.view.RecordFragment;

import butterknife.BindView;

public class HomeFragment extends TrillbitFragment {

    @BindView(R.id.pager)
    ViewPager mPager;

    @BindView(R.id.tabs)
    PagerSlidingTabStrip mPagerSlidingTabStrip;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    protected int getInflater() {
        return R.layout.fragment_home;
    }

    @Override
    protected void onViewCreated(Bundle savedInstanceState) {

        mPager.setAdapter(new MyAdapter(getActivity().getSupportFragmentManager()));
        mPagerSlidingTabStrip.setViewPager(mPager);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        private String[] titles = {getString(R.string.tab_title_record),
                getString(R.string.tab_title_product_info)};

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: {
                    return RecordFragment.newInstance();
                }
                case 1: {
                    return ProductFragment.newInstance();
                }
            }
            return null;
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
