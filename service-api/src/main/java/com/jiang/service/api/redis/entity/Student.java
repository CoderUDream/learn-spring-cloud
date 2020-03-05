package com.jiang.service.api.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by liyujiang on 2020/3/5.
 */
@Data
@AllArgsConstructor
public class Student {
    private String name;
    private int age;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
