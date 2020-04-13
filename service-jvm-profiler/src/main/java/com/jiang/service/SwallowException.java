package com.jiang.service;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * Created by liyujiang on 2019/12/12.
 *
 * 演示线程池吞噬异常
 */
@Slf4j
class ExceptionThreadPool extends ThreadPoolExecutor {
    public ExceptionThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
        BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    /**
     * 重写异常的处理回调方法，打印线程，不重写异常将不会被捕获
     * 
     * @param r
     * @param t
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        log.info("Throwable t={}", t.toString());
    }
}

@Slf4j
public class SwallowException {
    static ExecutorService executor =
        new ExceptionThreadPool(5, 5, 0, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    public static void main(String[] args) {
        executor.submit(new Worker());
        // executor.shutdown();

        // 下面这种情况就不会吞噬异常，会打印出堆栈信息
        // new Worker().run();
    }

    static class Worker implements Runnable {

        @Override
        public void run() {
            throw new RuntimeException("asdasd");
        }

    }
}
