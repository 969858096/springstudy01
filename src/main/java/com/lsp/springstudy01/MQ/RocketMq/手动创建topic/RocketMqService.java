package com.lsp.springstudy01.MQ.RocketMq.手动创建topic;

import cn.hutool.core.util.ObjectUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @FileName: RocketMqService
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/1/9 23:05
 */
@Component
@Slf4j
public class RocketMqService {

    @Autowired
    private RocketMQConfig config;

    @Value("${rocketmq.name-server}")
    private String nameServer;

    public void sendMessage(RocketParam rocketParam) throws Exception {


        //判断producer是否已经初始化
        DefaultMQProducer mqProducer = config.getProducerMap().get(rocketParam.getNameServer());
        if (ObjectUtil.isNull(mqProducer)) {
            synchronized (this) {
                mqProducer = config.createProduct(rocketParam);
                mqProducer.start();
            }
        }

        Set<String> topicActiveSet = config.getTopicActiveSet();
        if (!topicActiveSet.contains(rocketParam.getTopic())) {
            log.info("该topic 尚未激活 请先激活");
            throw new Exception("该topic 尚未激活 请先激活");
        }
        Message message = new Message();
        message.setTopic(rocketParam.getTopic());

        message.setBody(rocketParam.getData().toString().getBytes());

        message.setTags(rocketParam.getTag());
        mqProducer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("发送消息成功");
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("消息发送失败,{}", throwable.getMessage());
            }
        });
    }

    public void subTopic(RocketParam rocketParam) throws Exception {
        if (!config.getTopicActiveSet().contains(rocketParam.getTopic())) {
            log.info("topic 尚未激活 请先激活");
            throw new Exception("topic 尚未激活 请先激活");
        }

        DefaultMQPushConsumer mqPushConsumer = config.getConsumerMap().get(rocketParam.getNameServer());
        if (ObjectUtil.isNull(mqPushConsumer)) {
            mqPushConsumer = config.createConsumer(rocketParam);
        }
        config.subTopic(mqPushConsumer, rocketParam.getTopic());
    }

    public String activeTopic(RocketParam rocketParam) throws Exception {
        try {
            if (rocketParam.getNameServer().equals(nameServer)) {
                boolean result = RocketMQUtil.createTopic(nameServer, rocketParam.getTopic());
                if (result) {
                    config.getTopicActiveSet().add(rocketParam.getTopic());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "激活失败";
        }
        return "激活成功";
    }

    public void testCreateTopic() {

    }
}
