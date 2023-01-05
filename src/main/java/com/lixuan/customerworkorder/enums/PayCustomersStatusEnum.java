package com.lixuan.customerworkorder.enums;


import com.lixuan.customerworkorder.enumValidator.ValueEnum;


/**
 * 赔付客户status
 *
 * @author lixuan
 * @date 2022-04-01 14:36
 */
public enum PayCustomersStatusEnum implements ValueEnum<Integer> {

    /**
     * 未赔付
     */
    NOT_PAID(0),

    /**
     * 已赔付
     */
    PAID(1);


    public final Integer value;

    PayCustomersStatusEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
