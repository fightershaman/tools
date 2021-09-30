package com.bsr.tools.net;

import org.apache.commons.net.ftp.FTPClient;

import java.io.IOException;

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
}
