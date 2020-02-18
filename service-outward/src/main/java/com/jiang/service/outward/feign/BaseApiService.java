package com.jiang.service.outward.feign;

import com.jiang.service.outward.configuration.FeignConfiguration;
import com.jiang.service.outward.enums.EurekaApplicationConstant;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author liyujiang
 * @date 2019/11/20 17:33
 * @Description
 */
@FeignClient(value = EurekaApplicationConstant.SERVICE_API,
        configuration = FeignConfiguration.class,
        fallback = BaseApiFallBack.class)
public interface BaseApiService {

    /**
     * 获取swagger名字
     * 
     * @return
     */
    @RequestLine(value = "POST /service/getSwaggerName")
    String getSwaggerName(@RequestParam("name") String name);
}
