package com.glucose;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 扫描mapper接口
@MapperScan("com.glucose.mapper")
@SpringBootApplication
public class GlucoseMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GlucoseMonitorApplication.class, args);
    }
}