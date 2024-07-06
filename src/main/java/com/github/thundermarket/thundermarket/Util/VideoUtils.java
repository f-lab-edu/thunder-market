package com.github.thundermarket.thundermarket.Util;

import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public class VideoUtils {

    static {
        // 로그 레벨을 에러로그로 설정
        avutil.av_log_set_level(avutil.AV_LOG_ERROR);
    }

    public static long getVideoDuration(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream)) {

            grabber.start();
            long duration = grabber.getLengthInTime() / 1000000; // 마이크로초를 초로 변환
            grabber.stop();

            return duration;
        } catch (Exception e) {
            throw new IOException("Failed to get video duration", e);
        }
    }
}