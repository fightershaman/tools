package com.bsr.tools.net;

import org.apache.commons.net.ftp.FTPClient;

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
     * @param hostName 域名
     * @param port     端口
     * @param userName 登录用户
     * @param password 用户密码
     * @return
     * @throws IOException
     */
    public static FTPClient getClient(String hostName, int port, String userName, String password) throws IOException {
        FTPClient client = new FTPClient();
        client.connect(hostName, port);
        if (client.login(userName, password)) {

            return client;
        } else {
            client.disconnect();
            return null;
        }
    }

    /**
     * 获取ftp当前路径下的文件名
     *
     * @param client ftp客户端
     * @return 文件名集合
     * @throws IOException
     */
    public static List<String> dir(FTPClient client) throws IOException {
        List<String> nameList = null;
        if (null != client) {
            nameList = new ArrayList<>();
            String[] names = client.listNames();
            for (String name : names) {
                nameList.add(new String(name.getBytes(client.getControlEncoding()), "UTF-8"));
            }
        }
        return nameList;
    }
}
