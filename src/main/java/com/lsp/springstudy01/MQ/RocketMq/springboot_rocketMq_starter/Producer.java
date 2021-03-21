/*
package com.lsp.springstudy01.MQ.RocketMq.springboot_rocketMq_starter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @author : zhangwei
 * @description : mq消息发送服务
 * @date: 2020-08-20 19:11
 *//*

@Slf4j
@Service
@RestController
public class Producer {

    //@Value(value = "${rocketmq.producer.topic}")
    private String topic;

    @Value(value = "${rocketmq.producer.group}")
    private String group;

    @Autowired(required = false)
    private MqSendService mqSendService;

    @GetMapping("/test-rocketmq/sendMsg")
    public String testSendMsg() {
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        mqSendService.send(list,topic,group);
        return "send message success";
    }
}


*/
