package com.gjing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Gjing
 **/
@Configuration
public class PrintConfiguration {

    @Bean
    public PrintProcess printProcess() {
        return new PrintProcess();
    }
}
