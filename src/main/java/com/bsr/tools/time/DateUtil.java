package com.bsr.tools.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Date工具类
 */
public class DateUtil {
    /**
     * LocalDateTime转换Date
     *
     * @param ldt
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime ldt) {
        Instant instant = ldt.atZone(ZoneUtil.systemZoneId()).toInstant();
        return Date.from(instant);
    }
}
