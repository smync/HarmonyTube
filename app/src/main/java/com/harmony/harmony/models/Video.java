package com.harmony.harmony.models;

public class Video {

    private String composer;
    private String description;
    private String imageurl;
    private String performer;
    private String videourl;
    private String work;
    private String date;
    private String title;

    public Video(String composer, String description, String imageurl, String performer, String videourl, String work, String date, String title) {
        this.composer = composer;
        this.description = description;
        this.imageurl = imageurl;
        this.performer = performer;
        this.videourl = videourl;
        this.work = work;
        this.date = date;
        this.title = title;
    }

    public Video() {


    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
