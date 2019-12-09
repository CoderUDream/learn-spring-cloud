package com.jiang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyujiang on 2019/12/10.
 */
@RestController
public class JvmController {

    List<Object> list = new ArrayList();

    @GetMapping("/heap")
    public String heap() {

        while (true) {
            list.add(new Object());
        }
    }

}
