package com.sinhdroid.trillbit.app.module.base;

import android.support.annotation.StringRes;

/**
 * Created by deepanshu on 18/5/17.
 */

interface BaseView {

    void showLoading();

    void hideLoading();

    void showMessage(@StringRes int resId);

    void showMessage(String message);
}
