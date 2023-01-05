package com.lixuan.customerworkorder.enums;


import com.lixuan.customerworkorder.enumValidator.ValueEnum;


/**
 * 赔付客户status
 *
 * @author lixuan
 * @date 2022-04-01 14:36
 */
public enum ProxyPayStatusEnum implements ValueEnum<Integer> {

    /**
     * 无需赔付
     */
    No_NEED_TO_PAY(0),
    /**
     * 已赔付
     */
    PAID(1),
    /**
     * 未赔付
     */
    NOT_PAID(2);


    public final Integer value;

    ProxyPayStatusEnum(Integer value) {
        this.value = value;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

}
