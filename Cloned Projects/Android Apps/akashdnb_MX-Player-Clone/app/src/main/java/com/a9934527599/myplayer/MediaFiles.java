package com.a9934527599.myplayer;

import android.os.Parcel;
import android.os.Parcelable;

public class MediaFiles implements Parcelable {
    private String id;
    private String title;
    private String displayName;
    private String size;
    private String uration;
    private String path;
    private String dateAdded;

    public MediaFiles(String id, String title, String displayName, String size, String uration, String path, String dateAdded) {
        this.id = id;
        this.title = title;
        this.displayName = displayName;
        this.size = size;
        this.uration = uration;
        this.path = path;
        this.dateAdded = dateAdded;
    }

    protected MediaFiles(Parcel in) {
        id = in.readString();
        title = in.readString();
        displayName = in.readString();
        size = in.readString();
        uration = in.readString();
        path = in.readString();
        dateAdded = in.readString();
    }

    public static final Creator<MediaFiles> CREATOR = new Creator<MediaFiles>() {
        @Override
        public MediaFiles createFromParcel(Parcel in) {
            return new MediaFiles(in);
        }

        @Override
        public MediaFiles[] newArray(int size) {
            return new MediaFiles[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getUration() {
        return uration;
    }

    public void setUration(String uration) {
        this.uration = uration;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(displayName);
        dest.writeString(size);
        dest.writeString(uration);
        dest.writeString(path);
        dest.writeString(dateAdded);
    }
}
