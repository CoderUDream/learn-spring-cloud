package com.jiang.service.api.spring.registerbean.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by liyujiang on 2020/2/13.
 */
@Data
@AllArgsConstructor
public class ThirdPartyPerson {
    private String name;

    ThirdPartyPerson() {
        name = "jiang";
    }
}
