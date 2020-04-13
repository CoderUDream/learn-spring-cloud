package com.jiang.service.outward.feign;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;

/**
 * Created by liyujiang on 2020/2/8.
 */
@Slf4j
public class BaseApiEncoder implements Encoder {

    @Override
    public void encode(Object o, Type type, RequestTemplate requestTemplate) throws EncodeException {
        log.info("feign encoder");
    }
}
