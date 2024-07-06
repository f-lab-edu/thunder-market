package com.github.thundermarket.thundermarket.domain;

import com.github.thundermarket.thundermarket.Util.VideoUtils;
import com.github.thundermarket.thundermarket.constant.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class LocalFileStorage implements FileStorage {

    private final MultipartFile file;
    private final String fileName;
    private final String extension;
    private final long size;
    private final Path storagePath;
    private static final String STORAGE_LOCATION = System.getProperty("app.storage.path", "/tmp/app/storage/video/upload");

    static {
        try {
            Files.createDirectories(Paths.get(STORAGE_LOCATION));
        } catch (IOException e) {
            throw new RuntimeException("Could not create storage directory", e);
        }
    }

    public LocalFileStorage(MultipartFile file) {
        this.file = file;
        this.fileName = UUID.randomUUID().toString();
        this.extension = getFileExtension(file.getOriginalFilename());
        this.size = file.getSize();
        this.storagePath = Paths.get(STORAGE_LOCATION).resolve(fileName + "." + extension);
    }


    @Override
    public String save(MultipartFile file) throws IOException {
        file.transferTo(storagePath);
        return String.valueOf(storagePath);
    }

    @Override
    public boolean delete(String fileName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean validateFileExtension() {
        return FileStorageConst.ALLOWED_FILE_EXTENSIONS.contains(extension);
    }

    @Override
    public boolean validateVideoLength() throws IOException {
        if (VideoUtils.getVideoDuration(file) <= FileStorageConst.MAX_VIDEO_LENGTH) {
            return true;
        };
        return false;
    }

    @Override
    public boolean validateFileSize() {
        return size <= FileStorageConst.MAX_FILE_SIZE;
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }

    public String getFileName() {
        return fileName + "." + extension;
    }
}
