package com.bsr.tools.document;

import java.io.File;

/**
 * 文件夹工具类
 */
public class DocumentUtil {
    /**
     * 创建文件夹
     *
     * @param documentPath 文件夹路径
     */
    public static void mkdir(String documentPath) {
        File directory = new File(documentPath);
        if (!directory.exists() || !directory.isDirectory()) {
            directory.mkdirs();
        }
    }
}
