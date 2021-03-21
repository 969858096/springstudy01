package com.lsp.springstudy01.MQ.RocketMq.基本样例.handler;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;


/**
 * @FileName: DataHandler
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/3/20 22:13
 */
public class DataHandler implements Runnable {
    private Message message;
    private DefaultMQProducer producer;

    public DataHandler(Message message, DefaultMQProducer producer) {
        this.message = message;
        this.producer = producer;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10000; i++) {
                producer.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
