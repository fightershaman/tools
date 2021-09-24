package com.bsr.tools.file;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件夹工具类
 */
public class DirectoryUtil {
    /**
     * 文件夹是否存在，不存在则创建
     *
     * @param directoryPath 文件夹路径
     * @return 文件夹是否存在
     */
    public static boolean existsOrCreate(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            return directory.mkdirs();
        } else {
            return true;
        }
    }
}
