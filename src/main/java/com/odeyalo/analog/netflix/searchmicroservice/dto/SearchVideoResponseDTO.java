package com.odeyalo.analog.netflix.searchmicroservice.dto;

public class SearchVideoResponseDTO {
    private String videoId;
    private String videoName;
    private String videoAuthor;

    public SearchVideoResponseDTO() {
    }

    public SearchVideoResponseDTO(String videoId, String videoName, String videoAuthor) {
        this.videoId = videoId;
        this.videoName = videoName;
        this.videoAuthor = videoAuthor;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoAuthor() {
        return videoAuthor;
    }

    public void setVideoAuthor(String videoAuthor) {
        this.videoAuthor = videoAuthor;
    }
}
