package com.sinhadroid.trillbit.app.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.FragmentActivity;

/**
 * Created by deepanshu on 18/5/17.
 */

public interface BaseView {

    FragmentActivity getActivity();

    Context getContext();

    void showLoading();

    void hideLoading();

    void showMessage(@StringRes int resId);

    void showMessage(String message);
}
