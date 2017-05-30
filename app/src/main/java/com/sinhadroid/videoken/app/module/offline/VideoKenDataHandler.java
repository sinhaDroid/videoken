package com.sinhadroid.videoken.app.module.offline;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sinhadroid.videoken.app.module.common.AbstractDataHandler;
import com.sinhadroid.videoken.app.module.recorder.model.VideoKen;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by deepanshu on 29/5/17.
 */

public class VideoKenDataHandler extends AbstractDataHandler {

    private static final String SESSION_FILE_NAME = "videoKenData";

    private static final VideoKenDataHandler ourInstance = new VideoKenDataHandler();

    public static VideoKenDataHandler getInstance() {
        return ourInstance;
    }

    private VideoKenDataHandler() {
    }

    private SharedPreferences mSharedPreferences;

    private Gson gson;

    public void init(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(SESSION_FILE_NAME, Context.MODE_PRIVATE);
        this.gson = new Gson();
    }

    @Override
    public SharedPreferences getSharedPreferences() {
        return mSharedPreferences;
    }


    public void saveYouTubeVideoId(String id) {
        List<String> youTubeVideoIds = getYouTubeVideoIds();

        if (youTubeVideoIds.size() > 0 && !youTubeVideoIds.contains(id)) {
            youTubeVideoIds.add(id);
        }
        setSharedStringData("YOU_TUBE_VIDEO_IDS", gson.toJson(youTubeVideoIds));
    }

    private List<String> getYouTubeVideoIds() {
        String youTubeVideoId = getSharedStringData("YOU_TUBE_VIDEO_IDS");

        if (null != youTubeVideoId && youTubeVideoId.length() > 0) {
            List<String> stringList;
            Type type = new TypeToken<List<String>>() {
            }.getType();
            stringList = gson.fromJson(youTubeVideoId, type);
            return stringList;
        }

        return new ArrayList<>();
    }

    public void saveYouTubeComment(String id, VideoKen videoKen) {
        Map<String, List<VideoKen>> youTubeCommentMap = getYouTubeCommentMap();

        if (youTubeCommentMap.containsKey(id)) {
            youTubeCommentMap.get(id).add(videoKen);
        } else {
            List<VideoKen> itemList = new ArrayList<>();
            itemList.add(videoKen);

            youTubeCommentMap.put(id, itemList);
        }

        setSharedStringData("YOU_TUBE_COMMENT_MAP", gson.toJson(youTubeCommentMap));
    }

    public Map<String, List<VideoKen>> getYouTubeCommentMap() {
        String you_tube_comment_map = getSharedStringData("YOU_TUBE_COMMENT_MAP");

        if (null != you_tube_comment_map && you_tube_comment_map.length() > 0) {
            Map<String, List<VideoKen>> listMap;
            Type type = new TypeToken<Map<String, List<VideoKen>>>() {
            }.getType();
            listMap = gson.fromJson(you_tube_comment_map, type);
            return listMap;
        }

        return new HashMap<>();
    }
}
