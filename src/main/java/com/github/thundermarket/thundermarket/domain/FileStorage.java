package com.github.thundermarket.thundermarket.domain;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorage {

    String save(MultipartFile file) throws IOException;
    boolean delete(String fileName);
    boolean validateFileExtension();
    boolean validateVideoLength() throws IOException;
    boolean validateFileSize();
}
