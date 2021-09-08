package com.bsr.tools.date;

import java.time.ZoneId;

/**
 * 时区工具类
 */
public class ZoneUtil {
    /**
     * 获取系统时区
     *
     * @return
     */
    public static ZoneId systemZoneId() {
        return ZoneId.systemDefault();
    }
}
