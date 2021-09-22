package com.bsr.tools.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 输入输出流工具
 */
public class StreamUtil {
    /**
     * 输入流转输出流
     *
     * @param inputStream
     * @param outputStream
     * @return
     * @throws IOException
     */
    public static int inputToOutput(InputStream inputStream, OutputStream outputStream) throws IOException {
        int i = 0;
        int j = 0;
        byte[] bytes = new byte[1024 * 8];
        while ((j = inputStream.read(bytes)) > 0) {
            outputStream.write(bytes);
            outputStream.flush();
            i += j;
        }
        return i;
    }
}
