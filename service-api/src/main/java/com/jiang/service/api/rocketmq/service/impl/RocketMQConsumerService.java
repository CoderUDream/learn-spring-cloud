package com.jiang.service.api.rocketmq.service.impl;

import com.jiang.service.api.rocketmq.constants.Contants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.consumer.rebalance.AllocateMessageQueueAveragelyByCircle;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Created by liyujiang on 2020/1/22.
 */
@Slf4j
@Service
public class RocketMQConsumerService {
    private static final String TAGS[] = {"tag-a", "tag-b",
            "tag-c", "tag-d", "tag-e"};

    private DefaultMQPushConsumer mqConsumer;

    @PostConstruct
    void init() throws Exception {
        mqConsumer = new DefaultMQPushConsumer(Contants.MQ_GROUP);

        mqConsumer.setNamesrvAddr("106.52.124.74:9876");
        mqConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        mqConsumer.setMessageModel(MessageModel.BROADCASTING);
        mqConsumer.setAllocateMessageQueueStrategy(new AllocateMessageQueueAveragelyByCircle());

        mqConsumer.subscribe("TopicTest-For-Orderly", StringUtils.join(
                Arrays.asList(TAGS).stream().limit(3).collect(Collectors.toList()),
                " || "));
        AtomicLong consumeTimes = new AtomicLong(0);
        mqConsumer.registerMessageListener((MessageListenerOrderly) (msgs, context) -> {
            context.setAutoCommit(false);
            consumeTimes.incrementAndGet();
            log.info(Thread.currentThread().getName() + " Receive New Messages: " + msgs);

            if ((consumeTimes.get() % 2) == 0) {
                return ConsumeOrderlyStatus.SUCCESS;
            } else if ((consumeTimes.get() % 5) == 0) {
                context.setSuspendCurrentQueueTimeMillis(3000);
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }

            return ConsumeOrderlyStatus.SUCCESS;
        });
        mqConsumer.start();
    }


}
