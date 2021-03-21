package com.lsp.springstudy01.MQ.RocketMq.手动创建topic;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @FileName: DeviceData
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/1/13 21:46
 */
@Data
@Component
public class DeviceData implements Serializable {

    private String deviceId;
    private String imei;
    private String equipmentAgreement;
    private String message;

}
