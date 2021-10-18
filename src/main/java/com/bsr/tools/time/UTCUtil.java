package com.bsr.tools.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * UTC时间工具
 */
public class UTCUtil {

    /**
     * 根据日期和时间获取UTC时间戳
     *
     * @param localDate 日期
     * @param localTime 时间
     * @param zone      时区
     * @return
     */
    public static long dateTimeToUTC(LocalDate localDate, LocalTime localTime, ZoneOffset zone) {
        LocalDateTime ldt = LocalDateTime.of(localDate, localTime);
        return dateTimeToUTC(ldt, zone);
    }

    /**
     * 获取DateTime对象的UTC时间(单位：毫秒)
     *
     * @param localDateTime
     * @param zone
     * @return
     */
    public static long dateTimeToUTC(LocalDateTime localDateTime, ZoneOffset zone) {
        return localDateTime.toInstant(zone).getEpochSecond() * 1000L;
    }

    /**
     * 获取当前系统时间时间戳
     *
     * @return
     */
    public static long nowUTC() {
        return System.currentTimeMillis();
    }

    /**
     * 日期时间转换UTC时间
     *
     * @param date      日期时间字符串
     * @param formatter 日期时间格式
     * @param zone      时区
     * @return UTC时间戳
     */
    public static long stringToUTC(String date, String formatter, ZoneOffset zone) {
        LocalDateTime ldt = LocalDateTime.parse(date, DateTimeFormatter.ofPattern(formatter));
        return ldt.toInstant(zone).getEpochSecond() * 1000L;
    }
}