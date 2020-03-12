package com.dhq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@MapperScan("com.dhq.mapper")
@SpringBootApplication
public class PreventChangesApplication {
    public static void main(String[] args) {
        SpringApplication.run(PreventChangesApplication.class, args);
    }
}
