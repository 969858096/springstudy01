package com.lsp.springstudy01.MQ.RocketMq.基本样例;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @FileName: Consumer_负载均衡消费
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/12/30 21:10
 */
public class Consumer_负载均衡消费1 {

    private static String[] arr = {"topicA","topicB","topicC"};
    private static final String nameServer = "81.68.113.52:9876";
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者,指定组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("wqq");
        consumer.setInstanceName("instance@1");
        consumer.setNamesrvAddr(nameServer);
        //负载均衡模式消费
        consumer.setMessageModel(MessageModel.CLUSTERING);
        for (String topic : arr) {
            consumer.subscribe(topic,"*");
        }
        // 注册回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,ConsumeConcurrentlyContext context) {
                String s = new String(msgs.get(0).getBody());
                System.out.println("独立的线程22"+Thread.currentThread().getName()+"主题:"+context.getMessageQueue().getTopic()+" 消息："+s);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //启动消息者
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
