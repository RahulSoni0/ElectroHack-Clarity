package com.rahulsoni0.clarity.models;

public class ExploreModel {

    String title;
    String desc;
    String imageUrl;
    String detailsUrl;

    @Override
    public String toString() {
        return "ExploreModel{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", detailsUrl='" + detailsUrl + '\'' +
                '}';
    }

    public ExploreModel(String title, String desc, String imageUrl, String detailsUrl) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.detailsUrl = detailsUrl;
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

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }
}
