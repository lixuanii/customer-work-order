package com.lixuan.customerworkorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.lixuan.customerworkorder.mapper")
@SpringBootApplication
public class CustomerWorkOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerWorkOrderApplication.class, args);
    }

}
