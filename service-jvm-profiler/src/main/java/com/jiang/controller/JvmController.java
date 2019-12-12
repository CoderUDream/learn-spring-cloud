package com.jiang.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liyujiang on 2019/12/10.
 */
@Slf4j
@RestController
public class JvmController {


    ExecutorService threadPool = Executors.newFixedThreadPool(2);

    class OOMObject {
        // 1k
        byte[] bytes = new byte[1024];
    }

    @GetMapping("/heap")
    public String heap() {
        threadPool.submit(() -> {
            List<Object> list = new ArrayList();
            try {
                while (true) {
                    list.add(new OOMObject());
                }
            } catch (Exception e) {
                System.out.println("out of memory: " + e.toString());
            }
        });

        return "ok";
    }

    @GetMapping("/info")
    public String info() {
        return "jvm-profiler";
    }
}
