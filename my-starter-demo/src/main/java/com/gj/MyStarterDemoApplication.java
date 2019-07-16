package com.gj;

import cn.gjing.core.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger
public class MyStarterDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyStarterDemoApplication.class, args);
    }

}
