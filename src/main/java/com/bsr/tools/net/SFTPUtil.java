package com.bsr.tools.net;

import com.jcraft.jsch.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * SFTP工具类
 */
public class SFTPUtil {
    public static boolean download(String host, int port, String userName, String password, String path, String targetPath) throws IOException {
        JSch jsch = new JSch();
        try {
            //创建链接
            Session session = jsch.getSession(userName, host, port);
            session.setPassword(password);
            Properties properties = new Properties();
            properties.put("StrictHostKeyChecking", "no");
            session.setConfig(properties);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftp = (ChannelSftp) channel;
            //下载
            int index = path.lastIndexOf("/");
            String fileName = path;
            if (index > 0) {
                String directoryPath = path.substring(0, index + 1);
                fileName = path.substring(index + 1);
                sftp.cd(directoryPath);
            }
            File localFile = new File(targetPath);
            if (localFile.exists()) {
                //存在则删除
                localFile.delete();
                localFile.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(localFile)) {
                sftp.get(fileName, fos);
            }
            //关闭链接
            sftp.disconnect();
            session.disconnect();
            return true;
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
