package com.lixuan.customerworkorder.enums;

import com.lixuan.customerworkorder.enumValidator.NameValueEnum;

/**
 * @author lixuan
 * @date 2022-03-30 14:58
 */
public enum NodeStatusEnum implements NameValueEnum<Integer> {


    /**
     * 已核实
     */
    VERIFIED(0, "已核实"),
    /**
     * 转交审核
     */
    TRANSFER_REVIEW(1, "转交审核"),
    /**
     * 已审核（待出方案）
     */
    REVIEWED(2, "已审核"),
    /**
     * 转交方案
     */
    TRANSFER_PLAN(3, "转交方案"),
    /**
     * 已出方案（待确认方案） 处理方案
     */
    UNCONFIRMED_PLAN(4, "已出方案"),
    /**
     * 确认方案
     */
    CONFIRMED_PLAN(5, "确认方案"),
    /**
     * 赔付客户
     */
    PAY_CUSTOMER(6, "赔付客户"),
    /**
     * 代理赔付
     */
    PROXY_PAY(7, "代理赔付"),
    /**
     * 已办结
     */
    PROCESSED(8, "已办结");


    private final Integer value;

    private final String name;

    NodeStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }
}
