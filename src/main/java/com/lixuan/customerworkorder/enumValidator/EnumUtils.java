package com.lixuan.customerworkorder.enumValidator;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.Objects;

/**
 * 枚举工具类
 *
 * @author lixuan
 * @date 2022-03-22 15:45
 */
@SuppressWarnings("unchecked")
public class EnumUtils {

    /**
     * 判断枚举值是否存在于指定枚举数组中
     *
     * @param enums  枚举
     * @param values 参数
     * @return boolean
     **/
    public static <T> boolean isExist(ValueEnum<T>[] enums, T values) {
        if (Objects.isNull(values)) {
            return false;
        }
        return Arrays.stream(enums).anyMatch(item -> values.equals(item.getValue()));
    }

    /**
     * 判断枚举值是否存在指定枚举类中
     *
     * @param enumClass 枚举类
     * @param value     枚举值
     * @param <E>       枚举类型
     * @param <V>       值类型
     * @return 是否存在
     **/
    public static <E extends java.lang.Enum<? extends ValueEnum<V>>, V> boolean isExist(Class<E> enumClass, V value) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(Objects::nonNull)
                .anyMatch(item -> ((ValueEnum<V>) item).getValue().equals(value));
    }

    /**
     * 根据枚举值获取对应的名称
     *
     * @param enums 枚举列表
     * @param value 枚举值
     * @param <T>   值类型
     * @return 枚举名称
     */
    public static <T> String getNameByValue(NameValueEnum<T>[] enums, T value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return Arrays.stream(enums)
                .filter(Objects::nonNull)
                .filter(item -> value.equals(item.getValue()))
                .findFirst()
                .map(NameValueEnum::getName)
                .orElse(null);
    }

    /**
     * 根据枚举名称获取对应的枚举值
     *
     * @param enums 枚举列表
     * @param name  枚举名
     * @return 枚举值
     */
    public static <T> T getValueByName(NameValueEnum<T>[] enums, String name) {
        if (StrUtil.isBlank(name)) {
            return null;
        }
        return Arrays.stream(enums)
                .filter(Objects::nonNull)
                .filter(item -> name.equals(item.getName()))
                .findFirst()
                .map(NameValueEnum<T>::getValue)
                .orElse(null);
    }

    /**
     * 根据枚举值获取对应的枚举对象
     *
     * @param enums 枚举列表
     * @param value 枚举值
     * @return 枚举对象
     */
    public static <E extends java.lang.Enum<? extends ValueEnum<V>>, V> E getEnumByValue(E[] enums, V value) {
        if (Objects.isNull(value)) {
            return null;
        }
        return Arrays.stream(enums).filter(Objects::nonNull).filter(item -> ((ValueEnum<V>) item).getValue().equals(value)).findFirst().orElse(null);
    }

    /**
     * 根据枚举值获取对应的枚举对象
     *
     * @param enumClass 枚举class
     * @param value     枚举值
     * @return 枚举对象
     */
    public static <E extends java.lang.Enum<? extends ValueEnum<V>>, V> E getEnumByValue(Class<E> enumClass, V value) {
        return getEnumByValue(enumClass.getEnumConstants(), value);
    }


}
