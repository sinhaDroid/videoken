package com.sinhadroid.trillbit.app.module.offline;

import android.content.Context;
import android.content.SharedPreferences;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sinhadroid.trillbit.app.Constants;
import com.sinhadroid.trillbit.app.module.common.AbstractDataHandler;
import com.sinhadroid.trillbit.app.module.recorder.model.dto.Payload;
import com.sinhadroid.trillbit.app.webservice.MyWebService;

import java.util.ArrayList;
import java.util.List;

public class RecordDataHandler extends AbstractDataHandler {

    private static RecordDataHandler sRecordDataHandler = new RecordDataHandler();

    private SharedPreferences mSharedPreferences;

    public static RecordDataHandler getInstance() {
        return sRecordDataHandler;
    }

    private RecordDataHandler() {
    }

    public void init(Context context) {
        mSharedPreferences = context.getSharedPreferences(RecordDataHandler.class.getCanonicalName(), Context.MODE_PRIVATE);
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }

    public void addRecordInList(Payload payload) {
        List<Payload> recordList = getRecordList();

        recordList.add(payload);

        saveRecordList(recordList);
    }

    private void saveRecordList(List<Payload> recordList) {
        setSharedStringData(Constants.SharedKeys.RECORD_PAYLOAD,
                MyWebService.getInstance().getJsonStringFromObject(recordList));
    }

    public List<Payload> getRecordList() {
        String payloadData = getSharedStringData(Constants.SharedKeys.RECORD_PAYLOAD);

        if (null != payloadData && payloadData.length() > 0) {
            return MyWebService.getInstance()
                    .getObjectFromJson(getSharedStringData(Constants.SharedKeys.RECORD_PAYLOAD),
                            new TypeReference<List<Payload>>() {
                            });
        } else {
            return new ArrayList<>();
        }
    }

    public void saveNoOfCount(int count) {
        setSharedIntData(Constants.SharedKeys.NO_OF_COUNT, count);
    }

    public Integer getNoOfCount() {
        return getSharedIntData(Constants.SharedKeys.NO_OF_COUNT, 0);
    }

    public void setAudioRequest(boolean value) {
        setSharedBooleanData(Constants.ApiKeys.AUDIO, value);
    }

    public boolean isAudioRequest() {
        return getSharedBooleanData(Constants.ApiKeys.AUDIO, false);
    }
}
