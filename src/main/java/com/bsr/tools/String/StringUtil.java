package com.bsr.tools.String;

/**
 * 字符串工具类
 */
public class StringUtil {
    /**
     * 转换字符串，如果为null则转化为空字符串
     *
     * @param obj
     * @return
     */
    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    /**
     * 是否是空白字符串
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return str == null || "".equals(str.trim());
    }
}
