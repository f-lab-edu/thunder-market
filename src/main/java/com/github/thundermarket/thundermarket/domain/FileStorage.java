package com.github.thundermarket.thundermarket.domain;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {

    boolean save(MultipartFile file);
    boolean delete(String fileName);
    boolean validateFileExtension(String extension);
    boolean validateVideoLength(int seconds);
    boolean validateFileSize(long size);
}
