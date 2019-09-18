package com.originfriend.recruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author lxy
 * @date 2019/9/10 - 12:10
 */
@SpringBootApplication
public class RecruitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
