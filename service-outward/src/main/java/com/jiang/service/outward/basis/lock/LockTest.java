package com.jiang.service.outward.basis.lock;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by liyujiang on 2020/3/3.
 */
@Slf4j
public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        Object resourceA = new Object();
        Object resourceB = new Object();

        ReentrantLock lock = new ReentrantLock();
        Condition full = lock.newCondition();
        Condition empty = lock.newCondition();

        full.await();

        new Thread(() -> {
            synchronized (resourceA) {
                log.info("thread-1 synchronized resourceA...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (resourceB) {
                    log.info("thread-1 synchronized resourceB...");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (resourceB) {
                log.info("thread-2 synchronized resourceB...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (resourceA) {
                    log.info("thread-2 synchronized resourceA...");
                }
            }
        }).start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
