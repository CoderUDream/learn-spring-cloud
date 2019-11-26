package com.jiang.service.outward.feign;

import com.jiang.service.outward.enums.EurekaApplicationConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author liyujiang
 * @date 2019/11/20 17:33
 * @Description
 */
@FeignClient(EurekaApplicationConstant.SERVICE_API)
public interface BaseApiService {

    /**
     * 获取swagger名字
     * 
     * @return
     */
    @GetMapping("/service/getSwaggerName")
    String getSwaggerName();
}
