package com.jiang.learn.zuul.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by liyujiang on 2020/2/4.
 */
@RequestMapping("/zuul")
@RestController
public class ZuulTestController {

    @GetMapping("/hello")
    public String sayHello(@RequestParam("name") String name) {
        return "hello, weclome " + name;
    }
}
