package com.jiang.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by liyujiang on 2019/12/10.
 */
@Slf4j
@RestController
public class JvmController {

    ExecutorService threadPool = Executors.newFixedThreadPool(2);
    List<OOMObject> surList = new ArrayList<>(1024);

    class OOMObject {
        // 1k
        byte[] bytes = new byte[1024];
    }

    @GetMapping("/heap")
    public String heap() {
        threadPool.submit(() -> {
            List<OOMObject> list = new ArrayList();
            Random random = new Random();
            try {
                while (true) {
                    list.add(new OOMObject());
                    // 大于0.5M释放
                    if (list.size() > 500) {
                        if (random.nextInt(50) > 45) {
                            surList.addAll(list);
                        }

                        list.clear();
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                System.out.println("exception: " + e.toString());
            }
        });

        return "ok";
    }

    @GetMapping("/info")
    public String info() {
        return "jvm-profiler";
    }

    @GetMapping("/io")
    public String handleIO() {
        return "";
    }

}
