package com.small.missionboard.util;

import cn.hutool.core.io.FileUtil;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 文件的上传与下载
 */
@Slf4j
public class FileUtils {
    /**
     * 资源文件的路径
     */
    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String IMG_PATH_PREFIX = "static/upload/img/";
    private static final List<String> AVAILABLE_EXTENSION_LIST = new ArrayList<>(Arrays.asList("jpg", "png", "gif"));


    /**
     * 上传文件, 返回上传路径
     */
    public static String store(MultipartFile file, String path) {
        try {
            File destination = new File(path);
            file.transferTo(destination);
        } catch (IOException e) {
            log.error("文件写入失败: " + Arrays.toString(e.getStackTrace()));
        }
        return path;
    }

    /**
     * 下载文件
     */
    public static Resource load(String filePath) {
        try {
            return new UrlResource(filePath);
        } catch (MalformedURLException e) {
            throw new KnownException(ExceptionEnum.UNKNOWN_EXCEPTION);
        }
    }


    /**
     * 获取图片上传路径
     */
    public static String generateImagePath(MultipartFile image) {
        if (image == null) {
            throw new KnownException(ExceptionEnum.IMAGE_UPLOAD_FAIL);
        }

        String fileDirPath = RESOURCES_PATH + IMG_PATH_PREFIX;
        if (!FileUtil.exist(fileDirPath)) {
            FileUtil.mkdir(fileDirPath);
        }

        String fileName = image.getName();
        String extension = fileName.substring(fileName.lastIndexOf("." + 1));
        if (!AVAILABLE_EXTENSION_LIST.contains(extension)) {
            throw new KnownException(ExceptionEnum.IMAGE_UPLOAD_FAIL);
        }
        String newFileName = UUID.randomUUID().toString() + "." + extension;

        return fileDirPath + newFileName;
    }


}
