package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.dto.FileUploadResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileStorage {

    FileUploadResult save(MultipartFile file) throws IOException;
    boolean delete(String filePath);
}
