package com.github.thundermarket.thundermarket.constant;

import java.util.Set;

public class FileStorageConst {

    public static final int MAX_FILE_SIZE = 200 * 1024 * 1024; // 20MB
    public static final int MAX_VIDEO_LENGTH = 10; // 10 seconds
    public static final Set<String> ALLOWED_FILE_EXTENSIONS = Set.of("mp4", "avi", "mov"); // 화이트 리스트, 불변객체 사용

    public static final String NCLOUD_ENDPOINT = "https://kr.object.ncloudstorage.com";
    public static final String NCLOUD_REGION_NAME = "kr-standard";
    public static final String NCLOUD_BUCKET_NAME = "thunder-market-bucket";
    public static final String NCLOUD_VIDEO_DIRECTORY = "video/";
    public static final String NCLOUD_THUMBNAIL_DIRECTORY = "thumbnail/";
    public static final String NCLOUD_ACCESS_KEY = System.getenv("NCLOUD_ACCESS_KEY");
    public static final String NCLOUD_SECRET_KEY = System.getenv("NCLOUD_SECRET_KEY");
}
