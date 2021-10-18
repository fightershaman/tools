package com.bsr.tools.time;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 时间字符串工具
 */
public class StringUtil {

    /**
     * 按时间格式获取字符串
     *
     * @param localDate 日期
     * @param formatter 时间格式
     * @return
     */
    public static String localDateToString(LocalDate localDate, String formatter) {
        return localDate.format(DateTimeFormatter.ofPattern(formatter));
    }

    /**
     * UTC时间转换成系统默认时区的日期时间字符串
     *
     * @param ms        UTC时间(毫秒)
     * @param formatter 输出日期格式
     * @return 日期时间字符串, 异常返回null
     */
    public static String utcToString(long ms, String formatter) {
        return utcToString(ms, formatter, ZoneId.systemDefault());
    }

    /**
     * UTC时间转换成日期时间字符串
     *
     * @param ms        UTC时间(毫秒)
     * @param formatter 输出日期格式
     * @param zoneId    时区
     * @return 日期时间字符串, 异常返回null
     */
    public static String utcToString(long ms, String formatter, ZoneId zoneId) {
        try {
            Instant instant = Instant.ofEpochMilli(ms);
            return LocalDateTime.ofInstant(instant, zoneId).format(DateTimeFormatter.ofPattern(formatter));
        } catch (DateTimeException e) {
            e.printStackTrace();
        }
        return null;
    }
}