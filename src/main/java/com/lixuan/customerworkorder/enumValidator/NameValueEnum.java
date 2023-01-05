package com.lixuan.customerworkorder.enumValidator;

/**
 * 带有枚举值以枚举名称的枚举接口
 *
 * @author lixuan
 * @date 2022-03-22 15:43
 */
public interface NameValueEnum<T> extends ValueEnum<T> {

    /**
     * 获取枚举名称
     *
     * @return String 枚举名
     * @author lixuan
     * @date 2022/3/22 15:44
     **/
    String getName();
}
