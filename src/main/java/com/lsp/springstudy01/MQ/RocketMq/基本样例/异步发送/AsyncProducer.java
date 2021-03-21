package com.lsp.springstudy01.MQ.RocketMq.基本样例.异步发送;

import com.lsp.springstudy01.MQ.RocketMq.基本样例.handler.DataHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @FileName: AsyncProducer
 * @Description:
 * @AuthOr: lsp
 * @Date: 2020/12/31 14:48
 */
@Slf4j
public class AsyncProducer {
    private static final String nameServer = "81.68.113.52:9876";

    public static void main(String[] args) throws Exception {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("myProducer1");
        // 设置NameServer的地址
        producer.setNamesrvAddr(nameServer);
        // 启动Producer实例
        producer.start();

        long begin = System.currentTimeMillis() / 1000;

        Message msgA = new Message("topicA", "*", ("我爱韦琪琪A").getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message msgB = new Message("topicB", "*", ("我爱韦琪琪B").getBytes(RemotingHelper.DEFAULT_CHARSET));
        Message msgC = new Message("topicC", "*", ("我爱韦琪琪C").getBytes(RemotingHelper.DEFAULT_CHARSET));

        for (int i = 0; i < 100000; i++) {
            producer.sendOneway(msgA);
            producer.sendOneway(msgB);
            producer.sendOneway(msgC);
        }

        /*for (int i = 0; i < 10000; i++) {
            producer.send(msgA);
            producer.send(msgB);
            producer.send(msgC);
        }
*/

        //threadPool.submit(new DataHandler(msgA,producer));
        //threadPool.submit(new DataHandler(msgB,producer));
        //threadPool.submit(new DataHandler(msgC,producer));
        long end = System.currentTimeMillis() / 1000;

        System.out.println("时间：" + (end - begin) + "秒");
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
        threadPool.shutdown();
    }
}
