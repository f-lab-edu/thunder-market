package com.github.thundermarket.thundermarket.Util;

import org.bytedeco.ffmpeg.global.avutil;
import org.bytedeco.javacv.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
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

    public static byte[] generateThumbnail(MultipartFile file) throws IOException {
        try (InputStream inputStream = file.getInputStream();
             FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream)) {

            grabber.start();
            Frame frame = grabber.grabImage();

            if (frame != null) {
                Java2DFrameConverter converter = new Java2DFrameConverter();
                BufferedImage bufferedImage = converter.getBufferedImage(frame);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "jpg", baos);
                grabber.stop();

                return baos.toByteArray();
            } else {
                throw new IOException("Failed to grab video frame");
            }
        } catch (Exception e) {
            throw new IOException("Failed to generate thumbnail", e);
        }
    }
}