package com.github.thundermarket.thundermarket.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileUploadResult {
    private final String videoFilePath;
    private final String thumbnailFilePath;
}
