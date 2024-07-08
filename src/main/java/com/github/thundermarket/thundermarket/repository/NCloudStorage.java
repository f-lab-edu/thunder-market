package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.Util.VideoUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.github.thundermarket.thundermarket.constant.*;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

@Component
public class NCloudStorage implements FileStorage {

    @Override
    public String save(MultipartFile file) throws IOException {
        validateFile(file);

        S3Client s3 = S3Client.builder()
                .endpointOverride(URI.create(FileStorageConst.NCLOUD_ENDPOINT))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(FileStorageConst.NCLOUD_ACCESS_KEY, FileStorageConst.NCLOUD_SECRET_KEY)))
                .region(Region.of(FileStorageConst.NCLOUD_REGION_NAME))
                .build();

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String objectKey = FileStorageConst.NCLOUD_VIDEO_DIRECTORY + fileName;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(FileStorageConst.NCLOUD_BUCKET_NAME)
                .key(objectKey)
                .contentType(file.getContentType())
                .build();

        s3.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        return FileStorageConst.NCLOUD_ENDPOINT + "/" + FileStorageConst.NCLOUD_BUCKET_NAME + "/" + objectKey;
    }

    @Override
    public boolean delete(String filePath) {
        return false;
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
