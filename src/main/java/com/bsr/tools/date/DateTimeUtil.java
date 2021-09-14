package com.bsr.tools.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 时间日期工具
 */
public class DateTimeUtil {
    /**
     * 日期转换
     *
     * @param yearToAdd
     * @param monthToAdd
     * @param dayToAdd
     * @return
     */
    public static LocalDate dateChange(Long yearToAdd, Long monthToAdd, Long dayToAdd) {
        LocalDate ld = LocalDate.now();
        if (yearToAdd != null) {
            ld = ld.plusYears(yearToAdd);
        }
        if (monthToAdd != null) {
            ld = ld.plusMonths(monthToAdd);
        }
        if (dayToAdd != null) {
            ld = ld.plusDays(dayToAdd);
        }
        return ld;
    }

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