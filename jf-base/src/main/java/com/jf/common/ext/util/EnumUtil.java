package com.jf.common.ext.util;

import org.springframework.util.StringUtils;

/**
 * @author luoyb
 * Created on 2019/11/18
 */
public final class EnumUtil {

    public static <T extends Enum<T>> T toEnum(String name, Class<T> enumClass, T defaultValue) {
        if (!StringUtils.hasText(name))
            return defaultValue;
        try {
            return Enum.valueOf(enumClass, name);
        } catch (IllegalArgumentException e) {
            return defaultValue;
        }
    }

    public static <T extends Enum<T>> T toEnum(String name, Class<T> enumClass) {
        if (!StringUtils.hasText(name)) {
            return null;
        }
        try {
            return Enum.valueOf(enumClass, name);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("%s不能找到%s对应的枚举值", enumClass, name), e);
        }
    }

    public static <T extends Enum<T>> T toNotNullEnum(String name, Class<T> enumClass) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("要转换的name不能为空.");
        }

        try {
            return Enum.valueOf(enumClass, name);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("%s不能找到%s对应的枚举值", enumClass, name), e);
        }
    }

}
