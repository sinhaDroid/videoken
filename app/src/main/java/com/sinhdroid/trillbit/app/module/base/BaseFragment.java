package com.sinhdroid.trillbit.app.module.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by deepanshu on 18/5/17.
 */

public class BaseFragment extends Fragment implements BaseView {

    private BaseActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) this.mActivity = (BaseActivity) context;
    }

    @Override
    public void showLoading() {
        if (null != mActivity) {
            mActivity.showLoading();
        }
    }

    @Override
    public void hideLoading() {
        if (null != mActivity) {
            mActivity.hideLoading();
        }
    }

    @Override
    public void showMessage(@StringRes int resId) {
        if (null != mActivity) {
            mActivity.showMessage(resId);
        }
    }

    @Override
    public void showMessage(String message) {
        if (null != mActivity) {
            mActivity.showMessage(message);
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public View findViewById(int id) {
        return getView().findViewById(id);
    }

    public void showSoftKeyboard(android.view.View view) {
        getInputMethodManager().showSoftInput(view, 0);
    }

    public void hideSoftKeyboard() {
        if (null != mActivity.getCurrentFocus())
            getInputMethodManager().hideSoftInputFromWindow(mActivity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }

    private InputMethodManager getInputMethodManager() {
        return (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }
}
