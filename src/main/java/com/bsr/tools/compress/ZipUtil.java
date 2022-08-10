package com.bsr.tools.compress;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {

    public static void zip(String zipPath, String inPath) {
        File inFile = new File(inPath);
        if (inFile.isDirectory()) {
            File[] files = inFile.listFiles();

        } else {


        }
    }

    public static void unzip(String zipPath, String outPath) throws IOException {
        File file = new File(zipPath);
        if (!file.exists()) {
            throw new FileNotFoundException("zip file not found");
        }
        ZipFile zipFile = new ZipFile(file);
        Enumeration entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            System.out.println(entry.getName());
            if (entry.isDirectory()) {
                //文件夹
                File newFile = new File(outPath, entry.getName());
                if (!newFile.exists()) {
                    newFile.mkdirs();
                }
            } else {
                //文件
                File newFile = new File(outPath, entry.getName());
                File parentFile = newFile.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }
                try (InputStream zis = zipFile.getInputStream(entry); FileOutputStream fos = new FileOutputStream(newFile)) {
                    byte[] bytes = new byte[8 * 1024];
                    int length = 0;
                    while ((length = zis.read(bytes)) > 0) {
                        fos.write(bytes, 0, length);
                    }
                }
            }
        }
    }


}
