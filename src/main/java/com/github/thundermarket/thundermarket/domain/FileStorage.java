package com.github.thundermarket.thundermarket.domain;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {

    boolean save(MultipartFile file);
    boolean delete(String fileName);
    boolean validateFileExtension();
    boolean validateVideoLength();
    boolean validateFileSize();
}
