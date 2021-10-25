package com.bsr.tools.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 时间日期工具
 */
public class LocalDateTimeUtil {
    /**
     * Date转换LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return instant.atZone(ZoneUtil.systemZoneId()).toLocalDateTime();
    }
}