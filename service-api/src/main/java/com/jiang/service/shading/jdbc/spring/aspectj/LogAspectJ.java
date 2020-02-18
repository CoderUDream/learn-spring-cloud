package com.jiang.service.shading.jdbc.spring.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * Created by liyujiang on 2020/2/14.
 */
@Slf4j
@Aspect
public class LogAspectJ {

    @Pointcut("execution(public Integer com.jiang.service.shading.jdbc.spring.aspectj.MathCalculator.*(..))")
    void pointCut() {
    }

    @Before("pointCut()")
    void before(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        log.info(joinPoint.getSignature().getDeclaringType() +
                " args:" + Arrays.asList(args) + " LogAspectJ before...");
    }

    @After("pointCut()")
    void after(JoinPoint joinPoint) {
        log.info(joinPoint.getSignature().getDeclaringType() + " LogAspectJ after...");
    }

    /**
     * JoinPoint必须放在第一个参数
     *
     * @param joinPoint
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    void afterReturning(JoinPoint joinPoint, Integer result) {
        log.info(joinPoint.getSignature().getDeclaringType() + " LogAspectJ afterReturning result:" + result + " ...");
    }

    /**
     * JoinPoint必须放在第一个参数
     *
     * @param joinPoint
     */
    @AfterThrowing(value = "pointCut()", throwing = "exception")
    void afterThrowing(JoinPoint joinPoint, Exception exception) {
        log.info(joinPoint.getSignature().getDeclaringType() + " LogAspectJ afterThrowing " + exception + "...");
    }

//    @Around(value = "pointCut()")
//    void around(ProceedingJoinPoint joinPoint) throws Throwable {
//        joinPoint.proceed();
//    }
}
