package com.lsp.springstudy01;

import com.lsp.springstudy01.MQ.RocketMq.手动创建topic.RocketMQConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@MapperScan("com.lsp.springstudy01.MQ.RocketMq.手动创建topic")
public class Springstudy01Application implements CommandLineRunner {

    @Autowired
    private RocketMQConfig config;

    public static void main(String[] args) {
        SpringApplication.run(Springstudy01Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        config.rocketInit();
    }
}
