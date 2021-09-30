package com.bsr.tools.file;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.*;
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
        return PosixFilePermissions.fromString(permission);
    }

    /**
     * 文件权限（数字模式）
     *
     * @param permission 权限
     * @return
     */
    public static Set<PosixFilePermission> numericMode(String permission) {
        if (permission.length() == 3) {
            String permissionString = permissionNumericToString(permission.charAt(0)) + permissionNumericToString(permission.charAt(1)) + permissionNumericToString(permission.charAt(2));
            return mode(permissionString);
        } else {
            throw new IllegalArgumentException("Invalid mode");
        }
    }

    /**
     * 修改文件用户
     *
     * @param pathString 文件地址
     * @param userName   用户
     * @throws IOException
     */
    public static void changeOwner(String pathString, String userName) throws IOException {
        changeOwner(pathString, userName, userName);
    }

    /**
     * 修改文件用户和用户组
     *
     * @param pathString 文件地址
     * @param userName   用户
     * @param groupName  用户分组
     * @throws IOException
     */
    public static void changeOwner(String pathString, String userName, String groupName) throws IOException {
        Path path = Paths.get(pathString);
        UserPrincipalLookupService service = FileSystems.getDefault().getUserPrincipalLookupService();
        UserPrincipal userPrincipal = service.lookupPrincipalByName(userName);
        GroupPrincipal groupPrincipal = service.lookupPrincipalByGroupName(groupName);
        PosixFileAttributeView pfav = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        pfav.setOwner(userPrincipal);
        pfav.setGroup(groupPrincipal);
    }

    /**
     * 修改文件权限
     *
     * @param pathString    文件地址
     * @param permissionSet 权限集合
     * @throws IOException
     */
    public static void changeMode(String pathString, Set<PosixFilePermission> permissionSet) throws IOException {
        Path path = Paths.get(pathString);
        PosixFileAttributeView pfav = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        pfav.setPermissions(permissionSet);
    }

    /**
     * 权限数字字符转换成字符串
     *
     * @param c 数字字符
     * @return 权限字符串
     */
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
