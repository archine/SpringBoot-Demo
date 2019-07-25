package com.gj;

import cn.gjing.core.EnableSwagger;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger
@MapperScan(basePackages = "com.gj.repository")
public class MybatisPostgreApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPostgreApplication.class, args);
    }

}
