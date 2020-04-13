package com.jiang.service.api.rocketmq.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

/**
 * Created by liyujiang on 2020/1/18.
 */
@Service
@Slf4j
public class RocketMQProducerService implements InitializingBean {

    private static final String TAGS[] = {"tag-a", "tag-b",
            "tag-c", "tag-d", "tag-e"};


    DefaultMQProducer producer;

    public boolean init() throws Exception {

        //Instantiate with a producer group name.
        producer = new
                DefaultMQProducer("DefaultCluster");
        // Specify name server addresses.
        producer.setNamesrvAddr("106.52.124.74:9876");
        //Launch the instance.
        producer.start();

        return true;
    }

    /**
     * 同步发送
     *
     * @throws Exception
     */
    public void syncSend() throws Exception {
        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest-For-Jiang" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call syncSend message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
            log.info("result={} %n", sendResult);
        }
    }

    /**
     * 异步发送
     *
     * @throws Exception
     */
    public void asyncSend() throws Exception {
        SendCallback sendCallback = new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                log.info("async send success! sendResult={}", sendResult.toString());
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("async send failed! e={}", throwable);

            }
        };

        for (int i = 0; i < 100; i++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest-For-Jiang" /* Topic */,
                    "TagA" /* Tag */,
                    UUID.randomUUID().toString(),
                    ("Hello RocketMQ " +
                            i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call syncSend message to deliver message to one of brokers.
            producer.send(msg, sendCallback);
        }
    }

    public void sendOrderly() throws Exception {
        SendCallback sendCallback = new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

                log.info("orderly send success! queueId={}, sendResult={}",
                        sendResult.getMessageQueue().getQueueId(),
                        sendResult.toString());
            }

            @Override
            public void onException(Throwable throwable) {
                log.error("orderly send failed! e={}", throwable);

            }
        };

        //int queueNums = producer.getDefaultTopicQueueNums();
        Random random = new Random();

        for (int orderId = 0; orderId < 100; orderId++) {
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("TopicTest-For-Orderly" /* Topic */,
                    TAGS[random.nextInt(TAGS.length)] /* Tag */,
                    UUID.randomUUID().toString(),
                    ("Orderly RocketMQ " +
                            orderId).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            //Call syncSend message to deliver message to one of brokers.
            producer.send(msg, (mqs, msg1, arg) -> {
                Integer idx = (Integer) arg;
                return mqs.get(idx % mqs.size());
            }, orderId, sendCallback);
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
