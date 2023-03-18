package com.hwly.tokenapp;

import com.hwly.tokenapp.token.AppTokenManage;
import com.hwly.tokenapp.token.RedisTokenDao;
import com.hwly.tokenapp.utils.RedisUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TokenappApplication {

    public static void main(String[] args) {
        SpringApplication.run(TokenappApplication.class, args);
    }

    @Bean
    public AppTokenManage appTokenManage(){return new AppTokenManage();}

    @Bean
    public RedisTokenDao redisTokenDao(){return new RedisTokenDao();}

    @Bean
    public RedisUtil redisUtil(){return new RedisUtil();}

}
