package com.example.safedistrict;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement   //开启事务功能，等同于xml配置方式的 <tx:annotation-driven />
@SpringBootApplication
public class SafedistrictApplication  extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SafedistrictApplication.class, args);
    }
}
