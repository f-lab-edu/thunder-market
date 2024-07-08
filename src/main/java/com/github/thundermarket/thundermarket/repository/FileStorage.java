package com.github.thundermarket.thundermarket.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorage {

    String save(MultipartFile file) throws IOException;
    boolean delete(String filePath);
}
