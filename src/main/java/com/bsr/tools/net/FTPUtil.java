package com.bsr.tools.net;

import com.bsr.tools.file.DirectoryUtil;
import com.bsr.tools.file.FileUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * FTP工具类
 */
public class FTPUtil {
    /**
     * 获取ftp client
     *
     * @param host 域名
     * @param port     端口
     * @param userName 登录用户
     * @param password 用户密码
     * @return
     * @throws IOException
     */
    public static FTPClient getClient(String host, int port, String userName, String password) throws IOException {
        FTPClient client = new FTPClient();
        client.connect(host, port);
        if (client.login(userName, password)) {
            client.setControlEncoding("UTF-8");
            return client;
        } else {
            client.disconnect();
            return null;
        }
    }

    /**
     * 关闭链接
     *
     * @param client
     * @throws IOException
     */
    public static void close(FTPClient client) throws IOException {
        client.logout();
        client.disconnect();
    }

    /**
     * 获取ftp当前路径下的文件名
     *
     * @param client ftp客户端
     * @return 文件名集合
     * @throws IOException
     */
    public static List<String> dir(FTPClient client) throws IOException {
        List<String> nameList = new ArrayList<>();
        String[] names = client.listNames();
        for (String name : names) {
            nameList.add(name);
        }
        return nameList;
    }

    /**
     * 获取ftp当前路径下的文件夹
     *
     * @param client ftp客户端
     * @return 文件夹名集合
     * @throws IOException
     */
    public static List<String> dirDirectory(FTPClient client) throws IOException {
        List<String> nameList = new ArrayList<>();
        FTPFile[] directories = client.listDirectories();
        for (FTPFile directory : directories) {
            nameList.add(directory.getName());
        }
        return nameList;
    }

    /**
     * 切换目录
     *
     * @param client ftp客户端
     * @param path   路径
     * @return
     * @throws IOException
     */
    public static boolean cd(FTPClient client, String path) throws IOException {
        return client.changeWorkingDirectory(path);
    }

    /**
     * 下载文件
     *
     * @param client        ftp客户端
     * @param fileName      文件名
     * @param directoryPath 本地存储路径
     * @return
     * @throws IOException
     */
    public static boolean download(FTPClient client, String fileName, String directoryPath) throws IOException {
        DirectoryUtil.existsOrCreate(directoryPath);
        if (!directoryPath.endsWith(FileUtil.separator)) {
            directoryPath += FileUtil.separator;
        }
        try (FileOutputStream fos = new FileOutputStream(directoryPath + fileName)) {
            return client.retrieveFile(fileName, fos);
        }
    }

    /**
     * 上传文件
     *
     * @param client   ftp客户端
     * @param filePath 文件路径
     * @return
     * @throws IOException
     */
    public static boolean upload(FTPClient client, String filePath) throws IOException {
        String[] strs = filePath.split(FileUtil.separator);
        String fileName = strs[strs.length - 1];
        try (FileInputStream fis = new FileInputStream(filePath)) {
            return client.storeFile(fileName, fis);
        }
    }

    /**
     * 删除ftp服务器上的文件
     *
     * @param client   ftp客户端
     * @param fileName 文件名
     * @return
     * @throws IOException
     */
    public static boolean remove(FTPClient client, String fileName) throws IOException {
        return client.deleteFile(fileName);
    }
}
