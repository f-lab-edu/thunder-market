package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.Util.VideoUtils;
import com.github.thundermarket.thundermarket.constant.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Component
public class LocalProductVideoStorage implements FileStorage {

    private static final String STORAGE_LOCATION = System.getProperty("app.storage.path", "/tmp/app/storage/video/upload");

    static {
        try {
            Files.createDirectories(Paths.get(STORAGE_LOCATION));
        } catch (IOException e) {
            throw new RuntimeException("Could not create storage directory", e);
        }
    }

    public LocalProductVideoStorage() {
    }


    @Override
    public String save(MultipartFile file) throws IOException {
        String fileName = UUID.randomUUID().toString();
        String extension = getFileExtension(file.getOriginalFilename());
        Path storagePath = Paths.get(STORAGE_LOCATION).resolve(fileName + "." + extension);

        if (!FileStorageConst.ALLOWED_FILE_EXTENSIONS.contains(extension)) {
            throw new IllegalArgumentException("File extension not allowed");
        }

        if (FileStorageConst.MAX_VIDEO_LENGTH < VideoUtils.getVideoDuration(file)) {
            throw new IllegalArgumentException("Video length exceeds the limit");
        }

        if (FileStorageConst.MAX_FILE_SIZE < file.getSize()) {
            throw new IllegalArgumentException("File size exceeds the limit");
        }

        file.transferTo(storagePath);
        return String.valueOf(storagePath);
    }

    @Override
    public boolean delete(String filePath) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
}
