package com.jiang.service.api.rocketmq.controller;

import com.jiang.service.api.rocketmq.service.impl.RocketMQConsumerService;
import com.jiang.service.api.rocketmq.service.impl.RocketMQProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyujiang
 * @date 2019/11/16 14:34
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/mq")
public class MQController {

    @Autowired
    RocketMQProducerService mqProducerService;
    @Autowired
    RocketMQConsumerService mqConsumerService;

    @RequestMapping("/asyncSend")
    public String rocketSend() throws Exception {
        mqProducerService.asyncSend();
        return "success";
    }

    @RequestMapping("/sendOrderly")
    public String sendOrderly() throws Exception {
        mqProducerService.sendOrderly();
        return "success";
    }
}
