package com.bsr.tools.time;

import java.time.LocalDate;

public class LocalDateUtil {
    /**
     * 日期转换
     *
     * @param yearToAdd
     * @param monthToAdd
     * @param dayToAdd
     * @return
     */
    public static LocalDate dateChange(Long yearToAdd, Long monthToAdd, Long dayToAdd) {
        return dateChange(LocalDate.now(), yearToAdd, monthToAdd, dayToAdd);
    }

    /**
     * 日期转换
     *
     * @param localDate
     * @param yearToAdd
     * @param monthToAdd
     * @param dayToAdd
     * @return
     */
    public static LocalDate dateChange(LocalDate localDate, Long yearToAdd, Long monthToAdd, Long dayToAdd) {
        if (yearToAdd != null) {
            localDate = localDate.plusYears(yearToAdd);
        }
        if (monthToAdd != null) {
            localDate = localDate.plusMonths(monthToAdd);
        }
        if (dayToAdd != null) {
            localDate = localDate.plusDays(dayToAdd);
        }
        return localDate;
    }
}
