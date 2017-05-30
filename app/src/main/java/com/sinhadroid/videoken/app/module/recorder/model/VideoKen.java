package com.sinhadroid.videoken.app.module.recorder.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by deepanshu on 29/5/17.
 */

public class VideoKen implements Parcelable {

    String name;

    String filePath;

    long length;

    String recordedText;

    public VideoKen() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public String getRecordedText() {
        return recordedText;
    }

    public void setRecordedText(String recordedText) {
        this.recordedText = recordedText;
    }

    public VideoKen(Parcel in) {
        name = in.readString();
        filePath = in.readString();
        length = in.readLong();
        recordedText = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(filePath);
        dest.writeLong(length);
        dest.writeString(recordedText);
    }

    @SuppressWarnings("unused")
    public static final Creator<VideoKen> CREATOR = new Creator<VideoKen>() {
        @Override
        public VideoKen createFromParcel(Parcel in) {
            return new VideoKen(in);
        }

        @Override
        public VideoKen[] newArray(int size) {
            return new VideoKen[size];
        }
    };
}
