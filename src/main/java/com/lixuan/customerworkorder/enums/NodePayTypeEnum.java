package com.lixuan.customerworkorder.enums;


import com.lixuan.customerworkorder.enumValidator.ValueEnum;

/**
 * 节点赔付类型
 *
 * @author lixuan
 * @date 2022-03-29 17:19
 */
public enum NodePayTypeEnum implements ValueEnum<String> {

    /**
     * 赔付客户
     */
    PAY_CUSTOMERS("payCustomers"),
    /**
     * 代理赔付
     */
    PROXY_PAY("proxyPay");


    private final String value;

    NodePayTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
