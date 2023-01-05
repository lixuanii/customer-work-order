package com.lixuan.customerworkorder.utils;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusGeneratorTest {

    public static void main(String[] args) {
        MybatisPlusGenerator.generator(new String[]{"biz_"},"biz_customer_complaint_info",
                "biz_customer_complaint_node",
                "biz_customer_complaint_type");
    }
}