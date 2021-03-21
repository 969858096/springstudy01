package com.lsp.springstudy01.MQ.RocketMq.手动创建topic;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.protocol.route.BrokerData;
import org.apache.rocketmq.remoting.exception.RemotingConnectException;
import org.apache.rocketmq.remoting.exception.RemotingSendRequestException;
import org.apache.rocketmq.remoting.exception.RemotingTimeoutException;

import java.util.Map;
import java.util.Objects;

/**
 * @FileName: Test
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/1/8 20:29
 */
public class Test {
    public static void main(String[] args) throws Exception {
        String nameServer = "81.68.113.52:9876";
        /*Map<String, BrokerData> allBrokerInfo = RocketMQUtil.getAllBrokerInfo(nameServer);
        System.out.println(allBrokerInfo);
        System.out.println("------------------------");
        System.out.println(RocketMQUtil.getClusterInfo(nameServer).getBrokerAddrTable());
        System.out.println(RocketMQUtil.getClusterInfo(nameServer).getClusterAddrTable());*/
        for (String s : Objects.requireNonNull(RocketMQUtil.getAllTopic(nameServer))) {
            System.out.println(s);
        }
    }
}
