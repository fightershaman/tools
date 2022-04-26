package com.bsr.tools.net;

import com.google.gson.Gson;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class HttpUtil {
    public static void main(String[] args) throws IOException {
        //String str = "http://test2-im.imshuixin.net:18011/api/users/getSilentUserList?silentTime=1647913397000&pageNum=1&pageSize=10";
        Map<String, Object> param = new HashMap<>();
       /* param.put("silentTime", "1647913397000");
        param.put("pageNum", "1");
        param.put("pageSize", "16");
        String str = "http://test2-im.imshuixin.net:18011/api/users/getSilentUserList";
        System.out.println(get(str, param));*/
        param.put("userId", "3a82d4wnyn0g");
        param.put("hexPublishId", "67ca28437170449a825db1a21f143f89");
        String str = "http://test2-im.imshuixin.net:18011/api/cfcsync/robot/addLike";
        System.out.println(post(str, param));

    }

    public static String get(String path, Map<String, Object> param) throws IOException {
        String url = path;
        if (param.size() > 0) {
            StringJoiner joiner = new StringJoiner("&");
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                if (entry.getKey() != null) {
                    joiner.add(entry.getKey() + "=" + String.valueOf(entry.getValue()));
                }
            }
            if (joiner.length() > 0) {
                url = path + "?" + joiner.toString();
            }
        }
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setConnectTimeout(30 * 1000);
        connection.setReadTimeout(60 * 1000);
        connection.setRequestProperty("Content-Type", "application/json;utf-8");
        connection.connect();
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            StringBuilder builder = new StringBuilder();
            char[] chars = new char[8192];
            try (InputStream is = connection.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                int i = 0;
                while ((i = reader.read(chars)) > 0) {
                    builder.append(chars, 0, i);
                }
                connection.disconnect();
                return builder.toString();
            }
        }
        connection.disconnect();
        return null;
    }

    public static String post(String path, Map<String, Object> param) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(path).openConnection();
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(30 * 1000);
        connection.setReadTimeout(60 * 1000);
        connection.setRequestProperty("Content-Type", "application/json;utf-8");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        Gson gson = new Gson();
        //发送消息
        String paramString = gson.toJson(param);
        try (OutputStream os = connection.getOutputStream()) {
            os.write(paramString.getBytes(StandardCharsets.UTF_8));
            os.flush();
        }
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            StringBuilder builder = new StringBuilder();
            char[] chars = new char[8192];
            int i = 0;
            try (InputStream is = connection.getInputStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                while ((i = reader.read(chars)) > 0) {
                    builder.append(chars, 0, i);
                }
                connection.disconnect();
                return builder.toString();
            }
        }
        connection.disconnect();
        return null;
    }
}
