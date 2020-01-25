package com.jiang.service.api.controller;

import com.jiang.service.api.service.rocketmq.impl.RocketMQConsumerService;
import com.jiang.service.api.service.rocketmq.impl.RocketMQProducerService;
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
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    RocketMQProducerService mqProducerService;
    @Autowired
    RocketMQConsumerService mqConsumerService;

    @Value("${swagger.name:unknown}")
    private String name;

    @RequestMapping("/getSwaggerName")
    public String getSwaggerName() {
        return "service-api:" + name;
    }

    @RequestMapping("/rocketmq/aysncSend")
    public String rocketSend() throws Exception {
        mqProducerService.asyncSend();
        return "success";
    }

    @RequestMapping("/rocketmq/sendOrderly")
    public String sendOrderly() throws Exception {
        mqProducerService.sendOrderly();
        return "success";
    }
}
