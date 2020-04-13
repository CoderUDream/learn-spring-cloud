package com.jiang.service.outward.feign;

import feign.RequestLine;
import org.springframework.stereotype.Component;

/**
 * Created by liyujiang on 2020/2/8.
 */
@Component
public class BaseApiFallBack implements BaseApiService {
    @Override
    @RequestLine("POST /service/getSwaggerName")
    public String getSwaggerName(String name) {
        return "fallback~";
    }
}
