/*
package com.lsp.springstudy01.MQ.RocketMq.springboot_rocketMq_starter;

import com.lsp.springstudy01.MQ.RocketMq.springboot_rocketMq_starter.RocketMqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;


*/
/**
 * @author : zhangwei
 * @description : ed
 * @date: 2020-08-20 16:29
 *//*

@Slf4j
@Component
// topic需要和生产者的topic一致，consumerGroup属性是必须指定的，内容可以随意
@RocketMQMessageListener(topic = "${rocketmq.consume.topic}", consumerGroup = "${rocketmq.consume.group}")
public  class Consumer implements  RocketMQListener<RocketMqMessage> {

    @Override
    public void onMessage(RocketMqMessage message) {
        log.info("======我收到了消息,消息内容为:{}",message);
    }
}


*/
