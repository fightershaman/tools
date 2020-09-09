package com.bsr.tools.date;

import java.time.LocalDate;

/**
 * Copyright  2020Yoongoo
 *
 * @Title: LocalUtil
 * @Project:
 * @date: 2020-09-04 17:48
 * @author: zhanghaofei
 * @Description:
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