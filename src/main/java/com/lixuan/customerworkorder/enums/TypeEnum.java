package com.lixuan.customerworkorder.enums;

import com.lixuan.customerworkorder.enumValidator.ValueEnum;

/**
 * 客诉类型
 *
 * @author lixuan
 * @date 2022-03-29 10:45
 */
public enum TypeEnum implements ValueEnum<String> {

    /**
     * 丢件
     */
    LOST("lost"),
    /**
     * 索赔
     */
    CLAIM("claim"),
    /**
     * 破损
     */
    DAMAGED("damaged"),
    /**
     * 延误
     */
    DELAY("delay"),
    /**
     * 错货
     */
    MISMATCH("mismatch");


    private final String value;

    TypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
