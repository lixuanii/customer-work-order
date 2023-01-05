package com.lixuan.customerworkorder.enums;


import com.lixuan.customerworkorder.enumValidator.NameValueEnum;

/**
 * 客诉状态
 *
 * @author lixuan
 * @date 2022-03-29 14:48
 */
public enum InfoStatusEnum implements NameValueEnum<Integer> {


    /**
     * 待核实
     */
    UNVERIFIED(0, "待核实"),
    /**
     * 待审核（已核实）
     */
    UNREVIEWED(1, "待审核"),
    /**
     * 已审核（待出方案）
     */
    REVIEWED(2, "已审核"),
    /**
     * 待确认方案（已出方案）
     */
    UNCONFIRMED_PLAN(3, "待确认方案"),
    /**
     * 待办结（确认方案）
     */
    CONFIRMED_PLAN(4, "确认方案"),
    /**
     * 已处理
     */
    PROCESSED(5, "已处理");

    private final Integer value;

    private final String name;

    InfoStatusEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
