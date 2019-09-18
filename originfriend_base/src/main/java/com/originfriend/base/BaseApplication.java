package com.originfriend.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author lxy
 * @date 2019/9/6 - 10:52
 */
@SpringBootApplication
//SpringBoot类加载原理：spring-web中有个META-INF.services文件夹，
    //文件夹下有个ServletContainerInitializer文件，该文件中的类会被自动装载。
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
