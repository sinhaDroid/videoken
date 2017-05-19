package com.sinhadroid.trillbit.app.module.offline;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sinhadroid.trillbit.app.Constants;
import com.sinhadroid.trillbit.app.module.common.AbstractDataHandler;
import com.sinhadroid.trillbit.app.module.login.model.dto.Payload;
import com.sinhadroid.trillbit.app.webservice.MyWebService;

public class ProductDataHandler extends AbstractDataHandler {

    private static ProductDataHandler sProductDataHandler = new ProductDataHandler();

    private SharedPreferences mSharedPreferences;

    public static ProductDataHandler getInstance() {
        return sProductDataHandler;
    }

    private ProductDataHandler() {
    }

    public void init(Context context) {
        mSharedPreferences = context.getSharedPreferences(ProductDataHandler.class.getCanonicalName(), Context.MODE_PRIVATE);
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public void savePayload(Payload payload) {
        setSharedStringData(Constants.SharedKeys.PAYLOAD, MyWebService.getInstance().getJsonStringFromObject(payload));
    }

    public Payload getPayload() {
        return MyWebService.getInstance()
                .getObjectFromJson(getSharedStringData(Constants.SharedKeys.PAYLOAD),
                        new TypeReference<Payload>() {
                        });
    }
}
