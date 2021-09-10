package com.bsr.tools.date;

import java.time.LocalDate;

/**
 * 时间日期工具
 */
public class DateTimeUtil {
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
}