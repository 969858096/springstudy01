package com.lsp.springstudy01.MQ.RocketMq.手动创建topic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.rocketmq.common.message.Message;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @FileName: RocketParam
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/1/10 21:01
 */
@Data
@Component
public class RocketParam implements Serializable {

    private String nameServer;
    private String topic;
    private String tag;
    private String accessKey;
    private String secretKey;
    //private String messages;
    private DeviceData data;
}
