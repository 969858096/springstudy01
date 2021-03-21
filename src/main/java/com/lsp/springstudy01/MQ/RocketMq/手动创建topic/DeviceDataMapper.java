package com.lsp.springstudy01.MQ.RocketMq.手动创建topic;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeviceDataMapper {

    @Insert("insert into device_data values (#{device.deviceId},#{device.imei},#{device.equipmentAgreement},#{device}.message))")
    void insertDeviceData(@Param("device") DeviceData device);
}
