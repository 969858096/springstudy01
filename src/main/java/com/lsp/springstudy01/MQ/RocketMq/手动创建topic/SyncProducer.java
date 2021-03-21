package com.lsp.springstudy01.MQ.RocketMq.手动创建topic;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @FileName: SyncProducer
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/12/30 21:08
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("myProducer");
        // 设置NameServer的地址
        producer.setNamesrvAddr("81.68.113.52:9876");
        // 启动Producer实例
        producer.start();
        // 创建消息，并指定Topic，Tag和消息体
        //System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, "81.68.113.52:9876");
        //RocketMQUtil.createTopic("81.68.113.52:9876","createTopic2");
        Message msg = new Message("topic3" /* Topic */,
                "TagB" /* Tag */,
                ("生产消息啦啦啦5").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
        );
        // 发送消息到一个Broker
        SendResult sendResult = producer.send(msg);
        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", sendResult);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
