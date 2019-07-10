package com.gj.commandline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;

/**
 * @author Gjing
 */
//@Component
@Order(1)
public class MyStartRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("自己定义的第一个启动后事件开始执行。。。。。。。");
    }
}
