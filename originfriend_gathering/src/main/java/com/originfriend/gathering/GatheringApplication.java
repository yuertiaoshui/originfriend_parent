package com.originfriend.gathering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

@SpringBootApplication
//表示我们要使用SpringBoot的缓存
@EnableCaching
public class GatheringApplication {

   public static void main(String[] args) {
      SpringApplication.run(GatheringApplication.class, args);
   }

   @Bean
   public IdWorker idWorker(){
      return new IdWorker(1, 1);
   }
   
}