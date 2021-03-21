package com.lsp.springstudy01.MQ.RocketMq.基本样例.handler;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * @FileName: MySendCallBack
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/3/20 22:17
 */
public class MySendCallBack implements SendCallback {
    @Override
    public void onSuccess(SendResult sendResult) {
        System.out.println("发送成功:"+sendResult.getSendStatus());
    }

    @Override
    public void onException(Throwable e) {
        System.out.println("发送异常"+e.getMessage());
    }
}
