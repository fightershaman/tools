package com.bsr.tools.file;

import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

/**
 * unix文件权限工具
 */
public class UnixFilePermissionUtil {
    /**
     * 文件权限（字符串模式）
     *
     * @param permission 权限字符串
     * @return
     */
    public static Set<PosixFilePermission> mode(String permission) {
        Set<PosixFilePermission> set = null;
        try {
            set = PosixFilePermissions.fromString(permission);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            set = new HashSet<>();
        }
        return set;
    }

    /**
     * 文件权限（数字模式）
     *
     * @param permission 权限
     * @return
     */
    public static Set<PosixFilePermission> numericMode(String permission) {
        Set<PosixFilePermission> set = null;
        if (permission.length() == 3) {
            try {
                String permissionString = permissionNumericToString(permission.charAt(0)) + permissionNumericToString(permission.charAt(1)) + permissionNumericToString(permission.charAt(2));
                set = mode(permissionString);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                set = new HashSet<>();
            }
        } else {
            set = new HashSet<>();
        }
        return set;
    }


    private static String permissionNumericToString(char c) {
        switch (c) {
            case '0':
                return "---";
            case '1':
                return "--x";
            case '2':
                return "-w-";
            case '3':
                return "-wx";
            case '4':
                return "r--";
            case '5':
                return "r-x";
            case '6':
                return "rw-";
            case '7':
                return "rwx";
            default:
                throw new IllegalArgumentException("Invalid mode");
        }
    }
}
