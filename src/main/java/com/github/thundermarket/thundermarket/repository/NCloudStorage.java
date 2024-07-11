package com.github.thundermarket.thundermarket.repository;

import com.github.thundermarket.thundermarket.Util.VideoUtils;
import com.github.thundermarket.thundermarket.domain.FileUploadResult;
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
    public FileUploadResult save(MultipartFile file) throws IOException {
        validateFile(file);

        S3Client s3 = S3Client.builder()
                .endpointOverride(URI.create(FileStorageConst.NCLOUD_ENDPOINT))
                .credentialsProvider(StaticCredentialsProvider.create(
                        AwsBasicCredentials.create(FileStorageConst.NCLOUD_ACCESS_KEY, FileStorageConst.NCLOUD_SECRET_KEY)))
                .region(Region.of(FileStorageConst.NCLOUD_REGION_NAME))
                .build();

        UUID uuid = UUID.randomUUID();
        String fileName = "video_" + uuid + "_" + file.getOriginalFilename();
        String videoObjectKey = FileStorageConst.NCLOUD_VIDEO_DIRECTORY + fileName;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(FileStorageConst.NCLOUD_BUCKET_NAME)
                .key(videoObjectKey)
                .contentType(file.getContentType())
                .build();

        s3.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

        byte[] thumbnailBytes = VideoUtils.generateThumbnail(file);
        String thumbnailFileName = "thumbnail_" + uuid + ".jpg";
        String thumbnailObjectKey = FileStorageConst.NCLOUD_THUMBNAIL_DIRECTORY + thumbnailFileName;

        PutObjectRequest thumbnailPutRequest = PutObjectRequest.builder()
                .bucket(FileStorageConst.NCLOUD_BUCKET_NAME)
                .key(thumbnailObjectKey)
                .contentType("image/jpeg")
                .build();

        s3.putObject(thumbnailPutRequest, RequestBody.fromBytes(thumbnailBytes));

        String videoFilePath = FileStorageConst.NCLOUD_ENDPOINT + "/" + FileStorageConst.NCLOUD_BUCKET_NAME + "/" + videoObjectKey;
        String thumbnailFilePath = FileStorageConst.NCLOUD_ENDPOINT + "/" + FileStorageConst.NCLOUD_BUCKET_NAME + "/" + thumbnailObjectKey;
        return new FileUploadResult(videoFilePath, thumbnailFilePath);
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
