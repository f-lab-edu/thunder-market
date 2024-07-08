package com.github.thundermarket.thundermarket.domain;

public class FileUploadResult {
    private final String videoFilePath;
    private final String thumbnailFilePath;

    public FileUploadResult(String videoFilePath, String thumbnailFilePath) {
        this.videoFilePath = videoFilePath;
        this.thumbnailFilePath = thumbnailFilePath;
    }

    public String getVideoFilePath() {
        return videoFilePath;
    }

    public String getThumbnailFilePath() {
        return thumbnailFilePath;
    }
}
