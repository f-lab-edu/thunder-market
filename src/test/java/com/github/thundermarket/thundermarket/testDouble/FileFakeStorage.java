package com.github.thundermarket.thundermarket.testDouble;

import com.github.thundermarket.thundermarket.Util.VideoUtils;
import com.github.thundermarket.thundermarket.constant.*;
import com.github.thundermarket.thundermarket.dto.FileUploadResult;
import com.github.thundermarket.thundermarket.repository.FileStorage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class FileFakeStorage implements FileStorage {

    @Override
    public FileUploadResult save(MultipartFile file) throws IOException {
        validateFile(file);

        Path videoFilePath = Files.createTempDirectory("test-video").resolve(UUID.randomUUID() + ".mp4");
        Path thumbnailFilePath = Files.createTempDirectory("test-thumbnail").resolve(UUID.randomUUID() + ".jpg");

        return new FileUploadResult(videoFilePath.toString(), thumbnailFilePath.toString());
    }

    @Override
    public boolean delete(String filePath) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    private void validateFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File must not be null or empty");
        }

        if (!FileStorageConst.ALLOWED_FILE_EXTENSIONS.contains(getFileExtension(file.getOriginalFilename()))) {
            throw new IllegalArgumentException("File extension not allowed");
        }

        if (FileStorageConst.MAX_VIDEO_LENGTH < VideoUtils.getVideoDuration(file)) {
            throw new IllegalArgumentException("Video length exceeds the limit");
        }

        if (FileStorageConst.MAX_FILE_SIZE < file.getSize()) {
            throw new IllegalArgumentException("File size exceeds the limit");
        }
    }

    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    }
}
