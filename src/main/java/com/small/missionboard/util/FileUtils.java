package com.small.missionboard.util;

import cn.hutool.core.io.FileUtil;
import com.small.missionboard.common.KnownException;
import com.small.missionboard.enums.ExceptionEnum;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件的上传与下载
 */
public class FileUtils {
    /**
     * 资源文件的路径
     */
    private static final String UPLOAD_DIR = "C:" + File.separator + "uploadingDir" + File.separator;


    /**
     * 上传文件, 返回上传路径
     */
    public static void store(MultipartFile file, String path) {
        try {
            File destination = new File(path);
            file.transferTo(destination);
        } catch (IOException e) {
            throw new KnownException(ExceptionEnum.FILE_IO_EXCEPTION);
        }
    }

    /**
     * 下载文件
     */
    public static byte[] load(String filePath) {
        if (!FileUtil.exist(filePath)) {
            throw new KnownException(ExceptionEnum.DOWNLOADING_FILE_NOT_EXITS);
        }
        return FileUtil.readBytes(filePath);
    }


    /**
     * 获取图片上传路径
     */
    public static String generateImagePath(MultipartFile image) {
        if (image == null) {
            throw new KnownException(ExceptionEnum.IMAGE_UPLOAD_FAIL);
        }

        String fileName = image.getOriginalFilename();
        assert fileName != null;
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!isImage(extension)) {
            throw new KnownException(ExceptionEnum.IMAGE_UPLOAD_FAIL);
        }

        String newFileName = UUID.randomUUID().toString() + "." + extension;
        // 每天用不同的文件夹保存
        String fileDir = UPLOAD_DIR + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd")) + File.separator;

        if (!FileUtil.exist(fileDir)) {
            File mkdir = FileUtil.mkdir(fileDir);
        }
        return fileDir + newFileName;
    }

    /**
     * 检查文件名是否为图片
     */
    private static boolean isImage(String extension) {
        if (extension.isEmpty()) {
            return false;
        }
        String reg = "(.JPEG|.jpeg|.JPG|.jpg|.PNG|.png|.GIF|.gif|.BMP|.bmp)$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher("." + extension);
        return matcher.find();
    }


}
