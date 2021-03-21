package com.lsp.springstudy01.MQ.RocketMq.手动创建topic;

import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @FileName: RocketController
 * @Description:
 * @AuthOr: lsp
 * @Date: 2021/1/9 20:41
 */
@RestController
@RequestMapping("TestRocket")
public class RocketController {

    @Autowired
    private RocketMqService rocketMqService;

    @Autowired
    private RocketMQConfig config;


    @PostMapping("sendMessage")
    public String sendMessage(@RequestBody RocketParam rocketParam) {
        try {
            rocketMqService.sendMessage(rocketParam);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }

    @PostMapping("activeTopicAndSub")
    public String activeTopicAndSub(@RequestBody RocketParam rocketParam) {
        try {
            rocketMqService.subTopic(rocketParam);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "success";
    }

    /*@PostMapping("active")
    public String activeTopic(@RequestBody RocketParam rocketParam) {
        try {
            rocketMqService.activeTopic(rocketParam);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        return "success";
    }
*/

}
