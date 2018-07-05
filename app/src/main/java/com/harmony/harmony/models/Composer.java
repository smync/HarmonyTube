package com.harmony.harmony.models;

public class Composer {

    private String composerId;
    private String composerName;
    private String composerDetails;
    private String composerNumberOfVideos;
    private String composerImageUrl;


    public Composer() {


    }

    public Composer(String composerId, String composerName, String composerDetails, String composerNumberOfVideos, String composerImageUrl) {
        this.composerId = composerId;
        this.composerName = composerName;
        this.composerDetails = composerDetails;
        this.composerNumberOfVideos = composerNumberOfVideos;
        this.composerImageUrl = composerImageUrl;
    }

    public String getComposerId() {
        return composerId;
    }

    public void setComposerId(String composerId) {
        this.composerId = composerId;
    }

    public String getComposerName() {
        return composerName;
    }

    public void setComposerName(String composerName) {
        this.composerName = composerName;
    }

    public String getComposerDetails() {
        return composerDetails;
    }

    public void setComposerDetails(String composerDetails) {
        this.composerDetails = composerDetails;
    }

    public String getComposerNumberOfVideos() {
        return composerNumberOfVideos;
    }

    public void setComposerNumberOfVideos(String composerNumberOfVideos) {
        this.composerNumberOfVideos = composerNumberOfVideos;
    }

    public String getComposerImageUrl() {
        return composerImageUrl;
    }

    public void setComposerImageUrl(String composerImageUrl) {
        this.composerImageUrl = composerImageUrl;
    }
}
