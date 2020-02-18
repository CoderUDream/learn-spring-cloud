package com.jiang.service.shading.jdbc.spring.aspectj;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by liyujiang on 2020/2/15.
 */
@Slf4j
public class MathCalculatorImpl implements MathCalculator {
    public Integer div(Integer i, Integer j) {
        log.info("MathCalculator#div ...");
        return i / j;
    }
}
