package com.lsp.springstudy01.MQ.RocketMq.基本样例.同步发送;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
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
        // 创建消息，并指定Topic，Tag和消息体B
        for (int i = 0; i < 10000; i++) {
            Message msgA = new Message("topicA",
                    "*",
                    ("特康普A").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            Message msgB = new Message("topicB",
                    "*",
                    ("特康普B").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            Message msgC = new Message("topicC",
                    "*",
                    ("特康普C").getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            // 发送消息到一个Broker
            SendResult sendResultA = producer.send(msgA);
            SendResult sendResultB = producer.send(msgB);
            SendResult sendResultC = producer.send(msgC);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResultA);
            System.out.printf("%s%n", sendResultB);
            System.out.printf("%s%n", sendResultC);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
