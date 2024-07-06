package com.github.thundermarket.thundermarket.domain;

import com.github.thundermarket.thundermarket.constant.*;
import org.springframework.web.multipart.MultipartFile;

public class LocalFileStorage implements FileStorage {
    @Override
    public boolean save(MultipartFile file) {
        return false;
    }

    @Override
    public boolean delete(String fileName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean validateFileExtension(String extension) {
        if (FileStorageConst.ALLOWED_FILE_EXTENSIONS.contains(extension)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean validateVideoLength(int seconds) {
        if (seconds <= FileStorageConst.MAX_VIDEO_LENGTH) {
            return true;
        }
        return false;
    }

    @Override
    public boolean validateFileSize(long size) {
        if (size <= FileStorageConst.MAX_FILE_SIZE) {
            return true;
        }
        return false;
    }
}
