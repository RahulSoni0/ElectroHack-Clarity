package com.rahulsoni0.clarity.cache;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "savedlist")
public class SavedListEntityModel {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "desc")
    String desc;
    @ColumnInfo(name = "imageUrl")
    String imageUrl;
    @ColumnInfo(name = "detailUrl")
    String detailUrl;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @Override
    public String toString() {
        return "SavedListEntityModel{" +
                "uid=" + uid +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", detailUrl='" + detailUrl + '\'' +
                '}';
    }

    public SavedListEntityModel(String title, String desc, String imageUrl, String detailUrl) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.detailUrl = detailUrl;
    }
}
