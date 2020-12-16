package com.nullstdio.fieldbuzz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CvFileDataModel {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("tsync_id")
    @Expose
    private String tsync_id;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("file")
    @Expose
    private String file;

    public CvFileDataModel() {
    }

    public CvFileDataModel(int id, String tsync_id, String path, String file) {
        this.id = id;
        this.tsync_id = tsync_id;
        this.path = path;
        this.file = file;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTsync_id() {
        return tsync_id;
    }

    public void setTsync_id(String tsync_id) {
        this.tsync_id = tsync_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
