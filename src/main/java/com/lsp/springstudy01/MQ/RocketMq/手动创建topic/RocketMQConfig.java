package com.lsp.springstudy01.MQ.RocketMq.手动创建topic;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.lsp.springstudy01.utils.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.acl.common.AclClientRPCHook;
import org.apache.rocketmq.acl.common.SessionCredentials;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragely;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.MixAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.RPCHook;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;

/**
 * @FileName: RocketMQConfig
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/1/8 19:53
 */
@Configuration
@Slf4j
public class RocketMQConfig implements InitializingBean {
    // 必须保证这里能获取到正确的namesrv地址，否则再次gg
    @Value("${rocketmq.name-server}")
    private String nameServer;

    @Value("${rocketmq.producer.group}")
    private String producerGroup;

    @Value("${rocketmq.consumer.group}")
    private String consumerGroup;

    @Value("${rocketmq.default.instance}")
    private String instance;
    @Value("${rocketmq.product.access-key}")
    private String accessKey;
    @Value("${rocketmq.product.secret-key}")
    private String secretKey;
    @Autowired
    private DeviceDataMapper dataMapper;

    private final static Object lock = new Object();
    private static DefaultMQProducer producer;
    private static DefaultMQPushConsumer consumer;
    private static Map<String, DefaultMQProducer> producerMap;
    private static Map<String, DefaultMQPushConsumer> consumerMap;
    private static Set<String> set;


    public void rocketInit() throws Exception {
        this.afterPropertiesSet();
        this.getConsumer();
        this.getProducer();
        this.initRocketMap();
        this.getTopicActiveSet();
        this.start();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.setProperty(MixAll.NAMESRV_ADDR_PROPERTY, nameServer);
    }

    @Bean
    public DefaultMQPushConsumer getConsumer() {
        if (consumer == null) {
            synchronized (lock) {
                DefaultMQPushConsumer mqConsumer = new DefaultMQPushConsumer(consumerGroup, getAclRPCHook(accessKey, secretKey), new AllocateMessageQueueAveragely());
                mqConsumer.setInstanceName(nameServer + instance);
                mqConsumer.setNamesrvAddr(nameServer);
                mqConsumer.setVipChannelEnabled(false);
                mqConsumer.setMessageModel(MessageModel.CLUSTERING);
                mqConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
                consumer = mqConsumer;
            }
        }
        return consumer;
    }

    @Bean
    public DefaultMQProducer getProducer() throws MQClientException {
        if (producer == null) {
            synchronized (lock) {
                DefaultMQProducer mqProducer = new DefaultMQProducer(producerGroup, getAclRPCHook(accessKey, secretKey));
                mqProducer.setInstanceName(nameServer + instance);
                mqProducer.setNamesrvAddr(nameServer);
                mqProducer.setRetryTimesWhenSendAsyncFailed(3);
                mqProducer.setSendMessageWithVIPChannel(false);
                mqProducer.setVipChannelEnabled(false);
                producer = mqProducer;
            }
        }
        return producer;
    }


    public void initRocketMap() {
        getConsumerMap();
        getProducerMap();
    }

    public Map<String, DefaultMQPushConsumer> getConsumerMap() {
        if (ObjectUtil.isEmpty(consumerMap)) {
            synchronized (lock) {
                consumerMap = new HashMap<>();
                consumerMap.put(nameServer, consumer);
            }
        }
        return consumerMap;
    }

    public Map<String, DefaultMQProducer> getProducerMap() {
        if (ObjectUtil.isEmpty(producerMap)) {
            synchronized (lock) {
                producerMap = new HashMap<>();
                producerMap.put(nameServer, producer);
            }
        }
        return producerMap;
    }

    public Set<String> getTopicActiveSet() throws Exception {
        if (ObjectUtil.isNull(set)) {
            synchronized (lock) {
                Set<String> setNew = new HashSet<>();
                setNew.add("topic1");
                setNew.add("topic2");
                for (String topic : setNew) {
                    this.subTopic(consumer, topic);
                }

              /*  List<String> allTopic = RocketMQUtil.getAllTopic(nameServer);
                assert allTopic != null;
                setNew.addAll(allTopic);*/
                set = setNew;
            }
        }
        return set;
    }


    public void start() {
        try {
            consumer.start();
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }


    @PreDestroy
    public void shutdown() {
        try {
            consumer.shutdown();
            producer.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("关闭RocketMq服务的Producer及Consumer服务异常：{}", e.getMessage());
        }
        log.info("关闭RocketMq服务的Producer及Consumer");
    }

    public void subTopic(DefaultMQPushConsumer mqPushConsumer, String topic) {
        try {
            mqPushConsumer.subscribe(topic, "*");
            mqPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    String message = new String(msgs.get(0).getBody());
                    log.info("订阅到消息--topic:{},message:{}", msgs.get(0).getTopic(), message);
                    try {
                        String substring = message.substring(11, message.length() - 1);
                        System.out.println("substring:"+substring);
                        StringBuilder sb = new StringBuilder();
                        String[] split = substring.split(",");


                        for (int i = 0; i < split.length; i++) {
                            String[] split1 = split[i].split("=");
                            if (i == split.length-1){
                                sb.append(split1[0] + ":" + split1[1]);
                            }else {
                                sb.append(split1[0] + ":" + split1[1]+",");
                            }
                        }
                        System.out.println("json:"+sb.toString());
                        DeviceData deviceData = new DeviceData();
                        JSONObject jsonObject = JSON.parseObject(sb.toString());
                        System.out.println(jsonObject.toString());
                       /* deviceData.setDeviceId(MyUtils.createId());
                        deviceData.setImei(MyUtils.createId());
                        String equipmentAgreement = jsonObject.get("equipmentAgreement").toString();
                        String data = jsonObject.get("message").toString();
                        System.out.println(equipmentAgreement);
                        System.out.println(data);*/
                        //deviceData.setEquipmentAgreement();
                        //deviceData.setMessage();
                        dataMapper.insertDeviceData(deviceData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("数据入库失败-----------------");
                    }
                    log.info("数据成功入库----------");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public DefaultMQProducer createProduct(RocketParam rocketParam) {
        DefaultMQProducer mqProducer = null;
        synchronized (lock) {
            mqProducer = new DefaultMQProducer(producerGroup, getAclRPCHook(rocketParam.getAccessKey(), rocketParam.getSecretKey()));
            mqProducer.setInstanceName(rocketParam.getNameServer() + instance);
            mqProducer.setNamesrvAddr(rocketParam.getNameServer());
            mqProducer.setRetryTimesWhenSendAsyncFailed(3);
            mqProducer.setSendMessageWithVIPChannel(false);
            mqProducer.setVipChannelEnabled(false);
            producer = mqProducer;
            producerMap.put(nameServer, producer);
        }
        return mqProducer;
    }

    public DefaultMQPushConsumer createConsumer(RocketParam rocketParam) throws MQClientException {
        DefaultMQPushConsumer mqPushConsumer = null;
        synchronized (lock) {
            mqPushConsumer = new DefaultMQPushConsumer(consumerGroup, getAclRPCHook(accessKey, secretKey), new AllocateMessageQueueAveragely());
            mqPushConsumer.setInstanceName(rocketParam.getNameServer() + instance);
            mqPushConsumer.setNamesrvAddr(rocketParam.getNameServer());
            mqPushConsumer.setVipChannelEnabled(false);
            mqPushConsumer.setMessageModel(MessageModel.CLUSTERING);
            mqPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
            mqPushConsumer.start();
            mqPushConsumer.subscribe(rocketParam.getTopic(), "*");
            mqPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                                ConsumeConcurrentlyContext context) {
                    String message = new String(msgs.get(0).getBody());
                    log.info("订阅到消息--topic:{},message:{}", msgs.get(0).getTopic(), message);
                    try {
                        JSONObject jsonObject = JSON.parseObject(message);
                        DeviceData deviceData = new DeviceData();
                        deviceData.setDeviceId(MyUtils.createId());
                        deviceData.setImei(MyUtils.createId());
                        String equipmentAgreement = jsonObject.get("equipmentAgreement").toString();
                        String data = jsonObject.get("message").toString();
                        System.out.println(equipmentAgreement);
                        System.out.println(data);
                        //deviceData.setEquipmentAgreement();
                        //deviceData.setMessage();
                        dataMapper.insertDeviceData(deviceData);
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error("数据入库失败-----------------");
                    }
                    log.info("数据成功入库----------");
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            set.add(rocketParam.getTopic());
            consumerMap.put(nameServer, mqPushConsumer);
        }
        return mqPushConsumer;
    }

    static RPCHook getAclRPCHook(String accessKey, String secretKey) {
        return new AclClientRPCHook(new SessionCredentials(accessKey, secretKey));
    }
}
