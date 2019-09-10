package com.gjing.quartz;

import cn.gjing.core.EnableSwagger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableSwagger
@EnableJpaAuditing
public class QuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class, args);
    }

}
