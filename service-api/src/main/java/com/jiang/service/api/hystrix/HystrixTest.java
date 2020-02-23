package com.jiang.service.api.hystrix;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jiang.service.api.hystrix.command.UserCommand;
import com.netflix.hystrix.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by liyujiang on 2020/2/21.
 */
@Slf4j
@Component
public class HystrixTest implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        test();
    }

    public void test() throws Exception {
        BlockingQueue<Map.Entry<HystrixCommand<Integer>, Future<Integer>>> queues = new LinkedBlockingQueue<>();

        for (int i = 0; i < 1500; i++) {
            Thread.sleep(10);
            HystrixCommand<Integer> command = new UserCommand(String.valueOf(i));
            Map.Entry<HystrixCommand<Integer>, Future<Integer>> entry = new HashMap.SimpleEntry<>(command, command.queue());
            queues.add(entry);
            //log.info("command:" + i + ", isCircuitBreakerOpen:" + command.isCircuitBreakerOpen());
        }

        for (Map.Entry<HystrixCommand<Integer>, Future<Integer>> entry : queues) {
            log.info("command:" + entry.getValue().get() + ", " + entry.getKey().toString());
        }
    }
}
