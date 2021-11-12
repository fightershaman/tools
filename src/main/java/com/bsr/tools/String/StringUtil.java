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

    /**
     * 截取字符串中间部分
     *
     * @param str
     * @param prefix
     * @param suffix
     * @return
     */
    public static String intercept(String str, String prefix, String suffix) {
        int pre = str.indexOf(prefix);
        if (pre < 0) {
            return null;
        }
        int suf = str.indexOf(suffix, pre);
        if (suf < 0) {
            return null;
        }
        int preLength = prefix.length();
        str = str.substring(pre + preLength, suf);
        int index = str.lastIndexOf(prefix);
        if (index >= 0) {
            str = str.substring(index + preLength);
        }
        return str;
    }

}
