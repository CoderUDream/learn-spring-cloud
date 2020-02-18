package com.jiang.service.outward.controller;

import com.jiang.service.outward.feign.BaseApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyujiang
 * @date 2019/11/20 18:09
 * @Description
 */
@RestController
@RequestMapping("/outward")
public class OutwardController {

    @Autowired
    BaseApiService apiService;

    @RequestMapping("/getSwaggerName")
    public String getSwaggerName() {
        return apiService.getSwaggerName("jiang");
    }
}
