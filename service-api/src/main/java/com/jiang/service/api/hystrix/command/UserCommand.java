package com.jiang.service.api.hystrix.command;


import com.netflix.hystrix.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * Created by liyujiang on 2020/2/21.
 */
@Slf4j
public class UserCommand extends HystrixCommand<Integer> {

    private String commandKey;

    public UserCommand(String key) {
        // 当前Factory的key
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("UserCommand"))
                // 当前命令的key
                .andCommandKey(HystrixCommandKey.Factory.asKey(String.valueOf(key)))
                // 命令属性
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                        //允许熔断
                        .withCircuitBreakerEnabled(true)
                        //开启超时
                        .withExecutionTimeoutEnabled(true)
                        //超时时长
                        .withExecutionTimeoutInMilliseconds(2000)

                        //熔断策略 采用线程模式 还有信号量模式
                        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)

                        //当错误率超过50%，发生熔断
                        .withCircuitBreakerErrorThresholdPercentage(50)
                        /**
                         * 当请求超过10的时候 熔断器才开始允许工作，也就是请求量太低的话，不需要熔断器工作
                         * 和线程池的rollingStatisticalWindowBuckets 和 rollingStatisticalWindowInMilliseconds搭配使用
                         *
                         * rollingStatisticalWindowInMilliseconds / rollingStatisticalWindowBuckets = 1000
                         * 意思在1秒内会统计一次，1s的请求量要达到2个才达到要使用熔断器
                         *
                         * 使用滑动窗口算法
                         */
                        .withCircuitBreakerRequestVolumeThreshold(1)
                        //统计时间内的bucket数量
                        .withMetricsRollingStatisticalWindowBuckets(1)
                        //统计的时间区间
                        .withMetricsRollingStatisticalWindowInMilliseconds(2000)

                        // 当发生熔断了，过2秒回开始试探放量
                        .withCircuitBreakerSleepWindowInMilliseconds(5000))

                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(2)
                        .withMaximumSize(5)
                        .withKeepAliveTimeMinutes(2)
                        .withMaxQueueSize(1024)));

        this.commandKey = key;
    }


    @Override
    protected Integer run() throws Exception {
        Random random = new Random();
        int num = random.nextInt(3) + 1;

        Thread.sleep(10);
        if (num != 2) {
            throw new Exception("run error!");
        }

        log.info("run " + commandKey);
        return 0;
    }

    @Override
    protected Integer getFallback() {
        log.info("fallback: " + commandKey);
        return -1;
    }

    @Override
    public String toString() {
        return commandKey + " isCircuitBreakerOpen: " + this.isCircuitBreakerOpen() +
                ", isResponseThreadPoolRejected:" + this.isResponseThreadPoolRejected() +
                ", Metrics:" + this.getMetrics().getHealthCounts().toString();
    }
}
