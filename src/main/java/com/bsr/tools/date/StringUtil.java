package com.bsr.tools.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 时间字符串工具
 *
 * @Title: StringTools
 * @Project:
 * @date: 2020-09-03 16:03
 * @author: zhanghaofei
 * @Description:
 */
public class StringUtil {

    /**
     * 输出间隔参数的日期
     *
     * @param localDate 日期
     * @param formatter 时间格式
     * @return
     */
    public static String changeDateToDateString(LocalDate localDate, String formatter) {
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