package com.jiang.service.outward.configuration;

import com.jiang.service.outward.feign.BaseApiEncoder;
import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liyujiang on 2020/2/8.
 */
@Slf4j
@Configuration
public class FeignConfiguration {

    @Bean
    public Contract feignContract(){
        return new Contract.Default();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /***
     * 自定义请求拦截器
     * @return
     */
    @Bean
    public FeignBasicAuthRequestInterceptor feignAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }

    static class FeignBasicAuthRequestInterceptor implements RequestInterceptor {
        public FeignBasicAuthRequestInterceptor() {
        }

        @Override
        public void apply(RequestTemplate template) {
            // 业务逻辑
            log.info("拦截器~~");
        }
    }



    /**
     * 解码器
     * @return
     */
    @Bean
    public Encoder encoder() {
        return new BaseApiEncoder();
    }
}
