package com.originfriend.qa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author lxy
 * @date 2019/9/11 - 10:46
 */
@SpringBootApplication
public class QaApplication {
    public static void main(String[] args) {
        SpringApplication.run(QaApplication.class,args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker(1,1);
    }
}
