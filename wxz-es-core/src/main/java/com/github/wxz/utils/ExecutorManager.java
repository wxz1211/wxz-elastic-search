package com.github.wxz.utils;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xianzhi.wang
 * @date 2018/1/9 -14:42
 */
public class ExecutorManager {
    private static ThreadFactory threadFactory = r -> new Thread(r, ExecutorManager.class.getSimpleName());

    private static ThreadPoolExecutor threadPoolExecutor =
            new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 2,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(), threadFactory);

    public static void execute(Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

}
