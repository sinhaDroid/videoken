package com.sinhadroid.trillbit.app.webservice;

import android.util.Log;

import com.sinhadroid.trillbit.app.Trillbit;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class TrillbitCallBack<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        // checking if it not authorized
        if (response.code() == 401) {
            Trillbit.getInstance().logout();
        }
    }

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        onResponse(call, (Response<T>) Response.error(getEmptyResponseBody(), new okhttp3.Response.Builder()
                .code(0)
                .message(t.getMessage())
                .protocol(Protocol.HTTP_1_1)
                .request(new Request.Builder().url("http://localhost/").build())
                .build()));

        Log.d("TrillbitCallBack", "Not authorize check");
    }

    private ResponseBody getEmptyResponseBody() {
        return new ResponseBody() {
            @Override
            public MediaType contentType() {
                return null;
            }

            @Override
            public long contentLength() {
                return 0;
            }

            @Override
            public BufferedSource source() {
                return null;
            }
        };
    }
}